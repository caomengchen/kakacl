/**
 * 人员信息管理初始化
 */
var Account = {
    id: "AccountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Account.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', width:"50px", valign: 'middle'},
            /*{title: '角色 0=普通用户，1=经纪人', field: 'role', visible: true, align: 'center', valign: 'middle'},*/
            /*{title: '', field: 'avatar', visible: true, align: 'center', valign: 'middle'},*/
            {title: '昵称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle'},
            {title: '年龄', field: 'age', visible: true, align: 'center', valign: 'middle'},
            /*{title: '手机号码', field: 'phone', visible: true, align: 'center', valign: 'middle'},*/
            /*{title: '工号', field: 'jobNum', visible: true, align: 'center', valign: 'middle'},*/
            {title: '电子邮件', field: 'email', visible: true, align: 'center', valign: 'middle'},
            /*{title: '省', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '城市', field: 'city', visible: true, align: 'center', valign: 'middle'},
            {title: '区域', field: 'area', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '密码', field: 'password', visible: true, align: 'center', valign: 'middle'},
            {title: '账号状态 0=正常，1=冻结', field: 'status', visible: true, align: 'center', valign: 'middle'},*/
            {title: '求职状态（0=暂不考虑，1=找工作，2=想换工作，3=已在职）', field: 'workStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '工作的企业（停用，使用企业用户表）', field: 'companyId', visible: true, align: 'center', valign: 'middle'}
            /*{title: '职位id', field: 'positionId', visible: true, align: 'center', valign: 'middle'},
            {title: '积分', field: 'points', visible: true, align: 'center', valign: 'middle'},
            {title: '客服id', field: 'serviceId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新状态', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
Account.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Account.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加人员信息
 */
Account.openAddAccount = function () {
    var index = layer.open({
        type: 2,
        title: '添加人员信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/account/account_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看人员信息详情
 */
Account.openAccountDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '人员信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/account/account_update/' + Account.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除人员信息
 */
Account.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/account/delete", function (data) {
            Feng.success("删除成功!");
            Account.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("accountId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询人员信息列表
 */
Account.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Account.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Account.initColumn();
    var table = new BSTable(Account.id, "/account/list", defaultColunms);
    table.setPaginationType("client");
    Account.table = table.init();
});
