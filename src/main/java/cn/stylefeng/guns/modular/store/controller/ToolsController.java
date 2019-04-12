package cn.stylefeng.guns.modular.store.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.stylefeng.guns.config.*;
import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.ImportExcelUntil;
import cn.stylefeng.guns.core.common.POIUtil;
import cn.stylefeng.guns.modular.store.model.CommonKV;
import cn.stylefeng.guns.modular.store.vo.CommonMaps;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import cn.stylefeng.guns.modular.store.model.BaseRegion;
import cn.stylefeng.guns.modular.store.service.IBaseRegionService;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 工具控制器
* @ClassName: ToolsController  
* @Description: 主要返回前端的一些工具，例如后端定义的常量
* @author wangwei  
* @date 2018年11月2日  
*
 */
@Controller
@RequestMapping("tools")
public class ToolsController extends BaseController {
	
	@Autowired
	private IBaseRegionService iBaseRegionService;

	@Autowired
	private GunsProperties gunsProperties;
	
	String[] nations = new String[] {"汉族","蒙古族","回族","藏族","维吾尔族","苗族","彝族","壮族","布依族","朝鲜族","满族","侗族","瑶族","白族","土家族",
            "哈尼族","哈萨克族","傣族","黎族","傈僳族","佤族","畲族","高山族","拉祜族","水族","东乡族","纳西族","景颇族","柯尔克孜族",
            "土族","达斡尔族","仫佬族","羌族","布朗族","撒拉族","毛南族","仡佬族","锡伯族","阿昌族","普米族","塔吉克族","怒族", "乌孜别克族",
           "俄罗斯族","鄂温克族","德昂族","保安族","裕固族","京族","塔塔尔族","独龙族","鄂伦春族","赫哲族","门巴族","珞巴族","基诺族"};
	
