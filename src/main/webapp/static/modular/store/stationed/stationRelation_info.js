/**
 * 初始化店员店长管理详情对话框
 */
var StationRelationInfoDlg = {
    stationRelationInfoData : {}
};

/**
 * 清除数据
 */
StationRelationInfoDlg.clearData = function() {
    this.stationRelationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StationRelationInfoDlg.set = function(key, val) {
    this.stationRelationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StationRelationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StationRelationInfoDlg.close = function() {
    parent.layer.close(window.parent.StationRelation.layerIndex);
}

/**
 * 收集数据
 */
StationRelationInfoDlg.collectData = function() {
    this
    .set('userid')
    .set('companyid')
}

/**
 * 提交添加
 */
StationRelationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if(this.StationedInfoData.get("userid")==115||this.StationedInfoData.get("companyid")==""){
        alert("请填写完整信息！")
        return;
    }


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/stationed/addRelation", function(data){
        Feng.success("添加成功!");
        window.parent.StationRelation.table.refresh();
        StationRelationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.stationRelationInfoData);
    ajax.start();
}

$(function() {

});
