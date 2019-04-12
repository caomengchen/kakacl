/**
 *test
 */
var Test = {
    id: "testTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid:0
};

/**
 * 初始化表格的列
 */
Test.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'aaa', visible: false, align: 'center', valign: 'middle'},
        {title: 'bbb', field: 'bbb', align: 'center', valign: 'middle', sortable: true}
        ]
    return columns;
};


/**
 * 检查是否选中
 */
Test.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    console.log("seItem:"+selected);
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Test.seItem = selected[0];
        console.log("seItem:"+Test.seItem.id);
        return true;
    }
};

/**
 * 点击添加test
 */
Test.openAddTest = function () {
    var index = layer.open({
        type: 2,
        title: '添加test',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/test/test_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 */
Test.openChangeTest = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改test',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/test/test_edit/' + this.seItem.aaa

        });
        console.log(index);
        this.layerIndex = index;
    }
};

/**
 * 删除test
 */
Test.delTest = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/test/remove", function () {
                Feng.success("删除成功!");
                Test.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("aaa", Test.seItem.aaa);
            ajax.start();
        };

        Feng.confirm("是否删除角test " + Test.seItem.bbb + "?",operation);
    }
};



/**
 * 搜索角色
 */
Test.search = function () {
    var queryData = {};
    queryData['bbb'] = $("#bbb").val();
    console.log("seItem:"+queryData);
    Test.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = Test.initColumn();
    var table = new BSTable(Test.id, "/test/list", defaultColunms);
    table.setPaginationType("client");
    table.init();
    Test.table = table;
});
