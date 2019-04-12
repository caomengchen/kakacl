/**
 * 门店日求职信息管理初始化
 */
var PeopleRecordAuto = {
    id: "PeopleRecordAutoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PeopleRecordAuto.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            /*{title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},*/
            {title : '序号', visible: true, align: "center", width:"50px", formatter: function (value, row, index) {
                //获取每页显示的数量
                var pageSize=$('#PeopleRecordAutoTable').bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber=$('#PeopleRecordAutoTable').bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                // return pageSize * (pageNumber - 1) + index + 1;
                return index + 1;
            }},
            /*{title: '', field: 'storeManagerId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '店长姓名', field: 'storeManagerName', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '店长手机号', field: 'storeManagerPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '人数', field: 'peopleCount', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '区域名称', field: 'areaName', visible: true, align: 'center', valign: 'middle'},
            {title: '门店名称', field: 'storeName', visible: true, align: 'center', valign: 'middle'},
            {title: '招聘日期', field: 'recomeTime', visible: true, align: 'center', valign: 'middle'}/*,
            {title: '创建日期', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'delFlag', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
PeopleRecordAuto.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PeopleRecordAuto.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加门店日求职信息
 */
PeopleRecordAuto.openAddPeopleRecordAuto = function () {
    var index = layer.open({
        type: 2,
        title: '添加门店日求职信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/peopleRecordAuto/peopleRecordAuto_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看门店日求职信息详情
 */
PeopleRecordAuto.openPeopleRecordAutoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '门店日求职信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/peopleRecordAuto/peopleRecordAuto_update/' + PeopleRecordAuto.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除门店日求职信息
 */
PeopleRecordAuto.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/peopleRecordAuto/delete", function (data) {
            Feng.success("删除成功!");
            PeopleRecordAuto.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("peopleRecordAutoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询门店日求职信息列表
 */
PeopleRecordAuto.search = function () {
    var queryData = {};
    queryData['store_manager_name'] = $("#store_manager_name").val();
    PeopleRecordAuto.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PeopleRecordAuto.initColumn();
    var table = new BSTable(PeopleRecordAuto.id, "/peopleRecordAuto/list", defaultColunms);
    table.setPaginationType("client");
    PeopleRecordAuto.table = table.init();
});
