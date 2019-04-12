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
 * 职位
 * </p>
 *
 * @author wangwei
 * @since 2018-12-18
 */
@TableName("store_position")
public class Position extends Model<Position> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 企业id
     */
    @TableField("company_id")
    private Long companyId;

    @TableField(exist = false)
    private String companyName;
    /**
     * 行业id
     */
    @TableField("industry_id")
    private Long industryId;
    /**
     * 所属岗位id
     */
    @TableField("station_id")
    private Long stationId;
    /**
     * 工种
     */
    @TableField("type_id")
    private Long typeId;
    /**
     * 职位名称
     */
    private String name;
    /**
     * 职位描述
     */
    private String info;
    /**
     * 福利
     */
    private String welfare;
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 职位图片地址
     */
    @TableField("position_img")
    private String positionImg;
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getPositionImg() {
        return positionImg;
    }

    public void setPositionImg(String positionImg) {
        this.positionImg = positionImg;
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
        return "Position{" +
        ", id=" + id +
        ", companyId=" + companyId +
        ", industryId=" + industryId +
        ", stationId=" + stationId +
        ", typeId=" + typeId +
        ", name=" + name +
        ", info=" + info +
        ", welfare=" + welfare +
        ", isDeleted=" + isDeleted +
        ", positionImg=" + positionImg +
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
}
