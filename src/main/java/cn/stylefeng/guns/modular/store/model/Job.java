package cn.stylefeng.guns.modular.store.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 职位
 * </p>
 *
 * @author wangwei
 * @since 2018-11-27
 */
@TableName("store_job")
public class Job extends Model<Job> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 职位id
     */
    @TableField("position_id")
    private Long positionId;
    /**
     * 企业id
     */
    @TableField("company_id")
    private Long companyId;
    /**
     * 行业id
     */
    @TableField("industry_id")
    private Long industryId;
    /**
     * 岗位id
     */
    @TableField("station_id")
    private Long stationId;
    /**
     * 职位薪资类型（0=月薪，1=周薪，2=兼职）
     */
    @TableField("wages_type")
    private Integer wagesType;
    /**
     * 薪资上限
     */
    @TableField("wages_top")
    private BigDecimal wagesTop;
    /**
     * 基础薪资
     */
    @TableField("wages_base")
    private BigDecimal wagesBase;
    /**
     * 最高年龄
     */
    @TableField("age_top")
    private Integer ageTop;
    /**
     * 最低年龄
     */
    @TableField("age_base")
    private Integer ageBase;
    /**
     * 经纪人返现金额
     */
    @TableField("agent_return")
    private BigDecimal agentReturn;
    /**
     * 经纪人返现周期
     */
    @TableField("agent_cycle")
    private Integer agentCycle;
    /**
     * 员工返现金额
     */
    @TableField("employee_return")
    private BigDecimal employeeReturn;
    /**
     * 员工返现周期
     */
    @TableField("employee_cycle")
    private Integer employeeCycle;
    /**
     * 省
     */
    private Long province;
    /**
     * 城市
     */
    private Long city;
    /**
     * 区域
     */
    private Long area;
    /**
     * 地址
     */
    private String address;
    /**
     * 职位诱惑
     */
    private String attract;
    /**
     * 申请人数
     */
    @TableField("apply_count")
    private Integer applyCount;
    /**
     * 浏览量
     */
    private Integer pageviews;
    /**
     * 收藏量
     */
    private Integer collections;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 0=未删 1=已删
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @TableField("update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Integer getWagesType() {
        return wagesType;
    }

    public void setWagesType(Integer wagesType) {
        this.wagesType = wagesType;
    }

    public BigDecimal getWagesTop() {
        return wagesTop;
    }

    public void setWagesTop(BigDecimal wagesTop) {
        this.wagesTop = wagesTop;
    }

    public BigDecimal getWagesBase() {
        return wagesBase;
    }

    public void setWagesBase(BigDecimal wagesBase) {
        this.wagesBase = wagesBase;
    }

    public Integer getAgeTop() {
        return ageTop;
    }

    public void setAgeTop(Integer ageTop) {
        this.ageTop = ageTop;
    }

    public Integer getAgeBase() {
        return ageBase;
    }

    public void setAgeBase(Integer ageBase) {
        this.ageBase = ageBase;
    }

    public BigDecimal getAgentReturn() {
        return agentReturn;
    }

    public void setAgentReturn(BigDecimal agentReturn) {
        this.agentReturn = agentReturn;
    }

    public Integer getAgentCycle() {
        return agentCycle;
    }

    public void setAgentCycle(Integer agentCycle) {
        this.agentCycle = agentCycle;
    }

    public BigDecimal getEmployeeReturn() {
        return employeeReturn;
    }

    public void setEmployeeReturn(BigDecimal employeeReturn) {
        this.employeeReturn = employeeReturn;
    }

    public Integer getEmployeeCycle() {
        return employeeCycle;
    }

    public void setEmployeeCycle(Integer employeeCycle) {
        this.employeeCycle = employeeCycle;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAttract() {
        return attract;
    }

    public void setAttract(String attract) {
        this.attract = attract;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public Integer getPageviews() {
        return pageviews;
    }

    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }

    public Integer getCollections() {
        return collections;
    }

    public void setCollections(Integer collections) {
        this.collections = collections;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Job{" +
        ", id=" + id +
        ", positionId=" + positionId +
        ", companyId=" + companyId +
        ", industryId=" + industryId +
        ", stationId=" + stationId +
        ", wagesType=" + wagesType +
        ", wagesTop=" + wagesTop +
        ", wagesBase=" + wagesBase +
        ", ageTop=" + ageTop +
        ", ageBase=" + ageBase +
        ", agentReturn=" + agentReturn +
        ", agentCycle=" + agentCycle +
        ", employeeReturn=" + employeeReturn +
        ", employeeCycle=" + employeeCycle +
        ", province=" + province +
        ", city=" + city +
        ", area=" + area +
        ", address=" + address +
        ", attract=" + attract +
        ", applyCount=" + applyCount +
        ", pageviews=" + pageviews +
        ", collections=" + collections +
        ", sort=" + sort +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
