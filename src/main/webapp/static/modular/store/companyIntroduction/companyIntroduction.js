/**
 * 企业介绍管理初始化
 */
var CompanyIntroduction = {
    id: "CompanyIntroductionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CompanyIntroduction.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '企业编号', field: 'id', visible: false, align: 'center', valign: 'middle'},
           {title: '企业介绍编号', field: 'introductionId', visible: false, align: 'center', valign: 'middle'},
            {title : '序号', visible: true, align: "center", width:"50px", formatter: function (value, row, index) {
                //获取每页显示的数量
                var pageSize=$('#CompanyIntroductionTable').bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber=$('#CompanyIntroductionTable').bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                //return pageSize * (pageNumber - 1) + index + 1;
                return index + 1;
            }},
            {title: '企业名称', field: 'companyName', visible: true, align: 'center', valign: 'middle'},
            {title: '介绍内容', field: 'content', visible: true, align: 'center', valign: 'middle' ,formatter: function (value, row, index) {
                return value;
            }},
            /*{title: '介绍内容图片', field: 'contentImg', visible: true, align: 'center', valign: 'middle'},*/
           /* {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},*/
            /*{title: '创建者', field: 'createBy', visible: true, align: 'center', valign: 'middle'},*/
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CompanyIntroduction.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CompanyIntroduction.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加企业介绍
 */
CompanyIntroduction.openAddCompanyIntroduction = function () {
    var index = layer.open({
        type: 2,
        title: '添加企业介绍',
        // area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/companyIntroduction/companyIntroduction_add',
        success: function(layero,index){
            layer.full(index);
        }
    });
    this.layerIndex = index;
};

/**
 * 打开查看企业介绍详情
 */
CompanyIntroduction.openCompanyIntroductionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '企业介绍详情',
            // area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/companyIntroduction/companyIntroduction_detail/' + CompanyIntroduction.seItem.introductionId,
            success: function(layero,index){
                layer.full(index);
            }
        });
        this.layerIndex = index;
    }
};

CompanyIntroduction.openCompanyIntroductionUpdate = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '企业介绍更新',
            // area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/companyIntroduction/companyIntroduction_update/' + CompanyIntroduction.seItem.introductionId + "?companyId=" + CompanyIntroduction.seItem.id,
            success: function(layero,index){
                layer.full(index);
            }
        });
        this.layerIndex = index;
    }
};

/**
 * 删除企业介绍
 */
CompanyIntroduction.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/companyIntroduction/delete", function (data) {
            Feng.success("删除成功!");
            CompanyIntroduction.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("companyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询企业介绍列表
 */
CompanyIntroduction.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CompanyIntroduction.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CompanyIntroduction.initColumn();
    var table = new BSTable(CompanyIntroduction.id, "/companyIntroduction/list", defaultColunms);
    table.setPaginationType("client");
    CompanyIntroduction.table = table.init();
});
