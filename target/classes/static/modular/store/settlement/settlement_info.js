/**
 * 初始化门店结算详情对话框
 */
var SettlementInfoDlg = {
    settlementInfoData : {}
};

/**
 * 清除数据
 */
SettlementInfoDlg.clearData = function() {
    this.settlementInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SettlementInfoDlg.set = function(key, val) {
    this.settlementInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SettlementInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SettlementInfoDlg.close = function() {
    parent.layer.close(window.parent.Settlement.layerIndex);
}

/**
 * 收集数据
 */
SettlementInfoDlg.collectData = function() {
    this
    .set('id')
    .set('type')
    .set('no')
    .set('sysUserId')
    .set('storeAccountId')
    .set('billingCycle')
    .set('billPrice')
    .set('billCycle')
    .set('createTime')
    .set('accountStatus')
    .set('remakes')
    .set('delFlag')
    .set('startWarkDate');
}

/**
 * 提交添加
 */
SettlementInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/settlement/add", function(data){
        Feng.success("添加成功!");
        window.parent.Settlement.table.refresh();
        SettlementInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.settlementInfoData);
    ajax.start();
};

/**
 * 提交雇员的工作状态
 */
SettlementInfoDlg.editAcountStatusSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/settlement/updateAccountStatus", function(data){
        Feng.success("修改用户工作状态成功!");
        window.parent.Settlement.table.refresh();
        SettlementInfoDlg.close();
    },function(data){
        Feng.error("修改用户工作状态失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.settlementInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
SettlementInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/settlement/update", function(data){
        Feng.success("修改成功!");
        window.parent.Settlement.table.refresh();
        SettlementInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.settlementInfoData);
    ajax.start();
};

$(function() {

});
