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
 * 公司信息Serializable
 * </p>
 *
 * @author wangwei
 * @since 2018-11-29
 */
@TableName("store_company")
public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(exist = false)
    private Long introductionId;
    /**
     * 公司名称
     */
    @TableField("company_name")
    private String companyName;
    /**
     * 企业行业
     */
    @TableField("industry_id")
    private Long industryId;

    @TableField(exist = false)
    private String content;
    /**
     * 企业类型
     */
    @TableField("company_type")
    private String companyType;
    /**
     * 企业人数范围下限
     */
    @TableField("count_base")
    private Integer countBase;
    /**
     * 企业人数范围上限
     */
    @TableField("count_top")
    private Integer countTop;
    /**
     * 联系人名称
     */
    @TableField("contact_name")
    private String contactName;
    /**
     * 联系电话
     */
    @TableField("contact_phone")
    private String contactPhone;
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
     * 详细地址
     */
    private String address;
    /**
     * 企业介绍图
     */
    private String image;

    @TableField(exist = false)
    private String companyImg;
    @TableField(exist = false)
    private String contentImg;
    /**
     * 公司信息
     */
    private String info;
    /**
     * 排序
     */
    private Integer sort;
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public Integer getCountBase() {
        return countBase;
    }

    public void setCountBase(Integer countBase) {
        this.countBase = countBase;
    }

    public Integer getCountTop() {
        return countTop;
    }

    public void setCountTop(Integer countTop) {
        this.countTop = countTop;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
        return "Company{" +
        ", id=" + id +
        ", companyName=" + companyName +
        ", industryId=" + industryId +
        ", companyType=" + companyType +
        ", countBase=" + countBase +
        ", countTop=" + countTop +
        ", contactName=" + contactName +
        ", contactPhone=" + contactPhone +
        ", province=" + province +
        ", city=" + city +
        ", area=" + area +
        ", address=" + address +
        ", image=" + image +
        ", info=" + info +
        ", sort=" + sort +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public String getCompanyImg() {
        return companyImg;
    }

    public void setCompanyImg(String companyImg) {
        this.companyImg = companyImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIntroductionId() {
        return introductionId;
    }

    public void setIntroductionId(Long introductionId) {
        this.introductionId = introductionId;
    }
}
