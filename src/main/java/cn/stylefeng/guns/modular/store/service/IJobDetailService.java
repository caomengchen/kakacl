package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.Job;
import cn.stylefeng.guns.modular.store.model.JobDetail;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-28
 */
public interface IJobDetailService extends IService<JobDetail> {

    JobDetail selectByJobId(Long jobId);

    boolean updateByJobId(JobDetail entity, Job job);

    /**
     * 更新职位状态
     * @param jobId
     * @param oldStatus
     * @param newStatus
     * @return
     */
    boolean updateStatusByJobId(Long jobId, int oldStatus, int newStatus);

    boolean updatePrice(Long id, String price, int oldStatus, int newStatus);
}
