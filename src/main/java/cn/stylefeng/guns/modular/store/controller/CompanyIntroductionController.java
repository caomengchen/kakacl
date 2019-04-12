package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.config.GlobalStr;
import cn.stylefeng.guns.core.common.StrUtils;
import cn.stylefeng.guns.core.common.exception.StoreExceptionEnum;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.store.model.Company;
import cn.stylefeng.guns.modular.store.service.ICompanyService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.store.model.CompanyIntroduction;
import cn.stylefeng.guns.modular.store.service.ICompanyIntroductionService;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业介绍控制器
 *
 * @author fengshuonan
 * @Date 2018-12-17 11:42:43
 */
@Controller
@RequestMapping("/companyIntroduction")
public class CompanyIntroductionController extends BaseController {

    private String PREFIX = "/store/companyIntroduction/";

    @Autowired
    private ICompanyIntroductionService companyIntroductionService;

    @Autowired
    private ICompanyService companyService;

    /**
     * 跳转到企业介绍首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "companyIntroduction.html";
    }

    /**
     * 跳转到添加企业介绍
     */
    @RequestMapping("/companyIntroduction_add")
    public String companyIntroductionAdd(Model model) {
        Company company = new Company();
        model.addAttribute("item",company);
        return PREFIX + "companyIntroduction_add.html";
    }

    /**
     * 跳转到修改企业介绍
     */
    @RequestMapping("/companyIntroduction_update/{companyIntroductionId}")
    public String companyIntroductionUpdate(@PathVariable Integer companyIntroductionId, HttpServletRequest request, Model model) {
        if(companyIntroductionId == GlobalNumber.INT_FIX_0) {
            CompanyIntroduction companyIntroduction = new CompanyIntroduction();
            model.addAttribute("item",companyIntroduction);
            LogObjectHolder.me().set(companyIntroduction);
            String companyId = request.getParameter("companyId");
            Company company = companyService.selectById(companyId);
            model.addAttribute("company",company);
            LogObjectHolder.me().set(company);
        }else {
            CompanyIntroduction companyIntroduction = companyIntroductionService.selectById(companyIntroductionId);
            model.addAttribute("item",companyIntroduction);
            LogObjectHolder.me().set(companyIntroduction);
            Company company = companyService.selectById(companyIntroduction.getCompanyId());
            model.addAttribute("company",company);
            LogObjectHolder.me().set(company);
        }
        return PREFIX + "companyIntroduction_edit.html";
    }

    @RequestMapping("/companyIntroduction_detail/{companyIntroductionId}")
    public String companyIntroductionDetail(@PathVariable Integer companyIntroductionId, Model model) {
        CompanyIntroduction companyIntroduction = companyIntroductionService.selectById(companyIntroductionId);
        if(companyIntroduction == null) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60008);
        }
        model.addAttribute("item",companyIntroduction);
        LogObjectHolder.me().set(companyIntroduction);
        Company company = companyService.selectById(companyIntroduction.getCompanyId());
        model.addAttribute("company",company);
        LogObjectHolder.me().set(company);
        return PREFIX + "companyIntroduction_detail.html";
    }

    /**
     * 获取企业介绍列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Map<String, Object> params = new HashedMap();
        params.put("companyName", condition);
        List<Company> companyList = companyService.selectList(params);
        for (int i = 0; i < companyList.size(); i++) {
            if(companyList.get(i).getIntroductionId() == null) {
                companyList.get(i).setIntroductionId(Long.valueOf(GlobalNumber.INT_FIX_0));
            }
        }
        return companyList;
    }

    /**
     * 新增企业介绍
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(CompanyIntroduction companyIntroduction) {
        String companyName = companyIntroduction.getCompanyName();
        // 公司图片地址
        String companyImg = companyIntroduction.getCompanyImg();
        // 先查询企业存在不吧
        List<Company> companyList =  companyService.selectList(new EntityWrapper<Company>().eq("company_name", companyName).eq("is_deleted", GlobalNumber.INT_DEL_FLAG_0));
        if(companyList.size() != GlobalNumber.INT_FIX_0) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60003);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("companyName", companyName);
        params.put("companyImg", companyImg);
        params.put("area", companyIntroduction.getArea());
        companyService.insertCompany(params);
        // 然后新增数据再查询一波
        companyList =  companyService.selectList(new EntityWrapper<Company>().eq("company_name", companyName));
        if(companyList.size() ==  0) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60004);
        }
        // 再将公司的主键存到公司介绍里
        Long companyId = companyList.get(0).getId();
        companyIntroduction.setCompanyId(companyId);
        companyIntroduction.setCreatetime(new Date());
        companyIntroduction.setCreateBy(ShiroKit.getUser().getName());
        companyIntroduction.setContent(StrUtils.htmlUnescape(companyIntroduction.getContent()));
        companyIntroductionService.insert(companyIntroduction);
        return SUCCESS_TIP;
    }

    /**
     * 删除企业介绍
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer companyId) {
        Map<String, Object> params = new HashedMap();
        params.put("id", companyId);
        params.put("isDeleted", GlobalNumber.INT_DEL_FLAG_1);
        companyService.updateDeleteFlag(params);
        return SUCCESS_TIP;
    }

    /**
     * 修改企业介绍
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CompanyIntroduction companyIntroduction) {
        String companyImg = companyIntroduction.getCompanyImg();
        String contentImg = companyIntroduction.getContentImg();
        String compnyName = "";
        if(companyIntroduction.getId() == null) {
            // 新建
            CompanyIntroduction introduction = new CompanyIntroduction();
            introduction.setContent(StrUtils.htmlUnescape(companyIntroduction.getContent()));
            if(StringUtils.isNoneBlank(companyIntroduction.getContentImg())) {
                introduction.setContentImg(companyIntroduction.getContentImg());
            }
            List<Company> companyList = companyService.selectList(new EntityWrapper<Company>()
                    .eq("company_name",companyIntroduction.getCompanyName())
                    .eq("is_deleted",GlobalNumber.INT_DEL_FLAG_0));
            if(companyList != null && companyList.size() == GlobalNumber.INT_FIX_1) {
                compnyName = companyList.get(GlobalNumber.INT_FIX_0).getCompanyName();
                introduction.setCompanyId(companyList.get(GlobalNumber.INT_FIX_0).getId());
            } else {
                throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60007);
            }
            companyIntroductionService.insert(introduction);
        } else {
            compnyName = companyIntroduction.getCompanyName();
            companyIntroduction.setContent(StrUtils.htmlUnescape(companyIntroduction.getContent()));
            if(GlobalStr.FIX_STR_ASKLL_35.equals(contentImg)) {
                companyIntroduction.setContentImg(null);
            }
            companyIntroductionService.updateById(companyIntroduction);
        }
        if(!GlobalStr.FIX_STR_ASKLL_35.equals(companyImg)) {
            companyService.updateImgPath(companyImg, compnyName);
        }
        return SUCCESS_TIP;
    }

    /**
     * 企业介绍详情
     */
    @RequestMapping(value = "/detail/{companyIntroductionId}")
    @ResponseBody
    public Object detail(@PathVariable("companyIntroductionId") Integer companyIntroductionId) {
        return companyIntroductionService.selectById(companyIntroductionId);
    }
}
