/**
 * 店员店长管理管理初始化
 */
var StationRelation = {
    id: "StationRelationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
StationRelation.initColumn = function () {
    return [
            {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', width:"50px", valign: 'middle'},
            {title: '驻厂人员编号', field: 'userid', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '公司编号', field: 'companyid', visible: true, align: 'center', valign: 'middle'},
            {title: '驻厂人员', field: 'name', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '公司名称', field: 'companyname', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
StationRelation.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        StationRelation.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加店员店长管理
 */
StationRelation.openAddClerkJoin = function () {
    var index = layer.open({
        type: 2,
        title: '添加店员店长管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/stationed/Relationadd'
    });
    this.layerIndex = index;
};


StationRelation.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/stationed/deleteRelation", function (data) {
            Feng.success("删除成功!");
            StationRelation.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询店员店长管理列表
 */
StationRelation.search = function () {
    var queryData = {};
    queryData['name'] = $("#condition").val();
    StationRelation.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = StationRelation.initColumn();
    var table = new BSTable(StationRelation.id, "/stationed/getList", defaultColunms);
    table.setPaginationType("client");
    StationRelation.table = table.init();
});
