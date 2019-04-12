package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.dao.JobMapper;
import cn.stylefeng.guns.modular.store.model.Job;
import cn.stylefeng.guns.modular.store.model.JobDetail;
import cn.stylefeng.guns.modular.store.dao.JobDetailMapper;
import cn.stylefeng.guns.modular.store.service.IJobDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-28
 */
@Service
public class JobDetailServiceImpl extends ServiceImpl<JobDetailMapper, JobDetail> implements IJobDetailService {

    @Resource
    private JobDetailMapper mapper;

    @Resource
    private JobMapper jobMapper;

    @Override
    public JobDetail selectByJobId(Long jobId) {
        return mapper.selectByJobId(jobId);
    }

    @Override
    @Transactional
    public boolean updateByJobId(JobDetail entity, Job job) {
        boolean falg = false;
        try {
            jobMapper.updateById(job);
            mapper.updateStatusByJobId(entity);
            falg = true;
        } catch (Exception e) {
            falg = false;
        }
        return falg;
    }

    /**
     * 更新状态
     *
     * @param jobId
     * @param oldStatus
     * @param newStatus
     * @return
     */
    @Override
    public boolean updateStatusByJobId(Long jobId, int oldStatus, int newStatus) {
        return mapper.updateStatusByJobId(jobId, oldStatus, newStatus);
    }

    @Override
    public boolean updatePrice(Long id, String price, int oldStatus, int newStatus) {
        return mapper.updatePrice(id, price, oldStatus, newStatus);
    }
}
