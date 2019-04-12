/**
 * 初始化补贴进度详情对话框
 */
var SubsidyInfoDlg = {
    subsidyInfoData : {}
};

/**
 * 清除数据
 */
SubsidyInfoDlg.clearData = function() {
    this.subsidyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SubsidyInfoDlg.set = function(key, val) {
    this.subsidyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SubsidyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SubsidyInfoDlg.close = function() {
    parent.layer.close(window.parent.Subsidy.layerIndex);
}

/**
 * 收集数据
 */
SubsidyInfoDlg.collectData = function() {
    this
    .set('id')
    .set('type')
    .set('no')
    .set('partyA')
    .set('partyBIdCardId')
    .set('partBBackCard')
    .set('partyBPhoneNum')
    .set('companyId')
    .set('workType')
    .set('startWarkDate')
    .set('endWarkDate')
    .set('workPeriod')
    .set('employeeCycle')
    .set('billingCycle')
    .set('subsidyPrice')
    .set('period')
    .set('createTime')
    .set('remakes')
    .set('delFlag');
}

/**
 * 提交添加
 */
SubsidyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/subsidy/add", function(data){
        Feng.success("添加成功!");
        window.parent.Subsidy.table.refresh();
        SubsidyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.subsidyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SubsidyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/subsidy/update", function(data){
        Feng.success("修改成功!");
        window.parent.Subsidy.table.refresh();
        SubsidyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.subsidyInfoData);
    ajax.start();
}

$(function() {

});
