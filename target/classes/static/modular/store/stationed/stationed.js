/**
 * 招聘信息管理初始化
 */
var Stationed = {
    id: "StaffTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Stationed.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '员工姓名', field: 'nickname', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '身份证号码', field: 'card_no', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '员工工号', field: 'job_num', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle' ,sortable: true,
            formatter:function (value,row,index ) {
                if (value == 1) {
                    value = "男"
                } else if (value == 2) {
                    value = "女"
                }
                return value;
            }
        },
        {title: '联系电话', field: 'phone', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '公司编号', field: 'company_id', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '公司名称', field: 'company_name', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '员工状态', field: 'work_view', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '来源中介', field: 'name', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '开户行', field: 'bank_name', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '卡号', field: 'bank_card', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '返费价格', field: 'returnFee', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '返费打款日期', field: 'returnFee_time', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '面试日期', field: 'interview_data', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '入职日期', field: 'report_data', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '离职日期', field: 'leave_data', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '离职原因', field: 'leave_reason', visible: true, align: 'center', valign: 'middle' ,sortable: true,
            formatter:function (value,row,index ) {
                if (value == 1) {
                    value = "正常离职"
                } else if (value == 2) {
                    value = "自动离职"
                }else if(value == 3){
                    value="辞退"
                }else if(value == 4){
                    value="其他原因"
                }
                return value;
            }
        },

        {title: '扣款原因', field: 'deduction_reason', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle' ,sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Stationed.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Stationed.seItem = selected[0];
        return true;
    }
};


Stationed.openStaffUpdateStatus = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '更新员工状态详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/stationed/stationed_update_status/' + Stationed.seItem.id
        });
        this.layerIndex = index;
    }
};



/**
 * 查询招聘信息列表
 */
Stationed.search = function () {
    var queryData = {};
    queryData['companyname'] = $("#companyname").val();
    queryData['nickname'] = $("#nickname").val();
    queryData['sourcename'] = $("#sourcename").val();
    queryData['startTime'] = $("#start_time").val();
    queryData['endTime'] = $("#end_time").val();
    queryData['workStatus'] = $("#workStatus").val();
    console.log($("#workStatus").val())
    Stationed.table.refresh({query: queryData});
};
Stationed.download = function() {
    window.open(Feng.ctxPath + "/stationed/download/staffInformation.xls");
};

$(function () {
    var defaultColunms = Stationed.initColumn();
    var table = new BSTable(Stationed.id, "/stationed/getStaffList", defaultColunms);
    table.setPaginationType("client");
    Stationed.table = table.init();
});
