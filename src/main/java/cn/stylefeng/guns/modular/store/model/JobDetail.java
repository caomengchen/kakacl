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
 * 
 * </p>
 *
 * @author wangwei
 * @since 2018-11-28
 */
@TableName("store_job_detail")
public class JobDetail extends Model<JobDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("job_id")
    private Long jobId;
    /**
     * 招聘简章
     */
    @TableField("hiring_post")
    private String hiringPost;
    /**
     * 详情图片地址
     */
    @TableField("company_img")
    private String companyImg;

    /**
     * 发薪模式，默认为1，底薪加班
     */
    @TableField("employee_cycle")
    private Integer employeeCycle;
    /**
     * 企业招聘总人数
     */
    @TableField("number_people")
    private Integer numberPeople;
    /**
     * 补贴方式,默认为定期返
     */
    @TableField("billing_cycle")
    private Integer billingCycle;
    
    /**
     * 补贴方式展示层
     */
    @TableField(exist = false)
    private String billingCycleView;
    /**
     * 补贴价格
     */
    @TableField("allowance_price")
    private String allowancePrice;
    /**
     * 补贴周期,结算周期
     */
    @TableField("allowance_cycle")
    private String allowanceCycle;
    /**
     * 单位、：默认为考勤日
     */
    private Integer unit;

    public String getUnitView() {
        return unitView;
    }

    public void setUnitView(String unitView) {
        this.unitView = unitView;
    }

    @TableField(exist = false)
    private String unitView;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 截止日期
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 招聘条件
     */
    private String condition;
    /**
     * 福利待遇
     */
    private String welfare;
    @TableField("create_date")
    private Date createDate;


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

    public String getHiringPost() {
        return hiringPost;
    }

    public void setHiringPost(String hiringPost) {
        this.hiringPost = hiringPost;
    }

    public String getCompanyImg() {
        return companyImg;
    }

    public void setCompanyImg(String companyImg) {
        this.companyImg = companyImg;
    }

    public Integer getEmployeeCycle() {
        return employeeCycle;
    }

    public void setEmployeeCycle(Integer employeeCycle) {
        this.employeeCycle = employeeCycle;
    }

    public Integer getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(Integer numberPeople) {
        this.numberPeople = numberPeople;
    }

    public Integer getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(Integer billingCycle) {
        this.billingCycle = billingCycle;
    }

    public String getAllowancePrice() {
        return allowancePrice;
    }

    public void setAllowancePrice(String allowancePrice) {
        this.allowancePrice = allowancePrice;
    }

    public String getAllowanceCycle() {
        return allowanceCycle;
    }

    public void setAllowanceCycle(String allowanceCycle) {
        this.allowanceCycle = allowanceCycle;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "JobDetail{" +
        ", id=" + id +
        ", jobId=" + jobId +
        ", hiringPost=" + hiringPost +
        ", companyImg=" + companyImg +
        ", employeeCycle=" + employeeCycle +
        ", numberPeople=" + numberPeople +
        ", billingCycle=" + billingCycle +
        ", allowancePrice=" + allowancePrice +
        ", allowanceCycle=" + allowanceCycle +
        ", unit=" + unit +
        ", status=" + status +
        ", endTime=" + endTime +
        ", condition=" + condition +
        ", welfare=" + welfare +
        ", createDate=" + createDate +
        "}";
    }

	public String getBillingCycleView() {
		return billingCycleView;
	}

	public void setBillingCycleView(String billingCycleView) {
		this.billingCycleView = billingCycleView;
	}
}
