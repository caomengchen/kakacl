package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.store.model.Account;
import cn.stylefeng.guns.modular.store.model.WorkCheck;
import cn.stylefeng.guns.modular.store.model.WorkStatus;
import cn.stylefeng.guns.modular.store.service.IAccountService;
import cn.stylefeng.guns.modular.store.service.ICompanyService;
import cn.stylefeng.guns.modular.store.service.IWorkCheckService;
import cn.stylefeng.guns.modular.store.service.IWorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangjunsheng
 * @version v1.0.0
 * @description 员工考勤
 * @date 2019-3-29
 */
@Controller
@RequestMapping("/workStatus")
public class WorkStatusController {

    @Autowired
    private IWorkStatusService iWorkStatusService;

    @Autowired
    private IWorkCheckService iWorkCheckService;

    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private ICompanyService iCompanyService;

    private final String  PREFIX = "/store/workStatus/";

    @RequestMapping("")
    public String view(){
        return PREFIX+"workStatus.html";
    }

    //查询列表
    @RequestMapping("/getList")
    @ResponseBody
    public List<Map> getList(String companyName,String nickName,String workName,Integer hrsAddtion,
                             Integer hrsNormal,Integer extType,String startTime,String endTime){
        ShiroUser shiroUser = ShiroKit.getUser();
        Map map = new HashMap();
        map.put("companyName",companyName);
        map.put("nickName",nickName);
        map.put("workName",workName);
        map.put("hrsAddtion",hrsAddtion);
        map.put("hrsNormal",hrsNormal);
        map.put("extType",extType);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("userid",shiroUser.getId());
      return iWorkStatusService.getlist(map);
    }


//跳转至增加页面
    @RequestMapping("/addWorkStatus")
    public String toAadd(){
        return  PREFIX+"workStatus_add.html";
    }


    //查询该猪场下所有企业的考勤组信息
    @RequestMapping("/findWorkCheckList")
    @ResponseBody
    public List<Map> findWorkCheckList(){
        ShiroUser shiroUser = ShiroKit.getUser();
        return iWorkStatusService.findWorkCheckList(shiroUser.getId());
    };

    //查询该岗位下的所有员工
    @RequestMapping("/getStaffList/{wid}")
    @ResponseBody
    public List<Map> getStaffList(@PathVariable("wid") Integer wid){
       WorkCheck workCheck = iWorkCheckService.selectById(wid);
        List<Map> list=new ArrayList<Map>();
        list =iWorkStatusService.getStaffList(workCheck.getCid(),workCheck.getPid());
        return list;
    };


    //添加考勤记录
    @RequestMapping("/add")
    @ResponseBody
    public String addWorkStatus(WorkStatus workStatus){
       Boolean falg = iWorkStatusService.insert(workStatus);
        if(falg){
            return "添加成功!";
        }
        return "添加失败!";
    };

    //跳转到修改页面
    @RequestMapping("/toEdit/{id}")
    public String toEdit(@PathVariable Integer id, Model model){
        WorkStatus workStatus = iWorkStatusService.selectById(id);
        model.addAttribute("workStatus",workStatus);
        return PREFIX+"workStatus_edit.html";
    }


