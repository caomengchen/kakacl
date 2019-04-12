package cn.stylefeng.guns.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用枚举类型
 * 主要定义有 </br>
 * <p>
 *      1.补贴方式
 *      2.结算方式
 *      4.结算周期
 *      3.人员来源
 *      5.入职状态
 *      6.工种长度 长期工、短期工、临时工、日结工等
 *      7.考勤单位 考勤日、工时、自然日
 *
 *      // 每个类型间隔为100个单位
 * </p>
 */
public enum GlobalEnums {
	// 门店对员工的金额为结算; 公司对门店为补贴
	
    // 结算方式
    B_50001("结算打卡日", 50001),
    B_50002("结算工作日", 50002),
    B_50003("结算小时工", 50003),
    B_50004("结算自然日", 50004),
    B_50005("结算定期返", 50005),
    
    // 结算周期 ？
    B_50201("结算自然日", 50201),
    B_50205("结算定期返", 50205),
    
    // 补贴方式
    B_50101("补贴打卡日", 50101),
    B_50102("补贴工作日", 50102),
    B_50103("补贴小时工", 50103),
    B_50104("补贴自然日", 50104),
    B_50105("补贴定期返", 50105),
    B_50106("补贴总价", 50106),
    B_50107("补贴差价", 50107),
    // 1.2.3升级仅使用一下数据分别补贴方式
    B_50108("定期补贴", 50108),
    B_50109("到期补贴", 50109),
    B_50110("管理费补贴", 50110),
    B_50111("总价补贴", 50111),

    // 人员来源
    B_50301("自有", 50301),
    B_50302("门店", 50302),
    B_50303("平台", 50303),

    // 工种长度类型
    B_50501("长期工", 50501),
    B_50502("短期工", 50502),
    B_50503("临时工", 50503),
    B_50504("日结工", 50504),

    // 考勤类型 unit
    B_50601("考勤日", 50601),
    B_50602("工时", 50602),
    B_50603("工作日", 50603),
    B_50604("自然日", 50604),
    B_50606("打卡日", 50606),
    
    // 发薪模式
    B_50701("月薪", 50701),
    B_50702("小时工", 50702),
    B_50703("日结工（白班）", 50703),
    B_50704("日结工（夜班）", 50704),
    B_50705("周周发", 50705),
    B_50706("底薪加班", 50706),
    
    // 性别
    GENDER_50801("男", 50801),
    GENDER_50802("女", 50802),
    
    // 角色 0=普通用户，1=经纪人
    ROLE_51000("普通用户", 0),
    ROLE_51001("经纪人", 1),
    
    // 账号状态 0=正常，1=冻结
    ACCOUNT_STATUS_52000("账号正常", 52000),
    ACCOUNT_STATUS_52001("账号冻结", 52001),
    
    // 入职状态 雇员的状态以此为唯一
    EMPLOYMENT_STATUS_52100("入职中", 52100),
    EMPLOYMENT_STATUS_52101("待入职", 52101),
    EMPLOYMENT_STATUS_52102("等待面试", 52102),
    EMPLOYMENT_STATUS_52103("等待体检", 52103),
    EMPLOYMENT_STATUS_52104("面试未通过", 52104),
    EMPLOYMENT_STATUS_52105("体检未通过", 52105),
    EMPLOYMENT_STATUS_52106("面试通过未入职", 52106),
    EMPLOYMENT_STATUS_52107("体检通过未入职", 52107),
    EMPLOYMENT_STATUS_52108("公司原因未入职", 52108),
    EMPLOYMENT_STATUS_52109("待报道", 52109),
    EMPLOYMENT_STATUS_52110("离职", 52110),
    
    // 数据异常
    ERROR_STATUS_53000("数据异常", 53000),
    
    DATA_54000("正常", 54000),
    DATA_54001("已删除", 54001),
    DATA_54002("暂停", 54002),
    DATA_54003("停止", 54003),
    DATA_54004("待审核", 54004),
    DATA_54005("停招", 54005),
    DATA_54006("冻结", 54006),
    DATA_54007("异常", 54007),
    DATA_54008("审核驳回", 54008),
    
    B_999999("其他", 999999);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private GlobalEnums(String name, int index) {
        this.name = name;
        this.index = index;
    }
    
    public static String getName(int index) {
        for (GlobalEnums c : GlobalEnums.values()) {  
            if (c.index == index) {  
                return c.name;  
            }  
        }  
        return "";  
    }
    
    public static Integer getIndexByName(String name) {
        for (GlobalEnums c : GlobalEnums.values()) {  
            if (c.name.equals(name)) {  
                return c.index;  
            }  
        }  
        return 0;  
    }
    
    public Map<String, Object> findMaps() {
    	Map<String, Object> map = new HashMap<>();
    	return map;
    }
    
    //覆盖方法
    @Override
    public String toString() {
        return this.index + "_" + this.name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
    
}
