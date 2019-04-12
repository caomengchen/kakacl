/**
 * 店员店长管理管理初始化
 */
var ClerkManager = {
    id: "ClerkManagerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ClerkManager.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: false, align: 'center', width:"50px", valign: 'middle'},
            {title : '序号', visible: true, align: "center", width:"50px",formatter: function (value, row, index) {
                //获取每页显示的数量
                var pageSize=$('#ClerkManagerTable').bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber=$('#ClerkManagerTable').bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                return index + 1;
            }},
            {title: '店员', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '账户', field: 'account', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号码', field: 'phone', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',sortable: true,formatter: function (value, row, index) {
                if(value == 1) {
                    return "正常";
                } else if(value == 2) {
                    return "冻结";
                } else if(value ==3) {
                    return "已删除";
                }
                return "未知";
            }}
    ];
};

/**
 * 检查是否选中
 */
ClerkManager.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ClerkManager.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加店员店长管理
 */
ClerkManager.openAddClerkManagger = function () {
    var index = layer.open({
        type: 2,
        title: '添加店员店长管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/clerkManager/clerkManager_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看店员店长管理详情
 */
ClerkManager.openClerkManaggeretail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '店员店长管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/clerkManager/clerkManager_update/' + ClerkManagger.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除店员店长管理
 */
ClerkManager.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/clerkManager/delete", function (data) {
            Feng.success("删除成功!");
            ClerkManagger.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userId",this.seItem.id);
        ajax.start();
    }
};

ClerkManager.freeze = function () {
    if (this.check()) {
        var id = this.seItem.id;
        layer.confirm('您确认冻结吗？', {
            btn : [ '确定', '取消' ]
        }, function(index) {
            layer.close(index);
            var ajax = new $ax(Feng.ctxPath + "/clerkManager/freeze", function (data) {
                Feng.success("冻结成功!");
                ClerkManager.table.refresh();
            }, function (data) {
                Feng.error("冻结失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userId", id);
            ajax.start();
        });
    }
};

ClerkManager.unfreeze = function () {
    if (this.check()) {
        var id = this.seItem.id;
        layer.confirm('您确认解冻吗？', {
            btn : [ '确定', '取消' ]
        }, function(index) {
            layer.close(index);
            var ajax = new $ax(Feng.ctxPath + "/clerkManager/unfreeze", function (data) {
                Feng.success("解冻成功!");
                ClerkManager.table.refresh();
            }, function (data) {
                Feng.error("解冻失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userId", id);
            ajax.start();
        });
    }
};

/**
 * 查询店员店长管理列表
 */
ClerkManager.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    queryData['phone'] = $("#phone").val();
    queryData['account'] = $("#account").val();
    ClerkManagger.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ClerkManager.initColumn();
    var table = new BSTable(ClerkManager.id, "/clerkManager/list", defaultColunms);
    table.setPaginationType("client");
    ClerkManager.table = table.init();
});
