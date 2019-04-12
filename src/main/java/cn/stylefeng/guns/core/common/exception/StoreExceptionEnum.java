package cn.stylefeng.guns.core.common.exception;

import cn.stylefeng.roses.kernel.model.exception.AbstractBaseExceptionEnum;

public enum StoreExceptionEnum implements AbstractBaseExceptionEnum {
	/**
     * 数据
     */
    DATABASE_ERROR(60001, "项目数据异常"),
    DATABASE_ERROR_60002(60002, "数据状态变更，请重新选择后再操作"),
    DATABASE_ERROR_60003(60003, "企业名称的数据已经存在"),
    DATABASE_ERROR_60004(60004, "企业信息录入失败"),
    DATABASE_ERROR_60005(60005, "数据当前状态不需要审核，请重新选择后再操作"),
    DATABASE_ERROR_60006(60006, "数据当前状态待审核，不支持修改数据"),
    DATABASE_ERROR_60007(60007, "数据中企业名称唯一性重复，请修改企业名称数据或删除重复的企业名称"),
    DATABASE_ERROR_60008(60008, "数据中没有企业介绍，请先修改企业介绍，然后查看详情"),
    DATABASE_ERROR_60009(60009, "数据中，当前公司名下相同职位已经存在，请确认， 1.修改为新的职位名称，2.删除已经存在的职位， 3.修改已存在的职位数据"),

    PARAM_ERROR(70001, "参数异常"),

    PARAM_ERROR_70002(70002, "参数结算周期不能为空"),
    PARAM_ERROR_70003(70003, "参数结算价格不能为空"),
    PARAM_ERROR_70004(70004, "参数开户行银行卡暂不支持，请重新输入"),
    PARAM_ERROR_70005(70005, "参数开户行银行卡不是储蓄卡，请重新输入"),

    PARAM_ERROR_71001(71001, "总人数为0，不能接单"),
    PARAM_ERROR_71002(71002, "报名人数已经达到峰值，暂停报名"),

    PARAM_ERROR_80001(80001, "具有咔咔创联关键字，请修改"),
    
    AUTH_REQUEST_ERROR(400, "账号密码错误"),

    AUTH_REQUEST_ERROR_40001(40001, "数据当前状态，无权修改"),


    RUN_TIME_ERROR(50010, "运行时异常"),
    ;

	StoreExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
