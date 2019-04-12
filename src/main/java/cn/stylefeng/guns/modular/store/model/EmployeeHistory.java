package cn.stylefeng.guns.modular.store.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员就职状态历史更新表
 * </p>
 *
 * @author wangwei
 * @since 2018-12-12
 */
@TableName("store_employee_history")
public class EmployeeHistory extends Model<EmployeeHistory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 职位主键
     */
    @TableField("job_id")
    private Long jobId;
    /**
     * 店员主键
     */
    @TableField("store_manager_id")
    private Long storeManagerId;
    /**
     * 雇员主键
     */
    @TableField("user_account_id")
    private Long userAccountId;
    /**
     * 雇员就职状态
     */
    @TableField("sys_user_old_work_status")
    private Integer sysUserOldWorkStatus;
    /**
     * 雇员就职状态
     */
    @TableField("sys_user_new_work_status")
    private Integer sysUserNewWorkStatus;
    @TableField("create_time")
    private Date createTime;
    private String remakes;
    /**
     * 删除标记：0，未删除；1，删除
     */
    @TableField("del_flag")
    private Integer delFlag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getStoreManagerId() {
        return storeManagerId;
    }

    public void setStoreManagerId(Long storeManagerId) {
        this.storeManagerId = storeManagerId;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Integer getSysUserOldWorkStatus() {
        return sysUserOldWorkStatus;
    }

    public void setSysUserOldWorkStatus(Integer sysUserOldWorkStatus) {
        this.sysUserOldWorkStatus = sysUserOldWorkStatus;
    }

    public Integer getSysUserNewWorkStatus() {
        return sysUserNewWorkStatus;
    }

    public void setSysUserNewWorkStatus(Integer sysUserNewWorkStatus) {
        this.sysUserNewWorkStatus = sysUserNewWorkStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemakes() {
        return remakes;
    }

    public void setRemakes(String remakes) {
        this.remakes = remakes;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EmployeeHistory{" +
        ", id=" + id +
        ", jobId=" + jobId +
        ", storeManagerId=" + storeManagerId +
        ", userAccountId=" + userAccountId +
        ", sysUserOldWorkStatus=" + sysUserOldWorkStatus +
        ", sysUserNewWorkStatus=" + sysUserNewWorkStatus +
        ", createTime=" + createTime +
        ", remakes=" + remakes +
        ", delFlag=" + delFlag +
        "}";
    }
}
