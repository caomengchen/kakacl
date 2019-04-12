/**
 * 初始化意见建议详情对话框
 */
var SuggestInfoDlg = {
    suggestInfoData : {}
};

/**
 * 清除数据
 */
SuggestInfoDlg.clearData = function() {
    this.suggestInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SuggestInfoDlg.set = function(key, val) {
    this.suggestInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SuggestInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SuggestInfoDlg.close = function() {
    parent.layer.close(window.parent.Suggest.layerIndex);
}

/**
 * 收集数据
 */
SuggestInfoDlg.collectData = function() {
    this
    .set('id')
    .set('user_name')
    .set('work_num')
    .set('id_card')
    .set('income_date')
    .set('gender')
    .set('line')
    .set('group_name')
    .set('birthplace')
    .set('phone_num')
    .set('remark')
    .set('entry_time');
}

/**
 * 提交添加
 */
SuggestInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sdm/index/save", function(data){
        console.info(data);
        Feng.success("添加成功!");
        window.parent.Suggest.table.refresh();
        SuggestInfoDlg.close();
    },function(data){
        console.info(data);
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.suggestInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SuggestInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/suggest/update", function(data){
        Feng.success("修改成功!");
        window.parent.Suggest.table.refresh();
        SuggestInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.suggestInfoData);
    ajax.start();
}

$(function() {

});
