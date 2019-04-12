package cn.stylefeng.guns.modular.store.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 地址表
 * </p>
 *
 * @author wangwei
 * @since 2018-11-30
 */
@TableName("store_base_region")
public class BaseRegion extends Model<BaseRegion> {

    private static final long serialVersionUID = 1L;

    private Long id;
    @TableField("parent_id")
    private Long parentId;
    private Integer type;
    private String name;
    private String alias;
    private String zip;
    /**
     * 来源
     */
    private String source;
    private Date created;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "BaseRegion{" +
        ", id=" + id +
        ", parentId=" + parentId +
        ", type=" + type +
        ", name=" + name +
        ", alias=" + alias +
        ", zip=" + zip +
        ", source=" + source +
        ", created=" + created +
        "}";
    }
}
