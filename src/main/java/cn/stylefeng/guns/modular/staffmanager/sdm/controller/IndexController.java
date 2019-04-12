package cn.stylefeng.guns.modular.staffmanager.sdm.controller;

import cn.stylefeng.guns.config.GlobalEnums;
import cn.stylefeng.guns.core.util.DateUtils;
import cn.stylefeng.guns.modular.staffmanager.sdm.service.PeoplerecordService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import java.util.*;

/**
 * @author wangwei
 * @version v1.0.0
 * @description 控制器
 * @date 2019-02-23
 */
@Controller
@RequestMapping("/sdm/index")
public class IndexController extends BaseController {

    private String PREFIX = "/sdm/view/";

    @Autowired
    private PeoplerecordService peoplerecordService;

    @RequestMapping("/index")
    public String index() {
        return PREFIX + "index.html";
    }

    @RequestMapping("/indexDetail")
    public String indexDetail() {
        return PREFIX + "detail/index.html";
    }

    @RequestMapping("/add")
    public String add() {
        return PREFIX + "info_add.html";
    }

    // start_create_time end_create_time or start_entry_time end_entry_time
    // type=1 按创建时间查询 2 按入职时间查询 默认查询最近一天
    @RequestMapping("findInfo")
    public @ResponseBody Object findInfo(Map params, HttpServletRequest request,
                                         @RequestParam(value = "type", defaultValue = "1") String type,
                                         @RequestParam(value = "start_create_time", defaultValue = "0") String start_create_time,
                                         @RequestParam(value = "end_create_time", defaultValue = "0") String end_create_time,
                                         @RequestParam(value = "start_entry_time", defaultValue = "0") String start_entry_time,
                                         @RequestParam(value = "end_entry_time", defaultValue = "0") String end_entry_time,
                                         @RequestParam(value = "work_num", defaultValue = "") String work_num) {
        switch (type) {
            case "1" :
                if(start_create_time.equals("0") && end_create_time.equals("0")) {
                    params.put("start_create_time", DateUtils.getYesterdayDateSecond());
                    params.put("end_create_time", DateUtils.getCurrentDateSecond());
                } else {
                    params.put("start_create_time", DateUtils.dateToSecond(start_create_time));
                    params.put("end_create_time", DateUtils.dateToSecond(end_create_time));
                }
                break;
            case "2" :
                params.put("start_create_time", DateUtils.dateToSecond(start_entry_time));
                params.put("end_create_time", DateUtils.dateToSecond(end_entry_time));
                break;
        }
        params.put("work_num", work_num);
        List<Map> result = peoplerecordService.selectList(params);
        for (int i = 0; i < result.size(); i++) {
            result.get(i).put("create_time", DateUtils.getSecondToString(Long.valueOf(result.get(i).get("create_time") + ""), "yyyy-MM-dd"));
            Object entry_time = result.get(i).get("entry_time");
            if(StringUtils.isNotEmpty(entry_time + "") && entry_time != null) {
                result.get(i).put("entry_time", DateUtils.getSecondToString(Long.valueOf(result.get(i).get("entry_time") + ""), "yyyy-MM-dd"));
            }
            Object income_date = result.get(i).get("income_date");
            if(StringUtils.isNotEmpty(income_date + "") && income_date != null) {
                result.get(i).put("income_date", DateUtils.getSecondToString(Long.valueOf(result.get(i).get("income_date") + ""), "yyyy-MM-dd"));
            }
            Object gender = result.get(i).get("gender");
            if(StringUtils.isNotEmpty(gender + "") && gender != null) {
                result.get(i).put("gender", result.get(i).get("gender").equals("1") ? "男" : "女" );
            }
            result.get(i).put("work_status", GlobalEnums.getName(Integer.parseInt(result.get(i).get("work_status") + "")));
        }
        return result;
    }

    // 保存一条数据 user_name, work_num, id_card, income_date, gender, line, group_name, birthplace, phone_num, entry_time, remack, create_time
    @PostMapping("save")
    public @ResponseBody Object save(Map params, HttpServletRequest request,
                     @RequestParam(value = "user_name") String user_name,
                     @RequestParam(value = "work_num", defaultValue = "0") String work_num,
                     @RequestParam(value = "id_card") String id_card,
                     @RequestParam(value = "income_date") String income_date,
                     @RequestParam(value = "gender") String gender,
                     @RequestParam(value = "line") String line,
                     @RequestParam(value = "group_name") String group_name,
                     @RequestParam(value = "birthplace") String birthplace,
                     @RequestParam(value = "phone_num", defaultValue = "0") String phone_num,
                     @RequestParam(value = "price", defaultValue = "0") String price,
                     @RequestParam(value = "b_type", defaultValue = "0") String b_type,
                     @RequestParam(value = "salary_type", defaultValue = "0") String salary_type,
                     @RequestParam(value = "entry_time", defaultValue = "") String entry_time,
                     @RequestParam(value = "work_status", defaultValue = "52101") String work_status,
                     @RequestParam(value = "record_time", defaultValue = "0") String record_time,
                     @RequestParam(value = "remark", defaultValue = "1") String remark) {
        entry_time = DateUtils.dateToSecond(entry_time);
        income_date = DateUtils.dateToSecond(income_date);
        // user_name, work_num, id_card, income_date, gender, line, group_name, birthplace, phone_num, entry_time, remark
        params.put("create_time", DateUtils.getCurrentDateSecond());
        params.put("user_name", user_name);
        params.put("work_num", work_num);
        params.put("id_card", id_card);
        params.put("income_date", income_date);
        params.put("gender", gender);
        params.put("line", line);
        params.put("group_name", group_name);
        params.put("birthplace", birthplace);
        params.put("phone_num", phone_num);
        params.put("price", price);
        params.put("b_type", b_type);
        params.put("salary_type", salary_type);
        params.put("entry_time", entry_time);
        params.put("work_status", work_status);
        params.put("record_time", record_time);
        params.put("remark", remark);
        peoplerecordService.save(params);
        return SUCCESS_TIP;
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") String id, Model model) {
        Map data = peoplerecordService.selectById(id);
        model.addAttribute("item",data);
        LogObjectHolder.me().set(data);
        return PREFIX + "detail.html";
    }

    // 删除一个用户
    @PostMapping("delete")
    public @ResponseBody Object save(Map params, @RequestParam String id) {
        params.put("id", id);
        peoplerecordService.delete(params);
        return SUCCESS_TIP;
    }
}
