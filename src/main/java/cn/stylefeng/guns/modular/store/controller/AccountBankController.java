package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.store.model.AccountBank;
import cn.stylefeng.guns.modular.store.service.IAccountBankService;

/**
 * 用户银行卡控制器
 *
 * @author fengshuonan
 * @Date 2018-11-30 11:42:39
 */
@Controller
@RequestMapping("/accountBank")
public class AccountBankController extends BaseController {

    private String PREFIX = "/store/accountBank/";

    @Autowired
    private IAccountBankService accountBankService;

    /**
     * 跳转到用户银行卡首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "accountBank.html";
    }

    /**
     * 跳转到添加用户银行卡
     */
    @RequestMapping("/accountBank_add")
    public String accountBankAdd() {
        return PREFIX + "accountBank_add.html";
    }

    /**
     * 跳转到修改用户银行卡
     */
    @RequestMapping("/accountBank_update/{accountBankId}")
    public String accountBankUpdate(@PathVariable Integer accountBankId, Model model) {
        AccountBank accountBank = accountBankService.selectById(accountBankId);
        model.addAttribute("item",accountBank);
        LogObjectHolder.me().set(accountBank);
        return PREFIX + "accountBank_edit.html";
    }

    /**
     * 获取用户银行卡列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return accountBankService.selectList(null);
    }

    /**
     * 新增用户银行卡
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AccountBank accountBank) {
        accountBankService.insert(accountBank);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户银行卡
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer accountBankId) {
        accountBankService.deleteById(accountBankId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户银行卡
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AccountBank accountBank) {
        accountBankService.updateById(accountBank);
        return SUCCESS_TIP;
    }

    /**
     * 用户银行卡详情
     */
    @RequestMapping(value = "/detail/{accountBankId}")
    @ResponseBody
    public Object detail(@PathVariable("accountBankId") Integer accountBankId) {
        return accountBankService.selectById(accountBankId);
    }
}
