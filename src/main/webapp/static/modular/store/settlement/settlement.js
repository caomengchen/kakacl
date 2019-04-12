/**
 * 门店结算管理初始化
 */
var Settlement = {
    id: "SettlementTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};
/**
 * 初始化表格的列
 */
Settlement.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '编号', field: 'id', visible: false, align: 'center', valign: 'middle', width:"50px", sortable : "true"},
        {title: '人员姓名', field: 'name', visible: true, align: 'center', valign: 'middle', sortable : "false"},
        {title: '企业名称', field: 'companyName', visible: true, align: 'center', valign: 'middle', sortable : "false"},
        {title: '登记时间', field: 'createtime', visible: true, align: 'center', valign: 'middle', sortable : "true"},
        {title: '工作状态', field: 'workStatusView', visible: true, align: 'center', valign: 'middle', sortable : "true"},
        {title: '结算周期', field: 'billCycle', visible: true, align: 'center', valign: 'middle', sortable : "true"},
        {title: '考勤类型', field: 'billingCycleView', visible: true, align: 'center', valign: 'middle', sortable : "true"},
        {title: '结算价格(元)', field: 'billPrice', visible: true, align: 'center', valign: 'middle', sortable : "true"},
        {title: '来源', field: '', field: 'originView', visible: true, align: 'center', valign: 'middle', sortable : "false"},
        {title: '备注', field: '', visible: true, align: 'center', valign: 'middle', sortable : "false"}
    ];
};

/**
 * 检查是否选中
 */
Settlement.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Settlement.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加门店结算
 */
Settlement.openAddSettlement = function () {
    var index = layer.open({
        type: 2,
        title: '添加门店结算',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/settlement/settlement_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看门店结算详情
 */
Settlement.store_openSettlementDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '雇员详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/settlement/store_settlement_update/' + Settlement.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 修改雇员状态页面
 */
Settlement.openSettlementUpdateStatus = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '更新雇员状态详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/settlement/settlement_update_status/' + Settlement.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除门店结算
 */
Settlement.delete = function () {
    var flag = 0;
    if (this.check()) {
        var id = this.seItem.id;
        layer.confirm('删除后无法恢复，确定删除吗？', {
            btn: ['确定','取消']
        }, function(){
            var ajax = new $ax(Feng.ctxPath + "/settlement/delete", function (data) {
                Feng.success("删除成功!");
                Settlement.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("settlementId",id);
            ajax.start();
            layer.close(layer.index);
        }, function(){
            layer.msg('你点击了取消。', {icon: 5}, {
                time: 20000,
                btn: ['关闭']
            });
        });
    }
};

/**
 * 查询门店结算列表
 */
Settlement.search = function () {
    var queryData = {};
    queryData['username'] = $("#username").val();
    queryData['workStatus'] = $("#workStatus").val();
    queryData['billingCycle'] = $("#billingCycle").val();
    queryData['units'] = $("#units").val();

    Settlement.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Settlement.initColumn();
    var table = new BSTable(Settlement.id, "/settlement/list", defaultColunms);
    table.setPaginationType("client");
    Settlement.table = table.init();
});