    //跳转到修改页面
    @RequestMapping("/edit")
    @ResponseBody
    public String update(WorkStatus workStatus){
        Boolean flage = iWorkStatusService.updateAllColumnById(workStatus);
        if(flage){
            return "修改成功！";
        }
        return "修改失败！";
    }
    //逻辑删除
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        Boolean flage = iWorkStatusService.deleteById(id);
        if(flage){
            return "删除成功！";
        }
        return "删除失败！";
    };

    /*每个员工的工时
    *分为双休加班，节假日加班，普通加班
    * 主要展示信息包括，公司名称，岗位名称，姓名，工号，总工时，总加班工时，点击总加班工时，可以看到各个加班类型的工时
    *
    * */


    @RequestMapping("/toWorkingHour")
    public String toWorkingHour(){
        return  PREFIX+"workStatus_workHour.html";
    }

    @RequestMapping("/statisticalWorkingHourList")
    @ResponseBody
    public List<Map> StatisticalWorkingHour(String startTime,String endTime,String nickName,String companyName,String jobNum){
        List<Map> list=new ArrayList<Map>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ShiroUser shiroUser = ShiroKit.getUser();
        Map map = new HashMap();
        map.put("userid",shiroUser.getId());
        map.put("nickName",nickName);
        map.put("companyName",companyName);
        map.put("jobNum",jobNum);
        if(startTime==null){
            Calendar calendar =Calendar.getInstance();//获取当前时间
            calendar.add(Calendar.MONTH, -1);
            calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
            calendar.set(Calendar.HOUR_OF_DAY, 00);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            map.put("startTime",format.format(calendar.getTime()));
        }else{
            map.put("startTime",  startTime);
        }
        if(endTime==null){
            Calendar calendar1 =Calendar.getInstance();//获取当前时间
            calendar1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天,设置为0时，则为上个月最后一天，这你设置为本月第一天的0时0分0秒
            calendar1.set(Calendar.HOUR_OF_DAY, 00);
            calendar1.set(Calendar.MINUTE, 00);
            calendar1.set(Calendar.SECOND, 00);
            map.put("endTime",format.format(calendar1.getTime()));
        }else{

            map.put("endTime", endTime);
        }
        list = iWorkStatusService.getWorkAttendance(map);
        //将数据库中的普通工时和加班工时加起来，算出总工时
        for(int i= 0;i<list.size();i++){
            list.get(i).put("allHrs",((BigDecimal)list.get(i).get("hrsAddtion")).add((BigDecimal)list.get(i).get("hrsNormal")));
            //根据uid 查出用户姓名
              Integer uid =  (Integer)list.get(i).get("uid");
              Account account = iAccountService.selectById(uid);
            list.get(i).put("nickName",account.getNickname());
            list.get(i).put("jobNum",account.getJobNum());
              Integer gid =  (Integer)list.get(i).get("gid");
              WorkCheck workCheck = iWorkCheckService.selectById(gid);
              list.get(i).put("extType",workCheck.getExtType());
              list.get(i).put("title",workCheck.getTitle());
              list.get(i).put("pName",workCheck.getName());
              String name1 = iCompanyService.selectById(workCheck.getCid()).getCompanyName();
              list.get(i).put("companyName",name1);
            System.out.print("加班工时："+list.get(i).get("hrsAddtion")+"正常工时："+list.get(i).get("hrsNormal")+list.get(i).get("allHrs"));
        }
          return list;
    };





    public static void main(String[] args) {
        Calendar calendar =Calendar.getInstance();//获取当前时间
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        Calendar calendar1 =Calendar.getInstance();//获取当前时间
        calendar1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天,设置为0时，则为上个月最后一天，这你设置为本月第一天的0时0分0秒
        calendar1.set(Calendar.HOUR_OF_DAY, 00);
        calendar1.set(Calendar.MINUTE, 00);
        calendar1.set(Calendar.SECOND, 00);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print(new Date().getTime());



        //        Iterator<Map> iterator =list.iterator();
//        while (iterator.hasNext()){
//
//            Integer uid =  (Integer) iterator.next().get("uid");
//            Account account = iAccountService.selectById(uid);
//            if(nickName!=null&&!nickName.trim().equals("")){
//                if(account.getNickname().equals(nickName)){
//                    iterator.next().put("nickName",account.getNickname());
//                }else{
//                    iterator.remove();
//                }
//            }
//            if(jobNum!=null&&!jobNum.trim().equals("")){
//                if(account.getNickname().equals(nickName)){
//                    iterator.next().put("jobNum",account.getJobNum());
//                }else{
//                    iterator.remove();
//                }
//            }
//
//            Integer gid =  (Integer)iterator.next().get("gid");
//            WorkCheck workCheck = iWorkCheckService.selectById(gid);
//            iterator.next().put("extType",workCheck.getExtType());
//            iterator.next().put("title",workCheck.getTitle());
//            iterator.next().put("pName",workCheck.getName());
//            String name1 = iCompanyService.selectById(workCheck.getCid()).getCompanyName();
//            if(companyName!=null&&!companyName.trim().equals("")){
//                if(name1.equals(companyName)){
//                    iterator.next().put("companyName",name1);
//                }else{
//                    iterator.remove();
//                }
//            }
//        }


    }



}
