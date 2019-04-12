package cn.stylefeng.guns.modular.store.service;

import java.util.List;
import java.util.Map;

public interface PersionAnlysisTaskService {

    // 获取有效的授权门店数据集合
    List<Map> findListAuthorize(Map params);

    // 获取授权有效期内的店长用户集合数据
    List<Map> findStoreManagers(Map params);

    // 查询某段时间内的招聘人数 start_create_time end_create_time
    List<Map> findPersion(Map params);

    // 获取某个门店下某天的招聘人数
    List<Map> findZPNumByDay(Map params);

    // 根据用户主键查询一个求职者
    Map findUserById(Map params);

    // 根据用户主键查询一个求职者
    Map findSysUserById(Map params);

    // 根据主键查询一条数据
    Map findJobById(Map params);

    // 根据职位主键查询一条详情
    Map findJobDetailById(Map params);

    // 根据店长主键和时间区间查询录入的人员信息
    List<Map> findUsersByManagerId(Map params);

    // 根据主键查询一条公司数据
    Map findCompanyById(Map params);

    // 保存一条分析的求职者信息 user_id, user_name, phone_num, price, b_type, salary_type, source_store_manager, entry_time, work_status, create_time, record_time, remark
    boolean insertUserAnalysisDetail(Map params);
}
