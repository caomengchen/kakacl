package cn.stylefeng.guns.modular.store.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangwjunsheng
 * @version v1.0.0
 * @description 员工考勤类
 * @date
 */
@TableName("store_workStatus")
public class WorkStatus extends Model<WorkStatus> {


    //主键
    @TableId(value="id",type = IdType.AUTO )
    private Integer id;

    //用户ID
    @TableField("uid")
    private Integer uid;

    //考勤组ID
    @TableField("gid")
    private Integer gid;

    //考勤组ID
    @TableField("remarks")
    private String remarks;


    //考勤组ID
    @TableField("expstate")
    private Integer expstate;

    //第一段上班打卡时间
    @TableField("begin1_Time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bTime1;

    //第一段下班打卡时间
    @TableField("end1_Time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date eTime1;

    //第二段上班打卡时间
    @TableField("begin2_Time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bTime2;

    //第二段下班打卡时间
    @TableField("end2_Time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date eTime2;

    //第三段上班打卡时间
    @TableField("begin3_Time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bTime3;

    //第三段下班打卡时间
    @TableField("end3_Time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date eTime3;

    //缺勤工时
    @TableField("hrs_lacked")
    private BigDecimal hrsLocked;
    //加班工时
    @TableField("hrs_addtion")
    private BigDecimal hrsAddtion;
    //正常工时
    @TableField("hrs_normal")
    private BigDecimal hrsNormal;



    //创建时间
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //修改时间
    @TableField("update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public WorkStatus(){};

    public WorkStatus(Integer id, Integer uid, Integer gid, String remarks, Integer expstate, Date bTime, Date eTime, Date createTime, Date updateTime) {
        this.id = id;
        this.uid = uid;
        this.gid = gid;
        this.remarks = remarks;
        this.expstate = expstate;
        this.bTime1 = bTime;
        this.eTime1 = eTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getExpstate() {
        return expstate;
    }

    public void setExpstate(Integer expstate) {
        this.expstate = expstate;
    }

    public Date getbTime1() {
        return bTime1;
    }

    public void setbTime1(Date bTime1) {
        this.bTime1 = bTime1;
    }

    public Date geteTime1() {
        return eTime1;
    }

    public void seteTime1(Date eTime1) {
        this.eTime1 = eTime1;
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


    public Date getbTime2() {return bTime2;}

    public void setbTime2(Date bTime2) {this.bTime2 = bTime2;}

    public Date geteTime2() { return eTime2;}

    public void seteTime2(Date eTime2) {this.eTime2 = eTime2;}

    public Date getbTime3() {return bTime3;}

    public void setbTime3(Date bTime3) {this.bTime3 = bTime3;}

    public Date geteTime3() {return eTime3;}

    public void seteTime3(Date eTime3) {this.eTime3 = eTime3;}

    public BigDecimal getHrsLocked() { return hrsLocked;}

    public void setHrsLocked(BigDecimal hrsLocked) {this.hrsLocked = hrsLocked;}

    public BigDecimal getHrsAddtion() {return hrsAddtion;}

    public void setHrsAddtion(BigDecimal hrsAddtion) {this.hrsAddtion = hrsAddtion; }

    public BigDecimal getHrsNormal() { return hrsNormal;}

    public void setHrsNormal(BigDecimal hrsNormal) {this.hrsNormal = hrsNormal;}

    @Override
    public String toString() {
        return "WorkStatus{" +
                "id=" + id +
                ", uid=" + uid +
                ", gid=" + gid +
                ", remarks='" + remarks + '\'' +
                ", expstate=" + expstate +
                ", bTime1=" + bTime1 +
                ", eTime1=" + eTime1 +
                ", bTime2=" + bTime2 +
                ", eTime2=" + eTime2 +
                ", bTime3=" + bTime3 +
                ", eTime3=" + eTime3 +
                ", hrsLocked=" + hrsLocked +
                ", hrsAddtion=" + hrsAddtion +
                ", hrsNormal=" + hrsNormal +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
