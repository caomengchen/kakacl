/**
 * 职位信息管理初始化
 */
var Position = {
    id: "PositionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Position.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true, formatter: function(value, row, index){
                var arr = sessionStorage.getItem('position_download_ids').split(",");
                for(var i = 0; i < arr.length; i ++) {
                    if (row.id == arr[i])
                        return {
                            //disabled : true,//设置是否可用
                            checked : true
                        };
                }
                return value;
            }},
            {title: '编号', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '企业id', field: 'companyId', visible: false, align: 'center', valign: 'middle'},
            {title : '序号', visible: true, align: "center", width:"50px", formatter: function (value, row, index) {
                //获取每页显示的数量
                var pageSize=$('#PositionTable').bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber=$('#PositionTable').bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                //return pageSize * (pageNumber - 1) + index + 1;
                return index + 1;
            }},
            {title: '企业名称', field: 'companyName', visible: true, align: 'center', valign: 'middle',
                formatter: function (value, row, index) {
                value = '<a target="_blank" href="'+ Feng.ctxPath +'/company/company_detail/'+ row.companyId + '">' + value + '</a>';
                return value;
            }},
            {title: '职位名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '职位描述', field: 'info', visible: true, align: 'center', valign: 'middle'},
            {title: '福利待遇', field: 'welfare', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Position.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Position.seItem = selected[0];
        return true;
    }
};

var ids = new Array();
sessionStorage.setItem('position_download_ids', '');
Position.confirmChecked = function() {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Position.seItem = selected[0];
        for(var i = 0; i < selected.length; i ++){
            ids[i] = selected[i].id;
        }
        sessionStorage.setItem("position_download_ids", ids + "," + sessionStorage.getItem("position_download_ids"));
        console.info("position_confirm_ids ... ...");
        console.info(sessionStorage.getItem('position_download_ids'));
        return true;
    }
};

Position.download = function() {
    console.info('download ... ...' + uniq(sessionStorage.getItem('position_download_ids')));
    sessionStorage.setItem('position_download_ids', uniq(sessionStorage.getItem('position_download_ids')));
    window.open(Feng.ctxPath + "/position/download/" + sessionStorage.getItem('position_download_ids') + "?" + "filename=" + "job.xls");
};

function uniq(array){
   /* var temp = [];
    for(var i = 0; i < array.length; i++) {
        //如果当前数组的第i项在当前数组中第一次出现的位置是i，才存入数组；否则代表是重复的
        if(array.indexOf(array[i]) == i){
            temp.push(array[i])
        }
    }*/
    return array;
}

/**
 * 点击添加职位信息
 */
Position.openAddPosition = function () {
    var index = layer.open({
        type: 2,
        title: '添加职位信息',
        // area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/position/position_add',
        success: function(layero,index){
            layer.full(index);
        }
    });
    this.layerIndex = index;
};

/**
 * 打开查看职位信息详情
 */
Position.openPositionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '职位信息详情',
            //area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/position/position_update/' + Position.seItem.id,
            success: function(layero,index){
                layer.full(index);
            }
        });
        this.layerIndex = index;
    }
};

/**
 * 删除职位信息
 */
Position.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/position/delete", function (data) {
            Feng.success("删除成功!");
            Position.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("positionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询职位信息列表
 */
Position.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['companyName'] = $("#companyName").val();
    Position.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Position.initColumn();
    var table = new BSTable(Position.id, "/position/list", defaultColunms);
    table.setPaginationType("client");
    Position.table = table.init();
});
