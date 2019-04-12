/**
 * 公司信息管理初始化
 */
var Company = {
    id: "CompanyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Company.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '公司名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '企业行业', field: 'industryId', visible: true, align: 'center', valign: 'middle'},
            {title: '企业类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '企业人数范围下限', field: 'countBase', visible: true, align: 'center', valign: 'middle'},
            {title: '企业人数范围上限', field: 'countTop', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人名称', field: 'contactName', visible: true, align: 'center', valign: 'middle'},
            {title: '联系电话', field: 'contactPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '省', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '城市', field: 'city', visible: true, align: 'center', valign: 'middle'},
            {title: '区域', field: 'area', visible: true, align: 'center', valign: 'middle'},
            {title: '详细地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '企业介绍图', field: 'image', visible: true, align: 'center', valign: 'middle'},
            {title: '公司信息', field: 'info', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Company.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Company.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加公司信息
 */
Company.openAddCompany = function () {
    var index = layer.open({
        type: 2,
        title: '添加公司信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/company/company_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看公司信息详情
 */
Company.openCompanyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '公司信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/company/company_update/' + Company.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除公司信息
 */
Company.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/company/delete", function (data) {
            Feng.success("删除成功!");
            Company.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("companyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询公司信息列表
 */
Company.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Company.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Company.initColumn();
    var table = new BSTable(Company.id, "/company/list", defaultColunms);
    table.setPaginationType("client");
    Company.table = table.init();
});
