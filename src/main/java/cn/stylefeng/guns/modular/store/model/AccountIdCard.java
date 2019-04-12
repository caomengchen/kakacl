package cn.stylefeng.guns.modular.store.model;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2018-11-30
 */
@TableName("store_account_id_card")
public class AccountIdCard extends Model<AccountIdCard> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("account_id")
    private Long accountId;
    /**
     * 身份证上的姓名
     */
    private String name;
    /**
     * 身份证上的性别
     */
    private String gender;
    /**
     * 籍贯，默认为身份证上的省市县
     */
    private String birthplace;
    /**
     * 民族
     */
    private String nation;
    /**
     * 现在居住的地址
     */
    private String address;
    /**
     * 身份证号码
     */
    @TableField("card_no")
    private String cardNo;
    /**
     * 身份证上的地址
     */
    @TableField("card_address")
    private String cardAddress;
    /**
     * 身份证生效时间
     */
    @TableField("start_deadline")
    private Date startDeadline;
    /**
     * 身份证最后期限
     */
    @TableField("end_deadline")
    private Date endDeadline;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardAddress() {
        return cardAddress;
    }

    public void setCardAddress(String cardAddress) {
        this.cardAddress = cardAddress;
    }

    public Date getStartDeadline() {
        return startDeadline;
    }

    public void setStartDeadline(Date startDeadline) {
        this.startDeadline = startDeadline;
    }

    public Date getEndDeadline() {
        return endDeadline;
    }

    public void setEndDeadline(Date endDeadline) {
        this.endDeadline = endDeadline;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AccountIdCard{" +
        ", id=" + id +
        ", accountId=" + accountId +
        ", name=" + name +
        ", gender=" + gender +
        ", birthplace=" + birthplace +
        ", address=" + address +
        ", cardNo=" + cardNo +
        ", cardAddress=" + cardAddress +
        ", startDeadline=" + startDeadline +
        ", endDeadline=" + endDeadline +
        "}";
    }

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
}
