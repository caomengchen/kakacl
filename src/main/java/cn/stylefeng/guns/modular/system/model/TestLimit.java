package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

@TableName("test")
public class TestLimit extends Model<TestLimit> {

    private static final long serialVersionUID = 1L;
    
    @TableId(value = "aaa" ,type = IdType.AUTO)
    private Integer aaa ;

    private String bbb;



    public String getBbb() {
        return bbb;
    }

    public void setBbb(String bbb) {
        this.bbb = bbb;
    }

    public Integer getAaa() {
        return aaa;
    }

    public void setAaa(Integer aaa) {
        this.aaa = aaa;
    }
    @Override
    protected Serializable pkVal() {
        return this.aaa;
    }
    @Override
    public String toString() {
        return "Test{" +
                "aaa=" + aaa +
                ", bbb='" + bbb + '\'' +
                '}';
    }
}
