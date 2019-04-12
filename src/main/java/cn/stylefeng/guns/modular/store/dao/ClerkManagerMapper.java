package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.system.model.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClerkManagerMapper extends BaseMapper<User> {

    @Select("SELECT * FROM sys_user SU LEFT JOIN store_clerk_join SCJ ON SCJ.store_clerk_id = SU.id WHERE SCJ.del_flag = 0 AND SU.`status` != 3 AND SCJ.store_id = #{store_manager_id}")
    List<User> selectClerk(java.util.Map<String, Object> params);
}
