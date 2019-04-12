package cn.stylefeng.guns.modular.store.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2018-11-29
 */
@TableName("store_account")
public class Account extends Model<Account> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色 0=普通用户，1=经纪人
     */
    private Integer role;
    private String avatar;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 工号
     */
    @TableField("job_num")
    private String jobNum;
    /**
     * 电子邮件
     */
    private String email;
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
     * 密码
     */
    private String password;
    /**
     * 账号状态 0=正常，1=冻结
     */
    private Integer status;
    /**
     * 求职状态（0=暂不考虑，1=找工作，2=想换工作，3=已在职）
     */
    @TableField("work_status")
    private Integer workStatus;
    /**
     * 工作的企业（停用，使用企业用户表）
     */
    @TableField("company_id")
    private Long companyId;
    /**
     * 职位id
     */
    @TableField("position_id")
    private Long positionId;
    /**
     * 积分
     */
    private Integer points;
    /**
     * 客服id
     */
    @TableField("service_id")
    private Long serviceId;
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新状态
     */
    @TableField("update_time")
    private Date updateTime;

    /*
     *报道日期
     */
    @TableField("report_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rDate;
    /*
     *离职日期
     */
    @TableField("leave_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lDate;
    /*
     *面试日期
     */
    @TableField("interview_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date iDate;
    /*
     *离职原因
     */
    @TableField("leave_reason")
    private Integer lReason;
    /*
     *扣款原因
     */
    @TableField("deduction_reason")
    private String dReason;

    @TableField("remark")
    private String remark;

    //l来源ID（哪个中介的资源）
    @TableField("sourceid")
    private Integer sourceid;

    //录入人员ID
    @TableField("inputterid")
    private Integer inputterid;

    //返费
    @TableField("returnFee")
    private String returnFee;
    //代理商名称
    @TableField("agent_name")
    private String agentName;

    //返费打款日期
    @TableField("returnFee_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnFeeTime;

    //读卡时间（到咔咔创联报道的时间）
    @TableField("read_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date readTime;

    //工种模式
    @TableField("workType")
    private String workType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getrDate() { return rDate; }

    public void setrDate(Date rDate) { this.rDate = rDate;  }

    public Date getlDate() {return lDate;}

    public void setlDate(Date lDate) { this.lDate = lDate;}

    public Date getiDate() { return iDate;}

    public void setiDate(Date iDate) {this.iDate = iDate;}
    public String getdReason() {return dReason;}

    public void setdReason(String dReason) { this.dReason = dReason;}

    public String getRemark() {return remark;}

    public void setRemark(String remark) {this.remark = remark;}

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public Integer getInputterid() {
        return inputterid;
    }

    public void setInputterid(Integer inputterid) {
        this.inputterid = inputterid;
    }

    public Integer getlReason() { return lReason;}

    public void setlReason(Integer lReason) {this.lReason = lReason;}

    public String getReturnFee() {return returnFee;}

    public void setReturnFee(String returnFee) {this.returnFee = returnFee;}

    public Date getReturnFeeTime() {return returnFeeTime;}

    public void setReturnFeeTime(Date returnFeeTime) {this.returnFeeTime = returnFeeTime;}

    public Date getReadTime() {return readTime;}

    public void setReadTime(Date readTime) {this.readTime = readTime;}

    public String getWorkType() {return workType;}

    public void setWorkType(String workType) {this.workType = workType;}

    public String getAgentName() { return agentName;}

    public void setAgentName(String agentName) {this.agentName = agentName;}

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", role=" + role +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", jobNum='" + jobNum + '\'' +
                ", email='" + email + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", area=" + area +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", workStatus=" + workStatus +
                ", companyId=" + companyId +
                ", positionId=" + positionId +
                ", points=" + points +
                ", serviceId=" + serviceId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", rDate=" + rDate +
                ", lDate=" + lDate +
                ", iDate=" + iDate +
                ", lReason=" + lReason +
                ", dReason='" + dReason + '\'' +
                ", remark='" + remark + '\'' +
                ", sourceid=" + sourceid +
                ", inputterid=" + inputterid +
                ", returnFee='" + returnFee + '\'' +
                ", agentName='" + agentName + '\'' +
                ", returnFeeTime=" + returnFeeTime +
                ", readTime=" + readTime +
                ", workType='" + workType + '\'' +
                '}';
    }

    @Override
    protected Serializable pkVal() {return this.id;}


}
