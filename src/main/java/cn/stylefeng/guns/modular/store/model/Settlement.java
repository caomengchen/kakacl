package cn.stylefeng.guns.modular.store.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 结算表
 * </p>
 *
 * @author wangwei
 * @since 2018-11-30
 */
@TableName("store_settlement")
public class Settlement extends Model<Settlement> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 补贴类型：1就业补贴
     */
    private Integer type;

    /**
     * 入职时间
     */
    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startWarkDate;
    /**
     * 补贴编号
     */
    private String no;
    /**
     * 店员主键
     */
    @TableField("sys_user_id")
    private Long sysUserId;
    /**
     * 职位主键
     */
    @TableField("job_id")
    private Long jobId;

    @TableField("create_by")
    private Long createBy;
    /**
     * 雇员主键
     */
    @TableField("store_account_id")
    private Long storeAccountId;

    @TableField(exist = false)
    private String storeAccountNameVIew;

    /**
     * 账户的状态
     * @return
     */
    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    @TableField(exist = false)
    private Integer accountStatus;

    /**
     * 结算方式
     */
    @TableField("billing_cycle")
    private Integer billingCycle;

    @TableField(exist = false)
    private String billingCycleView;
    /**
     * 结算价格
     */
    @TableField("bill_price")
    private String billPrice;
    /**
     * 结算周期
     */
    @TableField("bill_cycle")
    private String billCycle;
    @TableField("create_time")
    private Date createTime;
    private String remakes;
    /**
     * 删除标记：0，未删除；1，删除
     */
    @TableField("del_flag")
    private Integer delFlag;

    public Date getStartWarkDate() {
        return startWarkDate;
    }

    public void setStartWarkDate(Date startWarkDate) {
        this.startWarkDate = startWarkDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNo() {
        return no;
    }

    public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public void setNo(String no) {
        this.no = no;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Long storeAccountId) {
        this.storeAccountId = storeAccountId;
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
        return "Settlement{" +
        ", id=" + id +
        ", type=" + type +
        ", no=" + no +
        ", sysUserId=" + sysUserId +
        ", storeAccountId=" + storeAccountId +
        ", billingCycle=" + billingCycle +
        ", billPrice=" + billPrice +
        ", billCycle=" + billCycle +
        ", createTime=" + createTime +
        ", remakes=" + remakes +
        ", delFlag=" + delFlag +
        "}";
    }

    public void setStoreAccountNameVIew(String storeAccountNameVIew) {
        this.storeAccountNameVIew = storeAccountNameVIew;
    }

    public String getStoreAccountNameVIew() {
        return storeAccountNameVIew;
    }

    public String getBillingCycleView() {
        return billingCycleView;
    }

    public void setBillingCycleView(String billingCycleView) {
        this.billingCycleView = billingCycleView;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
}
