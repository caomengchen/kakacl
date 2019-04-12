package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.ClerkJob;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 店员职位表 Mapper 接口
 * </p>
 *
 * @author wangwei
 * @since 2018-11-29
 */
public interface ClerkJobMapper extends BaseMapper<ClerkJob> {

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

	@Select("select * from store_clerk_job where id = #{clerkJobId}")
	public ClerkJob selectByJobId(Long clerkJobId);
}
