package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.store.model.PeopleRecordAuto;
import cn.stylefeng.guns.modular.store.service.IPeopleRecordAutoService;

import java.util.List;

/**
 * 门店日求职信息控制器
 *
 * @author fengshuonan
 * @Date 2019-01-17 13:51:07
 */
@Controller
@RequestMapping("/peopleRecordAuto")
public class PeopleRecordAutoController extends BaseController {

    private String PREFIX = "/store/peopleRecordAuto/";

    @Autowired
    private IPeopleRecordAutoService peopleRecordAutoService;

    /**
     * 跳转到门店日求职信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "peopleRecordAuto.html";
    }

    /**
     * 跳转到添加门店日求职信息
     */
    @RequestMapping("/peopleRecordAuto_add")
    public String peopleRecordAutoAdd() {
        return PREFIX + "peopleRecordAuto_add.html";
    }

    /**
     * 跳转到修改门店日求职信息
     */
    @RequestMapping("/peopleRecordAuto_update/{peopleRecordAutoId}")
    public String peopleRecordAutoUpdate(@PathVariable Integer peopleRecordAutoId, Model model) {
        PeopleRecordAuto peopleRecordAuto = peopleRecordAutoService.selectById(peopleRecordAutoId);
        model.addAttribute("item",peopleRecordAuto);
        LogObjectHolder.me().set(peopleRecordAuto);
        return PREFIX + "peopleRecordAuto_edit.html";
    }

    /**
     * 获取门店日求职信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String store_manager_name) {
        EntityWrapper entity = new EntityWrapper<PeopleRecordAuto>();
        entity.like("store_manager_name", store_manager_name);
        entity.orderBy("id", false);
        List<PeopleRecordAuto> data = peopleRecordAutoService.selectList(entity);
        return data;
    }

    /**
     * 新增门店日求职信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PeopleRecordAuto peopleRecordAuto) {
        peopleRecordAutoService.insert(peopleRecordAuto);
        return SUCCESS_TIP;
    }

    /**
     * 删除门店日求职信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer peopleRecordAutoId) {
        peopleRecordAutoService.deleteById(peopleRecordAutoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改门店日求职信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PeopleRecordAuto peopleRecordAuto) {
        peopleRecordAutoService.updateById(peopleRecordAuto);
        return SUCCESS_TIP;
    }

    /**
     * 门店日求职信息详情
     */
    @RequestMapping(value = "/detail/{peopleRecordAutoId}")
    @ResponseBody
    public Object detail(@PathVariable("peopleRecordAutoId") Integer peopleRecordAutoId) {
        return peopleRecordAutoService.selectById(peopleRecordAutoId);
    }
}
