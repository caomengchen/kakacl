/**
 *test详情对话框（可用于添加和修改对话框）
 */
var TestInfoDlg = {
    TestInfoData: {},
    validateFields: {
        bbb: {
            validators: {
                notEmpty: {
                    message: 'bbb不能为空'
                }
            }
        }

    }
};

/**
 * 清除数据
 */
TestInfoDlg.clearData = function () {
    this.TestInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TestInfoDlg.set = function (key, value) {
    this.TestInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TestInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
TestInfoDlg.close = function () {
    parent.layer.close(window.parent.Test.layerIndex);
};




/**
 * 收集数据
 */
TestInfoDlg.collectData = function () {
    this.set('aaa').set('bbb');
};

/**
 * 验证数据是否为空
 */
TestInfoDlg.validate = function () {
    $('#testInfoForm').data("bootstrapValidator").resetForm();
    $('#testInfoForm').bootstrapValidator('validate');
    return $("#testInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
TestInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/test/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Test.table.refresh();
        TestInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.TestInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
TestInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/test/edit", function (data) {
        Feng.success("修改成功!");
        window.parent.Test.table.refresh();
        TestInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.TestInfoData);
    ajax.start();
};

$(function () {
    Feng.initValidator("testInfoForm", TestInfoDlg.validateFields);


});
