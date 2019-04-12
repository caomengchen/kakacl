package cn.stylefeng.guns.modular.store.vo;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

public class JobAdd extends Model<JobAdd> {
	// 主键
	private Long id;
	// 企业主键
	private Long companyId;
	// 职位主键
	private String positionId;
	// 行业id
	private String industryId;
	// 岗位id
	private String stationId;
	private String hiringPost = "招聘简章";
	// 发薪模式
	private Integer employeeCycle;
	//  企业招聘总人数
	private Integer numberPeople;
	// 补贴方式,默认为定期返
	private Integer billingCycle;
	// 补贴价格
	private String allowancePrice;
	// 补贴周期
	private String allowanceCycle;
	private Integer unit;
	private String endTime;
	// 招聘条件
	private String condition;
	// 福利
	private String welfare;
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getHiringPost() {
		return hiringPost;
	}
	public void setHiringPost(String hiringPost) {
		this.hiringPost = hiringPost;
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
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
