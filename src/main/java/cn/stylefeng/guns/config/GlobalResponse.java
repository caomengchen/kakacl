package cn.stylefeng.guns.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: GlobalResponse  
* @Description: 一般数据返回实体类
* @author wangwei  
* @date 2018年10月31日  
*
 */
public class GlobalResponse {

	private String code = "200";
	private String message;
	// 存放响应中的信息
    private Map<String,Object> responseContent;
    
    private String remarkes;
    
    GlobalResponse() {
    	this.responseContent = new HashMap<>();
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, Object> getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(Map<String, Object> responseContent) {
		this.responseContent = responseContent;
	}

	public String getRemarkes() {
		return remarkes;
	}

	public void setRemarkes(String remarkes) {
		this.remarkes = remarkes;
	}
	
	public static GlobalResponse getInstanseGlobalResponse() {
		return new GlobalResponse();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
