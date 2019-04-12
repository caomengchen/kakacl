/**
 * 初始化人员信息详情对话框
 */
var AccountInfoDlg = {
    accountInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        idCard: {
            validators: {
                notEmpty: {
                    message: '身份证号码不能为空'
                },regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                    regexp: /^[a-zA-Z0-9\.]+$/,
                    message: '只能是数字和字母.'
                },stringLength: {
                    min: 15,
                    max: 18,
                    message: '身份证长度为15或18位'
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '手机号码不能为空'
                },regexp: {
                    regexp: /^[0-9]+$/,
                    message: '只能是数字'
                }
            }
        },
        address: {
            validators: {
                notEmpty: {
                    message: '身份证地址不能为空'
                }
            }
        },
        billPrice: {
            validators: {
                notEmpty: {
                    message: '结算价格不能为空'
                },numeric: {
                    message: '价格必须是数字，支持返费一次'
                }
            }
        },
        billCycle: {
            validators: {
                notEmpty: {
                    message: '结算周期不能为空'
                },numeric: {
                    message: '结算周期必须是数字，支持返费一期'
                }
            }
        }
    }
};

/**
 * 验证数据是否为空
 */
AccountInfoDlg.validate = function () {
    $('#accountInfoForm').data("bootstrapValidator").resetForm();
    $('#accountInfoForm').bootstrapValidator('validate');
    return $("#accountInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
AccountInfoDlg.clearData = function() {
    this.accountInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccountInfoDlg.set = function(key, val) {
    this.accountInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccountInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AccountInfoDlg.close = function() {
    parent.layer.close(window.parent.ClerkJob.layerIndex);
}

/**
 * 收集数据
 */
AccountInfoDlg.collectData = function() {
    this
    .set('name')
    .set("jobId")
    .set('nickname')
    .set('gender')
    .set('nickname')
    .set('birthplace')
    .set('address')
    .set('idCard')
    .set('phone')
    .set('backNum')
    .set('billPrice')
    .set('billCycle')
    .set('nationality')
    .set('billingCycle');
}

/**
 * 提交添加
 */
AccountInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    var ajax = new $ax(Feng.ctxPath + "/clerkJob/addEmployeeSignIn", function(data){
        Feng.success("报名成功!");
        //window.parent.Account.table.refresh();
        location.reload();
        //AccountInfoDlg.close();
    },function(data){
        Feng.error("报名失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.accountInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("accountInfoForm", AccountInfoDlg.validateFields);
});
