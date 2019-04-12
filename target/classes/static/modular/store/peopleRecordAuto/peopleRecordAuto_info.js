/**
 * 初始化门店日求职信息详情对话框
 */
var PeopleRecordAutoInfoDlg = {
    peopleRecordAutoInfoData : {}
};

/**
 * 清除数据
 */
PeopleRecordAutoInfoDlg.clearData = function() {
    this.peopleRecordAutoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PeopleRecordAutoInfoDlg.set = function(key, val) {
    this.peopleRecordAutoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PeopleRecordAutoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PeopleRecordAutoInfoDlg.close = function() {
    parent.layer.close(window.parent.PeopleRecordAuto.layerIndex);
}

/**
 * 收集数据
 */
PeopleRecordAutoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('storeManagerId')
    .set('storeManagerName')
    .set('storeManagerPhone')
    .set('peopleCount')
    .set('areaName')
    .set('storeName')
    .set('recomeTime')
    .set('createTime')
    .set('delFlag');
}

/**
 * 提交添加
 */
PeopleRecordAutoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/peopleRecordAuto/add", function(data){
        Feng.success("添加成功!");
        window.parent.PeopleRecordAuto.table.refresh();
        PeopleRecordAutoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.peopleRecordAutoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PeopleRecordAutoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/peopleRecordAuto/update", function(data){
        Feng.success("修改成功!");
        window.parent.PeopleRecordAuto.table.refresh();
        PeopleRecordAutoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.peopleRecordAutoInfoData);
    ajax.start();
}

$(function() {

});
