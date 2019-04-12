package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalEnums;
import cn.stylefeng.guns.config.GlobalMessages;
import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.config.GlobalResponse;
import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.ReadExcel;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.store.model.Account;
import cn.stylefeng.guns.modular.store.model.AccountBank;
import cn.stylefeng.guns.modular.store.model.Agent;
import cn.stylefeng.guns.modular.store.model.Company;
import cn.stylefeng.guns.modular.store.service.*;
import cn.stylefeng.guns.modular.system.service.IRoleService;
import cn.stylefeng.guns.modular.system.service.IUserService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangjunsheng
 * @version v1.0.0
 * @description 驻场人员接口，主要用于查询，修改所有在职人员名单
 * @date
 */
@Controller
@RequestMapping("/stationed")
@Transactional
public class stationedController extends BaseController {
    private final String  PREFIX = "/store/stationed/";
    @Autowired
    private IUserService userService;
    @Autowired
    private StationedService stationedService;
    @Autowired
    private ICompanyService iCompanyService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IAccountBankService iAccountBankService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private GunsProperties gunsProperties;
    @Autowired
    private IRoleService iRoleService;



    //跳转该账号下所有的公司员工信息
    @RequestMapping("/list")
    public String index(){return  PREFIX+"stationed.html";}

    @RequestMapping("/update")
    public String userList(){return  PREFIX+"stationRelation.html";}

