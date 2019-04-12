package cn.stylefeng.guns.modular.store.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wangjunsheng
 * @version v1.0.0
 * @description 考勤组表VO类
 * @date 2079-3-27
 */
public class WorkCheck {
    //主键
    private Integer id;

    //别名
    private String name;

    //工厂ID
    private Integer cid;

    //岗位ID
    private Integer pid;

    //班次名称
    private String title;

    //加班类型（0:普通加班，1：周末加班；2：节假日加班）
    private Integer extType;

    //逻辑删除（0:删除；1：逻辑删除）
    private Integer disabled;

    //第一次上班打卡时间
    private String fSTime;

    //第一次下班打卡时间
    private String fETime;

    //第二次上班打卡时间
    private String sSTime;

    //第二次下班打卡时间
    private String sETime;

    //加班起始时间
    private String ebegin;

    //加班结束时间
    private String eend;

    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;


    //修改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    //公司id
    private Integer companyId;

    //公司名称
    private String companyname;

    //岗位名称
    private String workname;

    public WorkCheck(){};

    public WorkCheck(Integer id, String name, Integer cid, Integer pid, String title, Integer extType, Integer disabled, String fSTime, String fETime, String sSTime, String sETime, String ebegin, String eend, Date createTime, Date updateTime, Integer companyId, String companyname, String workname) {
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
        this.companyId = companyId;
        this.companyname = companyname;
        this.workname = workname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
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
                ", companyId=" + companyId +
                ", companyname='" + companyname + '\'' +
                ", workname='" + workname + '\'' +
                '}';
    }
}
