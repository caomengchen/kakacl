package cn.stylefeng.guns.modular.store.vo;

import java.util.Date;

public class SettlementVo {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long accountId;
    private String name;
    private Date createtime;
    private int updateTime;
    private Integer workStatus;
    private String workStatusView;
    private Integer billingCycle;
    private String billingCycleView;
    private String billPrice;
    private String billCycle;
    // 店员主键
    private Integer sysUserId;
    private String originView;
    private Long createBy;
    private Long updateBy;
    private String companyName;

    @Override
    public String toString() {
        return "SettlementVo{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", createtime=" + createtime +
                ", workStatus=" + workStatus +
                ", workStatusView='" + workStatusView + '\'' +
                ", billingCycle=" + billingCycle +
                ", billingCycleView='" + billingCycleView + '\'' +
                ", billPrice='" + billPrice + '\'' +
                '}';
    }



    public String getBillCycle() {
        return billCycle;
    }

    public void setBillCycle(String billCycle) {
        this.billCycle = billCycle;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public Integer getBillingCycle() {
        return billingCycle;
    }

    public String getBillPrice() {
        return billPrice;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public void setBillingCycle(Integer billingCycle) {
        this.billingCycle = billingCycle;
    }

    public void setBillPrice(String billPrice) {
        this.billPrice = billPrice;
    }
    public String getWorkStatusView() {
        return workStatusView;
    }

    public void setWorkStatusView(String workStatusView) {
        this.workStatusView = workStatusView;
    }

    public String getBillingCycleView() {
        return billingCycleView;
    }

    public void setBillingCycleView(String billingCycleView) {
        this.billingCycleView = billingCycleView;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getOriginView() {
        return originView;
    }

    public void setOriginView(String originView) {
        this.originView = originView;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
