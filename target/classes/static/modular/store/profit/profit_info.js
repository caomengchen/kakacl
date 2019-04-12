/**
 * 初始化利润核算详情对话框
 */
var ProfitInfoDlg = {
    profitInfoData : {}
};

/**
 * 清除数据
 */
ProfitInfoDlg.clearData = function() {
    this.profitInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProfitInfoDlg.set = function(key, val) {
    this.profitInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProfitInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ProfitInfoDlg.close = function() {
    parent.layer.close(window.parent.Profit.layerIndex);
}

/**
 * 收集数据
 */
ProfitInfoDlg.collectData = function() {
    this
    .set('id')
    .set('storeId')
    .set('storeProfit')
    .set('year')
    .set('month')
    .set('createTime')
    .set('delFlag');
}

/**
 * 提交添加
 */
ProfitInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/profit/add", function(data){
        Feng.success("添加成功!");
        window.parent.Profit.table.refresh();
        ProfitInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.profitInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ProfitInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/profit/update", function(data){
        Feng.success("修改成功!");
        window.parent.Profit.table.refresh();
        ProfitInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.profitInfoData);
    ajax.start();
}

$(function() {

});
