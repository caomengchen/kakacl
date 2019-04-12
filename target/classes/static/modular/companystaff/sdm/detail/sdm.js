/**
 * 意见建议管理初始化
 */
var Suggest = {
    id: "SuggestTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Suggest.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '主键', field: 'id', visible: false, align: 'center', width:"50px", valign: 'middle'},
        {title: '姓名', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '性别', field: 'content', visible: true, align: 'center', valign: 'middle'},
        {title: '工号', field: 'status', visible: true, align: 'center', valign: 'middle'},
        {title: '入职日期', field: 'remakes', visible: true, align: 'center', valign: 'middle'},
        {title: '线号', field: 'createBy', visible: true, align: 'center', valign: 'middle',sortable: true},
        {title: '组别', field: 'createBy', visible: true, align: 'center', valign: 'middle',sortable: true},
        {title: '班别', field: 'createBy', visible: true, align: 'center', valign: 'middle',sortable: true},
        {title: '籍贯', field: 'createBy', visible: true, align: 'center', valign: 'middle',sortable: true},
        {title: '电话', field: 'createBy', visible: true, align: 'center', valign: 'middle',sortable: true},
        {title: '备注', field: 'createBy', visible: true, align: 'center', valign: 'middle',sortable: true},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle' ,sortable: true},
    ];
};

/**
 * 检查是否选中
 */
Suggest.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Suggest.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加意见建议
 */
Suggest.openAddSuggest = function () {
    var index = layer.open({
        type: 2,
        title: '添加意见建议',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/suggest/suggest_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看意见建议详情
 */
Suggest.openSuggestDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '意见建议详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/suggest/suggest_update/' + Suggest.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除意见建议
 */
Suggest.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/suggest/delete", function (data) {
            Feng.success("删除成功!");
            Suggest.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("suggestId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询意见建议列表
 */
Suggest.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Suggest.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Suggest.initColumn();
    var table = new BSTable(Suggest.id, "/suggest/list", defaultColunms);
    table.setPaginationType("client");
    Suggest.table = table.init();
});
