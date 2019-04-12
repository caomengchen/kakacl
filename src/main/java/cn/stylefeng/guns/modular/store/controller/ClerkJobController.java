package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.util.BackUtils;
import cn.stylefeng.guns.modular.store.model.*;
import cn.stylefeng.guns.modular.store.service.*;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.config.GlobalEnums;
import cn.stylefeng.guns.config.GlobalStr;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.exception.StoreExceptionEnum;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.store.vo.Employee;

/**
 * 店员职位控制器
 *
 * @author fengshuonan
 * @Date 2018-11-29 08:51:51
 */
@Controller
@RequestMapping("/clerkJob")
public class ClerkJobController extends BaseController {

    private String PREFIX = "/store/clerkJob/";

    @Autowired
    private IClerkJobService clerkJobService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private IJobService jobService;
    
    @Autowired
    private ICompanyService companyService;
    
    @Autowired
    private IJobDetailService jobDetailService;

    @Autowired
    private ISettlementService settlementService;

    /**
     * 跳转到店员职位首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "clerkJob.html";
    }

    /**
     * 跳转到添加店员职位
     */
    @RequestMapping("/clerkJob_add")
    public String clerkJobAdd() {
        return PREFIX + "clerkJob_add.html";
    }

    /**
     * 跳转到修改店员职位
     */
    @RequestMapping("/clerkJob_update/{clerkJobId}")
    public String clerkJobUpdate(@PathVariable Integer clerkJobId, Model model) {
        ClerkJob clerkJob = clerkJobService.selectById(clerkJobId);
        model.addAttribute("item",clerkJob);
        LogObjectHolder.me().set(clerkJob);
        return PREFIX + "clerkJob_edit.html";
    }

    /**
     * 店员获取职位列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	Map<String, Object> paramsMap = new HashMap<>();
    	ShiroUser user = ShiroKit.getUser();
    	paramsMap.put("companyName", condition);
    	// 如果是店长，则展示店长的数据
    	if(user.getRoleList().contains(GlobalNumber.INT_STORE_MANAGER_6)) {
            paramsMap.put("storeId", user.getId());
        } else {
            paramsMap.put("storeClerkId", user.getId());
        }
    	paramsMap.put("endTime", DateUtil.formatDateTime(new Date()));
     	// 根据店长主键查询店员
    	List<cn.stylefeng.guns.modular.store.vo.Job> jobList = clerkJobService.getClerkJobViewList(paramsMap);
    	return jobList;
    }

    /**
     * 新增店员职位
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ClerkJob clerkJob) {
        clerkJobService.insert(clerkJob);
        return SUCCESS_TIP;
    }

    /**
     * 删除店员职位
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long clerkJobId) {
        // clerkJobService.deleteById(clerkJobId);
        ClerkJob entity = new ClerkJob();
        entity.setId(clerkJobId);
        entity.setDelFlag(GlobalNumber.INT_DEL_FLAG_1);
        clerkJobService.updateById(entity);
        return SUCCESS_TIP;
    }

    /**
     * 修改店员职位
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ClerkJob clerkJob) {
        clerkJobService.updateById(clerkJob);
        return SUCCESS_TIP;
    }

    /**
     * 店员职位详情
     */
    @RequestMapping(value = "/detail/{clerkJobId}")
    @ResponseBody
    public Object detail(@PathVariable("clerkJobId") Integer jobId) {
    	Job job = jobService.selectById(jobId);
        return job;
    }
    
