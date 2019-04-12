package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IUserService;
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
import cn.stylefeng.guns.modular.system.model.ClerkJoin;
import cn.stylefeng.guns.modular.store.service.IClerkJoinService;

import java.util.*;

/**
 * 店员店长管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-29 11:30:54
 */
@Controller
@RequestMapping("/clerkJoin")
public class ClerkJoinController extends BaseController {

    private String PREFIX = "/store/clerkJoin/";

    @Autowired
    private IClerkJoinService clerkJoinService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到店员店长管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "clerkJoin.html";
    }

    /**
     * 跳转到添加店员店长管理
     */
    @RequestMapping("/clerkJoin_add")
    public String clerkJoinAdd() {
        return PREFIX + "clerkJoin_add.html";
    }

    /**
     * 跳转到修改店员店长管理
     */
    @RequestMapping("/clerkJoin_update/{clerkJoinId}")
    public String clerkJoinUpdate(@PathVariable Integer clerkJoinId, Model model) {
        ClerkJoin clerkJoin = clerkJoinService.selectById(clerkJoinId);
        model.addAttribute("item",clerkJoin);
        LogObjectHolder.me().set(clerkJoin);
        return PREFIX + "clerkJoin_edit.html";
    }

    /**
     * 获取店员店长管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        String storeName = condition;
        Map<String, Object> params = new HashMap<>();
        params.put("name", storeName);
        List<User> uList = userService.selectByMap(params);
        List<Integer> storeManagerIds = new ArrayList<>();
        for(int i = GlobalNumber.INT_FIX_0; i < uList.size(); i ++) {
            Integer id = uList.get(i).getId();
            storeManagerIds.add(id);
        }
        List<ClerkJoin> data = clerkJoinService.selectList(new EntityWrapper<ClerkJoin>().in("store_id", storeManagerIds));
        for(int i = GlobalNumber.INT_FIX_0; i < data.size(); i ++) {
            Long storeManagerId = data.get(i).getStoreId();
            data.get(i).setStoreManagerNameView(userService.selectById(storeManagerId).getName());
            Long clerkId = data.get(i).getStoreClerkId();
            data.get(i).setStoreClerkNameView(userService.selectById(clerkId).getName());
        }
        return data;
    }

    /**
     * 新增店员店长管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ClerkJoin clerkJoin) {
        clerkJoin.setDelFlag(GlobalNumber.INT_DEL_FLAG_0);
        String name = ShiroKit.getUser().getName();
        clerkJoin.setCreateBy(name);
        clerkJoin.setCreateTime(new Date());
        clerkJoinService.insert(clerkJoin);
        return SUCCESS_TIP;
    }

    /**
     * 删除店员店长管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long clerkJoinId) {
        clerkJoinService.deleteById(clerkJoinId);
        return SUCCESS_TIP;
    }

    /**
     * 修改店员店长管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ClerkJoin clerkJoin) {
        clerkJoinService.updateById(clerkJoin);
        return SUCCESS_TIP;
    }

    /**
     * 店员店长管理详情
     */
    @RequestMapping(value = "/detail/{clerkJoinId}")
    @ResponseBody
    public Object detail(@PathVariable("clerkJoinId") Integer clerkJoinId) {
        return clerkJoinService.selectById(clerkJoinId);
    }
}
