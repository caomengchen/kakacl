/**
 * 招聘信息管理初始化
 */
var WorkCheck = {
    id: "WorkCheckTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WorkCheck.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '别名', field: 'name', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '班次', field: 'title', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '岗位名称', field: 'workname', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '加班类型', field: 'extType', visible: true, align: 'center', valign: 'middle' ,sortable: true,
            formatter:function (value,row,index ) {
                if (value == 0) {
                    value = "普通加班"
                } else if (value == 1) {
                    value = "周末加班"
                }else{
                    value ="节假日加班"
                }
                return value;
            }
        },
        {title: '首次上班时间', field: 'fSTime', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '首次下班时间', field: 'fETime', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '二次上班时间', field: 'sSTime', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '二次下班时间', field: 'sETime', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '加班起始时间', field: 'ebegin', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '加班结束时间', field: 'eend', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '公司编号', field: 'companyId', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '公司名称', field: 'companyname', visible: true, align: 'center', valign: 'middle' ,sortable: true},

    ];
};

/**
 * 检查是否选中
 */
WorkCheck.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WorkCheck.seItem = selected[0];
        return true;
    }
};



/*跳转到修改页面*/

WorkCheck.edit = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改考勤组信息',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/workCheck/toEdit/' + WorkCheck.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 点击添加店员店长管理
 */
WorkCheck.openAddWorkCheck = function () {
    var index = layer.open({
        type: 2,
        title: '添加考勤组',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/workCheck/addWorkCheck'
    });
    this.layerIndex = index;
};
/**
 * 删除招聘信息
 */
WorkCheck.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/workCheck/delete", function (data) {
            Feng.success("删除成功!");
            WorkCheck.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询招聘信息列表
 */
WorkCheck.search = function () {
    var queryData = {};
    queryData['companyname'] = $("#companyname").val();
    queryData['workname'] = $("#workname").val();
    queryData['fSTime'] = $("#fSTime").val();
    queryData['fETime'] = $("#fETime").val();
    WorkCheck.table.refresh({query: queryData});
};
WorkCheck.download = function() {
    window.open(Feng.ctxPath + "/stationed/download/staffInformation.xls");
};

$(function () {
    var defaultColunms = WorkCheck.initColumn();
    var table = new BSTable(WorkCheck.id, "/workCheck/getList", defaultColunms);
    table.setPaginationType("client");
    WorkCheck.table = table.init();
});
