package cn.stylefeng.guns.modular.staffmanager.sdm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;

/**
 * @author wangwei
 * @version v1.0.0
 * @description 人员纪录mapper
 * @date 2019-02-25
 */
@Mapper
public interface PeoplerecordMapper {

    // 根据创建时间查询纪录的用户信息 start_create_time end_create_time or start_entry_time end_entry_time
//    @Select("SELECT * FROM store_people_record_auto_detail_analysis WHERE create_time BETWEEN '11' AND '111' AND del_flag = 0")
    @Select({"<script>",
        "SELECT * FROM company_record_staff_info",
        "WHERE 1=1",
        "<when test='start_create_time != null'>",
        "AND create_time BETWEEN #{start_create_time} AND #{end_create_time}",
        "</when>" +
        "<when test='start_entry_time != null'>",
        "AND entry_time BETWEEN #{start_entry_time} AND #{end_entry_time}",
        "</when>" +
        "<when test='work_num != null'>",
        "AND work_num LIKE CONCAT('%',#{work_num},'%')",
        "</when>" +
        "AND del_flag = 0 ORDER BY create_time DESC",
        "</script>"})
    List<Map> selectList(Map params);

    // 保存一条数据 user_name, work_num, id_card, income_date, gender, line, group_name, birthplace, phone_num, entry_time, remack, create_time
    @Insert("INSERT INTO company_record_staff_info (user_name, work_num, id_card, income_date, gender, line, group_name, birthplace, phone_num, entry_time, remark, work_status, create_time) VALUES (#{user_name}, #{work_num}, #{id_card}, #{income_date}, #{gender}, #{line}, #{group_name}, #{birthplace}, #{phone_num}, #{entry_time}, #{remark}, #{work_status}, #{create_time})")
    boolean save(Map params);

    // 更新一条数据 user_id, phone_num, price, b_type, salary_type, source_store_manager, entry_time, work_status, record_time, remark
    @Update("UPDATE company_record_staff_info SET phone_num=#{phone_num}, entry_time=#{entry_time}, work_status=#{work_status}, remark=#{remark} WHERE (id=#{id})")
    boolean update(Map params);

    // 根据主键查询数据
    @Select("SELECT * FROM company_record_staff_info WHERE id = #{id} AND del_flag = 0 LIMIT 1")
    Map selectById(String id);

    // 根据ID删除一个用户
    @Update("update company_record_staff_info set del_flag = 1 where id = #{id}")
    boolean delete(Map params);
}
