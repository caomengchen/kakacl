package cn.stylefeng.guns.modular.store.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangjunsheng
 * @version v1.0.0
 * @description 代理商
 * @date 2019/3/11
 */
@TableName("store_agent")
public class Agent extends Model<Agent> {
    //代理商ID
    private Integer id;
    //代理商名称
    @TableField("name")
    private String name;

    //代理商状态（0：创建；1：激活；2：逻辑删除）
    @TableField("status")
    private Integer status;

    //店长id
    @TableField("userid")
    private Integer userid;

    //店长名称
    @TableField("username")
    private String username;

    //创建时间
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //修改时间
    @TableField("update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public Agent(){};
    public Agent(Integer id, String name, Integer status, Integer userid, String username, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.userid = userid;
        this.username = username;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {this.id = id;}

    public String getName() { return name; }

    public void setName(String name) {this.name = name;}

    public Integer getStatus() {return status;}

    public void setStatus(Integer status) {this.status = status;}

    public Integer getUserid() { return userid;}

    public void setUserid(Integer userid) {this.userid = userid;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public Date getCreateTime() { return createTime;}

    public void setCreateTime(Date createTime) {this.createTime = createTime;}

    public Date getUpdateTime() {return updateTime;}

    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime;}

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", userid=" + userid +
                ", username=" + username +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
