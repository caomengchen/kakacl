/**
 * 初始化店员职位详情对话框
 */
var ClerkJobInfoDlg = {
    clerkJobInfoData : {}
};

/**
 * 清除数据
 */
ClerkJobInfoDlg.clearData = function() {
    this.clerkJobInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClerkJobInfoDlg.set = function(key, val) {
    this.clerkJobInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClerkJobInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ClerkJobInfoDlg.close = function() {
    parent.layer.close(window.parent.ClerkJob.layerIndex);
}

/**
 * 收集数据
 */
ClerkJobInfoDlg.collectData = function() {
    this
    .set('id')
    .set('storeId')
    .set('jobId')
    .set('billingCycle')
    .set('billPrice')
    .set('billCycle')
    .set('delFlag')
    .set('createTime')
    .set('createBy')
    .set('updateTime')
    .set('updateBy');
}

/**
 * 提交添加
 */
ClerkJobInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clerkJob/add", function(data){
        Feng.success("添加成功!");
        window.parent.ClerkJob.table.refresh();
        ClerkJobInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clerkJobInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ClerkJobInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clerkJob/update", function(data){
        Feng.success("修改成功!");
        window.parent.ClerkJob.table.refresh();
        ClerkJobInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clerkJobInfoData);
    ajax.start();
}

$(function() {

});
