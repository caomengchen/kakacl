package com.kakacl.service.test;

import cn.stylefeng.guns.base.BaseJunit;
import cn.stylefeng.guns.config.GlobalEnums;
import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.util.DateUtils;
import cn.stylefeng.guns.core.util.HolidayUtils;
import cn.stylefeng.guns.core.util.WorkDateUtil;
import cn.stylefeng.guns.modular.store.dao.SettlementMapper;
import cn.stylefeng.guns.modular.store.dao.SubsidyMapper;
import cn.stylefeng.guns.modular.store.model.Settlement;
import cn.stylefeng.guns.modular.store.service.IEmployeeHistoryService;
import cn.stylefeng.guns.modular.store.service.ISettlementService;
import cn.stylefeng.guns.modular.store.service.ISubsidyService;
import cn.stylefeng.guns.modular.system.model.OperationLog;
import cn.stylefeng.guns.modular.system.model.Subsidy;
import cn.stylefeng.guns.modular.system.model.TestLimit;
import cn.stylefeng.guns.modular.system.service.TestLimitService;
import cn.stylefeng.roses.core.util.HttpContext;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javafx.scene.SubScene;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangwei<br/>
 * @Description: <br/>
 * @date 2018/12/14 8:11<br/>
 * ${TAGS}
 */
public class MyTest extends BaseJunit {

    @Resource
    private SubsidyMapper subsidyMapper;

    @Test
    public void test() {
        // 获取没有删除，有开始时间 没有计算出结束时间的数据。
        List<Subsidy> data = subsidyMapper.selectList(new EntityWrapper<Subsidy>()
                .eq("del_flag","0")
                .isNotNull("start_wark_date")
                .isNull("end_wark_date"));
        System.out.println(JSON.toJSONString(data));

        for(int i = GlobalNumber.INT_FIX_0; i < data.size(); i ++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 结束的时间
            Date endWarkDate = null;
            // 开始的时间
            Date startDate = data.get(i).getStartWarkDate();
            // 获取补贴周期，根据补贴方式和开始时间计算 结束时间。
            Integer workPeriod = data.get(i).getWorkPeriod();
            // 根据数据判断方式进行判定如何计算 考勤日
            if(GlobalNumber.INT_50601 == workPeriod) {
                // 考情日-需要导入数据

            } else if(GlobalNumber.INT_50602 == workPeriod) {
                // 工时 -需要导入数据
            } else if(GlobalNumber.INT_50603 == workPeriod) {
                // 工作日 - 这里按一周五天给计算
                endWarkDate = DateUtils.getWorkDate(startDate, workPeriod);
            } else if(GlobalNumber.INT_50604 == workPeriod) {
                // 自然日 这里按日常天数进行计算
                endWarkDate = DateUtils.addAndSubtractDaysByGetTime(new Date(), workPeriod);
            } else if(GlobalNumber.INT_50605 == workPeriod) {
                // 其他 - 这里不做计算
            } else if(GlobalNumber.INT_50606 == workPeriod) {
                // 打卡日-需要导入数据
            }

            Subsidy subsidy = new Subsidy();
            subsidy.setId(data.get(i).getId());
            subsidy.setEndWarkDate(endWarkDate);
            if(endWarkDate != null) {
                subsidyMapper.updateById(subsidy);
            }

            // 每次计算后睡30秒以免暂用资源
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }


//        Page<Subsidy> page = new PageFactory<Subsidy>().defaultPage();
//        HttpServletRequest request = HttpContext.getRequest();
//        request.setAttribute("limit","10");
//        request.setAttribute("offset","10");
//        data = subsidyMapper.selectPage(page, new EntityWrapper<Subsidy>()
//                .eq("del_flag","0")
//                .isNotNull("start_wark_date")
//                .isNull("end_wark_date"));
//
//        System.out.println(JSON.toJSONString(data));
    }

    @Test
    public void ss() {
        try {
            new WorkDateUtil().getWorkDays();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new WorkDateUtil().getWorkDays();
    }
    @Autowired
    private TestLimitService testLimitService;
    @Test
    public void testLimtit(){

        PageHelper.startPage(10,2);
        List<TestLimit> lists  = testLimitService.lists();
        PageInfo<TestLimit>  pageInfo = new PageInfo<TestLimit>(lists);
        System.out .println("结果:"+pageInfo);
    }
}
