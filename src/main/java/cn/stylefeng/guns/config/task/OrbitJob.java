package cn.stylefeng.guns.config.task;

import cn.stylefeng.guns.config.GlobalEnums;
import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.util.DateUtils;
import cn.stylefeng.guns.modular.store.dao.SubsidyMapper;
import cn.stylefeng.guns.modular.store.model.EmployeeHistory;
import cn.stylefeng.guns.modular.store.model.Settlement;
import cn.stylefeng.guns.modular.store.service.IEmployeeHistoryService;
import cn.stylefeng.guns.modular.store.service.ISettlementService;
import cn.stylefeng.guns.modular.store.service.ISubsidyService;
import cn.stylefeng.guns.modular.system.model.Subsidy;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 雇员求职历史记录表
 */
@Component
@EnableScheduling
public class OrbitJob {
    private static Logger logger = LoggerFactory.getLogger(OrbitJob.class);

    @Autowired
    private ISettlementService settlementService;

    @Autowired
    private ISubsidyService subsidyService;

    @Autowired
    private IEmployeeHistoryService employeeHistoryService;

    /**
     * 店员提交的雇员表，如果员工的状态为入职中 (52100) 状态，且在轨迹表中相同员工不是相同职位，则增加员工的行为轨迹，对门店的来源进行追溯。
     *
     */
    //@Scheduled(cron="0/10 * * * * ?")
    public void job(){

        // 获取没有删除，有开始时间 没有计算出结束时间的数据。
        List<Subsidy> data = subsidyService.selectList(new EntityWrapper<Subsidy>()
                .eq("del_flag","0")
                .isNotNull("start_wark_date")
                .isNull("end_wark_date"));
        System.out.println(JSON.toJSONString(data));

        for(int i = GlobalNumber.INT_FIX_0; i < data.size(); i ++) {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 结束的时间
            Date endWarkDate = null;
            // 开始的时间
            Date startDate = data.get(i).getStartWarkDate();
            // 获取补贴周期，根据补贴方式和开始时间计算 结束时间。
            int workPeriod = data.get(i).getWorkPeriod();
            System.out.println(workPeriod + " - " + (workPeriod == GlobalNumber.INT_50603) + "-" + GlobalNumber.INT_50603);
            System.out.println(workPeriod + " - " + (workPeriod == GlobalNumber.INT_50604));
            // 根据数据判断方式进行判定如何计算 考勤日
            if(GlobalNumber.INT_50601 == workPeriod) {
                // 考情日-需要导入数据

            } else if(GlobalNumber.INT_50602 == workPeriod) {
                // 工时 -需要导入数据
            } else if(GlobalNumber.INT_50603 == workPeriod) {
                System.out.println("50603");
                // 工作日 - 这里按一周五天给计算
                endWarkDate = DateUtils.getWorkDate(startDate, workPeriod);
            } else if(GlobalNumber.INT_50604 == workPeriod) {
                System.out.println("50604");
                // 自然日 这里按日常天数进行计算
                endWarkDate = DateUtils.addAndSubtractDaysByGetTime(startDate, workPeriod);
            } else if(GlobalNumber.INT_50605 == workPeriod) {
                // 其他 - 这里不做计算
            } else if(GlobalNumber.INT_50606 == workPeriod) {
                // 打卡日-需要导入数据
            }

            Subsidy subsidy = new Subsidy();
            subsidy.setId(data.get(i).getId());
            subsidy.setEndWarkDate(endWarkDate);
            if(endWarkDate != null) {
                subsidyService.updateById(subsidy);
            }

            // 每次计算后睡30秒以免暂用资源
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        }
    }
}
