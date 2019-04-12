package cn.stylefeng.guns.config.task;

import cn.stylefeng.guns.core.util.DateUtils;
import cn.stylefeng.guns.modular.store.service.PersionAnlysisTaskService;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * @author wangwei
 * @version v1.0.0
 * @description 人员分析任务
 * @date 2019-02-21
 */
@Component
@EnableScheduling
public class PersonnelAnalysisTask extends BaseTask {

    @Autowired
    private PersionAnlysisTaskService persionAnlysisTaskService;

    /*
     * 当前任务每天运行一次
     *
     * 根据天获取每个门店的招聘数据
     * 1. 获取门店列表
     * 2. 获取门店昨天的招聘信息
     * 3. 获取门店招聘的人员信息
     * 4. 获取门店招聘信息的人员基础信息情况
     * 5. 获取人员当前的就职状况
     * 6. 获取补贴信息，人员的工作福利，当前状态
     * 7. 其他关联信息
     *
     */
//    @Scheduled(cron="0/10 * * * * ?")
    public void analysisTask(){
        // 加锁

        Map params = new HashMap();
        List<Map> data = null;
        // 某一天招聘的人数
        int persion_total = 0;
        log.info(" analysisTask {} ", System.currentTimeMillis());
        // 1. 获取所有的门店没有到期的门店信息和门店店长。
        String date = DateUtils.getDate("yyyy-MM-dd");
        params.put("end_time", date);
//      data = persionAnlysisTaskService.findStoreManagers(params);
        // 门店数据
        params.clear();
        params.put("start_create_time", DateUtils.getYesterdayDate("yyyy-MM-dd HH:mm:ss"));
        params.put("end_create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
//        // 查询有效的门店
        data = persionAnlysisTaskService.findPersion(params);
//        log.info("persion data: {}", data);

        for (Map tmp : data) {
            persion_total = 0;
            // 查询店长信息
            Object store_manager_id = tmp.get("store_manager_id");
            params.clear();
            params.put("id", store_manager_id);
            Map sysuserMap = persionAnlysisTaskService.findSysUserById(params);
            log.info("sysuserMap: {}", sysuserMap);
            // 根据店长查询门店招聘人人员信息
            params.clear();
            params.put("start_create_time", DateUtils.getYesterdayDate("yyyy-MM-dd HH:mm:ss"));
            params.put("end_create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            params.put("store_manager_id", store_manager_id);
            List<Map> data2 = persionAnlysisTaskService.findUsersByManagerId(params);
            for (Map tmp2 : data2) {
                ++ persion_total;
                Object user_id = tmp2.get("user_account_id");
                Object job_id = tmp.get("job_id");
                Object work_status = tmp.get("sys_user_new_work_status");
                // 根据用户名查询用户信息
                params.clear();
                params.put("id", user_id);
                // 求职者信息， 用户的状态仅获取当前这个职位的状态，可能不是当前用户的状态，用户有几率同时兼职多个工作岗位。
                Map userMap = persionAnlysisTaskService.findUserById(params);
                if(userMap == null) {
                    continue;
                }
                log.info("userMap: {}", userMap);
                userMap.get("nickname");
                userMap.get("phone");
                // 职位主键查询发布价格和公司
                params.clear();
                params.put("job_id", job_id);
                // 职位价格 福利 条件  薪资 公司主键等信息
                Map jobDetailMap = persionAnlysisTaskService.findJobDetailById(params);
                log.info("jobDetailMap: {}", jobDetailMap);
                params.clear();
                params.put("id", job_id);
                Map jobMap = persionAnlysisTaskService.findJobById(params);
                log.info("jobMap: {}", jobMap);
                // 获取公司信息
                params.clear();
                params.put("id", job_id);
                Map comapnyMap = persionAnlysisTaskService.findCompanyById(params);
                log.info("comapnyMap: {}", comapnyMap);
                // 保存人员信息
                // user_id, user_name, phone_num, price, b_type, salary_type, source_store_manager, entry_time, work_status, create_time, record_time, remark
                params.clear();
                params.put("user_id", user_id);
                params.put("user_name", userMap.get("nickname"));
                params.put("phone_num", userMap.get("phone"));
                params.put("b_type", jobDetailMap.get("billing_cycle"));
                params.put("salary_type", "0");
                params.put("price", "0");
                params.put("source_store_manager", store_manager_id);
                params.put("entry_time", null);
                params.put("work_status", "0");
                params.put("create_time", System.currentTimeMillis());
                params.put("record_time", "0");
                params.put("remark", "");
                boolean flag = persionAnlysisTaskService.insertUserAnalysisDetail(params);
                if(!flag) {
                    log.warn("UserAnalysisDetail warnning : {}", params);
                }
            }

            // 保存门店


        }
        // 电话
        // 发单价格
        // 补贴类型
        // 补贴条件 字符串-10工作日返10元，工时总价类均计算在此字符串内)
        // 薪资类型
        // 求职者来源
        // 当前状态
        // 入职时间
        // 备注（如果求职者离职，则需要离职原因。 如：未知，旷工，主动离职）。
    }

    public static void main(String[] args) {
        String date = DateUtils.getDate("yyyy-MM-dd");
        System.out.println(date);
    }
}
