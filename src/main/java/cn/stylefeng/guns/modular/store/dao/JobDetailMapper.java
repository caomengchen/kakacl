package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.JobDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangwei
 * @since 2018-11-28
 */
public interface JobDetailMapper extends BaseMapper<JobDetail> {

    @Select("select * from store_job_detail where job_id = #{jobId}")
    JobDetail selectByJobId(Long jobId);

    @Update("UPDATE store_job_detail SET status = #{status} where job_id = #{jobId}")
    boolean updateStatusByJobId(JobDetail jd);

    /**
     * 更新职位状态
     * @param jobId
     * @param oldStatus
     * @param status
     * @return
     */
    @Update("UPDATE store_job_detail SET status = #{status} where job_id = #{jobId}")
    boolean updateStatusByJobId(@Param("jobId") Long jobId, @Param("oldStatus")int oldStatus, @Param("status")int status);

    @Update("UPDATE store_job_detail SET allowance_price = #{price}, status = #{newStatus} where id = #{id} AND status = #{oldStatus}")
    boolean updatePrice(@Param("id")Long id, @Param("price")String price, @Param("oldStatus")int oldStatus, @Param("newStatus")int newStatus);
}
