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
import cn.stylefeng.guns.core.shiro.ShiroKit;

import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.store.model.Account;
import cn.stylefeng.guns.modular.store.service.IAccountService;

/**
 * 人员信息控制器
 *
 * @author fengshuonan
 * @Date 2018-11-27 12:02:37
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

    private String PREFIX = "/store/account/";

    @Autowired
    private IAccountService accountService;

    /**
     * 跳转到人员信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "account.html";
    }

    /**
     * 跳转到添加人员信息
     */
    @RequestMapping("/account_add")
    public String accountAdd() {
        return PREFIX + "account_add.html";
    }

    /**
     * 跳转到修改人员信息
     */
    @RequestMapping("/account_update/{accountId}")
    public String accountUpdate(@PathVariable Integer accountId, Model model) {
        Account account = accountService.selectById(accountId);
        model.addAttribute("item",account);
        LogObjectHolder.me().set(account);
        return PREFIX + "account_edit.html";
    }

    /**
     * 获取人员信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<Account> aList = new ArrayList<>();
    	if(ShiroKit.isAdmin()) {
    		aList = accountService.selectList(null);
    	}
        return aList;
    }

    /**
     * 新增人员信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Account account) {
        accountService.insert(account);
        return SUCCESS_TIP;
    }

    /**
     * 删除人员信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer accountId) {
        accountService.deleteById(accountId);
        return SUCCESS_TIP;
    }

    /**
     * 修改人员信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Account account) {
        accountService.updateById(account);
        return SUCCESS_TIP;
    }

    /**
     * 人员信息详情
     */
    @RequestMapping(value = "/detail/{accountId}")
    @ResponseBody
    public Object detail(@PathVariable("accountId") Integer accountId) {
        return accountService.selectById(accountId);
    }
}
