package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.dictmap.UserDict;
import cn.stylefeng.guns.core.common.constant.state.ManagerStatus;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.store.service.ClerkManagerService;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/clerkManager")
public class ClerkManagerColtroller extends BaseController {

    private String PREFIX = "/store/clerkManager/";

    @Autowired
    private IUserService userService;

    @Autowired
    private ClerkManagerService clerkManagerService;

    /**
     * 跳转到店员店长管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "clerkList.html";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String name, String account, String phone) {
        List<User> userList = null;
        java.util.Map<String, Object> params = new HashMap<>();
        if(ShiroKit.getUser().roleList.contains(GlobalNumber.INT_STORE_MANAGER_6)) {
            // userService.selectList(new EntityWrapper<User>().like("name",name).like("account",account).like("phone",phone));
            params.put("store_manager_id", ShiroKit.getUser().getId());
            userList = clerkManagerService.selectClerk(params);
        }
        return userList;
    }

    /**
     * 冻结用户
     */
    @RequestMapping("/freeze")
    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
    @ResponseBody
    public ResponseData freeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能冻结超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_FREEZE_ADMIN);
        }
        this.userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 解除冻结用户
     */
    @RequestMapping("/unfreeze")
    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    @ResponseBody
    public ResponseData unfreeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.setStatus(userId, ManagerStatus.OK.getCode());
        return SUCCESS_TIP;
    }
}
