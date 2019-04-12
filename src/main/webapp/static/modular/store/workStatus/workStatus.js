/**
 * 招聘信息管理初始化
 */
var WorkStatus = {
    id: "WorkStatusTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WorkStatus.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '编号', field: 'id', visible: false, align: 'center', valign: 'middle' ,sortable: true},
        {title: '姓名', field: 'nickname', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '企业名称', field: 'companyName', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '班次', field: 'title', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '岗位名称', field: 'workName', visible: true, align: 'center', valign: 'middle' ,sortable: true},
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
        {title: '首次上班时间', field: 'bTime1', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '首次下班时间', field: 'eTime1', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '二次上班时间', field: 'bTime2', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '二次下班时间', field: 'eTime2', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '加班起始时间', field: 'bTime3', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '加班结束时间', field: 'eTime3', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '缺勤时间', field: 'hrsLacked', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '加班时间', field: 'hrsAddtion', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '正常工时', field: 'hrsNormal', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle' ,sortable: true},

    ];
};

/**
 * 检查是否选中
 */
WorkStatus.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WorkStatus.seItem = selected[0];
        return true;
    }
};


/*跳转到修改页面*/
WorkStatus.edit = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改考勤组信息',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/workStatus/toEdit/' + WorkStatus.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 点击添加
 */
WorkStatus.openAddWorkStatus = function () {
    var index = layer.open({
        type: 2,
        title: '添加考勤组',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/workStatus/addWorkStatus'
    });
    this.layerIndex = index;
};
/**
 * 删除考勤信息
 */
WorkStatus.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/workStatus/delete", function (data) {
            Feng.success("删除成功!");
            WorkStatus.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};


/*跳转到工资条页面*/
WorkStatus.toWorkingHour = function () {

        var index = layer.open({
            type: 2,
            title: '修改考勤组信息',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/workStatus/toWorkingHour'
        });
        this.layerIndex = index;

};
/**
 * 查询考勤信息列表
 */
WorkStatus.search = function () {
    var queryData = {};
    queryData['companyName'] = $("#companyName").val();
    queryData['workName'] = $("#workName").val();
    queryData['nickName'] = $("#nickName").val();
    queryData['hrsAddtion'] = $("#hrsAddtion").val();
    queryData['hrsNormal'] = $("#hrsNormal").val();
    queryData['extType'] = $("#extType").val();
    queryData['startTime'] = $("#startTime").val();
    queryData['endTime'] = $("#endTime").val();
    WorkStatus.table.refresh({query: queryData});
};
WorkStatus.download = function() {
    window.open(Feng.ctxPath + "/stationed/download/staffInformation.xls");
};

$(function () {
    var defaultColunms = WorkStatus.initColumn();
    var table = new BSTable(WorkStatus.id, "/workStatus/getList", defaultColunms);
    table.setPaginationType("client");
    WorkStatus.table = table.init();
});
