/**
 * 初始化招聘信息详情对话框
 */
var JobInfoDlg = {
    // ^\d+(\+\d+)?$ 匹配 1 和 1+1 的正则
    jobInfoData : {},
    validateFields: {
        allowancePrice: {
            validators: {
                notEmpty: {
                    message: '补贴价格不能为空'
                },numeric: {
                    message: '补贴价格必须是数字，支持返费一期'
                }
            }
        },
        allowanceCycle: {
            validators: {
                notEmpty: {
                    message: '补贴周期不能为空'
                },numeric: {
                    message: '补贴周期必须是数字，支持返费一期'
                }
            }
        },
        numberPeople: {
            validators: {
                notEmpty: {
                    message: '招聘人数不能为空'
                },numeric: {
                    message: '招聘人数必须是数字'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
JobInfoDlg.clearData = function() {
    this.jobInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
JobInfoDlg.set = function(key, val) {
    this.jobInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
JobInfoDlg.get = function(key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
JobInfoDlg.close = function() {
    parent.layer.close(window.parent.Job.layerIndex);
};

/**
 * 收集数据
 */
JobInfoDlg.collectData = function() {
    //this.jobInfoData['welfare'] = JobInfoDlg.welfare.txt.html();
    //this.jobInfoData['condition'] = JobInfoDlg.condition.txt.html();
    this
    .set('companyId')
    .set('positionId')
    .set('industryId')
    .set('stationId')
    .set('employeeCycle')
    .set('numberPeople')
    .set('billingCycle')
    .set('allowancePrice')
    .set("allowanceCycle")
    .set('unit')
    .set('endTime')
    .set("condition")
    .set("welfare")
    .set('billingCycle')
    .set('billPrice')
    .set('billCycle');

};

/**
 * 验证数据是否为空
 */
JobInfoDlg.validate = function () {
    $('#jobForm').data("bootstrapValidator").resetForm();
    $('#jobForm').bootstrapValidator('validate');
    return $("#jobForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
JobInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/job/add", function(data){
        Feng.success("添加成功!");
        window.parent.Job.table.refresh();
        JobInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.jobInfoData);
    ajax.start();
};

/**
 * editJoin 店长接单
 */
JobInfoDlg.editJoin = function() {
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/job/join", function(data){
        Feng.success("接单成功!");
        window.parent.Job.table.refresh();
        JobInfoDlg.close();
    },function(data){
        Feng.error("接单失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.jobInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
JobInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/job/update", function(data){
        Feng.success("修改成功!");
        window.parent.Job.table.refresh();
        JobInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.jobInfoData);
    ajax.start();
};

$(function() {
    Feng.initValidator("jobForm", JobInfoDlg.validateFields);
});
