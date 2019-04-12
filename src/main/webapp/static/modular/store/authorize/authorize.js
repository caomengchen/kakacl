/**
 * 门店授权管理初始化
 */
var Authorize = {
    id: "AuthorizeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Authorize.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: false, align: 'center', width:"50px", valign: 'middle',sortable: true},
            {title: '门店名称', field: 'name', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '店长', field: 'sysUserView', visible: true, align: 'center', valign: 'middle'},
            {title: '授权开始时间', field: 'startTime', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '授权结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
            {title: '授权状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '创建者', field: 'createBy', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Authorize.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Authorize.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加门店授权
 */
Authorize.openAddAuthorize = function () {
    var index = layer.open({
        type: 2,
        title: '添加门店授权',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/authorize/authorize_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看门店授权详情
 */
Authorize.openAuthorizeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '门店授权详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/authorize/authorize_update/' + Authorize.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除门店授权
 */
Authorize.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/authorize/delete", function (data) {
            Feng.success("删除成功!");
            Authorize.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("authorizeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询门店授权列表
 */
Authorize.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Authorize.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Authorize.initColumn();
    var table = new BSTable(Authorize.id, "/authorize/list", defaultColunms);
    table.setPaginationType("client");
    Authorize.table = table.init();
});
