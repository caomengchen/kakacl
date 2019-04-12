/**
 * 门店授权管理初始化
 */
var Agent = {
    id: "AgentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Agent.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '编号', field: 'id', visible: true, align: 'center', width:"50px", valign: 'middle',sortable: true},
        {title: '代理商名称', field: 'name', visible: true, align: 'center', valign: 'middle',sortable: true},
        {title: '代理商状态', field: 'status', visible: true, align: 'center', valign: 'middle',
            formatter:function (value,row,index ) {
                if (value == 0) {
                    value = "创建"
                } else if (value == 1) {
                    value = "激活"
                } else if (value == 2) {
                    value = "冻结"
                }
                return value;
            }
        },
        {title: '授权开始时间', field: 'createTime', visible: true, align: 'center', valign: 'middle',sortable: true},
        {title: '最后修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
        {title: '授权者ID', field: 'userid', visible: true, align: 'center', valign: 'middle'},
        {title: '授权者', field: 'username', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Agent.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Agent.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加门店授权
 */
Agent.openAddAgent = function () {
    var index = layer.open({
        type: 2,
        title: '添加代理商',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/agent/agent_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看门店授权详情
 */
Agent.openAgentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '门店授权详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/agent/agent_update' + Agent.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除门店授权
 */
Agent.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/agent/delete", function (data) {
            Feng.success("删除成功!");
            Agent.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询门店授权列表
 */
Agent.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    Agent.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Agent.initColumn();
    var table = new BSTable(Agent.id, "/agent/getList", defaultColunms);
    table.setPaginationType("client");
    Agent.table = table.init();
});
