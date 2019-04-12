package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalEnums;
import cn.stylefeng.guns.config.GlobalMessages;
import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.config.GlobalResponse;
import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.POIUtil;
import cn.stylefeng.guns.core.common.ReadExcel;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.exception.StoreExceptionEnum;
import cn.stylefeng.guns.core.util.DateUtils;
import cn.stylefeng.guns.modular.store.model.*;
import cn.stylefeng.guns.modular.store.service.*;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import cn.hutool.core.date.DateUtil;

import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.store.vo.JobAdd;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 招聘信息控制器
 *
 * @author wangwei
 * @Date 2018-11-27 10:51:09
 */
@Controller
@RequestMapping("/job")
@Api(value = "JobController-职位信息接口")
public class JobController extends StoreBaseController {

    private String PREFIX = "/store/job/";

    @Autowired
    private IJobService jobService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private ICompanyIntroductionService companyIntroductionService;
    
    @Autowired
    private IJobDetailService jobDetailService;
    
    @Autowired
    private IClerkJobService clerkJobService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private ISettlementService settlementService;

    /**
     * 跳转到招聘信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "job.html";
    }

    /**
     * 跳转到添加招聘信息
     */
    @RequestMapping("/job_add")
    public String jobAdd() {
        return PREFIX + "job_add.html";
    }

    /**
     * 跳转到修改招聘信息
     */
    @RequestMapping("/job_update/{jobId}")
    public String jobUpdate(@PathVariable Long jobId, Model model) {
        Job job = jobService.selectById(jobId);
        model.addAttribute("item",job);
        LogObjectHolder.me().set(job);

        JobDetail jobDetail = jobDetailService.selectByJobId(jobId);
        jobDetail.setUnitView(GlobalEnums.getName(jobDetail.getUnit()));
        model.addAttribute("jobDetail",jobDetail);
        LogObjectHolder.me().set(jobDetail);

        Position position = positionService.selectById(job.getPositionId());
        model.addAttribute("position",position);
        LogObjectHolder.me().set(position);

        Company company = companyService.selectById(position.getCompanyId());
        model.addAttribute("company", company);
        LogObjectHolder.me().set(company);
        return PREFIX + "job_edit.html";
    }
    
