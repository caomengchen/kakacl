/**
 * 初始化门店结算详情对话框
 */
var WorkCheckInfoDlg = {
    workCheckInfoData : {},
    validateFields:{
        name: {
            validators: {
                notEmpty: {
                    message: '给考勤组去个名字吧'
                }
            }
        },
        title: {
            validators: {
                notEmpty: {
                    message: '班次不能为空'
                }
            }
        },
        extType: {
            validators: {
                notEmpty: {
                    message: '加班类型不能为空'
                },numeric: {
                    message: '加班类型必须是数字'
                }
            }
        },
        fSTime: {
            validators: {
                notEmpty: {
                    message: '首次上班时间不能为空'
                }
            }
        },
        fETime: {
            validators: {
                notEmpty: {
                    message: '首次下班时间不能为空'
                }
            }
        },
        companyId: {
            validators: {
                notEmpty: {
                    message: '企业不能为空'
                }
            }
        },
    }
};


/**
 * 清除数据
 */
WorkCheckInfoDlg.clearData = function() {
    this.workCheckInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WorkCheckInfoDlg.set = function(key, val) {
    this.workCheckInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WorkCheckInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WorkCheckInfoDlg.close = function() {
    parent.layer.close(window.parent.WorkCheck.layerIndex);
}

/**
 * 收集数据
 */
WorkCheckInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('title')
    .set('extType')
    .set('fSTime')
    .set('fETime')
    .set('sSTime')
    .set('sETime')
    .set('createTime')
    .set('ebegin')
    .set('eend')
    .set('cid')
    .set('pid')
    .set('workname')
}

WorkCheckInfoDlg.validate = function () {
    $('#WorkCheckForm').data("bootstrapValidator").resetForm();
    $('#WorkCheckForm').bootstrapValidator('validate');
    return $("#WorkCheckForm").data('bootstrapValidator').isValid();
};

/**
 * 提交雇员的工作状态
 */
WorkCheckInfoDlg.editAcountSubmit = function() {
    this.clearData();
    this.collectData();
    console.log(this.get("bankname")+this.get("bankcard")+"王俊生！")
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/stationed/updateAccount", function(data){
        Feng.success("修改用户成功!");
        window.parent.WorkCheck.table.refresh();
        WorkCheckInfoDlg.close();
    },function(data){
        Feng.error("修改用户工作失败!" + data + "!");
    });

    ajax.set(this.WorkCheckInfoData);
    ajax.start();
};



//提交增加
WorkCheckInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/workCheck/add", function(data){
        Feng.success("添加成功!");
        window.parent.WorkCheck.table.refresh();
        WorkCheckInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.workCheckInfoData);
    ajax.start();
}


/**
 * 提交修改
 */
WorkCheckInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/workCheck/edit", function(data){
        Feng.success("修改成功!");
        window.parent.WorkCheck.table.refresh();
        WorkCheckInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.workCheckInfoData);
    ajax.start();
};

$(function() {
    Feng.initValidator("WorkCheckForm", WorkCheckInfoDlg.validateFields);
});
