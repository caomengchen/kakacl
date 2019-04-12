/**
 * 初始化门店授权详情对话框
 */
var AuthorizeInfoDlg = {
    authorizeInfoData : {}
};

/**
 * 清除数据
 */
AuthorizeInfoDlg.clearData = function() {
    this.authorizeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AuthorizeInfoDlg.set = function(key, val) {
    this.authorizeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AuthorizeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AuthorizeInfoDlg.close = function() {
    parent.layer.close(window.parent.Authorize.layerIndex);
}

/**
 * 收集数据
 */
AuthorizeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('sysUserId')
    .set('storeNo')
    .set('areaName')
    .set('startTime')
    .set('endTime')
    .set('status')
    .set('createBy');
}

/**
 * 提交添加
 */
AuthorizeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/authorize/add", function(data){
        Feng.success("添加成功!");
        window.parent.Authorize.table.refresh();
        AuthorizeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.authorizeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AuthorizeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/authorize/update", function(data){
        Feng.success("修改成功!");
        window.parent.Authorize.table.refresh();
        AuthorizeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.authorizeInfoData);
    ajax.start();
}

$(function() {

});
