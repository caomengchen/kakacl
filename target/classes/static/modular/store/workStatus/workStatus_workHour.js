/**
 * 招聘信息管理初始化
 */
var WorkStatus = {
    id: "WorkHourTable",	//表格id
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
        {title: '员工编号', field: 'uid', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '姓名', field: 'nickName', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '岗位', field: 'pName', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '工号', field: 'jobNum', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '企业名称', field: 'companyName', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '考勤组名称', field: 'title', visible: true, align: 'center', valign: 'middle' ,sortable: true},
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
        {title: '正常工时', field: 'hrsNormal', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '加班工时', field: 'hrsAddtion', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '缺勤工时', field: 'hrsLacked', visible: true, align: 'center', valign: 'middle' ,sortable: true},

    ];
};


/**
 * 查询考勤信息列表
 */
WorkStatus.search = function () {
    var queryData = {};
    queryData['companyName'] = $("#companyName").val();
    queryData['pName'] = $("#pName").val();
    queryData['nickName'] = $("#nickName").val();
    queryData['jobNum'] = $("#jobNum").val();
    queryData['startTime'] = $("#startTime").val();
    queryData['endTime'] = $("#endTime").val();
    WorkStatus.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = WorkStatus.initColumn();
    var table = new BSTable(WorkStatus.id, "/workStatus/statisticalWorkingHourList", defaultColunms);
    table.setPaginationType("client");
    WorkStatus.table = table.init();
});
