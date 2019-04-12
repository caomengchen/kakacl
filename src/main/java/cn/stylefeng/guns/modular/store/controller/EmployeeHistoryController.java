package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.modular.store.service.ISettlementService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.store.model.EmployeeHistory;
import cn.stylefeng.guns.modular.store.service.IEmployeeHistoryService;

/**
 * 雇员职位历史控制器
 *
 * @author fengshuonan
 * @Date 2018-12-12 14:16:55
 */
@Controller
@RequestMapping("/employeeHistory")
public class EmployeeHistoryController extends BaseController {

    private String PREFIX = "/store/employeeHistory/";

    @Autowired
    private IEmployeeHistoryService employeeHistoryService;

    @Autowired
    private ISettlementService settlementService;

    /**
     * 跳转到雇员职位历史首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "employeeHistory.html";
    }

    /**
     * 跳转到添加雇员职位历史
     */
    @RequestMapping("/employeeHistory_add")
    public String employeeHistoryAdd() {
        return PREFIX + "employeeHistory_add.html";
    }

    /**
     * 跳转到修改雇员职位历史
     */
    @RequestMapping("/employeeHistory_update/{employeeHistoryId}")
    public String employeeHistoryUpdate(@PathVariable Integer employeeHistoryId, Model model) {
        EmployeeHistory employeeHistory = employeeHistoryService.selectById(employeeHistoryId);
        model.addAttribute("item",employeeHistory);
        LogObjectHolder.me().set(employeeHistory);
        return PREFIX + "employeeHistory_edit.html";
    }

    /**
     * 获取雇员职位历史列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return employeeHistoryService.selectList(null);
    }

    /**
     * 新增雇员职位历史
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(EmployeeHistory employeeHistory) {
        employeeHistoryService.insert(employeeHistory);
        return SUCCESS_TIP;
    }

    /**
     * 删除雇员职位历史
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer employeeHistoryId) {
        employeeHistoryService.deleteById(employeeHistoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改雇员职位历史
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(EmployeeHistory employeeHistory) {
        employeeHistoryService.updateById(employeeHistory);
        return SUCCESS_TIP;
    }

    /**
     * 雇员职位历史详情
     */
    @RequestMapping(value = "/detail/{employeeHistoryId}")
    @ResponseBody
    public Object detail(@PathVariable("employeeHistoryId") Integer employeeHistoryId) {
        return employeeHistoryService.selectById(employeeHistoryId);
    }
}
