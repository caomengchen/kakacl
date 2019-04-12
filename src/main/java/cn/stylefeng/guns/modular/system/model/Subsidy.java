package cn.stylefeng.guns.modular.system.model;

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
 * @since 2018-11-27
 */
@TableName("store_subsidy")
public class Subsidy extends Model<Subsidy> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 补贴类型：1就业补贴
     */
    private Integer type;
    /**
     * 补贴编号
     */
    private String no;
    /**
     * 甲方主键-门店
     */
    @TableField("party_a")
    private Long partyA;
    /**
     * 就业者-乙方身份证主键
     */
    @TableField("party_b_id_card_id")
    private Long partyBIdCardId;
    /**
     * 乙方银行卡号码
     */
    @TableField("part_b_back_card")
    private String partBBackCard;
    /**
     * 乙方手机号码
     */
    @TableField("party_b_phone_num")
    private String partyBPhoneNum;
    /**
     * 上班公司主键
     */
    @TableField("company_id")
    private Long companyId;
    /**
     * 工种
     */
    @TableField("work_type")
    private Long workType;
    /**
     * 开始工作日期,按录入为准
     */
    @TableField("start_wark_date")
    private Date startWarkDate;
    /**
     * 最后结算时间
     */
    @TableField("end_wark_date")
    private Date endWarkDate;
    /**
     * 结算方式 - 补贴单位
     */
    @TableField("work_period")
    private Integer workPeriod;
    /**
     * 发薪模式,结算方式，默认为1，底薪加班
     */
    @TableField("employee_cycle")
    private Integer employeeCycle;
    /**
     * 补贴方式,结算周期 默认为定期返
     */
    @TableField("billing_cycle")
    private String billingCycle;
    /**
     * 补贴价格(金额)
     */
    @TableField("subsidy_price")
    private String subsidyPrice;
    /**
     * 要求期限
     */
    private String period;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Long getPartyA() {
        return partyA;
    }

    public void setPartyA(Long partyA) {
        this.partyA = partyA;
    }

    public Long getPartyBIdCardId() {
        return partyBIdCardId;
    }

    public void setPartyBIdCardId(Long partyBIdCardId) {
        this.partyBIdCardId = partyBIdCardId;
    }

    public String getPartBBackCard() {
        return partBBackCard;
    }

    public void setPartBBackCard(String partBBackCard) {
        this.partBBackCard = partBBackCard;
    }

    public String getPartyBPhoneNum() {
        return partyBPhoneNum;
    }

    public void setPartyBPhoneNum(String partyBPhoneNum) {
        this.partyBPhoneNum = partyBPhoneNum;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getWorkType() {
        return workType;
    }

    public void setWorkType(Long workType) {
        this.workType = workType;
    }

    public Date getStartWarkDate() {
        return startWarkDate;
    }

    public void setStartWarkDate(Date startWarkDate) {
        this.startWarkDate = startWarkDate;
    }

    public Date getEndWarkDate() {
        return endWarkDate;
    }

    public void setEndWarkDate(Date endWarkDate) {
        this.endWarkDate = endWarkDate;
    }

    public Integer getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Integer workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Integer getEmployeeCycle() {
        return employeeCycle;
    }

    public void setEmployeeCycle(Integer employeeCycle) {
        this.employeeCycle = employeeCycle;
    }

    public String getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }

    public String getSubsidyPrice() {
        return subsidyPrice;
    }

    public void setSubsidyPrice(String subsidyPrice) {
        this.subsidyPrice = subsidyPrice;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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
        return "Subsidy{" +
        ", id=" + id +
        ", type=" + type +
        ", no=" + no +
        ", partyA=" + partyA +
        ", partyBIdCardId=" + partyBIdCardId +
        ", partBBackCard=" + partBBackCard +
        ", partyBPhoneNum=" + partyBPhoneNum +
        ", companyId=" + companyId +
        ", workType=" + workType +
        ", startWarkDate=" + startWarkDate +
        ", endWarkDate=" + endWarkDate +
        ", workPeriod=" + workPeriod +
        ", employeeCycle=" + employeeCycle +
        ", billingCycle=" + billingCycle +
        ", subsidyPrice=" + subsidyPrice +
        ", period=" + period +
        ", createTime=" + createTime +
        ", remakes=" + remakes +
        ", delFlag=" + delFlag +
        "}";
    }
}
