package cn.stylefeng.guns.modular.store.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangwei
 * @since 2018-12-04
 */
@TableName("store_authorize")
public class Authorize extends Model<Authorize> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 门店名称
     */
    private String name;
    // 区域名称
    @TableField("area_name")
    private String areaName;
    // 编号
    @TableField("store_no")
    private String storeNo;
    @TableField("sys_user_id")
    private Long sysUserId;
    @TableField(exist = false)
    private String sysUserView;

    @TableField("start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @TableField("end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    /**
     * 授权状态
     */
    private String status;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSysUserView() {
        return sysUserView;
    }

    public void setSysUserView(String sysUserView) {
        this.sysUserView = sysUserView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Authorize{" +
        ", id=" + id +
        ", name=" + name +
        ", sysUserId=" + sysUserId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", status=" + status +
        ", createBy=" + createBy +
        "}";
    }
}
