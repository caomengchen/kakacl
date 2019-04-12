package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.common.constant.dictmap.RoleDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.system.model.Role;
import cn.stylefeng.guns.modular.system.model.TestLimit;
import cn.stylefeng.guns.modular.system.service.TestLimitService;
import cn.stylefeng.guns.modular.system.warpper.RoleWarpper;
import cn.stylefeng.guns.modular.system.warpper.TestLimitWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestLimitController extends BaseController {
    private static String PREFIX = "/system/test";
    @Autowired
    private TestLimitService testLimitService;

    @RequestMapping("/limit")
    @ResponseBody
 public String testLimit (){
    // PageHelper.startPage(pageNum,pageSeiz);
     List<TestLimit> lists  = testLimitService.lists();
    // PageInfo<TestLimit> pageInfo = new PageInfo<TestLimit>(lists);
     System.out .println("结果:"+lists.toString());
     String  limit =  lists.toString();
    return limit;
 }
    /**
     * 跳转到Test列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/test.html";
    }
    /**
     * 跳转到添加test
     */
    @RequestMapping(value = "/test_add")
    public String testAdd() {
        return PREFIX + "/test_add.html";
    }

    /**
     * 跳转到修改test
     */
    @RequestMapping(value = "/test_edit/{aaa}")
    public String testEdit(@PathVariable Integer aaa, Model model) {
        if (ToolUtil.isEmpty(aaa)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        TestLimit testLimit = this.testLimitService.selectById(aaa);
        model.addAttribute("test",testLimit);
        LogObjectHolder.me().set(testLimit);
        return PREFIX + "/test_edit.html";
    }
    /**
     * 获取test列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String bbb) {
        System.out.println("结果："+super.getPara("bbb"));
        List<Map<String, Object>> roles = this.testLimitService.selectTests(bbb);
        System.out.println("结果："+roles);
        return super.warpObject(new TestLimitWarpper(roles));
    }
    /**
     * test新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseData add(@Valid TestLimit testLimit, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        testLimit.setAaa(null);
        this.testLimitService.insert(testLimit);
        return SUCCESS_TIP;
    }

    /**
     * test修改
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public ResponseData edit(@Valid TestLimit testLimit, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.testLimitService.updateById(testLimit);

        //删除缓存
        CacheUtil.removeAll(Cache.CONSTANT);
        return SUCCESS_TIP;
    }

    /**
     * 删除test
     */
    @RequestMapping(value = "/remove")
    @ResponseBody
    public ResponseData remove(@RequestParam Integer aaa) {
        if (ToolUtil.isEmpty(aaa)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        System.out.println("aaa:"+aaa);
        this.testLimitService.deleteById(aaa);

        //删除缓存
        CacheUtil.removeAll(Cache.CONSTANT);
        return SUCCESS_TIP;
    }

    /**
     * 查看test
     */
    @RequestMapping(value = "/view/{aaa}")
    @ResponseBody
    public ResponseData view(@PathVariable Integer aaa) {
        if (ToolUtil.isEmpty(aaa)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.testLimitService.selectById(aaa);
        return SUCCESS_TIP;
    }

}
