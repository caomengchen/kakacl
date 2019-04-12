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
import cn.stylefeng.guns.modular.store.model.JobDetail;
import cn.stylefeng.guns.modular.store.service.IJobDetailService;

/**
 * 职位详情控制器
 *
 * @author fengshuonan
 * @Date 2018-11-28 11:34:07
 */
@Controller
@RequestMapping("/jobDetail")
public class JobDetailController extends BaseController {

    private String PREFIX = "/store/jobDetail/";

    @Autowired
    private IJobDetailService jobDetailService;

    /**
     * 跳转到职位详情首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "jobDetail.html";
    }

    /**
     * 跳转到添加职位详情
     */
    @RequestMapping("/jobDetail_add")
    public String jobDetailAdd() {
        return PREFIX + "jobDetail_add.html";
    }

    /**
     * 跳转到修改职位详情
     */
    @RequestMapping("/jobDetail_update/{jobDetailId}")
    public String jobDetailUpdate(@PathVariable Integer jobDetailId, Model model) {
        JobDetail jobDetail = jobDetailService.selectById(jobDetailId);
        model.addAttribute("item",jobDetail);
        LogObjectHolder.me().set(jobDetail);
        return PREFIX + "jobDetail_edit.html";
    }

    /**
     * 获取职位详情列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return jobDetailService.selectList(null);
    }

    /**
     * 新增职位详情
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(JobDetail jobDetail) {
        jobDetailService.insert(jobDetail);
        return SUCCESS_TIP;
    }

    /**
     * 删除职位详情
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer jobDetailId) {
        jobDetailService.deleteById(jobDetailId);
        return SUCCESS_TIP;
    }

    /**
     * 修改职位详情
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(JobDetail jobDetail) {
        jobDetailService.updateById(jobDetail);
        return SUCCESS_TIP;
    }

    /**
     * 职位详情详情
     */
    @RequestMapping(value = "/detail/{jobDetailId}")
    @ResponseBody
    public Object detail(@PathVariable("jobDetailId") Integer jobDetailId) {
        return jobDetailService.selectById(jobDetailId);
    }
}