    /**
     * 跳转到接单信息,页面
     */
    @RequestMapping("/job_join/{jobId}")
    public String jobJoin(@PathVariable Long jobId, Model model) {
        // 根据部门判断是否是自有门店
        Integer deptId = ShiroKit.getUser().getDeptId();
        if(deptId == GlobalNumber.INT_30) {
            model.addAttribute("selfStore",1);
        } else {
            model.addAttribute("selfStore",0);
        }
        Job job = jobService.selectById(jobId);
        Map<String, Object> params = new HashMap<>();
        params.put("jobId", jobId);
        JobDetail jd = jobDetailService.selectByJobId(jobId);
        // 判断当前报名人数或者总人数不符合接单要求则取消进行提醒
        if(jd.getNumberPeople() == GlobalNumber.INT_FIX_0) {
            throw new ServiceException(StoreExceptionEnum.PARAM_ERROR_71001);
        } else if(settlementService.selectCount(params) >= jd.getNumberPeople()) {
            throw new ServiceException(StoreExceptionEnum.PARAM_ERROR_71002);
        } else if(jd.getStatus() != GlobalEnums.DATA_54000.getIndex()) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60002);
        }
        jd.setUnitView(GlobalEnums.getName(jd.getUnit()));
        model.addAttribute("item",job);
        model.addAttribute("jd",jd);
        LogObjectHolder.me().set(job);
        LogObjectHolder.me().set(jd);
        return PREFIX + "job_join.html";
    }

    /**
     * 获取招聘信息列表
     */
    @RequestMapping(value = "/list")
    @ApiOperation("职位查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "公司名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "end_time", value = "招聘截止时间（默认为当前日期后7天）", required = false, dataType = "String"),
    })
    @ResponseBody
    public Object list(@RequestParam(required = false) String condition,
                       @RequestParam(required = false) String end_time) {
    	Map<String, Object> paramsMap = new HashMap<>();
    	paramsMap.put("companyName", condition);
    	// 如果是店长 只能看见正常的数据
        if(ShiroKit.getUser().roleList.contains(GlobalNumber.INT_STORE_MANAGER_6)) {
            paramsMap.put("status", GlobalNumber.INT_54000);
        }
    	if(org.apache.commons.lang3.StringUtils.isNoneEmpty(end_time)) {
            paramsMap.put("startTime", DateUtil.formatDateTime(new Date()));
            paramsMap.put("endTime", end_time);
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(new java.util.Date());
            c.add(Calendar.DAY_OF_MONTH, GlobalNumber.INT_FIX_7);
            paramsMap.put("startTime", DateUtil.formatDateTime(new Date()));
            paramsMap.put("endTime", DateUtil.formatDateTime(new Date(c.getTimeInMillis())));
        }
        Object data = jobService.getJobStoreViewList(paramsMap);
    	return data;
    }
    
    /**
     * 店长接单
    * @Title: join  
    * @Description: 店长接单
    * @param @param condition
    * @param @return    参数  
    * @return Object    返回类型  
    * @throws
     */
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    @BussinessLog(value = "店长接单", key = "name")
    //@ApiOperation(value = "店长接单", notes = "店长接单，店长接单后店员可见")
    @ResponseBody
    public Object join(ClerkJob entity) {
        if(StringUtils.isEmpty(entity.getBillPrice())) {
            throw new ServiceException(StoreExceptionEnum.PARAM_ERROR_70003);
        } else if(StringUtils.isEmpty(entity.getBillCycle())) {
            throw new ServiceException(StoreExceptionEnum.PARAM_ERROR_70002);
        }
    	ShiroUser user = ShiroKit.getUser();
    	Integer storeId = user.getId();
    	String name = user.getName();
    	entity.setJobId(entity.getId());
    	entity.setStoreId(Long.valueOf(storeId));
    	entity.setCreateBy(name);
    	entity.setId(null);
    	entity.setDelFlag(GlobalNumber.INT_DEL_FLAG_0);
    	entity.setCreateTime(new Date());
    	boolean flag = clerkJobService.insert(entity);
    	if(flag)
            return SUCCESS_TIP;
        throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR);
    }
    
    /**
     * 增加职位
    * @Title: addJob  
    * @Description: 增加职位
    * @param @param jobAdd
    * @param @return    参数  
    * @return Object    返回类型  
    * @throws
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "添加职位", key = "name")
    @ResponseBody
    public Object add(JobAdd jobAdd) {
    	Job job = new Job();
    	Long companyId = jobAdd.getCompanyId();
    	String positionId = jobAdd.getPositionId();
    	Integer employeeCycle = jobAdd.getEmployeeCycle();
    	Integer numberPeople = jobAdd.getNumberPeople();
    	Integer billingCycle = jobAdd.getBillingCycle();
    	String allowancePrice = jobAdd.getAllowancePrice();
    	String allowanceCycle = jobAdd.getAllowanceCycle();
    	Integer unit = jobAdd.getUnit();
    	String endTime = jobAdd.getEndTime();
    	String condition = jobAdd.getCondition();
    	String welfare = jobAdd.getWelfare();
    	
    	job.setCompanyId(companyId);
    	job.setEmployeeCycle(employeeCycle);
    	job.setPositionId(Long.valueOf(positionId));

    	JobDetail jd = new JobDetail();
    	jd.setNumberPeople(numberPeople);
    	jd.setBillingCycle(billingCycle);
    	jd.setAllowancePrice(allowancePrice);
    	jd.setAllowanceCycle(allowanceCycle);
    	jd.setEmployeeCycle(employeeCycle);
    	jd.setUnit(unit);
    	jd.setEndTime(DateUtil.parse(endTime));
    	jd.setCondition(condition);
    	jd.setWelfare(welfare);
    	jd.setStatus(GlobalNumber.INT_54004);
    	jd.setCreateDate(new Date());
    	jobService.insert(job);
    	
    	jd.setJobId(job.getId());
    	jobDetailService.insert(jd);
        return SUCCESS_TIP;
    }

    /**
     * 职位审核
     * @param jobId 职位主键
     * @param model 模型
     * @return 职位审核页面
     */
    @RequestMapping("/job_review/{jobId}")
    public String job_review(@PathVariable Long jobId, Model model) {
        // 根据部门判断是否是自有门店
        Integer deptId = ShiroKit.getUser().getDeptId();
        if(deptId == GlobalNumber.INT_30) {
            model.addAttribute("selfStore",1);
        } else {
            model.addAttribute("selfStore",0);
        }
        Job job = jobService.selectById(jobId);
        Map<String, Object> params = new HashMap<>();
        params.put("jobId", jobId);
        JobDetail jd = jobDetailService.selectByJobId(jobId);
        // 判断当前报名人数或者总人数不符合接单要求则取消进行提醒
        if(jd.getNumberPeople() == GlobalNumber.INT_FIX_0) {
            throw new ServiceException(StoreExceptionEnum.PARAM_ERROR_71001);
        } else if(settlementService.selectCount(params) >= jd.getNumberPeople()) {
            throw new ServiceException(StoreExceptionEnum.PARAM_ERROR_71002);
        } else if(jd.getStatus() != GlobalEnums.DATA_54004.getIndex()) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60005);
        }
        jd.setUnitView(GlobalEnums.getName(jd.getUnit()));
        model.addAttribute("item",job);
        model.addAttribute("jd",jd);
        LogObjectHolder.me().set(job);
        LogObjectHolder.me().set(jd);
        return PREFIX + "job_review.html";
    }

    /**
     * 审核通过
     * @param jobAdd
     * @return
     */
    @RequestMapping("/job_review_pass")
    @ResponseBody
    public Object job_review_pass(JobAdd jobAdd) {
        Long jobId = jobAdd.getId();
        // 通过修改状态为正常
        Job job = jobService.selectById(jobId);
        if(job.getIsDeleted() == GlobalNumber.INT_DEL_FLAG_1) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60002);
        }
        boolean flag = jobDetailService.updateStatusByJobId(jobId, GlobalNumber.INT_54004, GlobalNumber.INT_54000);
        if(!flag) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60002);
        }
        return SUCCESS_TIP;
    }

    /**
     * 审核驳回
     * @param jobAdd
     * @param model
     * @return
     */
    @RequestMapping("/job_review_reject")
    @ResponseBody
    public Object job_review_reject(JobAdd jobAdd, Model model) {
        Long jobId = jobAdd.getId();
        // 驳回 修改状态为驳回
        Job job = jobService.selectById(jobId);
        if(job.getIsDeleted() == GlobalNumber.INT_DEL_FLAG_1) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60002);
        }
        boolean flag = jobDetailService.updateStatusByJobId(jobId, GlobalNumber.INT_54004, GlobalNumber.INT_54008);
        if(!flag) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60002);
        }
        return SUCCESS_TIP;
    }

    // 异步提醒
    @RequestMapping("/readExcel2")
    @BussinessLog(value = "导入职位", key = "name")
    @ResponseBody
    public GlobalResponse readExcel2(@RequestParam(value = "uploadFile") MultipartFile excelFile, HttpServletRequest req, HttpServletResponse resp) {
        GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
        Map<String, Object> maps = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        List<Job> jobs = new ArrayList<Job>();
        List<JobDetail> jobDetails = new ArrayList<JobDetail>();
        String path = gunsProperties.getFileUploadPath() + System.currentTimeMillis() + excelFile.getOriginalFilename();
        try {
            excelFile.transferTo(new File(path));
        } catch (IOException e) {

        }
        // 上传完毕，可以返回了
        // 如果错误的提醒可以使用其他方式进行
        // 处理完毕进行提醒

        List excelList = ReadExcel.readExcel(new File(path));
        if(excelList != null) {
            System.out.println(JSON.toJSONString(excelList));
            // 检查数据信息
            for (int i = GlobalNumber.INT_FIX_2; i < excelList.size(); i++) {
                List list = (List) excelList.get(i);
                String companyName = "";
                String positionName = "";
                int employeeCycle = 0;
                int numberPeople = 0;
                int billingCycle = 0;
                String allowancePrice = "0";
                String allowanceCycle = "0";
                String endTime = "";
                Integer unit = 0;
                int line = 3;
                Company company = null;
                for (int j = 0; j < list.size() && list.size() > 7; j++) {
                    System.out.println("s " + list.size());
                    // 获取指定行数据
                    switch (j) {
                        case 0:
                            companyName = list.get(j) + "";
                            List<Company> companyList = companyService.selectList(new EntityWrapper<Company>().eq("company_name",companyName));
                            if(companyList == null || companyList.size() != GlobalNumber.INT_FIX_1) {
                                globalresponse.setMessage(i + line + "行," + companyName + "，公司名称和数据确认不一致，请检查。");
                                globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                return globalresponse;
                            } else {
                                company = companyList.get(GlobalNumber.INT_FIX_0);
                            }
                            break;
                        case 1:
                            positionName = list.get(j) + "";
                            List<Position> positionList = positionService.selectList(new EntityWrapper<Position>().eq("name",positionName).eq("company_id", company.getId()));
                            if(positionList == null || positionList.size() != GlobalNumber.INT_FIX_1) {
                                globalresponse.setMessage(i + line + "行," + positionName + "，职位名称和数据确认不一致，请检查。");
                                globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                return globalresponse;
                            }
                            break;
                        case 2:
                            // 判断发薪模式
                            employeeCycle = GlobalEnums.getIndexByName(list.get(j) + "");
                            if(employeeCycle == GlobalNumber.INT_FIX_0) {
                                globalresponse.setMessage(i + line + "行," + list.get(j) + "" + "，发薪模式和数据确认不一致，请检查。");
                                globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                return globalresponse;
                            }
                            break;
                        case 3:
                            try{
                                numberPeople = Integer.parseInt(list.get(j) + "");
                                if(numberPeople < GlobalNumber.INT_FIX_0) {
                                    globalresponse.setMessage(i + line + "行," + list.get(j) + "" + "，人数不能小于0，请检查。");
                                    globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                    return globalresponse;
                                }
                            }catch (Exception e){
                                globalresponse.setMessage(i + line + "行," + list.get(j) + "" + "，人数必须为整数，请检查。");
                                globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                return globalresponse;
                            }
                            break;
                        case 4:
                            // 补贴方式
                            billingCycle = GlobalEnums.getIndexByName(list.get(j) + "");
                            if(billingCycle == GlobalNumber.INT_FIX_0) {
                                globalresponse.setMessage(i + line + "行," + list.get(j) + "" + "，补贴方式和数据确认不一致，请检查。");
                                globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                return globalresponse;
                            }
                            break;
                        case 5:
                            allowancePrice = list.get(j).toString();
                            try{
                                int price = Integer.parseInt(allowancePrice);
                                if(price < GlobalNumber.INT_FIX_0) {
                                    globalresponse.setMessage(i + line + "行," + list.get(j) + "" + "，价格不能小于0，请检查。");
                                    globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                    return globalresponse;
                                }
                            }catch (NumberFormatException e){
                                globalresponse.setMessage(i + line + "行," + list.get(j) + "" + "，价格无法转换，必须为整数，请检查。");
                                globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                return globalresponse;
                            }
                            break;
                        case 6:
                            // 补贴周期
                            allowanceCycle = list.get(j).toString();
                            try{
                                int price = Integer.parseInt(allowanceCycle);
                                if(price < GlobalNumber.INT_FIX_0) {
                                    globalresponse.setMessage(i + line + "行," + list.get(j) + "" + "，补贴周期不能小于0，请检查。");
                                    globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                    return globalresponse;
                                }
                            }catch (NumberFormatException e){
                                globalresponse.setMessage(i + line + "行," + list.get(j) + "" + "，补贴周期无法转换，必须为整数，请检查。");
                                globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                return globalresponse;
                            }
                            break;
                        case 7:
                            // 考勤方式
                            unit = GlobalEnums.getIndexByName(list.get(j) + "");
                            if(unit == GlobalNumber.INT_FIX_0) {
                                globalresponse.setMessage(i + line + "行," + list.get(j) + "" + "，补贴方式和数据确认不一致，请检查。");
                                globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                return globalresponse;
                            }
                            break;
                        case 8:
                            endTime = list.get(j).toString();
                            try {
                                org.apache.commons.lang3.time.DateUtils.parseDate(endTime,"yyyy/MM/dd HH:mm:ss");
                            } catch (Exception e) {
                                globalresponse.setMessage(i + line + "行," + list.get(j) + "" + "，时间格式无法转换，请检查。");
                                globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                return globalresponse;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        } else {
            globalresponse.setMessage("上传的文件中没有找到合理的数据，请检查。");
            globalresponse.setCode(GlobalMessages.S_40001.getCode());
            return globalresponse;
        }


        // 保存数据
        if(excelList != null) {
            for (int i = GlobalNumber.INT_FIX_2; i < excelList.size(); i++) {
                List list = (List) excelList.get(i);
                Job job = new Job();
                JobDetail jd = new JobDetail();

                Long companyId = 0L;
                Long positionId = 0L;
                int employeeCycle = 0;
                int numberPeople = 0;
                int billingCycle = 0;
                String allowancePrice = "0";
                String allowanceCycle = "0";
                String endTime = "";
                Integer unit = 0;
                for (int j = GlobalNumber.INT_FOR_0; j < list.size(); j++) {
                    // 获取指定行数据
                    switch (j){
                        case 0:
                            List<Company> companyList = companyService.selectList(new EntityWrapper<Company>().eq("company_name",list.get(j) + ""));
                            if(companyList == null || companyList.size() != GlobalNumber.INT_FIX_1) {
                                globalresponse.setMessage("第" + (i + GlobalNumber.INT_FIX_3) + "行附近，公司名称不正确，请检查。");
                                globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                return globalresponse;
                            } else {
                                companyId = companyList.get(GlobalNumber.INT_FIX_0).getId();
                            }
                            break;
                        case 1:
                            List<Position> positionList = positionService.selectList(new EntityWrapper<Position>().eq("name",list.get(j) + "").eq("company_id", companyId));
                            if(positionList == null || positionList.size() != GlobalNumber.INT_FIX_1) {
                                globalresponse.setMessage("第" + (i + GlobalNumber.INT_FIX_3) + "行附近，职位名称不正确，请检查。");
                                globalresponse.setCode(GlobalMessages.S_40001.getCode());
                                return globalresponse;
                            } else {
                                positionId = positionList.get(GlobalNumber.INT_FIX_0).getId();
                            }
                            break;
                        case 2:
                            // 判断发薪模式
                            employeeCycle = GlobalEnums.getIndexByName(list.get(j) + "");
                            break;
                        case 3:
                            numberPeople = Integer.parseInt(list.get(j) + "");
                            break;
                        case 4:
                            // 补贴方式
                            billingCycle = GlobalEnums.getIndexByName(list.get(j) + "");
                            break;
                        case 5:
                            allowancePrice = list.get(j).toString();
                            break;
                        case 6:
                            // 补贴周期
                            allowanceCycle = list.get(j).toString();
                            break;
                        case 7:
                            // 考勤方式
                            unit = GlobalEnums.getIndexByName(list.get(j) + "");
                            break;
                        case 8:
                            endTime = list.get(j).toString();
                            break;
                        default:
                            break;
                    }
                }

                if(companyId != GlobalNumber.INT_FIX_0) {
                    job.setCompanyId(companyId);
                    job.setEmployeeCycle(employeeCycle);
                    job.setPositionId(Long.valueOf(positionId));
                    jd.setNumberPeople(numberPeople);
                    jd.setBillingCycle(billingCycle);
                    jd.setAllowancePrice(allowancePrice);
                    jd.setAllowanceCycle(allowanceCycle);
                    jd.setEmployeeCycle(employeeCycle);
                    jd.setCreateDate(new Date());
                    jd.setUnit(unit);
                    try {
                        jd.setEndTime(org.apache.commons.lang3.time.DateUtils.parseDate(endTime,"yyyy/MM/dd HH:mm:ss"));
                    } catch (ParseException e) {
                        globalresponse.setMessage("第" + (i + GlobalNumber.INT_FIX_3) + "行附近有错误，请检查。");
                        globalresponse.setCode(GlobalMessages.S_40001.getCode());
                        return globalresponse;
                    }
                    //jd.setCondition(condition);
                    //jd.setWelfare(welfare);
                    jd.setStatus(GlobalNumber.INT_54004);
                    jobService.insert(job);
                    jd.setJobId(job.getId());
                    jobDetails.add(jd);
                    jobDetailService.insert(jd);
                }
            }
        }
        return globalresponse;
    }

    /**
     * 上传表格添加（增加职位）
     * 读取excel文件中的用户信息，保存在数据库中
     * 增加事物。如果有错误，全部回滚数据
     * @param excelFile
     */
    @RequestMapping("/readExcel")
    @BussinessLog(value = "导入职位", key = "name")
    @ResponseBody
    public GlobalResponse readExcel(@RequestParam(value = "uploadFile") MultipartFile excelFile, HttpServletRequest req, HttpServletResponse resp){
        GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
        Map<String, Object> maps = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        List<Job> jobs = new ArrayList<Job>();
        List<JobDetail> jobDetails = new ArrayList<JobDetail>();

        String path = gunsProperties.getFileUploadPath() + System.currentTimeMillis() + excelFile.getOriginalFilename();

        try {
            excelFile.transferTo(new File(path));
        } catch (IOException e) {

        }

        //try {
            List excelList = ReadExcel.readExcel(new File(path));
            if(excelList != null) {
                for (int i = 2; i < excelList.size(); i++) {
                    List list = (List) excelList.get(i);
                    Long companyId = 0L;
                    int positionId = 0;
                    int employeeCycle = 0;
                    int numberPeople = 0;
                    int billingCycle = 0;
                    String allowancePrice = "0";
                    String allowanceCycle = "0";
                    String endTime = "";
                    Integer unit = 0;
                    String condition = "";
                    String welfare = "";
                    for (int j = 0; j < list.size() && list.size() > 9; j++) {
                        System.out.println("s "  + list.size());
                        // 获取指定行数据
                        switch (j){
                            case 0:
                                companyId = Long.parseLong(list.get(j) + "");
                                break;
                            case 1:
                                positionId = Integer.parseInt(list.get(j) + "");
                                break;
                            case 2:
                                employeeCycle = Integer.parseInt(list.get(j) + "");
                                break;
                            case 3:
                                numberPeople = Integer.parseInt(list.get(j) + "");
                                break;
                            case 4:
                                // 补贴方式
                                billingCycle = Integer.parseInt(list.get(j) + "");
                                break;
                            case 5:
                                allowancePrice = list.get(j).toString();
                                break;
                            case 6:
                                // 补贴周期
                                allowanceCycle = list.get(j).toString();
                                break;
                            case 7:
                                // 考勤方式
                                unit = Integer.parseInt(list.get(j) + "");
                                break;
                            case 8:
                                endTime = list.get(j).toString();
                                break;
                            case 9:
                                condition = list.get(j).toString();
                                break;
                            case 10:
                                welfare = list.get(j).toString();
                                break;
                                default:
                                    break;
                        }
                    }
                    if(companyId != 0) {
                        Company companyEntity = companyService.selectById(companyId);
                        if(companyEntity == null) {
                            globalresponse.setMessage("第" + (i + GlobalNumber.INT_FIX_3) + "行附近，公司主键不正确，请检查。");
                            globalresponse.setCode(GlobalMessages.S_40001.getCode());
                            return globalresponse;
                        }
                    }
                }
            }

        List<String[]> dataList = null;
        try {
            dataList = POIUtil.readExcel(excelFile);
        } catch (IOException e) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR);
        }
        if(dataList.size() > GlobalNumber.INT_500) {
                globalresponse.setResponseContent(maps);
                globalresponse.setMessage(GlobalMessages.S_40002.getName());
                globalresponse.setCode(GlobalMessages.S_40002.getCode());
                return globalresponse;
        }

        if(excelList != null) {
            for (int i = 2; i < excelList.size(); i++) {
                List list = (List) excelList.get(i);
                Job job = new Job();
                JobDetail jd = new JobDetail();

                Long companyId = 0L;
                int positionId = 0;
                int employeeCycle = 0;
                int numberPeople = 0;
                int billingCycle = 0;
                String allowancePrice = "0";
                String allowanceCycle = "0";
                String endTime = "";
                Integer unit = 0;
                String condition = "";
                String welfare = "";

                for (int j = 0; j < list.size(); j++) {
                    // 获取指定行数据
                    switch (j){
                        case 0:
                            companyId = Long.parseLong(list.get(j) + "");
                            break;
                        case 1:
                            positionId = Integer.parseInt(list.get(j) + "");
                            break;
                        case 2:
                            employeeCycle = Integer.parseInt(list.get(j) + "");
                            break;
                        case 3:
                            numberPeople = Integer.parseInt(list.get(j) + "");
                            break;
                        case 4:
                            // 补贴方式
                            billingCycle = Integer.parseInt(list.get(j) + "");
                            break;
                        case 5:
                            allowancePrice = list.get(j).toString();
                            break;
                        case 6:
                            // 补贴周期
                            allowanceCycle = list.get(j).toString();
                            break;
                        case 7:
                            // 考勤方式
                            unit = Integer.parseInt(list.get(j) + "");
                            break;
                        case 8:
                            endTime = list.get(j).toString();
                            break;
                        case 9:
                            condition = list.get(j).toString();
                            break;
                        case 10:
                            welfare = list.get(j).toString();
                            break;
                        default:
                            break;
                    }
                }

                if(companyId != 0) {
                    job.setCompanyId(companyId);
                    job.setEmployeeCycle(employeeCycle);
                    job.setPositionId(Long.valueOf(positionId));

                    jd.setNumberPeople(numberPeople);
                    jd.setBillingCycle(billingCycle);
                    jd.setAllowancePrice(allowancePrice);
                    jd.setAllowanceCycle(allowanceCycle);
                    jd.setEmployeeCycle(employeeCycle);
                    jd.setCreateDate(new Date());
                    jd.setUnit(unit);
                    try {
                        jd.setEndTime(org.apache.commons.lang3.time.DateUtils.parseDate(endTime,"yyyy/MM/dd HH:mm:ss"));
                    } catch (ParseException e) {
                        globalresponse.setMessage("第" + (i + GlobalNumber.INT_FIX_3) + "行附近有错误，请检查。");
                        globalresponse.setCode(GlobalMessages.S_40001.getCode());
                        return globalresponse;
                    }
                    jd.setCondition(condition);
                    jd.setWelfare(welfare);
                    jd.setStatus(GlobalNumber.INT_54004);
                    jobService.insert(job);
                    jd.setJobId(job.getId());
                    jobDetails.add(jd);
                    jobDetailService.insert(jd);
                }
            }

        }



//            for(int i = 1; i<dataList.size();i++){
//                String[] data = dataList.get(i);
//                System.out.println(i + "josns:" + JSON.toJSONString(data));
//                try {
//                    if(data[0] == "") {
//                        break;
//                    } else {
//                        Long companyId = Long.parseLong(data[0]);
//                        int positionId = Integer.parseInt(data[1]);
//                        // 发薪模式
//                        Integer employeeCycle = Integer.parseInt(data[2]);
//                        // 招聘人数
//                        Integer numberPeople = Integer.parseInt(data[3]);
//                        // 补贴方式
//                        Integer billingCycle = Integer.parseInt(data[4]);
//                        // 补贴价格
//                        String allowancePrice = data[5];
//                        // 补贴周期
//                        String allowanceCycle = data[6];
//                        // 考情方式
//                        Integer unit = Integer.parseInt(data[7]);
//
//                        String endTime = data[8];
//                        // 条件
//                        String condition = data[9];
//                        // 福利
//                        String welfare = data[10];
//
//                        // TODO 检查每个职位，公司 各方式是否存在且正常。
//                        // 检查公司是否存在
//                        Company companyEntity = companyService.selectById(companyId);
//                        if(companyEntity == null) {
//                            globalresponse.setMessage("第" + (i + GlobalNumber.INT_FIX_3) + "行附近，公司主键不正确，请检查。");
//                            globalresponse.setCode(GlobalMessages.S_40001.getCode());
//                            return globalresponse;
//                        }
//                        // 职位检查
//                        // positionId
//
//
//                        Job job = new Job();
//                        job.setCompanyId(companyId);
//                        job.setEmployeeCycle(employeeCycle);
//                        job.setPositionId(Long.valueOf(positionId));
//                        jobs.add(job);
//
//                        JobDetail jd = new JobDetail();
//                        jd.setNumberPeople(numberPeople);
//                        jd.setBillingCycle(billingCycle);
//                        jd.setAllowancePrice(allowancePrice);
//                        jd.setAllowanceCycle(allowanceCycle);
//                        jd.setEmployeeCycle(employeeCycle);
//                        jd.setUnit(unit);
//                        jd.setEndTime(DateUtil.parse(endTime));
//                        jd.setCondition(condition);
//                        jd.setWelfare(welfare);
//                        jd.setStatus(GlobalNumber.INT_54000);
//                        //jobService.insert(job);
//                        jd.setJobId(job.getId());
//                        jobDetails.add(jd);
//                        //jobDetailService.insert(jd);
//                    }
//                } catch (Exception e) {
//                    globalresponse.setMessage("第" + (i + GlobalNumber.INT_FIX_3) + "行附近有错误，请检查。");
//                    globalresponse.setCode(GlobalMessages.S_40001.getCode());
//                    e.printStackTrace();
//                    return globalresponse;
//                }
//            }
        /*} catch (IOException e) {
            throw new ServiceException(StoreExceptionEnum.RUN_TIME_ERROR);
        }*/
        maps.put("jobs", jobs);
        maps.put("jobDetails", jobDetails);

        //jobService.addJobs(jobs, jobDetails);
        globalresponse.setResponseContent(maps);
        return globalresponse;
    }

    /**
     * 删除招聘信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer jobId) {
        //jobService.deleteById(jobId);
        Job job = jobService.selectById(jobId);
        JobDetail jd = new JobDetail();
        jd.setJobId(job.getId());
        jd.setStatus(GlobalEnums.ACCOUNT_STATUS_52001.getIndex());
        job.setIsDeleted(GlobalNumber.INT_DEL_FLAG_1);
        boolean flag = jobDetailService.updateByJobId(jd, job);
        if(flag)
            return SUCCESS_TIP;
        throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR);
    }

    /**
     * 修改招聘信息
     * @param jobDetail
     * @return
     */
    @RequestMapping(value = "/job_detail_update")
    @ResponseBody
    public Object detail_update(JobDetail jobDetail) {
        String price = jobDetail.getAllowancePrice();
        jobDetail = jobDetailService.selectById(jobDetail.getId());
        if(!jobDetail.getStatus().equals(GlobalNumber.INT_54008)) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60006);
        }
        boolean flag = jobDetailService.updatePrice(jobDetail.getId(), price, GlobalNumber.INT_54008, GlobalNumber.INT_54004);
        if(!flag) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60002);
        }
        return SUCCESS_TIP;
    }
    
    
    @RequestMapping(value = "/e_detail/{jobId}")
    @ResponseBody
    public Object e_detail(@PathVariable("jobId") Integer jobId) {
    	Job job = jobService.selectById(jobId);
        return job;
    }

    /**
     * 招聘信息详情
     */
    @RequestMapping(value = "/detail/{jobId}")
    public Object detail(@PathVariable("jobId") Integer jobId, Model model) {
    	Job job = jobService.selectById(jobId);
    	model.addAttribute("item",job);
        LogObjectHolder.me().set(job);

        // 查询价格
        List<JobDetail> jobDetailList = jobDetailService.selectList(new EntityWrapper<JobDetail>().eq("job_id",job.getId()));
        if(jobDetailList != null && jobDetailList.size() > GlobalNumber.INT_FOR_0) {
            JobDetail jobDetail = jobDetailList.get(GlobalNumber.INT_FIX_0);
            jobDetail.setUnitView(GlobalEnums.getName(jobDetail.getUnit()));
            model.addAttribute("jobDetail", jobDetail);
        }
        // 企业信息 企业名称 招聘简章
        Long companyId = job.getCompanyId();
        Company company =  companyService.selectById(companyId);
        model.addAttribute("company", company);
        LogObjectHolder.me().set(company);

        // 职位查询
        Position position = positionService.selectById(job.getPositionId());
        model.addAttribute("position", position);
        LogObjectHolder.me().set(position);
        return PREFIX + "job_detail.html";
    }
}
