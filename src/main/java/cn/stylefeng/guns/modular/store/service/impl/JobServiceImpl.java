package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.dao.SettlementMapper;
import cn.stylefeng.guns.modular.store.model.Job;
import cn.stylefeng.guns.config.GlobalEnums;
import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.modular.store.dao.JobMapper;
import cn.stylefeng.guns.modular.store.model.JobDetail;
import cn.stylefeng.guns.modular.store.service.IJobDetailService;
import cn.stylefeng.guns.modular.store.service.IJobService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 职位 服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-27
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

	@Resource
    private JobMapper jobMapper;

	@Resource
	cn.stylefeng.guns.modular.store.dao.SettlementMapper settlementMapper;
	
	@Autowired
    private IJobDetailService jobDetailService;
	
	@Override
	public List<Job> findJobStoreList(Map<String, Object> params) {
		List<Job> jobList = jobMapper.findJobStoreList(params);
		return jobList;
	}
	
	@Override
	public List<cn.stylefeng.guns.modular.store.vo.Job> getJobStoreViewList(Map<String, Object> params) {
		List<cn.stylefeng.guns.modular.store.vo.Job> data = jobMapper.getJobStoreViewList(params);
		for(int i = GlobalNumber.INT_FOR_0; i < data.size(); i ++) {
        	// 单位
        	String unit = data.get(i).getUnit();
        	String unitName = GlobalEnums.getName(Integer.parseInt(unit));
        	data.get(i).setUnit(unitName);
        	// 发薪模式
        	String employee_cycle = data.get(i).getEmployeeCycle();
        	String employeeCycleName = GlobalEnums.getName(Integer.parseInt(employee_cycle));
        	data.get(i).setEmployeeCycle(employeeCycleName);
        	// 补贴方式
        	String billing_cycle = data.get(i).getBillingCycle();
        	String billingCycle = GlobalEnums.getName(Integer.parseInt(billing_cycle));
        	data.get(i).setBillingCycle(billingCycle);
        	// 招聘总人数
        	String number_people = data.get(i).getPeopreNum();
        	// 已入职多少人
			params.put("jobId", data.get(i).getId());
        	Integer seeedingNum = settlementMapper.selectCount(params);
        	StringBuffer buffer = new StringBuffer(String.valueOf(seeedingNum)).append('/').append(number_people);
        	data.get(i).setPeopreNum(buffer.toString());
        	// 状态
        	data.get(i).setStatus(GlobalEnums.getName(Integer.parseInt(data.get(i).getStatus())));
        }
		return data;
	}

	@Override
	@Transactional
	public boolean addJobs(List<Job> jobs, List<JobDetail> jobDetails) {
		boolean flag;
		try {
            for(int i = GlobalNumber.INT_FIX_0; i < jobs.size(); i ++) {
                Job job = jobs.get(i);
                jobMapper.insert(job);
                Long jobId = job.getId();
                jobDetails.get(i).setJobId(jobId);
            }
            jobDetailService.insertBatch(jobDetails);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
		return flag;
	}
}
