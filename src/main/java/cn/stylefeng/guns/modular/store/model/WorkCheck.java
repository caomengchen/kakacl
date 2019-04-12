package cn.stylefeng.guns.modular.store.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangjunsheng
 * @version v1.0.0
 * @description 工厂的考勤组表
 * @date
 */
@TableName("store_workCheck")
public class WorkCheck extends Model<WorkCheck> {
    /*
     *
     * 功能描述
     * @author wangjunsheng
     * 该类主要是工厂考勤组表，用来映射工厂各个岗位的考勤制度
     * @date 2019/3/21
      * @param null
     * @return
     */
    private static final long serialVersionUID = 1L;

    //主键
    @TableId(value="id",type = IdType.AUTO )
    private Integer id;

    //别名
    @TableField("name")
    private String name;


    //工厂ID
    @TableField("cid")
    private Integer cid;


    //岗位ID
    @TableField("pid")
    private Integer pid;


    //班次名称
    @TableField("title")
    private String title;


    //加班类型（0:普通加班，1：周末加班；2：节假日加班）
    @TableField("extType")
    private Integer extType;


    //逻辑删除（0:删除；1：逻辑删除）
    @TableField("disabled")
    private Integer disabled;


    //第一次上班打卡时间
    @TableField("first_STime")
    private String fSTime;


    //第一次下班打卡时间
    @TableField("first_ETime")
    private String fETime;


    //第二次上班打卡时间
    @TableField("second_STime")
    private String sSTime;


    //第二次下班打卡时间
    @TableField("second_ETime")
    private String sETime;


    //加班起始时间
    @TableField("ext_begin")
    private String ebegin;


    //加班结束时间
    @TableField("ext_end")
    private String eend;


    //创建时间
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    //修改时间
    @TableField("update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public WorkCheck(){};


    public WorkCheck(Integer id, String name, Integer cid, Integer pid, String title, Integer extType, Integer disabled, String fSTime, String fETime, String sSTime, String sETime, String ebegin, String eend, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.cid = cid;
        this.pid = pid;
        this.title = title;
        this.extType = extType;
        this.disabled = disabled;
        this.fSTime = fSTime;
        this.fETime = fETime;
        this.sSTime = sSTime;
        this.sETime = sETime;
        this.ebegin = ebegin;
        this.eend = eend;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {return cid;}

    public void setCid(Integer cid) {this.cid = cid;}

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getExtType() {
        return extType;
    }

    public void setExtType(Integer extType) {
        this.extType = extType;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public String getfSTime() {
        return fSTime;
    }

    public void setfSTime(String fSTime) {
        this.fSTime = fSTime;
    }

    public String getfETime() {
        return fETime;
    }

    public void setfETime(String fETime) {
        this.fETime = fETime;
    }

    public String getsSTime() {
        return sSTime;
    }

    public void setsSTime(String sSTime) {
        this.sSTime = sSTime;
    }

    public String getsETime() {
        return sETime;
    }

    public void setsETime(String sETime) {
        this.sETime = sETime;
    }

    public String getEbegin() {
        return ebegin;
    }

    public void setEbegin(String ebegin) {
        this.ebegin = ebegin;
    }

    public String getEend() {
        return eend;
    }

    public void setEend(String eend) {
        this.eend = eend;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WorkCheck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cid=" + cid +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", extType=" + extType +
                ", disabled=" + disabled +
                ", fSTime='" + fSTime + '\'' +
                ", fETime='" + fETime + '\'' +
                ", sSTime='" + sSTime + '\'' +
                ", sETime='" + sETime + '\'' +
                ", ebegin='" + ebegin + '\'' +
                ", eend='" + eend + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
