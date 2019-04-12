package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.Account;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangwei
 * @since 2018-11-29
 */
public interface AccountMapper extends BaseMapper<Account> {

    @Update("UPDATE store_account SET work_status = #{status} WHERE id = #{id}")
    boolean updateAccountStatus(@Param("id") Long id, @Param("status") Integer status);

    @Update("UPDATE store_account SET phone = #{phone} WHERE id = #{id}")
    boolean updateAccountPhineNum(@Param("id") Long id, @Param("phone") String phone);

    List<Map> getstaffList(Map map);
    boolean updateAccount(Map map);

    Integer insertAccount(Account account);

    List<Map> getallstaffList(Map map);


}
