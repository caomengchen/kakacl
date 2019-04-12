/**
 * 初始化企业介绍详情对话框
 */
var CompanyIntroductionInfoDlg = {
    companyIntroductionInfoData : {}
};

/**
 * 清除数据
 */
CompanyIntroductionInfoDlg.clearData = function() {
    this.companyIntroductionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CompanyIntroductionInfoDlg.set = function(key, val) {
    this.companyIntroductionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CompanyIntroductionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CompanyIntroductionInfoDlg.close = function() {
    parent.layer.close(window.parent.CompanyIntroduction.layerIndex);
}

/**
 * 收集数据
 */
CompanyIntroductionInfoDlg.collectData = function() {
    this.companyIntroductionInfoData["content"] = CompanyIntroductionInfoDlg.editor.txt.html();
    this
    .set('id')
    .set('companyId')
    .set('companyImg')
    .set("area")
    .set('contentImg')
    .set('companyName')
    .set('status');
}

/**
 * 提交添加
 */
CompanyIntroductionInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/companyIntroduction/add", function(data){
        Feng.success("添加成功!");
        window.parent.CompanyIntroduction.table.refresh();
        CompanyIntroductionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.companyIntroductionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CompanyIntroductionInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/companyIntroduction/update", function(data){
        Feng.success("修改成功!");
        window.parent.CompanyIntroduction.table.refresh();
        CompanyIntroductionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.companyIntroductionInfoData);
    ajax.start();
}

$(function() {
    //初始化编辑器
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.create();
    editor.txt.html($("#contentVal").val());
    CompanyIntroductionInfoDlg.editor = editor;
});
