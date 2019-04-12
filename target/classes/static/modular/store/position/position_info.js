/**
 * 初始化职位信息详情对话框
 */
var PositionInfoDlg = {
    positionInfoData : {}
};

/**
 * 清除数据
 */
PositionInfoDlg.clearData = function() {
    this.positionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PositionInfoDlg.set = function(key, val) {
    this.positionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PositionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PositionInfoDlg.close = function() {
    parent.layer.close(window.parent.Position.layerIndex);
}

/**
 * 收集数据
 */
PositionInfoDlg.collectData = function() {
    this.positionInfoData["info"] = PositionInfoDlg.editor.txt.html();
    this.positionInfoData["welfare"] = PositionInfoDlg.welfare.txt.html();
    this
    .set('id')
    .set('companyId')
    .set('industryId')
    .set('stationId')
    .set('typeId')
    .set('name')
    .set('isDeleted')
    .set('positionImg');
}

/**
 * 提交添加
 */
PositionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/position/add", function(data){
        Feng.success("添加成功!");
        window.parent.Position.table.refresh();
        PositionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.positionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PositionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/position/update", function(data){
        Feng.success("修改成功!");
        window.parent.Position.table.refresh();
        PositionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.positionInfoData);
    ajax.start();
}

$(function() {
    var E = window.wangEditor;
    var welfare = new E('#welfare');
    welfare.create();
    welfare.txt.html($("#contentVal").val());
    PositionInfoDlg.welfare = welfare;

    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.create();
    editor.txt.html($("#contentVal").val());
    PositionInfoDlg.editor = editor;
});
