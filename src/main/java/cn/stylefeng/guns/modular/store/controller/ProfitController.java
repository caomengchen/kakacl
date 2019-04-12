package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.exception.StoreExceptionEnum;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.store.model.Suggest;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Profit;
import cn.stylefeng.guns.modular.store.service.IProfitService;

/**
 * 利润核算控制器
 *
 * @author fengshuonan
 * @Date 2018-11-27 12:25:28
 */
@Controller
@RequestMapping("/profit")
public class ProfitController extends BaseController {

    private String PREFIX = "/store/profit/";

    @Autowired
    private IProfitService profitService;

    /**
     * 跳转到利润核算首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "profit.html";
    }

    /**
     * 跳转到添加利润核算
     */
    @RequestMapping("/profit_add")
    public String profitAdd() {
        return PREFIX + "profit_add.html";
    }

    /**
     * 跳转到修改利润核算
     */
    @RequestMapping("/profit_update/{profitId}")
    public String profitUpdate(@PathVariable Integer profitId, Model model) {
        Profit profit = profitService.selectById(profitId);
        model.addAttribute("item",profit);
        LogObjectHolder.me().set(profit);
        return PREFIX + "profit_edit.html";
    }

    /**
     * 获取利润核算列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        // 仅展示自己门店的利润
        ShiroUser user = ShiroKit.getUser();
        Wrapper<Profit> params  = null;
        if(user.getRoleList().contains(GlobalNumber.INT_STORE_MANAGER_6)) {
            params = new EntityWrapper<Profit>().like("store_id", user.getId().toString());
        } else {
            throw new ServiceException(BizExceptionEnum.NO_PERMITION);
        }
        return profitService.selectList(new EntityWrapper<Profit>().like("store_id", user.getId().toString()).orderBy("id", false));
    }

    /**
     * 新增利润核算
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Profit profit) {
        profitService.insert(profit);
        return SUCCESS_TIP;
    }

    /**
     * 删除利润核算
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer profitId) {
        profitService.deleteById(profitId);
        return SUCCESS_TIP;
    }

    /**
     * 修改利润核算
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Profit profit) {
        profitService.updateById(profit);
        return SUCCESS_TIP;
    }

    /**
     * 利润核算详情
     */
    @RequestMapping(value = "/detail/{profitId}")
    @ResponseBody
    public Object detail(@PathVariable("profitId") Integer profitId) {
        return profitService.selectById(profitId);
    }
}
