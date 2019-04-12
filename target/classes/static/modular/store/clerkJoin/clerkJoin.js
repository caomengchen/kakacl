/**
 * 店员店长管理管理初始化
 */
var ClerkJoin = {
    id: "ClerkJoinTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ClerkJoin.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', width:"50px", valign: 'middle'},
            {title: '店长', field: 'storeManagerNameView', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '店员', field: 'storeClerkNameView', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '创建者', field: 'createBy', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新者', field: 'updateBy', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ClerkJoin.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ClerkJoin.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加店员店长管理
 */
ClerkJoin.openAddClerkJoin = function () {
    var index = layer.open({
        type: 2,
        title: '添加店员店长管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/clerkJoin/clerkJoin_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看店员店长管理详情
 */
ClerkJoin.openClerkJoinDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '店员店长管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/clerkJoin/clerkJoin_update/' + ClerkJoin.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除店员店长管理
 */
ClerkJoin.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/clerkJoin/delete", function (data) {
            Feng.success("删除成功!");
            ClerkJoin.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("clerkJoinId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询店员店长管理列表
 */
ClerkJoin.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ClerkJoin.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ClerkJoin.initColumn();
    var table = new BSTable(ClerkJoin.id, "/clerkJoin/list", defaultColunms);
    table.setPaginationType("client");
    ClerkJoin.table = table.init();
});
