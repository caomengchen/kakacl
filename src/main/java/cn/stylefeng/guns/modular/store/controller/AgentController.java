package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.store.model.Agent;
import cn.stylefeng.guns.modular.store.service.AgentService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangwei
 * @version v1.0.0
 * @description 代理商管理接口
 * @date 2016/3/11
 */
@Controller
@RequestMapping("/agent")
public class AgentController extends BaseController {
    private String PREFIX = "/store/agent/";
    @Autowired
    private AgentService agentService;

    @RequestMapping("")
    public String index(){return PREFIX+"agent.html";}

    //获取当前登录用户下的所有代理商的列表
    @RequestMapping("/getList")
    @ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "代理商名称", required = false, dataType = "String"),})
    @ResponseBody
    public List<Agent>getList(String name){
        ShiroUser shiroUser = ShiroKit.getUser();
        System.out.print(shiroUser.getId());
         Map map =new HashMap();
         map.put("userid",shiroUser.getId());
         map.put("name",name);
         return agentService.getList(map);
    }


    @RequestMapping("/agent_add")
    public String toAgentAdd() { return PREFIX + "agent_add.html"; }

    @RequestMapping("/add")
    @ResponseBody
    public Object agentAdd(Agent agent){
        Map map =new HashMap();
        map.put("name",agent.getName());
        map.put("username",agent.getUsername());
        map.put("userid",agent.getUserid());
        map.put("createTime",agent.getCreateTime());
        map.put("status", 2);
       agentService.insertAgent(map);
            return SUCCESS_TIP;
    }


    @RequestMapping("/agent_update{id}")
    public String toAgentUpdate(@PathVariable Integer id, Model model) {
        Map map =new HashMap();
        ShiroUser shiroUser = ShiroKit.getUser();
        map.put("id",id);
        map.put("userid",shiroUser.getId());
        List<Agent> list =new ArrayList<Agent>();
        list = agentService.getList(map);
        Agent agent = new Agent();
        agent=list.get(0);
         map.put("id",agent.getId());
         map.put("name",agent.getName());
         map.put("username",agent.getUsername());
         map.put("status",agent.getStatus());
        SimpleDateFormat sdf= new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        String createTime="";
        if(agent.getCreateTime()!=null){
            createTime =sdf.format(agent.getCreateTime());
        }
        map.put("createTime",createTime);
        model.addAttribute("item",map);
        LogObjectHolder.me().set(agent);
        return PREFIX + "agent_edit.html"; }


     @RequestMapping("/update")
     @ResponseBody
    public String agentUpdate(Agent agent){
        agent.setUpdateTime(new Date());
        Map map =new HashMap();
        map.put("id",agent.getId());
        map.put("name",agent.getName());
        map.put("username",agent.getUsername());
        map.put("userid",agent.getUserid());
        map.put("updateTime",agent.getUpdateTime());
        map.put("status",agent.getStatus());
        agentService.updateAgent(map);
        return "成功！";
    }
    @RequestMapping("/delete")
    @ResponseBody
    public String agentDelete(Agent agent){
        Map map = new HashMap();
        map.put("id",agent.getId());
        map.put("status",2);
        agentService.deleteAgent(map);
        return "成功！";
    }



}
