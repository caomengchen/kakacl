package cn.stylefeng.guns.config;

/**
 * 
* @ClassName: GlobalMessages  
* @Description: 全局消息提示类,用于给前端的消息提示后后端的日志记录  
* @author wangwei  
* @date 2018年10月31日  
*
 */
public enum GlobalMessages {

	// S表示一般
	S_200(200, "200", "成功"),
	S_201(201, "201", "验证失败"),
	S_202(201, "202", "验证码不正确"),
	S_203(203, "203", "验证码过期，请重新获取"),
	S_204(204, "204", "请登录"),
	S_205(205, "205", "请登录"),
	S_206(206, "206", "修改密码失败"),
	S_207(207, "207", "密码修改成功，欢迎下次登录使用新设置的密码登录"),
	S_208(208, "208", "密码修改成功，请使用新设置的密码登录"),
	S_10000(10000, "10000", "执行成功"),
	
	
	S_40000(40000, "40000", "没有权限的操作"),
	S_40001(40001, "40001", "导入的数据中，有内容错误"),
	S_40002(40002, "40002", "导入的数据中，数据行数操作限制上限"),

	S_40100(40100, "40100", "导入的数据中，第{}行公司主键不正确，请确认。"),
	
	// E表示错误和异常
	E_500(500, "500", "服务 异常，请稍后再试"),
	E_50000(50000, "", ""),

    M_999999(999999, "", "其他");

    // 成员变量
	private int index;
	private String code;
    private String name;

    // 构造方法
    private GlobalMessages(int index, String code, String name) {
    	this.code = code;
        this.index = index;
        this.name = name;
    }
    
    public static String getName(int index) {  
        for (GlobalMessages c : GlobalMessages.values()) {  
            if (c.index == index) {  
                return c.name;  
            }  
        }  
        return "";  
    }
    
    public static String getCode(int index) {  
        for (GlobalMessages c : GlobalMessages.values()) {  
            if (c.index == index) {  
                return c.code;  
            }  
        }  
        return "";  
    }
    
    //覆盖方法
    @Override
    public String toString() {
        return this.index + "_" + this.code + "_" + this.name;
    }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
