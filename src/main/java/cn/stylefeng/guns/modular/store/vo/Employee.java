package cn.stylefeng.guns.modular.store.vo;

/**
 * 雇员信息
* @ClassName: Employee  
* @Description: 雇员，主要是店员增加雇员信息的对象 
* @author wangwei  
* @date 2018年11月30日  
*
 */
public class Employee {

	private String name;
	private String nickname;
	private String gender;
	private Long jobId;
	// 民族
	private String nationality;
	private String birthplace;
	// 身份证地址
	private String address;
	private String idCard;
	private String phone;
	private String backNum;
	// 结算价格
    private String billPrice;
    // 结算周期
    private String billCycle;
    // 结算方式
    private String billingCycle;
    // 备注
    private String remarks;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBackNum() {
		return backNum;
	}
	public void setBackNum(String backNum) {
		this.backNum = backNum;
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
	public String getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
}
