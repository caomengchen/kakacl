package cn.stylefeng.guns.modular.store.vo;

import java.util.Date;

/**
 * 职位展示对象
* @ClassName: Job  
* @Description:  职位展示
* @author wangwei  
* @date 2018年11月28日  
*
 */
public class Job {

	// 职位主键
	private Integer id;
	// 店员的职位主键
	private String clerkjobIdView;
	 // 企业名称
    private String companyName;
    // 企业主键
    private Long companyId;
    // 区域名称
    private String areaName;
    // 招工类型
    private String jobType;
    // 招聘人数
    private String peopreNum;
    // 当前申请人数
    private String seeedingNum;
    // 发薪模式
    private String employeeCycle;
    // 补贴周期
    private String billingCycle;
    // 补贴价格
    private String allowancePrice;
    // 补贴方式
    private String allowanceCycle;
    // 考勤类型
    private String unit;
    // 截止日期
    private Date endTime;
    // 当前状态
    private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getPeopreNum() {
		return peopreNum;
	}
	public void setPeopreNum(String peopreNum) {
		this.peopreNum = peopreNum;
	}
	public String getEmployeeCycle() {
		return employeeCycle;
	}
	public void setEmployeeCycle(String employeeCycle) {
		this.employeeCycle = employeeCycle;
	}
	public String getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(String billingCycle) {
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClerkjobIdView() {
		return clerkjobIdView;
	}
	public void setClerkjobIdView(String clerkjobIdView) {
		this.clerkjobIdView = clerkjobIdView;
	}
	public String getSeeedingNum() {
		return seeedingNum;
	}
	public void setSeeedingNum(String seeedingNum) {
		this.seeedingNum = seeedingNum;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
