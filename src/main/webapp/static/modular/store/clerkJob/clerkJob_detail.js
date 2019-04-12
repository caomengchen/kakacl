/**
 * 初始化店员职位详情对话框
 */
var ClerkJobInfoDlg = {
    clerkJobInfoData : {}
};

/**
 * 清除数据
 */
ClerkJobInfoDlg.clearData = function() {
    this.clerkJobInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClerkJobInfoDlg.set = function(key, val) {
    this.clerkJobInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClerkJobInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ClerkJobInfoDlg.close = function() {
    parent.layer.close(window.parent.ClerkJob.layerIndex);
}

/**
 * 收集数据
 */
ClerkJobInfoDlg.collectData = function() {
	console.info("running collectData ...");
    this
    .set('id')
    .set('storeId')
    .set('jobId')
    .set('billingCycle')
    .set('billPrice')
    .set('billCycle')
    .set('delFlag')
    .set('createTime')
    .set('createBy')
    .set('updateTime')
    .set('updateBy');
}

/**
 * 店员在线报名
 */
ClerkJobInfoDlg.registration = function(clerkJobId) {
	// 跳转到报名页面
	var index = layer.open({
        type: 2,
        title: '在线报名',
        fix: false,
        maxmin: true,
        content: Feng.ctxPath + '/clerkJob/registration/' + clerkJobId,
        success: function(layero,index){
        	layer.full(index);
    	}
    });
    this.layerIndex = index;
}

$(function() {

});
