/**
 * 初始化店员店长管理详情对话框
 */
var ClerkJoinInfoDlg = {
    clerkJoinInfoData : {}
};

/**
 * 清除数据
 */
ClerkJoinInfoDlg.clearData = function() {
    this.clerkJoinInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClerkJoinInfoDlg.set = function(key, val) {
    this.clerkJoinInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClerkJoinInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ClerkJoinInfoDlg.close = function() {
    parent.layer.close(window.parent.ClerkJoin.layerIndex);
}

/**
 * 收集数据
 */
ClerkJoinInfoDlg.collectData = function() {
    this
    .set('id')
    .set('storeId')
    .set('storeClerkId')
    .set('delFlag')
    //.set('createTime')
    .set('createBy')
    //.set('updateTime')
    .set('updateBy');
}

/**
 * 提交添加
 */
ClerkJoinInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clerkJoin/add", function(data){
        Feng.success("添加成功!");
        window.parent.ClerkJoin.table.refresh();
        ClerkJoinInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clerkJoinInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ClerkJoinInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clerkJoin/update", function(data){
        Feng.success("修改成功!");
        window.parent.ClerkJoin.table.refresh();
        ClerkJoinInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clerkJoinInfoData);
    ajax.start();
}

$(function() {

});
