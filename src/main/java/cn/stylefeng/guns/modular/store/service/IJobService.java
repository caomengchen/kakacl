package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.Job;

import java.util.List;
import java.util.Map;

import cn.stylefeng.guns.modular.store.model.JobDetail;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 职位 服务类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-27
 */
public interface IJobService extends IService<Job> {
	
	public List<Job> findJobStoreList(Map<String, Object> params);
	
	public List<cn.stylefeng.guns.modular.store.vo.Job> getJobStoreViewList(Map<String, Object> params);

	/**
	 * 增加职位
	 * @param jobs
	 * @param jobDetails
	 * @return boolean 是否成功布尔
	 */
	public boolean addJobs(List<Job> jobs, List<JobDetail> jobDetails);

}
