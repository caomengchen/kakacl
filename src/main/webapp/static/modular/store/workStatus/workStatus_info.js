/**
 * 初始化门店结算详情对话框
 */
var WorkStatusInfoDlg = {
    workStatusInfoData : {},
    validateFields:{


        wid: {
            validators: {
                notEmpty: {
                    message: '考勤组不能为空'
                }
            }
        },
    }
};


/**
 * 清除数据
 */
WorkStatusInfoDlg.clearData = function() {
    this.workStatusInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WorkStatusInfoDlg.set = function(key, val) {
    this.workStatusInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WorkStatusInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WorkStatusInfoDlg.close = function() {
    parent.layer.close(window.parent.WorkStatus.layerIndex);
}

/**
 * 收集数据
 */
WorkStatusInfoDlg.collectData = function() {
    this
    .set('id')
    .set('uid')
    .set('gid')
    .set('bTime1')
    .set('eTime1')
    .set('bTime2')
    .set('eTime2')
    .set('bTime3')
    .set('eTime3')
    .set('hrsLocked')
    .set('hrsAddtion')
    .set('hrsNormal')
    .set('remarks')
    .set('createTime')
}

WorkStatusInfoDlg.validate = function () {
    $('#WorkStatusForm').data("bootstrapValidator").resetForm();
    $('#WorkStatusForm').bootstrapValidator('validate');
    return $("#WorkStatusForm").data('bootstrapValidator').isValid();
};

/**
 * 提交雇员的工作状态
 */
WorkStatusInfoDlg.editAcountSubmit = function() {
    this.clearData();
    this.collectData();
    console.log(this.get("bankname")+this.get("bankcard")+"王俊生！")
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/stationed/updateAccount", function(data){
        Feng.success("修改用户成功!");
        window.parent.WorkStatus.table.refresh();
        WorkStatusInfoDlg.close();
    },function(data){
        Feng.error("修改用户工作失败!" + data + "!");
    });

    ajax.set(this.workCheckInfoData);
    ajax.start();
};



//提交增加
WorkStatusInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/workStatus/add", function(data){
        Feng.success("添加成功!");
        window.parent.WorkStatus.table.refresh();
        WorkStatusInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.workStatusInfoData);
    ajax.start();
}


/**
 * 提交修改
 */
WorkStatusInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/workStatus/edit", function(data){
        Feng.success("修改成功!");
        window.parent.WorkStatus.table.refresh();
        WorkStatusInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.workStatusInfoData);
    ajax.start();
};

$(function() {
    Feng.initValidator("WorkStatusForm", WorkStatusInfoDlg.validateFields);
});
