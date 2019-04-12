package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
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
 * @since 2018-11-27
 */
@TableName("store_profit")
public class Profit extends Model<Profit> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("store_id")
    private Long storeId;
    /**
     * 门店收益
     */
    @TableField("store_profit")
    private BigDecimal storeProfit;
    private String year;
    private String month;
    @TableField("create_time")
    private Date createTime;
    /**
     * 删除标记：默认为0,1表示删除
     */
    @TableField("del_flag")
    private Integer delFlag;


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

    public BigDecimal getStoreProfit() {
        return storeProfit;
    }

    public void setStoreProfit(BigDecimal storeProfit) {
        this.storeProfit = storeProfit;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
        return "Profit{" +
        ", id=" + id +
        ", storeId=" + storeId +
        ", storeProfit=" + storeProfit +
        ", year=" + year +
        ", month=" + month +
        ", createTime=" + createTime +
        ", delFlag=" + delFlag +
        "}";
    }
}
