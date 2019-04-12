package cn.stylefeng.guns.modular.staffmanager.sdm.service;

import java.util.List;
import java.util.Map;

public interface PeoplerecordService {

    // 根据创建时间查询纪录的用户信息 start_create_time end_create_time or start_entry_time end_entry_time
    List<Map> selectList(Map params);

    // 保存一条数据 user_name, phone_num, price, b_type, salary_type, source_store_manager, entry_time, work_status, create_time, record_time, remark
    boolean save(Map params);

    // 更新一条数据 user_id, phone_num, price, b_type, salary_type, source_store_manager, entry_time, work_status, record_time, remark
    boolean update(Map params);

    // 根据主键查询数据
    Map selectById(String id);

    // 根据ID删除一个用户
    boolean delete(Map params);
}