	/**
	 * 民族获取
	* @Title: nations  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "nations", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse nations() {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		Map maps = new HashMap<>();
		maps.put("data", nations);
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}
	
	/**
	 * 获取城市级联
	* @Title: findBaseRegion  
	* @Description: 获取城市级联
	* @param @param parentId
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "findBaseRegion", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse findBaseRegion(Long parentId) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		parentId = parentId == null ? 100000000000L : parentId;
		List data = iBaseRegionService.selectList(new EntityWrapper<BaseRegion>().eq("parent_id", parentId));
		Map maps = new HashMap<>();
		maps.put("data", data);
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}
	
	/**
	 * 所有工作状态
	* @Title: findWorkStatus  
	* @Description: 返回当前系统定义的所有工作状态
	* @param @param globalresponse    参数  
	* @return void    返回类型  
	* @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "findWorkStatus", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse findWorkStatus() {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		Map<String, Object> maps = new HashMap<>();
		List<Object> data = new ArrayList<>();
		CommonMaps vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52100.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52100.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52101.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52101.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52102.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52102.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52103.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52103.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52104.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52104.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52104.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52104.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52105.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52105.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52106.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52106.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52107.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52107.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52108.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52108.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52109.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52109.getName() + "");
		vo = new CommonMaps();
		vo.setId(GlobalEnums.EMPLOYMENT_STATUS_52110.getIndex() + "");
		vo.setName(GlobalEnums.EMPLOYMENT_STATUS_52110.getName() + "");
		data.add(vo);
		maps.put("data", data);
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}




	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "findLeaveReason", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse findleaveReason() {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		Map<String, Object> maps = new HashMap<>();
		List<Object> data = new ArrayList<>();
		CommonMaps vo = new CommonMaps();
		vo.setId(MyEnums.LEAVE_1.getIndex() + "");
		vo.setName(MyEnums.LEAVE_1.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(MyEnums.LEAVE_2.getIndex() + "");
		vo.setName(MyEnums.LEAVE_2.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(MyEnums.LEAVE_3.getIndex() + "");
		vo.setName(MyEnums.LEAVE_3.getName() + "");
		data.add(vo);
		vo = new CommonMaps();
		vo.setId(MyEnums.LEAVE_4.getIndex() + "");
		vo.setName(MyEnums.LEAVE_4.getName() + "");
		data.add(vo);
		maps.put("data", data);
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}

	/**
	 * 结算方式
	* @Title: settlementStatus  
	* @Description: 结算方式
	* @param @param maps
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "settlementStatus", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse settlementStatus(Map maps) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		List data = new ArrayList();
		CommonKV kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50001.getIndex() + "");
		kv.setValue(GlobalEnums.B_50001.getName() + "");
		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50002.getIndex() + "");
		kv.setValue(GlobalEnums.B_50002.getName() + "");
		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50003.getIndex() + "");
		kv.setValue(GlobalEnums.B_50003.getName() + "");
		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50004.getIndex() + "");
		kv.setValue(GlobalEnums.B_50004.getName() + "");
		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50005.getIndex() + "");
		kv.setValue(GlobalEnums.B_50005.getName() + "");
		data.add(kv);
		maps.put("data", data);
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}
	
	/**
	 * 门店对工作人员的结算周期
	* @Title: settlementWeeks  
	* @Description: 门店对工作人员的结算周期 
	* @param @param maps
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "settlementWeeks", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse settlementWeeks(Map maps) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		maps.put(GlobalEnums.B_50201.getIndex() + "", GlobalEnums.B_50201.getName());
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}
	
	/**
	 * 公司对门店的补贴方式
	* @Title: subsidyStatus  
	* @Description:补贴方式 
	* @param @param maps
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "subsidyStatus", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse subsidyStatus(Map maps) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		List data = new ArrayList();
		CommonKV kv = new CommonKV();
//		kv.setKey(GlobalEnums.B_50101.getIndex() + "");
//		kv.setValue(GlobalEnums.B_50101.getName() + "");
//		data.add(kv);
//		kv = new CommonKV();
//		kv.setKey(GlobalEnums.B_50102.getIndex() + "");
//		kv.setValue(GlobalEnums.B_50102.getName() + "");
//		data.add(kv);
//		kv = new CommonKV();
//		kv.setKey(GlobalEnums.B_50103.getIndex() + "");
//		kv.setValue(GlobalEnums.B_50103.getName() + "");
//		data.add(kv);
//		kv = new CommonKV();
//		kv.setKey(GlobalEnums.B_50104.getIndex() + "");
//		kv.setValue(GlobalEnums.B_50104.getName() + "");
//		data.add(kv);
//		kv = new CommonKV();
//		kv.setKey(GlobalEnums.B_50105.getIndex() + "");
//		kv.setValue(GlobalEnums.B_50105.getName() + "");
//		data.add(kv);
//		kv = new CommonKV();
//		kv.setKey(GlobalEnums.B_50106.getIndex() + "");
//		kv.setValue(GlobalEnums.B_50106.getName() + "");
//		data.add(kv);
//		kv = new CommonKV();
//		kv.setKey(GlobalEnums.B_50107.getIndex() + "");
//		kv.setValue(GlobalEnums.B_50107.getName() + "");
//		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50108.getIndex() + "");
		kv.setValue(GlobalEnums.B_50108.getName() + "");
		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50109.getIndex() + "");
		kv.setValue(GlobalEnums.B_50109.getName() + "");
		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50110.getIndex() + "");
		kv.setValue(GlobalEnums.B_50110.getName() + "");
		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50111.getIndex() + "");
		kv.setValue(GlobalEnums.B_50111.getName() + "");
		data.add(kv);
		maps.put("data", data);
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}
	
	/**
	 * 公司对门店的补贴周期
	* @Title: subsidyWeeks  
	* @Description: 公司对门店的补贴周期
	* @param @param maps
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "subsidyWeeks", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse subsidyWeeks(Map maps) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}
	
	/**
	 * 工作人员的来源
	* @Title: personRsource  
	* @Description: 工作人员的来源
	* @param @param maps
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "personRsource", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse personRsource(Map maps) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		maps.put(GlobalEnums.B_50301.getIndex() + "", GlobalEnums.B_50301.getName());
		maps.put(GlobalEnums.B_50302.getIndex() + "", GlobalEnums.B_50302.getName());
		maps.put(GlobalEnums.B_50303.getIndex() + "", GlobalEnums.B_50303.getName());
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}
	
	/**
	 * 工作长度类型
	* @Title: weekTypes  
	* @Description: 工作长度类型
	* @param @param maps
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "weekTypes", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse weekTypes(Map maps) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		maps.put(GlobalEnums.B_50501.getIndex() + "", GlobalEnums.B_50501.getName());
		maps.put(GlobalEnums.B_50502.getIndex() + "", GlobalEnums.B_50502.getName());
		maps.put(GlobalEnums.B_50503.getIndex() + "", GlobalEnums.B_50503.getName());
		maps.put(GlobalEnums.B_50504.getIndex() + "", GlobalEnums.B_50504.getName());
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}

	/**
	 * 考勤类型
	 * @param maps
	 * @return
	 */
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "units", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse units(Map maps) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		List data = new ArrayList();
		CommonKV kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50601.getIndex() + "");
		kv.setValue(GlobalEnums.B_50601.getName() + "");
		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50602.getIndex() + "");
		kv.setValue(GlobalEnums.B_50602.getName() + "");
		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50603.getIndex() + "");
		kv.setValue(GlobalEnums.B_50603.getName() + "");
		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50604.getIndex() + "");
		kv.setValue(GlobalEnums.B_50604.getName() + "");
		data.add(kv);
		kv = new CommonKV();
		kv.setKey(GlobalEnums.B_50606.getIndex() + "");
		kv.setValue(GlobalEnums.B_50606.getName() + "");
		data.add(kv);
		maps.put("data", data);
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}
	
	/**
	 * 考勤单位
	* @Title: attendanceUnits  
	* @Description: 考勤单位
	* @param @param maps
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "attendanceUnits", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse attendanceUnits(Map maps) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		maps.put(GlobalEnums.B_50701.getIndex() + "", GlobalEnums.B_50701.getName());
		maps.put(GlobalEnums.B_50502.getIndex() + "", GlobalEnums.B_50502.getName());
		maps.put(GlobalEnums.B_50503.getIndex() + "", GlobalEnums.B_50503.getName());
		maps.put(GlobalEnums.B_50504.getIndex() + "", GlobalEnums.B_50504.getName());
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}
	
	/**
	 * 发放薪水模式
	* @Title: salaryPatterns  
	* @Description: 发放薪水模式
	* @param @param maps
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "salaryPatterns", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse salaryPatterns(Map maps) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		maps.put(GlobalEnums.B_50701.getIndex() + "", GlobalEnums.B_50701.getName());
		maps.put(GlobalEnums.B_50702.getIndex() + "", GlobalEnums.B_50702.getName());
		maps.put(GlobalEnums.B_50703.getIndex() + "", GlobalEnums.B_50703.getName());
		maps.put(GlobalEnums.B_50704.getIndex() + "", GlobalEnums.B_50704.getName());
		maps.put(GlobalEnums.B_50705.getIndex() + "", GlobalEnums.B_50705.getName());
		maps.put(GlobalEnums.B_50706.getIndex() + "", GlobalEnums.B_50706.getName());
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}
	
	/**
	 * 性别
	* @Title: genders  
	* @Description: 性别 
	* @param @param maps
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "genders", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse genders(Map maps) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		maps.put(GlobalEnums.GENDER_50801.getIndex() + "", GlobalEnums.GENDER_50801.getName());
		maps.put(GlobalEnums.GENDER_50802.getIndex() + "", GlobalEnums.GENDER_50802.getName());
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}
	
	/**
	 * 账号状态
	* @Title: accontStatus  
	* @Description: 账号状态
	* @param @param maps
	* @param @return    参数  
	* @return GlobalResponse    返回类型  
	* @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
	@RequestMapping(value = "accontStatus", method = RequestMethod.GET)
	@ResponseBody
	public GlobalResponse accontStatus(Map maps) {
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		maps.put(GlobalEnums.ACCOUNT_STATUS_52000.getIndex() + "", GlobalEnums.ACCOUNT_STATUS_52000.getName());
		maps.put(GlobalEnums.ACCOUNT_STATUS_52001.getIndex() + "", GlobalEnums.ACCOUNT_STATUS_52001.getName());
		globalresponse.setResponseContent(maps);
		return globalresponse;
	}

    /**
     * 公司介绍状态
     * @param maps
     * @return
     */
    @CrossOrigin(origins=GlobalStr.PERSISSION_PATHS, maxAge=GlobalNumber.INT_CROSS_MAXAGE_3600)
    @RequestMapping(value = "companyIntroductionStatus", method = RequestMethod.GET)
    @ResponseBody
    public GlobalResponse companyIntroductionStatus(Map maps) {
        GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
        List data = new ArrayList();
        CommonKV kv = new CommonKV();
        kv.setKey(GlobalEnums.DATA_54000.getIndex() + "");
        kv.setValue(GlobalEnums.DATA_54000.getName() + "");
        data.add(kv);
        kv = new CommonKV();
        kv.setKey(GlobalEnums.DATA_54001.getIndex() + "");
        kv.setValue(GlobalEnums.DATA_54001.getName() + "");
        data.add(kv);
        kv = new CommonKV();
        kv.setKey(GlobalEnums.DATA_54002.getIndex() + "");
        kv.setValue(GlobalEnums.DATA_54002.getName() + "");
        data.add(kv);
        kv = new CommonKV();
        kv.setKey(GlobalEnums.DATA_54003.getIndex() + "");
        kv.setValue(GlobalEnums.DATA_54003.getName() + "");
        data.add(kv);
        kv = new CommonKV();
        kv.setKey(GlobalEnums.DATA_54004.getIndex() + "");
        kv.setValue(GlobalEnums.DATA_54004.getName() + "");
        data.add(kv);
        kv = new CommonKV();
        kv.setKey(GlobalEnums.DATA_54005.getIndex() + "");
        kv.setValue(GlobalEnums.DATA_54005.getName() + "");
        data.add(kv);
        kv = new CommonKV();
        kv.setKey(GlobalEnums.DATA_54006.getIndex() + "");
        kv.setValue(GlobalEnums.DATA_54006.getName() + "");
        data.add(kv);
        kv = new CommonKV();
        kv.setKey(GlobalEnums.DATA_54007.getIndex() + "");
        kv.setValue(GlobalEnums.DATA_54007.getName() + "");
        data.add(kv);
        maps.put("data", data);
        globalresponse.setResponseContent(maps);
        return globalresponse;
    }


	/**
	 * 读取excel文件中的用户信息，保存在数据库中
	 * @param excelFile
	 */
	@RequestMapping("/readExcel")
	@ResponseBody
	public GlobalResponse readExcel(@RequestParam(value = "uploadFile") MultipartFile excelFile, HttpServletRequest req, HttpServletResponse resp){
		GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
		Map<String, Object> maps = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		List<User> allUsers = new ArrayList<User>();
		try {
			List<String[]> userList = POIUtil.readExcel(excelFile);
			for(int i = 0;i<userList.size();i++){
				String[] users = userList.get(i);
				if(users.length < 3) {
					// 如果没有数据，则继续进行
					continue;
				}
				System.out.println(i + "josns:" + JSON.toJSONString(users));
				try {
					int num = Integer.parseInt(users[0]);
					String name = users[1];
					System.out.println(i + "detail: " + num + " - " + name);
				} catch (Exception e) {
					globalresponse.setMessage("第" + (i + 3) + "行附近有错误，请检查。");
					globalresponse.setCode("201");
					return globalresponse;
				}
				/*User user = new User();
				user.setUserName(users[0]);
				user.setPassword(users[1]);
				user.setAge(Integer.parseInt(users[2]));
				allUsers.add(user);*/
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		param.put("allUsers", allUsers);
		//this.userService.insertUsers(param);
		globalresponse.setResponseContent(maps);

		return globalresponse;
	}

	/**
	 * 下载数据
	 * @param req 需要传入名称 file_name=test.xls
	 * @param response
	 * @return
	 */
	@RequestMapping("/download")
	@ResponseBody
	public String readExcel(HttpServletRequest req, HttpServletResponse response) {
		response.setContentType("application/x-download");
		String filedisplay = req.getParameter("file_name");
		String filedownload = gunsProperties.getFileDownloadPath() + filedisplay;
		try {
			filedisplay = URLEncoder.encode(filedisplay,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.addHeader("Content-Disposition","attachment;filename=" + filedisplay);
		OutputStream outp = null;
		FileInputStream in = null;
		try{
			outp = response.getOutputStream();
			in = new FileInputStream(filedownload);
			byte[] b = new byte[1024];
			int i = 0;
			while((i = in.read(b)) > 0)  {
				outp.write(b, 0, i);
			}
			outp.flush();
		} catch(Exception e){
			System.out.println("Error!");
			e.printStackTrace();
		}finally{
			if(in != null)
			{
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}
			if(outp != null)
			{
				try {
					outp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				outp = null;
			}
		}
		return "";
	}

    /**
     * 上传文件
     * @param files
     * @param request dirName 目录名称
     * @param resp
     * @return
     */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public GlobalResponse uploadFile(@RequestParam(value = "file") MultipartFile files,
							 HttpServletRequest request, HttpServletResponse resp) {
        GlobalResponse result = GlobalResponse.getInstanseGlobalResponse();
        Map<String, Object> maps = new HashMap<>();
        String dirName = request.getParameter("dirName") == null ? "default" : request.getParameter("dirName");
		resp.setCharacterEncoding("utf-8");
		String msg = "成功";
		String path = gunsProperties.getFileUploadPath();
        String ipAndPort = gunsProperties.getFileUploadIpAndPort();
        String name = files.getOriginalFilename();
        name = name.substring(name.lastIndexOf(".")).trim();
        String new_fileName = System.currentTimeMillis() + "" + name;
		try{
			path = path + File.separator + dirName + File.separator;
			File uploadFile = new File(path);
			if(!uploadFile.exists()){
				uploadFile.mkdirs();
			}
            path = path + File.separator + new_fileName;
            uploadFile = new File(path);
			files.transferTo(uploadFile);
		}catch(Exception e){
			msg="失败";
		}
        maps.put("msg", msg);
        maps.put("path", ipAndPort + File.separator + dirName + File.separator + new_fileName);
        result.setResponseContent(maps);
		return  result;
	}
}
