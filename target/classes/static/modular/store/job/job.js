/**
 * 招聘信息管理初始化
 */
var Job = {
    id: "JobTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Job.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '编号', field: 'id', visible: false, align: 'center', valign: 'middle' ,sortable: true},
        {title : '序号', visible: true, align: "center", width:"50px", formatter: function (value, row, index) {
                //获取每页显示的数量
                var pageSize=$('#JobTable').bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber=$('#JobTable').bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                // return pageSize * (pageNumber - 1) + index + 1;
                return index + 1;
        }},
        {title: '企业名称', field: 'companyName', visible: true, align: 'center', valign: 'middle' ,sortable: true,
            formatter: function (value, row, index) {
            value = '<a target="_blank" href="'+ Feng.ctxPath +'/company/company_detail/'+ row.companyId + '">' + value + '</a>';
            return value;
        }},
        {title: '区域', field: 'areaName', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '招聘信息', field: 'jobType', visible: true, align: 'center', valign: 'middle' ,sortable: true},
        {title: '报名/总(人数)', field: 'peopreNum', visible: true, align: 'center', valign: 'middle' ,sortable: true},
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
Job.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Job.seItem = selected[0];
        return true;
    }
};

Job.openReview = function() {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '职位审核',
            area: ['1000px', '420px'],
            fix: false,
            maxmin: true,
            content: Feng.ctxPath + '/job/job_review/' + Job.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 店长接单
 */
Job.join = function () {
	if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '店长接单',
            area: ['1000px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/job/job_join/' + Job.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 跳转到详情页
 */
Job.detail = function () {
	if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '职位详情',
            // area: ['800px', '420px'], //宽高
            fix: false,
            maxmin: true,
            content: Feng.ctxPath + '/job/detail/' + Job.seItem.id,
            success: function(layero,index){
                layer.full(index);
            }
        });
        this.layerIndex = index;
    }
};

/**
 * 点击添加招聘信息
 */
Job.openAddJob = function () {
    var index = layer.open({
        type: 2,
        title: '添加招聘信息',
        fix: false,
        maxmin: true,
        content: Feng.ctxPath + '/job/job_add',
        success: function(layero,index){
            layer.full(index);
        }
    });
    this.layerIndex = index;
};

/**
 * 打开查看招聘信息详情
 */
Job.openJobDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑职位',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/job/job_update/' + Job.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除招聘信息
 */
Job.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/job/delete", function (data) {
            Feng.success("删除成功!");
            Job.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("jobId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询招聘信息列表
 */
Job.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['end_time'] = $("#end_time").val();
    Job.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Job.initColumn();
    var table = new BSTable(Job.id, "/job/list", defaultColunms);
    table.setPaginationType("client");
    Job.table = table.init();
});
