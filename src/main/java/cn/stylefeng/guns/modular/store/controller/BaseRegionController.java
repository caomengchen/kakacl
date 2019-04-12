package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalNumber;
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
import cn.stylefeng.guns.modular.store.model.BaseRegion;
import cn.stylefeng.guns.modular.store.service.IBaseRegionService;

import java.util.List;

/**
 * 地址表控制器
 *
 * @author fengshuonan
 * @Date 2018-11-30 15:20:47
 */
@Controller
@RequestMapping("/baseRegion")
public class BaseRegionController extends BaseController {

    private String PREFIX = "/store/baseRegion/";

    @Autowired
    private IBaseRegionService baseRegionService;

    /**
     * 跳转到地址表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "baseRegion.html";
    }

    /**
     * 跳转到添加地址表
     */
    @RequestMapping("/baseRegion_add")
    public String baseRegionAdd() {
        return PREFIX + "baseRegion_add.html";
    }

    /**
     * 跳转到修改地址表
     */
    @RequestMapping("/baseRegion_update/{baseRegionId}")
    public String baseRegionUpdate(@PathVariable Integer baseRegionId, Model model) {
        BaseRegion baseRegion = baseRegionService.selectById(baseRegionId);
        model.addAttribute("item",baseRegion);
        LogObjectHolder.me().set(baseRegion);
        return PREFIX + "baseRegion_edit.html";
    }

    /**
     * 获取地址表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return baseRegionService.selectList(null);
    }

    /**
     * 根据参数查询列表
     * @param condition
     * @return
     */
    @RequestMapping(value = "/findTop")
    @ResponseBody
    public Object findTop(int condition) {
        int type = condition;
        List<BaseRegion> baseRegionList = baseRegionService.selectList(new EntityWrapper<BaseRegion>().eq("type",type));
        return baseRegionList;
    }

    @RequestMapping(value = "/findByParentId")
    @ResponseBody
    public Object findByParentId(String condition) {
        String findByParentId = condition;
        List<BaseRegion> baseRegionList = baseRegionService.selectList(new EntityWrapper<BaseRegion>().eq("parent_id",findByParentId));
        return baseRegionList;
    }

    /**
     * 新增地址表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BaseRegion baseRegion) {
        baseRegionService.insert(baseRegion);
        return SUCCESS_TIP;
    }

    /**
     * 删除地址表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer baseRegionId) {
        baseRegionService.deleteById(baseRegionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改地址表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BaseRegion baseRegion) {
        baseRegionService.updateById(baseRegion);
        return SUCCESS_TIP;
    }

    /**
     * 地址表详情
     */
    @RequestMapping(value = "/detail/{baseRegionId}")
    @ResponseBody
    public Object detail(@PathVariable("baseRegionId") Integer baseRegionId) {
        return baseRegionService.selectById(baseRegionId);
    }
}