    /**
     * 店员职位详情页面
    * @Title: clerkJobDetail  
    * @Description: 店员职位详情页面
    * @param @param jobId 职位主键
    * @param @return    参数  
    * @return String    返回类型  
    * @throws
     */
	@RequestMapping("clerkJobDetail/{clerkJobId}")
    public String clerkJobDetail(@PathVariable("clerkJobId") Long clerkJobId, Model model) {
        ClerkJob  clerkJob = clerkJobService.selectByJobId(clerkJobId);
        clerkJob.setBillingCycleView(GlobalEnums.getName(clerkJob.getBillingCycle()));
    	Job job = jobService.selectById(clerkJob.getJobId());
    	model.addAttribute("item",job);
    	// 企业信息 企业名称 招聘简章
    	Long companyId = job.getCompanyId();
    	Company company =  companyService.selectById(companyId);
    	model.addAttribute("company", company);
    	// 补贴信息 补贴方式 补贴价格 补贴周期 报名统计
    	Map<String, Object> columnMap = new HashMap<>();
    	columnMap.put("job_id", job.getId());
    	List<JobDetail> jdList = jobDetailService.selectByMap(columnMap);
    	JobDetail jobDetail = null;
    	if(jdList == null)
    		throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR);
    	jobDetail = jdList.get(GlobalNumber.INT_FIX_0);
    	if(jobDetail == null)
    		throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR);
    	if(ShiroKit.hasAnyRoles(GlobalStr.FIX_ROLE_NAM_CLERK)) {
    		List<ClerkJob> clerkList = clerkJobService.selectByMap(columnMap);
    	} else {
    		throw new ServiceException(BizExceptionEnum.NO_PERMITION);
    	}
    	model.addAttribute("jobDetail", jobDetail);
        super.setAttr("clerkJob", clerkJob);
        LogObjectHolder.me().set(job);
        LogObjectHolder.me().set(company);

        // 职位查询
        Position position = positionService.selectById(job.getPositionId());
        model.addAttribute("position", position);
        LogObjectHolder.me().set(position);
        return PREFIX + "clerkJob_detail.html";
    }

    /**
     * 店员报名登记页面
    * @Title: registration  
    * @Description: 店员报名登录
    * @param @param jobId
    * @param @param model
    * @param @return    参数  
    * @return String    返回类型  
    * @throws
     */
    @RequestMapping("registration/{clerkJobId}")
    public String registration(@PathVariable("clerkJobId") Long clerkJobId, Model model) {
        ClerkJob clerkJob = clerkJobService.selectByJobId(clerkJobId);

        Long jobId = clerkJob.getJobId();
    	Job job = jobService.selectById(jobId);
        JobDetail jd = jobDetailService.selectByJobId(jobId);

        String unitName = GlobalEnums.getName(jd.getUnit());
        jd.setUnitView(unitName);

        Map<String, Object> params = new HashMap<>();
        params.put("jobId", jobId);
        // 判断当前报名人数或者总人数不符合接单要求则取消进行提醒
        if(jd.getNumberPeople() == GlobalNumber.INT_FIX_0) {
            throw new ServiceException(StoreExceptionEnum.PARAM_ERROR_71001);
        } else if(settlementService.selectCount(params) >= jd.getNumberPeople()) {
            throw new ServiceException(StoreExceptionEnum.PARAM_ERROR_71002);
        }
    	model.addAttribute("item",job);
        model.addAttribute("jd",jd);
        model.addAttribute("clerkJob",clerkJob);
        LogObjectHolder.me().set(job);
        return PREFIX + "clerkJob_registration.html";
    }
    
    /**
     * 添加雇员
    * @Title: addEmployeeSignIn  
    * @Description: 添加雇员
    * @param @param jobId
    * @param @param model
    * @param @return    参数  
    * @return String    返回类型  
    * @throws
     */
    @RequestMapping("addEmployeeSignIn")
    @ResponseBody
    @BussinessLog(value = "添加雇员")
    public Object addEmployeeSignIn(Employee employee) throws Exception {
        String backNo = employee.getBackNum();
        if(!StringUtils.isEmpty(backNo)) {
            try {
                String backJson = BackUtils.getCardDetail(backNo);
                JSONObject obj = JSON.parseObject(backJson);

                if((boolean)obj.get("validated")) {
                    if(GlobalStr.FIX_CARDTYPE_DC.equals(obj.get("cardType"))) {
                        String bank = (String)obj.get(GlobalStr.FIX_BANK);
                        if(bank.equalsIgnoreCase(GlobalStr.FIX_ICBC)
                                || bank.equalsIgnoreCase(GlobalStr.FIX_CCB)
                                || bank.equalsIgnoreCase(GlobalStr.FIX_ABC)
                                || bank.equalsIgnoreCase(GlobalStr.FIX_BOCM)
                                || bank.equalsIgnoreCase(GlobalStr.FIX_BOC)) {
                        } else {
                            throw new ServiceException(StoreExceptionEnum.PARAM_ERROR_70004);
                        }
                    } else {
                        throw new ServiceException(StoreExceptionEnum.PARAM_ERROR_70005);
                    }
                } else {
                    throw new ServiceException(StoreExceptionEnum.PARAM_ERROR_70004);
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        }

        boolean flag = clerkJobService.addEmployeeSignIn(employee);
    	if(flag)
            return SUCCESS_TIP;
        throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR);
    }
}
