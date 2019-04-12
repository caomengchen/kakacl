package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.Job;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 职位 Mapper 接口
 * </p>
 *
 * @author wangwei
 * @since 2018-11-27
 */
public interface JobMapper extends BaseMapper<Job> {
	
	/**
	 * 根据条件查询职位信息
	* @Title: findJobStoreList  
	* @Description: 根据条件查询职位信息
	* @param @param params
	* @param @return    参数  
	* @return List<Job>    返回类型  
	* @throws
	 */
	public List<Job> findJobStoreList(Map<String, Object> params);
	
	/**
	 * 根据条件查询门店的职位信息
	* @Title: getJobStoreViewList  
	* @Description: 根据条件查询门店的职位信息，并且暂时在界面上
	* @param @param params
	* @param @return    参数  
	* @return List<cn.stylefeng.guns.modular.store.vo.Job>    返回类型  
	* @throws
	 */
	public List<cn.stylefeng.guns.modular.store.vo.Job> getJobStoreViewList(Map<String, Object> params);

}
