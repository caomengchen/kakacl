/**
 * 初始化招聘信息详情对话框
 */
var JobInfoDlg = {
    jobInfoData : {}
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
JobInfoDlg.collectData = function() {
    this
    .set('id')
    .set('positionId')
    .set('companyId')
    .set('industryId')
    .set('stationId')
    .set('wagesType')
    .set('wagesTop')
    .set('wagesBase')
    .set('ageTop')
    .set('ageBase')
    .set('agentReturn')
    .set('agentCycle')
    .set('employeeReturn')
    .set('employeeCycle')
    .set('province')
    .set('city')
    .set('area')
    .set('address')
    .set('attract')
    .set('applyCount')
    .set('pageviews')
    .set('collections')
    .set('sort')
    .set('isDeleted')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
JobInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/job/add", function(data){
        Feng.success("添加成功!");
        window.parent.Job.table.refresh();
        JobInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.jobInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
JobInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/job/update", function(data){
        Feng.success("修改成功!");
        window.parent.Job.table.refresh();
        JobInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.jobInfoData);
    ajax.start();
};

$(function() {

});
