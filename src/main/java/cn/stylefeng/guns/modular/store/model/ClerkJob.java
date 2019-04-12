package cn.stylefeng.guns.modular.store.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 店员职位表
 * </p>
 *
 * @author wangwei
 * @since 2018-11-29
 */
@TableName("store_clerk_job")
//@ApiModel(value="门店店员职位")
public class ClerkJob extends Model<ClerkJob> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 门店主键-店长主键
     */
    @TableField("store_id")
    //@ApiModelProperty(value="storeId" ,required=false)
    private Long storeId;
    /**
     * 职位主键
     */
    @TableField("job_id")
    //@ApiModelProperty(value="jobId" ,required=false)
    private Long jobId;
    /**
     * 结算方式
     */
    @TableField("billing_cycle")
    //@ApiModelProperty(value="billingCycle" ,required=false)
    private Integer billingCycle;

    @TableField(exist = false)
    //@ApiModelProperty(value="billingCycleView" ,required=false)
    private String billingCycleView;
    /**
     * 结算价格
     */
    @TableField("bill_price")
    //@ApiModelProperty(value="billPrice" ,required=false)
    private String billPrice;
    /**
     * 结算周期
     */
    @TableField("bill_cycle")
    //@ApiModelProperty(value="billCycle" ,required=false)
    private String billCycle;

    @TableField("clerk_is_reset")
    private Integer clerkIsReset;
    /**
     * 删除标记
     */
    @TableField("del_flag")
    //@ApiModelProperty(value="delFlag" ,required=false)
    private Integer delFlag;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getClerkIsReset() {
        return clerkIsReset;
    }

    public void setClerkIsReset(Integer clerkIsReset) {
        this.clerkIsReset = clerkIsReset;
    }

    public String getBillingCycleView() {
        return billingCycleView;
    }

    public void setBillingCycleView(String billingCycleView) {
        this.billingCycleView = billingCycleView;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(Integer billingCycle) {
        this.billingCycle = billingCycle;
    }

    public String getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(String billPrice) {
        this.billPrice = billPrice;
    }

    public String getBillCycle() {
        return billCycle;
    }

    public void setBillCycle(String billCycle) {
        this.billCycle = billCycle;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ClerkJob{" +
        ", id=" + id +
        ", storeId=" + storeId +
        ", jobId=" + jobId +
        ", billingCycle=" + billingCycle +
        ", billPrice=" + billPrice +
        ", billCycle=" + billCycle +
        ", delFlag=" + delFlag +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        "}";
    }
}
