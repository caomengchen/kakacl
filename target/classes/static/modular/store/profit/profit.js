/**
 * 利润核算管理初始化
 */
var Profit = {
    id: "ProfitTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Profit.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: false, align: 'center', valign: 'middle',sortable: true},
            {title : '序号', visible: true, align: "center", width:"50px", formatter: function (value, row, index) {
                //获取每页显示的数量
                var pageSize=$('#ProfitTable').bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber=$('#ProfitTable').bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                //return pageSize * (pageNumber - 1) + index + 1;
                return index + 1;
            }},
            /*{title: '门店主键', field: 'storeId', visible: true, align: 'center', valign: 'middle',sortable: true},*/
            {title: '门店收益（元）', field: 'storeProfit', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '年份', field: 'year', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '月份', field: 'month', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle',sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Profit.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Profit.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加利润核算
 */
Profit.openAddProfit = function () {
    var index = layer.open({
        type: 2,
        title: '添加利润核算',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/profit/profit_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看利润核算详情
 */
Profit.openProfitDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '利润核算详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/profit/profit_update/' + Profit.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除利润核算
 */
Profit.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/profit/delete", function (data) {
            Feng.success("删除成功!");
            Profit.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("profitId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询利润核算列表
 */
Profit.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Profit.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Profit.initColumn();
    var table = new BSTable(Profit.id, "/profit/list", defaultColunms);
    table.setPaginationType("client");
    Profit.table = table.init();
});
