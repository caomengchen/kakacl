/**
 * 初始化门店授权详情对话框
 */
var AgentInfoDlg = {
    agentInfoData : {}
};

/**
 * 清除数据
 */
AgentInfoDlg.clearData = function() {
    this.agentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AgentInfoDlg.set = function(key, val) {
    this.agentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AgentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AgentInfoDlg.close = function() {
    parent.layer.close(window.parent.Agent.layerIndex);
}

/**
 * 收集数据
 */
AgentInfoDlg.collectData = function() {
    this
        .set('id')
        .set('name')
        .set('status')
        .set('createTime')
        .set('updateTime')
        .set('userid')
        .set('username');
}

/**
 * 提交添加
 */
AgentInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/agent/add", function(data){
        Feng.success("添加成功!");
        window.parent.Agent.table.refresh();
        AgentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.agentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AgentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/agent/update", function(data){
        Feng.success("修改成功!");
        window.parent.Agent.table.refresh();
        AgentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.agentInfoData);
    ajax.start();
}

$(function() {

});
