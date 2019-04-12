package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Subsidy;
import cn.stylefeng.guns.modular.store.service.ISubsidyService;

/**
 * 补贴进度控制器
 *
 * @author fengshuonan
 * @Date 2018-11-27 12:08:37
 */
@Controller
@RequestMapping("/subsidy")
public class SubsidyController extends BaseController {

    private String PREFIX = "/store/subsidy/";

    @Autowired
    private ISubsidyService subsidyService;

    /**
     * 跳转到补贴进度首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "subsidy.html";
    }

    /**
     * 跳转到添加补贴进度
     */
    @RequestMapping("/subsidy_add")
    public String subsidyAdd() {
        return PREFIX + "subsidy_add.html";
    }

    /**
     * 跳转到修改补贴进度
     */
    @RequestMapping("/subsidy_update/{subsidyId}")
    public String subsidyUpdate(@PathVariable Integer subsidyId, Model model) {
        Subsidy subsidy = subsidyService.selectById(subsidyId);
        model.addAttribute("item",subsidy);
        LogObjectHolder.me().set(subsidy);
        return PREFIX + "subsidy_edit.html";
    }

    /**
     * 获取补贴进度列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<Subsidy> sList = new ArrayList<>();
        sList = subsidyService.selectList(null);
        return sList;
    }

    /**
     * 新增补贴进度
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Subsidy subsidy) {
        subsidyService.insert(subsidy);
        return SUCCESS_TIP;
    }

    /**
     * 删除补贴进度
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer subsidyId) {
        subsidyService.deleteById(subsidyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改补贴进度
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Subsidy subsidy) {
        subsidyService.updateById(subsidy);
        return SUCCESS_TIP;
    }

    /**
     * 补贴进度详情
     */
    @RequestMapping(value = "/detail/{subsidyId}")
    @ResponseBody
    public Object detail(@PathVariable("subsidyId") Integer subsidyId) {
        return subsidyService.selectById(subsidyId);
    }
}
