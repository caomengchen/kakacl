/**
 * 初始化招聘信息详情对话框
 */
var JobInfoDlg = {
    jobInfoData : {},
};

/**
 * 清除数据
 */
JobInfoDlg.clearData = function() {
    this.jobInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
JobInfoDlg.set = function(key, val) {
    this.jobInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
JobInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
JobInfoDlg.close = function() {
    parent.layer.close(window.parent.Job.layerIndex);
}

/**
 * 收集数据
 */
JobInfoDlg.collectClerkJobData = function() {
    this
    .set('id')
    .set('billingCycle')
    .set('billPrice')
    .set('billCycle')
    .set('clerkIsReset');
};

/**
 * 验证数据是否为空
 */
JobInfoDlg.validate = function () {
    $('#jobJoinForm').data("bootstrapValidator").resetForm();
    $('#jobJoinForm').bootstrapValidator('validate');
    return $("#jobJoinForm").data('bootstrapValidator').isValid();
};

/**
 * 审核通过 店长接单
 */
JobInfoDlg.reviewPass = function() {
    this.clearData();
    this.collectClerkJobData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/job/job_review_pass", function(data){
        Feng.success("审核成功!");
        window.parent.Job.table.refresh();
        JobInfoDlg.close();
    },function(data){
        Feng.error("提交失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.jobInfoData);
    ajax.start();
};

/**
 * reviewReject 审核驳回
 */
JobInfoDlg.reviewReject = function() {
    this.clearData();
    this.collectClerkJobData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/job/job_review_reject", function(data){
        Feng.success("提交成功!");
        window.parent.Job.table.refresh();
        JobInfoDlg.close();
    },function(data){
        Feng.error("提交失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.jobInfoData);
    ajax.start();
};

$(function() {
    Feng.initValidator("jobJoinForm", JobInfoDlg.validateFields);
});
