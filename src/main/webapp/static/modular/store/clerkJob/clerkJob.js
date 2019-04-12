/**
 * 店员职位管理初始化
 */
var ClerkJob = {
    id: "ClerkJobTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ClerkJob.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '工作职位编号', field: 'id', visible: false, align: 'center', width:"50px", valign: 'middle' ,sortable: true},
        {title: '店员职位编号', field: 'clerkjobIdView', width:"50px", visible: false, align: 'center', valign: 'middle' ,sortable: true},
        {title : '序号', visible: true, align: "center", width:"50px",formatter: function (value, row, index) {
                //获取每页显示的数量
                var pageSize=$('#ClerkJobTable').bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber=$('#ClerkJobTable').bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                return index + 1;
        }},
        {title: '企业名称', field: 'companyName', visible: true, align: 'center', valign: 'middle' ,sortable: true,
            formatter: function (value, row, index) {
                console.info(row);
                value = '<a target="_blank" href="'+ Feng.ctxPath +'/company/company_detail/'+ row.companyId + '">' + value + '</a>';
                return value;
            }},
        {title: '区域', field: 'areaName', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '招聘信息', field: 'jobType', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '人数', field: 'peopreNum', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '截止日期', field: 'endTime', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '发薪模式', field: 'employeeCycle', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '补贴方式', field: 'billingCycle', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '补贴周期', field: 'allowanceCycle', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '考勤方式', field: 'unit', visible: true, align: 'center', valign: 'middle' ,sortable: false},
        {title: '补贴价格(元)', field: 'allowancePrice', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle' ,sortable: false}
    ];
};

/**
 * 检查是否选中
 */
ClerkJob.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ClerkJob.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加店员职位
 */
ClerkJob.openAddClerkJob = function () {
    var index = layer.open({
        type: 2,
        title: '添加店员职位',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/clerkJob/clerkJob_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看店员职位详情
 */
ClerkJob.openClerkJobDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '店员职位详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/clerkJob/clerkJob_update/' + ClerkJob.seItem.id
        });
        this.layerIndex = index;
    }
};

ClerkJob.detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '店员职位详情',
            fix: false,
            maxmin: true,
            content: Feng.ctxPath + '/clerkJob/clerkJobDetail/' + ClerkJob.seItem.clerkjobIdView,
            success: function(layero,index){
            	layer.full(index);
        	 }
        });
        this.layerIndex = index;
    }
};

/**
 * 删除店员职位
 */
ClerkJob.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/clerkJob/delete", function (data) {
            Feng.success("删除成功!");
            ClerkJob.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("clerkJobId",this.seItem.clerkjobIdView);
        ajax.start();
    }
};

/**
 * 查询店员职位列表
 */
ClerkJob.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ClerkJob.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ClerkJob.initColumn();
    var table = new BSTable(ClerkJob.id, "/clerkJob/list", defaultColunms);
    table.setPaginationType("client");
    ClerkJob.table = table.init();
});
