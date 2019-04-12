package cn.stylefeng.guns.modular.store.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.*;

/**
 * @author wangwei
 * @version v1.0.0
 * @description 人员情况分析任务mapper
 * @date 2019-02-22
 */
public interface PersionAnlysisTaskMapper {

    // 获取有效的授权门店数据集合
    @Select("SELECT * FROM store_authorize WHERE end_time > #{end_time}")
    List<Map> findListAuthorize(Map params);

    // 获取授权有效期内的店长用户集合数据
    @Select("SELECT * FROM sys_user WHERE sys_user.id in (SELECT sys_user_id FROM store_authorize WHERE end_time > #{end_time})")
    List<Map> findStoreManagers(Map params);

    // 查询某段时间内的招聘人数 start_create_time end_create_time
    @Select("SELECT * FROM store_employee_history WHERE create_time BETWEEN #{start_create_time} AND #{end_create_time} AND del_flag = 0")
    List<Map> findPersion(Map params);

    // 获取某个门店下某天的招聘人数
    @Select("select 1 from dual")
    List<Map> findZPNumByDay(Map params);

    // 根据用户主键查询一个求职者
    @Select("SELECT * FROM store_account WHERE id = #{id} LIMIT 1")
    Map findUserById(Map params);

    // 根据用户主键查询一个求职者
    @Select("SELECT * FROM sys_user WHERE id = #{id} LIMIT 1")
    Map findSysUserById(Map params);

    // 根据职位主键查询一条详情
    @Select("SELECT * FROM store_job_detail WHERE job_id = #{job_id} LIMIT 1")
    Map findJobDetailById(Map params);

    // 根据主键查询一条数据
    @Select("SELECT * FROM store_job WHERE id = #{id} LIMIT 1")
    Map findJobById(Map params);

    // 根据店长主键和时间区间查询录入的人员信息 store_manager_id start_create_time end_create_time
    @Select("SELECT * FROM store_employee_history WHERE store_manager_id = #{store_manager_id} AND del_flag = 0 AND create_time BETWEEN #{start_create_time} AND #{end_create_time}")
    List<Map> findUsersByManagerId(Map params);

    // 根据主键查询一条公司数据
    @Select("SELECT * FROM store_job WHERE id = #{id} LIMIT 1")
    Map findCompanyById(Map params);

    // 保存一条分析的求职者信息 user_id, user_name, phone_num, price, b_type, salary_type, source_store_manager, entry_time, work_status, create_time, record_time, remark
    @Insert("INSERT INTO store_people_record_auto_detail_analysis (user_id, user_name, phone_num, price, b_type, salary_type, source_store_manager, entry_time, work_status, create_time, record_time, remark) VALUES (#{user_id}, #{user_name}, #{phone_num}, #{price}, #{b_type}, #{salary_type}, #{source_store_manager}, #{entry_time}, #{work_status}, #{create_time}, #{record_time}, #{remark})")
    boolean insertUserAnalysisDetail(Map params);
}
