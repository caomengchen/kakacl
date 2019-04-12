package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.roses.core.base.controller.BaseController;
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
import cn.stylefeng.guns.modular.store.model.Suggest;
import cn.stylefeng.guns.modular.store.service.ISuggestService;

import java.util.Date;
import java.util.List;

/**
 * 意见建议控制器
 *
 * @author fengshuonan
 * @Date 2018-12-06 15:42:28
 */
@Controller
@RequestMapping("/suggest")
public class SuggestController extends BaseController {

    private String PREFIX = "/store/suggest/";

    @Autowired
    private ISuggestService suggestService;

    /**
     * 跳转到意见建议首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "suggest.html";
    }

    /**
     * 跳转到添加意见建议
     */
    @RequestMapping("/suggest_add")
    public String suggestAdd() {
        return PREFIX + "suggest_add.html";
    }

    /**
     * 跳转到修改意见建议
     */
    @RequestMapping("/suggest_update/{suggestId}")
    public String suggestUpdate(@PathVariable Integer suggestId, Model model) {
        Suggest suggest = suggestService.selectById(suggestId);
        model.addAttribute("item",suggest);
        LogObjectHolder.me().set(suggest);
        return PREFIX + "suggest_edit.html";
    }

    /**
     * 获取意见建议列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Suggest> data = suggestService.selectList(new EntityWrapper<Suggest>().like("title", condition));
        return data;
    }

    /**
     * 新增意见建议
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Suggest suggest) {
        suggest.setCreateBy(ShiroKit.getUser().getName());
        suggest.setCreateTime(new Date());
        suggest.setStatus("正常");
        suggestService.insert(suggest);
        return SUCCESS_TIP;
    }

    /**
     * 删除意见建议
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer suggestId) {
        suggestService.deleteById(suggestId);
        return SUCCESS_TIP;
    }

    /**
     * 修改意见建议
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Suggest suggest) {
        suggestService.updateById(suggest);
        return SUCCESS_TIP;
    }

    /**
     * 意见建议详情
     */
    @RequestMapping(value = "/detail/{suggestId}")
    @ResponseBody
    public Object detail(@PathVariable("suggestId") Integer suggestId) {
        return suggestService.selectById(suggestId);
    }
}
