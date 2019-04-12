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
 * 店长店员关系表
 * </p>
 *
 * @author wangwei
 * @since 2018-11-29
 */
@TableName("store_clerk_join")
public class ClerkJoin extends Model<ClerkJoin> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 门店主键-店长主键
     */
    @TableField("store_id")
    private Long storeId;

    @TableField(exist = false)
    private String storeManagerNameView;
    /**
     * 职位主键
     */
    @TableField("store_clerk_id")
    private Long storeClerkId;
    @TableField(exist = false)
    private String storeClerkNameView;
    /**
     * 删除标记
     */
    @TableField("del_flag")
    private Integer delFlag;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    public String getStoreManagerNameView() {
        return storeManagerNameView;
    }

    public void setStoreManagerNameView(String storeManagerNameView) {
        this.storeManagerNameView = storeManagerNameView;
    }

    public String getStoreClerkNameView() {
        return storeClerkNameView;
    }

    public void setStoreClerkNameView(String storeClerkNameView) {
        this.storeClerkNameView = storeClerkNameView;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getStoreClerkId() {
        return storeClerkId;
    }

    public void setStoreClerkId(Long storeClerkId) {
        this.storeClerkId = storeClerkId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ClerkJoin{" +
        ", id=" + id +
        ", storeId=" + storeId +
        ", storeClerkId=" + storeClerkId +
        ", delFlag=" + delFlag +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        "}";
    }
}
