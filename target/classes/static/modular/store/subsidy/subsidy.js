/**
 * 补贴进度管理初始化
 */
var Subsidy = {
    id: "SubsidyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Subsidy.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '补贴类型：1就业补贴', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '补贴编号', field: 'no', visible: true, align: 'center', valign: 'middle'},
            {title: '甲方主键-门店', field: 'partyA', visible: true, align: 'center', valign: 'middle'},
            {title: '就业者-乙方身份证主键', field: 'partyBIdCardId', visible: true, align: 'center', valign: 'middle'},
            {title: '乙方银行卡号码', field: 'partBBackCard', visible: true, align: 'center', valign: 'middle'},
            {title: '乙方手机号码', field: 'partyBPhoneNum', visible: true, align: 'center', valign: 'middle'},
            {title: '上班公司主键', field: 'companyId', visible: true, align: 'center', valign: 'middle'},
            {title: '工种', field: 'workType', visible: true, align: 'center', valign: 'middle'},
            {title: '开始工作日期,按录入为准', field: 'startWarkDate', visible: true, align: 'center', valign: 'middle'},
            {title: '最后结算时间', field: 'endWarkDate', visible: true, align: 'center', valign: 'middle'},
            {title: '工作类型,结算模式，这里为小时，日，周，月等', field: 'workPeriod', visible: true, align: 'center', valign: 'middle'},
            {title: '发薪模式,结算方式，默认为1，底薪加班', field: 'employeeCycle', visible: true, align: 'center', valign: 'middle'},
            {title: '补贴方式,结算周期 默认为定期返', field: 'billingCycle', visible: true, align: 'center', valign: 'middle'},
            {title: '补贴价格(金额)', field: 'subsidyPrice', visible: true, align: 'center', valign: 'middle'},
            {title: '要求期限', field: 'period', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'remakes', visible: true, align: 'center', valign: 'middle'},
            {title: '删除标记：0，未删除；1，删除', field: 'delFlag', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Subsidy.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Subsidy.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加补贴进度
 */
Subsidy.openAddSubsidy = function () {
    var index = layer.open({
        type: 2,
        title: '添加补贴进度',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/subsidy/subsidy_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看补贴进度详情
 */
Subsidy.openSubsidyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '补贴进度详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/subsidy/subsidy_update/' + Subsidy.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除补贴进度
 */
Subsidy.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/subsidy/delete", function (data) {
            Feng.success("删除成功!");
            Subsidy.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("subsidyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询补贴进度列表
 */
Subsidy.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Subsidy.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Subsidy.initColumn();
    var table = new BSTable(Subsidy.id, "/subsidy/list", defaultColunms);
    table.setPaginationType("client");
    Subsidy.table = table.init();
});
