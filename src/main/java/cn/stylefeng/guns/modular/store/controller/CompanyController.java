package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.common.StrUtils;
import cn.stylefeng.guns.modular.store.model.CompanyIntroduction;
import cn.stylefeng.guns.modular.store.model.Position;
import cn.stylefeng.guns.modular.store.service.ICompanyIntroductionService;
import cn.stylefeng.guns.modular.store.service.IPositionService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.store.model.Company;
import cn.stylefeng.guns.modular.store.service.ICompanyService;
import cn.stylefeng.guns.modular.system.model.Dict;

/**
 * 公司信息控制器
 *
 * @author fengshuonan
 * @Date 2018-11-29 17:33:35
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

    private String PREFIX = "/store/company/";

    @Autowired
    private ICompanyIntroductionService companyIntroductionService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private ICompanyService companyService;
    
    @RequestMapping(value = "/findCompanyDict/{type}")
    @ResponseBody
    public static List<Dict> findInDict(@PathVariable Integer type) {
        return cn.stylefeng.guns.core.common.constant.factory.ConstantFactory.me().findInDict(type);
    }

    /**
     * 根据公司获取职位信息
     * @return
     */
    @RequestMapping(value = "/findPositionList/{companyId}")
    @ResponseBody
    public List<Dict> findPositionList(@PathVariable Integer companyId) {
        List<Dict> result = new ArrayList<>();
        // 根据公司主键查询职位
        List<Position> data = positionService.selectList(new EntityWrapper<Position>().eq("company_id",companyId).eq("is_deleted", GlobalNumber.INT_DEL_FLAG_0));
        Dict en = null;
        for(int i = 0 ; i < data.size() ; i ++) {
            en = new Dict();
            en.setId(Integer.parseInt(data.get(i).getId() + ""));
            en.setName(data.get(i).getName());
            result.add(en);
        }
        return result;
    }

    /**
     * 获取公司信息
     * @return
     */
    @RequestMapping(value = "/findCompanyList")
    @ResponseBody
    public List<Dict> findCompanyList() {
        List<Dict> result = new ArrayList<>();
        List<Company> data = companyService.selectList(new EntityWrapper<Company>().isNotNull("id").eq("is_deleted",GlobalNumber.INT_DEL_FLAG_0));
        Dict en = null;
        for(int i = 0 ; i < data.size() ; i ++) {
            en = new Dict();
            en.setId(Integer.parseInt(data.get(i).getId() + ""));
            en.setName(data.get(i).getCompanyName());
            result.add(en);
        }
        return result;
    }

    /**
     * 跳转到公司信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "company.html";
    }

    /**
     * 跳转到添加公司信息
     */
    @RequestMapping("/company_add")
    public String companyAdd() {
        return PREFIX + "company_add.html";
    }

    /**
     * 跳转到修改公司信息
     */
    @RequestMapping("/company_update/{companyId}")
    public String companyUpdate(@PathVariable Integer companyId, Model model) {
        Company company = companyService.selectById(companyId);
        model.addAttribute("item",company);
        LogObjectHolder.me().set(company);
        return PREFIX + "company_edit.html";
    }

    @RequestMapping("/company_detail/{companyId}")
    public String companyDetail(@PathVariable Integer companyId, Model model) {
        Company company = companyService.selectById(companyId);
        model.addAttribute("item",company);
        LogObjectHolder.me().set(company);

        CompanyIntroduction companyIntroduction = null;
        int companyIntroductionId = 0;
        List<CompanyIntroduction> companyIntroductionList = companyIntroductionService.selectList(new EntityWrapper<CompanyIntroduction>().eq("company_id",companyId));
        if(companyIntroductionList != null && companyIntroductionList.size() > 0) {
           companyIntroduction = companyIntroductionList.get(0);
            companyIntroductionId = companyIntroduction.getId();
            companyIntroduction = companyIntroductionService.selectById(companyIntroductionId);
        } else {
            companyIntroduction = new CompanyIntroduction();
        }
        model.addAttribute("companyIntroduction", companyIntroduction);
        LogObjectHolder.me().set(companyIntroduction);
        return PREFIX + "company_detail.html";
    }

    /**
     * 获取公司信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return companyService.selectList(new EntityWrapper<Company>().like("company_name",condition));
    }

    /**
     * 新增公司信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Company company) {
        company.setInfo(StrUtils.htmlUnescape(company.getInfo()));
        companyService.insert(company);
        return SUCCESS_TIP;
    }

    /**
     * 删除公司信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer companyId) {
        companyService.deleteById(companyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改公司信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Company company) {
        companyService.updateById(company);
        return SUCCESS_TIP;
    }

    /**
     * 公司信息详情
     */
    @RequestMapping(value = "/detail/{companyId}")
    @ResponseBody
    public Object detail(@PathVariable("companyId") Integer companyId) {
        return companyService.selectById(companyId);
    }
}
