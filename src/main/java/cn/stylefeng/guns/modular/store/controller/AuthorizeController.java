package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.config.GlobalStr;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.store.model.Suggest;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.store.model.Authorize;
import cn.stylefeng.guns.modular.store.service.IAuthorizeService;
import org.terracotta.offheapstore.HashingMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门店授权控制器
 *
 * @author fengshuonan
 * @Date 2018-12-04 15:42:31
 */
@Controller
@RequestMapping("/authorize")
public class AuthorizeController extends BaseController {

    private String PREFIX = "/store/authorize/";

    @Autowired
    private IAuthorizeService authorizeService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到门店授权首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "authorize.html";
    }

    /**
     * 跳转到添加门店授权
     */
    @RequestMapping("/authorize_add")
    public String authorizeAdd() {
        return PREFIX + "authorize_add.html";
    }

    /**
     * 跳转到修改门店授权
     */
    @RequestMapping("/authorize_update/{authorizeId}")
    public String authorizeUpdate(@PathVariable Integer authorizeId, Model model) {
        Authorize authorize = authorizeService.selectById(authorizeId);
        model.addAttribute("item",authorize);
        LogObjectHolder.me().set(authorize);
        return PREFIX + "authorize_edit.html";
    }

    /**
     * 获取门店授权列表
     */
    @RequestMapping(value = "/list")
    @ApiOperation("门店授权查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "门店名称", required = false, dataType = "String"),
    })
    @ResponseBody
    public Object list(String condition) {
        List<Authorize> data = authorizeService.selectList(new EntityWrapper<Authorize>().like("name", condition));
        for(int i = GlobalNumber.INT_FIX_0; i < data.size(); i ++) {
            Long id = data.get(i).getSysUserId();
            User u = userService.selectById(id);
            if(u != null) {
                data.get(i).setSysUserView(u.getName());
            }
        }
        return data;
    }

    /**
     * 新增门店授权
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Authorize authorize) {
        authorize.setId(null);
        authorize.setCreateBy(ShiroKit.getUser().getName());
        authorize.setStatus(GlobalStr.DATE_STATUS_NORMAL_STR);
        authorizeService.insertAuthorize(authorize);
        return SUCCESS_TIP;
    }

    /**
     * 删除门店授权
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer authorizeId) {
        //authorizeService.updateAuthorizeStatus(authorizeId, GlobalStr.DATE_STATUS_DELETE_STR);
        authorizeService.deleteById(authorizeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改门店授权
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Authorize authorize) {
        authorizeService.updateById(authorize);
        return SUCCESS_TIP;
    }

    /**
     * 门店授权详情
     */
    @RequestMapping(value = "/detail/{authorizeId}")
    @ResponseBody
    public Object detail(@PathVariable("authorizeId") Integer authorizeId) {
        return authorizeService.selectById(authorizeId);
    }
}
