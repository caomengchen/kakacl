package cn.stylefeng.guns.modular.store.model;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2019-01-17
 */
@TableName("store_people_record_auto")
public class PeopleRecordAuto extends Model<PeopleRecordAuto> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("store_manager_id")
    private Integer storeManagerId;
    @TableField("store_manager_name")
    private String storeManagerName;
    @TableField("store_manager_phone")
    private String storeManagerPhone;
    @TableField("people_count")
    private Integer peopleCount;
    @TableField("area_name")
    private String areaName;
    @TableField("store_name")
    private String storeName;
    @TableField("recome_time")
    private String recomeTime;
    @TableField("create_time")
    private Integer createTime;
    @TableField("del_flag")
    private Integer delFlag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreManagerId() {
        return storeManagerId;
    }

    public void setStoreManagerId(Integer storeManagerId) {
        this.storeManagerId = storeManagerId;
    }

    public String getStoreManagerName() {
        return storeManagerName;
    }

    public void setStoreManagerName(String storeManagerName) {
        this.storeManagerName = storeManagerName;
    }

    public String getStoreManagerPhone() {
        return storeManagerPhone;
    }

    public void setStoreManagerPhone(String storeManagerPhone) {
        this.storeManagerPhone = storeManagerPhone;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getRecomeTime() {
        return recomeTime;
    }

    public void setRecomeTime(String recomeTime) {
        this.recomeTime = recomeTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
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
        return "PeopleRecordAuto{" +
        ", id=" + id +
        ", storeManagerId=" + storeManagerId +
        ", storeManagerName=" + storeManagerName +
        ", storeManagerPhone=" + storeManagerPhone +
        ", peopleCount=" + peopleCount +
        ", areaName=" + areaName +
        ", storeName=" + storeName +
        ", recomeTime=" + recomeTime +
        ", createTime=" + createTime +
        ", delFlag=" + delFlag +
        "}";
    }
}
