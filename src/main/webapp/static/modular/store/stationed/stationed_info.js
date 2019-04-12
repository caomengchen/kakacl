/**
 * 初始化门店结算详情对话框
 */
var StationedInfoDlg = {
    StationedInfoData : {}
};


/**
 * 清除数据
 */
StationedInfoDlg.clearData = function() {
    this.StationedInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StationedInfoDlg.set = function(key, val) {
    this.StationedInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StationedInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StationedInfoDlg.close = function() {
    parent.layer.close(window.parent.Stationed.layerIndex);
}

/**
 * 收集数据
 */
StationedInfoDlg.collectData = function() {
    this
    .set('id')
    .set('nickname')
    .set('companyname')
    .set('workStatus')
    .set('iDate')
    .set('rDate')
    .set('lDate')
    .set('lReason')
    .set('rFee')
    .set('rfDate')
    .set('bankname')
    .set('bankcard')
    .set('dReason')
    .set('remark')
}



/**
 * 提交雇员的工作状态
 */
StationedInfoDlg.editAcountSubmit = function() {
    this.clearData();
    this.collectData();
    console.log(this.get("bankname")+this.get("bankcard")+"王俊生！")
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/stationed/updateAccount", function(data){
        Feng.success("修改用户成功!");
        window.parent.Stationed.table.refresh();
        StationedInfoDlg.close();
    },function(data){
        Feng.error("修改用户工作失败!" + data + "!");
    });

    ajax.set(this.StationedInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
StationedInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/settlement/update", function(data){
        Feng.success("修改成功!");
        window.parent.Stationed.table.refresh();
        StationedInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.StationedInfoData);
    ajax.start();
};

$(function() {

});