    //跳转到驻场人员和公司管理页面
    @RequestMapping("/Relationadd")
    public String clerkJoinAdd() {
        return PREFIX + "relation_add.html";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public List<Map> getUserList( String name){
       return stationedService.getList(name);
    }


    /*
     *
     * 查询所有的驻场人员
     * @author wangjunsheng
     * @date 2019/3/12
     * @param null
     * @return
     */
    @RequestMapping("/getStationList")
    @ResponseBody
    public List<Map> getStationList(){
        String condition = "驻厂";
        Integer rolid=0;
        List<Map<String, Object>> list=iRoleService.selectRoles(condition);
        for(int i=0;i<list.size();i++){
           if(list.get(i).get("name").equals("驻厂")){
               rolid=(Integer) list.get(i).get("id");
           }
        }
        return  userService.selectStationedPerson(rolid);
    };

/*
* 查询所有企业
* */
    @RequestMapping("/getCompanyList")
    @ResponseBody
    public List<Map>getCompanyList(){
      return iCompanyService.getList();
    }

    @RequestMapping("/addRelation")
    @ResponseBody
    public Object addRelation(Integer userid,Integer companyid){
        Map map =new HashMap();
        map.put("userid",userid);
        map.put("companyid",companyid);
        stationedService.insertRelation(map);
        return "成功！";
    }
    @RequestMapping("/deleteRelation")
    @ResponseBody
    public String deleteRelation(Integer id){
      stationedService.deleteRelation(id);
      return "成功！";
    }


    //获取该驻场人员账号下所有的员工的列表
    @RequestMapping("/getStaffList")
    @ResponseBody
    public List<Map> getStaffList( String nickname,String companyname,String sourcename,String rDate, Integer workStatus,String startTime,String endTime){
        ShiroUser shiroUser = ShiroKit.getUser();
        List<Map> data=null;
        Map map = new HashMap();
        map.put("id",shiroUser.getId());
        map.put("nickname",nickname);
        map.put("companyname",companyname);
        map.put("sourcename",sourcename);
        map.put("workStatus",workStatus);
        map.put("rDate",rDate);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
       if(shiroUser.getAccount().equals("yangjialin")){//如果超级管理员，则可以查看和修改所有的人员(默认查找住电装)
           data=iAccountService.getallstaffList(map);
           return data;
       } else{
           data =iAccountService.getstaffList(map);
           for(int i=0 ;i<data.size();i++){
               Integer status= (Integer) data.get(i).get("work_status");
               if(status==null){
                   data.get(i).put("work_view", GlobalEnums.getName(52101));
               }else{
                   data.get(i).put("work_view", GlobalEnums.getName(status));//根据工作状态去枚举类获取相应的枚举参数
               }
           }
           return data;
       }
    }

    //跳转到修改状态的页面
    @RequestMapping("/stationed_update_status/{id}")
    public String settlementUpdateStatus(@PathVariable Integer id, Model model) {
        Account account = iAccountService.selectById(id);
        Long companyid =account.getCompanyId();
        Company company = iCompanyService.selectById(companyid);
        Map map = new HashMap();
        map.put("accountId",account.getId());
       AccountBank accountBank = iAccountBankService.getAccountBank(map);
        map.put("bankname",accountBank.getBankName());
        map.put("bankcard",accountBank.getBankCard());
        map.put("dReason",account.getdReason());
        map.put("remark",account.getRemark());
        map.put("id",account.getId());
        map.put("nickname",account.getNickname());
        map.put("returnFee",account.getReturnFee());
        map.put("companyname",company.getCompanyName());
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String iDate="";
        String rDate="";
        String rlate="";
        String rfDate="";
        if(account.getiDate()!=null){
            iDate =  simpleDateFormat.format(account.getiDate());
        }
        if(account.getrDate()!=null){
             rDate =  simpleDateFormat.format(account.getrDate());
        }
        if(account.getlDate()!=null){
            rlate =  simpleDateFormat.format(account.getlDate());
        }
        if(account.getReturnFeeTime()!=null){
            rfDate =  simpleDateFormat.format(account.getReturnFeeTime());
        }
        map.put("iDate",iDate);
        map.put("rDate",rDate);
        map.put("lDate",rlate);
        map.put("rfDate",rfDate);
        model.addAttribute("item",map);
        LogObjectHolder.me().set(account);
        return PREFIX + "stationed_update_status.html";
    }


    //处理修改员工信息的数据
    @RequestMapping("/updateAccount")
    @ResponseBody
    public String updateAccount(Integer workStatus, Integer id, String iDate, String rDate, String lDate,
                                Integer lReason, String rfDate, Double rFee,String bankname,
                                String bankcard,String dReason,String remark){
        Map map =new HashMap();
        map.put("id",id);
        map.put("workStatus",workStatus);
        map.put("iDate",iDate);
        map.put("rDate",rDate);
        map.put("lDate",lDate);
        map.put("lReason",lReason);
        map.put("rfDate",rfDate);
        map.put("bankname",bankname);
        map.put("bankcard",bankcard);
        map.put("dReason",dReason);
        map.put("remark",remark);
        map.put("accountId",id);
        if(rFee!=null){
            BigDecimal bigDecimal =new BigDecimal(Double.toString(rFee));//將前台传来的返费转换成Bigdecimal类型
            map.put("rFeee",bigDecimal);
        }
        Boolean flag = iAccountService.updateAccount(map);
        if(flag){
            Boolean flag1 = iAccountBankService.updateAccountBank(map);
            if(flag1){
                return "修改成功";
            }else {
                return "修改失败";
            }
        }else{
            return "修改失败";
        }
    };

    // 异步提醒
    @RequestMapping("/readExcel2")
    @BussinessLog(value = "信息职位", key = "name")
    @ResponseBody
    public GlobalResponse readExcel(@RequestParam(value = "uploadFile")MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response){
        GlobalResponse globalresponse = GlobalResponse.getInstanseGlobalResponse();
        List stations = new ArrayList();
        Map map = new HashMap();
          //String path = gunsProperties.getFileUploadPath()+System.currentTimeMillis()+multipartFile.getOriginalFilename();
          String path = "D:\\tmp\\"+System.currentTimeMillis()+multipartFile.getOriginalFilename();//确定上传文件的保存位置
        try {
            multipartFile.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("文件保存失败！");
        }
        // 上传完毕，可以返回了
        // 如果错误的提醒可以使用其他方式进行
        // 处理完毕进行提醒
        List excelList = ReadExcel.readExcel2(new File(path));//（jxl的）这个方法只能读取xls格式的文件，切记切记（改天有时间可以自己写一个读取）
        //上传完毕之后将上传的文件删除
         File file1 =new File(path);
        if(file1.exists() && file1.isFile()){//判断是否存在并且为文件
            System.out.print("是文件");
            if(file1.delete()){               //删除之前先要记得关闭读取该文件的所有文件流，不然无法删除
                globalresponse.setMessage("上传结束。");
                System.out.print("删除成功");
            }
        }
        if(excelList != null){
            System.out.print(JSON.toJSONString(excelList));
            AccountBank accountBank =new AccountBank();
            Account account =new Account();
            //将excel表格中的数据拿出来进行比较,根据自己的业务逻辑，进行数据的修改
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            List title = (List)excelList.get( GlobalNumber.INT_FOR_1);
            List company =(List)excelList.get(GlobalNumber.INT_FOR_0);
            String username = "";
            String jobNum ="";
            String sex ="";
            String phone="";
            String companyname= company.get(1)+"";//公司名称
            Long id = (Long)iCompanyService.getCompanyByName(companyname).get("id");//目前只能根据公司的名称来模糊查询，以后需要精确查找
            String  agentName =""; //来源中介
            String bankname="";
            String bankcard="";//银行卡号
            String returnFee="";//返费
            String lReason="";//离职原因(方式)
            String Dreason ="";//扣款原因
            String remark = "";//备注
            String workType ="";//工种模式
            String startTime="";//身份证初始日期
            String endTime="";//身份证失效日期
            for(int i = GlobalNumber.INT_FIX_2; i<excelList.size(); i++){
                List list =(List)excelList.get(i);
                for(int j =  GlobalNumber.INT_FOR_0;j<list.size();j++){
                    //获取指定列数
                    if(title.get(j).equals("序号")){
                        String num= list.get(j)+"";
                        if(num==null||num.trim().equals("")){
                            globalresponse.setMessage("数据录入结束");
                            globalresponse.setCode(GlobalMessages.S_40001.getCode());
                            return globalresponse;
                        }
                    }
                    if(title.get(j).equals("姓名")){
                        username= list.get(j)+"";
                        if(username==null||username.trim().equals("")){
                            globalresponse.setMessage(i +  "行,"  + "姓名有误，请检查(此行之前的数据已经录入)。");
                            globalresponse.setCode(GlobalMessages.S_40001.getCode());
                            return globalresponse;
                        }else{
                          account.setNickname(username);
                        }
                    }
                    if(title.get(j).equals("身份证号码")){
                        if(list.get(j)+""!=null&&!list.get(j).toString().trim().equals("")){
                            map.put("cardNo",list.get(j)+"");
                        }else{
                          String cardNo = UUID.randomUUID().toString().replaceAll("-","");
                           map.put("cardNo",cardNo);
                        }
                    }
                    if(title.get(j).equals("民族")){
                        map.put("nation",list.get(j)+"");
                    }
                    if(title.get(j).equals("出生日期")){
                        map.put("birthday",list.get(j)+"");
                    }
                    if(title.get(j).equals("地址")){
                        map.put("address",list.get(j)+"");
                    }
                    if(title.get(j).equals("签发机关")){
                        map.put("organization",list.get(j)+"");
                    }
                    if(title.get(j).equals("有效期限")){
                        String str=list.get(j)+"";
                        if(str!=null&&!str.trim().equals("")){
                            startTime = str.substring(0,str.indexOf("-"));
                            endTime = str.substring(str.indexOf("-")+1);
                            startTime = startTime.replace("/","-");
                            endTime = endTime.replace("/","-");
                            map.put("startTime",startTime);
                            map.put("endTime",endTime);
                        }else{
                            map.put("startTime","");
                            map.put("endTime","");
                        }
                    }
                    if(title.get(j).equals("性别")){
                        sex= list.get(j)+"";
                        if(sex==null||sex.trim().equals("")){
                            globalresponse.setMessage(i + "行,"  + "性别有误，请检查(此行之前的数据已经录入)。");
                            globalresponse.setCode(GlobalMessages.S_40001.getCode());

                        }else{
                            if(sex.equals("男")){
                             account.setSex(1);
                            }else{
                             account.setSex(2);
                            }
                            account.setSex(1);
                            map.put("gender",sex);
                        }

                    }
                    if(title.get(j).equals("工号")){
                        jobNum= list.get(j)+"";
                        account.setJobNum(jobNum);
                    }
                    if(title.get(j).equals("联系电话")){
                        phone= list.get(j)+"";
                        account.setPhone(phone);
                    }
                    if(title.get(j).equals("面试日期")&&list.get(j)!=null){
                        if(list.get(j)!=null&&!list.get(j).toString().trim().equals("")){
                            Date date = null;
                            try {
                                date = simpleDateFormat.parse( list.get(j)+"");
                                account.setiDate(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if(title.get(j).equals("报到日期")&&list.get(j)!=null){
                        if(list.get(j)!=null&&!list.get(j).toString().trim().equals("")){
                            Date date = null;
                            try {
                                date = simpleDateFormat.parse( list.get(j)+"");
                                account.setrDate(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if(title.get(j).equals("离职日期")&&list.get(j)!=null){
                        if(list.get(j)!=null&&!list.get(j).toString().trim().equals("")){
                           // bData= list.get(j)+"".replaceAll("/","-");
                            Date date = null;
                            try {
                                date = simpleDateFormat.parse( list.get(j)+"");
                                account.setlDate(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if(title.get(j).equals("离职方式")){
                        lReason= list.get(j)+"";
                        if(lReason.equals("曠職")){
                            account.setlReason(2);
                        }else{
                            account.setlReason(1);
                        }
                    }
                    if(title.get(j).equals("扣款事由")){
                        Dreason= list.get(j)+"";
                        account.setdReason(Dreason);
                    }
                    if(title.get(j).equals("工种模式")){
                        workType= list.get(j)+"";
                        account.setWorkType(workType);
                    }
                    if(title.get(j).equals("来源中介")){
                        agentName= list.get(j)+"";
                        if(agentName!=null&&!agentName.trim().equals("")){
                             account.setAgentName(agentName);
                            Agent agent1= agentService.getAgnet(agentName);
                            if(agent1!=null){
                                account.setSourceid(agent1.getId());
                            }else{
                                account.setSourceid(1);
                            }
                        }else{
                            account.setSourceid(1);
                            account.setAgentName("自招");
                        }
                    }
                    if(title.get(j).equals("返费价格")){
                        returnFee= list.get(j)+"";
                        map.put("returnFee",returnFee);
                        account.setReturnFee(returnFee);
                    }
                    if(title.get(j).equals("返费打款日期")){
                        if(list.get(j)!=null&&!list.get(j).toString().trim().equals("")){
                          //bData= list.get(j)+"".replaceAll("/","-");
                            Date date = null;
                            try {
                                date = simpleDateFormat.parse( list.get(j)+"");
                                account.setReturnFeeTime(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if(title.get(j).equals("备注")){
                        remark= list.get(j)+"";
                        account.setRemark(remark);
                    }
                    if(title.get(j).equals("开户行")){
                        bankname= list.get(j)+"";
                        accountBank.setName(bankname);
                    }
                    if(title.get(j).equals("卡号")){
                        bankcard= list.get(j)+"";
                        accountBank.setBankCard(bankcard);
                    }
                }
                  account.setCompanyId(id);
                //进行数据的存储,先进行人员数据的存储(英文这里没有唯一标识，所以全部进行插入操作，等以后用身份证进行唯一标识判断，再做判断)
                if(id!=null&&account.getNickname()!=null){
                    Long index = iAccountService.insertAccount(account);
                    map.put("accountid",index);
                    map.put("name",account.getNickname());
                    stationedService.insertAccountIdCard(map);//上传身份证相关信息
                    //进行银行卡的信息和身份信息的存储
                    accountBank.setAccountId(index);
                    accountBank.setName(account.getNickname());
                    System.out.print(accountBank);
                    iAccountBankService.insert(accountBank);
                }
            }
        }else {
            globalresponse.setMessage("上传的文件中没有找到合理的数据，请检查。");
            globalresponse.setCode(GlobalMessages.S_40001.getCode());
            return globalresponse;
        }

        return  globalresponse;
    }


    //下载导入人员的模板
    @RequestMapping("/download/{filename}")
    @ResponseBody
    public String download(@PathVariable("filename") String filename,HttpServletResponse response,HttpServletRequest request){
        //String downloadpath ="D:\\tmp\\"+filename;
        String downloadpath =gunsProperties.getFileDownloadPath()+filename;//上传到服务器上的地址
        DataInputStream in = null;
        OutputStream out = null;
        response.reset();//清空输出流
        try {
            filename= URLEncoder.encode(filename,"UTF-8");//将文件名的格式转换成UTF-8,防止乱码
            response.setHeader("Content-disposition","attachment;filename="+filename);//告知浏览器是要下载文件
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  //response.setContentType("application/x-download");//设置文件类型,先用下面的方法进行设置
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("UTF-8");
            in = new DataInputStream(new FileInputStream(new File(downloadpath)));//将本地文件读入输入流
            out = response.getOutputStream();//输出流
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while((bytes =in.read(bufferOut))!=-1){//用字节输出流读取文件内容
                out.write(bufferOut,0,bytes);//将文件输出
            }
            out.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "下载完成，请注意查收！";
    }



    public static void main(String[] args) {
        String startTime="";//身份证初始日期
        String endTime="";//身份证失效日期
        String str ="2009/02/08-2019/02/08";
        startTime=str.substring(0,str.indexOf("-"));
        endTime=str.substring(str.indexOf("-")+1);
        startTime = startTime.replace("/","-");
        endTime = endTime.replace("/","-");
        String cardNo = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(startTime+"\n"+endTime+"\n"+cardNo);


    }

}
