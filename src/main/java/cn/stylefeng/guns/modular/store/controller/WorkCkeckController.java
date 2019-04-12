package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.store.model.Company;
import cn.stylefeng.guns.modular.store.model.Position;
import cn.stylefeng.guns.modular.store.model.WorkCheck;
import cn.stylefeng.guns.modular.store.service.ICompanyService;
import cn.stylefeng.guns.modular.store.service.IPositionService;
import cn.stylefeng.guns.modular.store.service.IWorkCheckService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author wangwjunsheng
 * @version v1.0.0
 * @description 公司考勤组表
 * @date 2019-3-27
 */
@Controller
@RequestMapping("/workCheck")
public class WorkCkeckController extends BaseController {

    private final String  PREFIX = "/store/workCheck/";

    @Autowired
    private  IWorkCheckService iWorkCheckService;

    @Autowired
    private IPositionService ipositionService;
    @Autowired
    private ICompanyService iCompanyService;



    @RequestMapping("")
    public String view(){
        return PREFIX+"workCheck.html";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public List<WorkCheck> getList(String companyname,String workname,String fSTime,String fETime){
        ShiroUser shiroUser = ShiroKit.getUser();
        Map map =new HashMap();
        if(companyname!=null&&!companyname.trim().equals("")){
            map.put("companyname",companyname);
        }
        if(workname!=null&&!workname.trim().equals("")){
            map.put("workname",workname);
        }
        if(fSTime!=null&&!fSTime.trim().equals("")){
            map.put("fSTime",fSTime);
        }if(fETime!=null&&!fETime.trim().equals("")){
            map.put("fETime",fETime);
        }
        map.put("userid",shiroUser.getId());
        return  iWorkCheckService.getList(map);
     }

     @RequestMapping("/addWorkCheck")
     public  String toAdd(){
        return PREFIX+"workCheck_add.html";
     };

    //查询驻场人员可以选择的所有公司
    @RequestMapping("/findCompanyList")
    @ResponseBody
    public List<Map> geCompanyList(){
        ShiroUser shiroUser = ShiroKit.getUser();
        return iWorkCheckService.getCompanyList(shiroUser.getId());
    };
    //查询驻场人员可以选择的所有公司的岗位
    @RequestMapping("/getPositionList/{companyId}")
    @ResponseBody
    public List<Map> getPosition(@PathVariable Integer companyId){
        return iWorkCheckService.getPositionList(companyId);
    };

    @RequestMapping("/add")
    @ResponseBody
    public String addWorkCheck(WorkCheck workCheck){
        //新增岗位的话，主要是添加新的岗位（store_position表），在添加考勤组表
        System.out.print("uoiefuwoinhbf ivuohfeuoiwhjioewhfgiuoewyuiofrdhewiuo");
        iWorkCheckService.insert(workCheck);
        return "成功了！";
    };

    //跳转到修改页面
    @RequestMapping("/toEdit/{id}")
    public String toEdit(@PathVariable Integer id, Model model){
           WorkCheck workCheck = iWorkCheckService.selectById(id);
           System.out.println("workCheck:"+workCheck);
          Position position = ipositionService.selectById(workCheck.getPid());
          Company company = iCompanyService.selectById(workCheck.getCid());
           model.addAttribute("workCheck",workCheck);
           model.addAttribute("companyName",company.getCompanyName());
           model.addAttribute("workname",position.getName());
            return PREFIX+"workCheck_edit.html";
    }

    @RequestMapping("/edit")
    @ResponseBody
  public String edit(WorkCheck workCheck){
        if (workCheck!=null&&workCheck.getDisabled()==null){
                workCheck.setDisabled(0);
        }
        System.out.println("workCheck:"+workCheck);
       Boolean flage = iWorkCheckService.insertOrUpdateAllColumn(workCheck);
       if(flage){
           return "修改成功！";
       }
        return "修改失败！";
    };

    //逻辑删除
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        Boolean flage = iWorkCheckService.updateWorkCheckById(id);
        if(flage){
            return "删除成功！";
        }
        return "删除失败！";
    };

}
