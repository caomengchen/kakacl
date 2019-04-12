package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.ClerkJob;
import cn.stylefeng.guns.modular.store.vo.Employee;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 店员职位表 服务类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-29
 */
public interface IClerkJobService extends IService<ClerkJob> {

	/**
	 * 根据条件查询门店的职位信息
	* @Title: getJobStoreViewList  
	* @Description: 根据条件查询门店的职位信息，并且暂时在界面上
	* @param @param params
	* @param @return    参数  
	* @return List<cn.stylefeng.guns.modular.store.vo.Job>    返回类型  
	* @throws
	 */
	public List<cn.stylefeng.guns.modular.store.vo.Job> getClerkJobViewList(Map<String, Object> params);
	
	/**
	 * 店员增加雇员
	* @Title: addEmployeeSignIn  
	* @Description: 店员增加雇员 
	* @param @param employee
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public boolean addEmployeeSignIn(Employee employee);

	public ClerkJob selectByJobId(Long clerkJobId);
}
