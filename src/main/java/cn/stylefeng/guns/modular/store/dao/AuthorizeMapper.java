package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.Authorize;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangwei
 * @since 2018-12-04
 */
public interface AuthorizeMapper extends BaseMapper<Authorize> {

    @Select("select * from store_authorize where sys_user_id = #{storeId}")
    Authorize selectByStoreId(Integer storeId);

    @Insert("INSERT INTO store_authorize (name, sys_user_id, start_time, end_time, status, create_by) VALUES (#{entity.name},#{entity.sysUserId}, #{entity.startTime}, #{entity.endTime}, #{entity.status}, #{entity.createBy})")
    boolean insertAuthorize(@Param("entity")Authorize authorize);

    @Update("UPDATE store_authorize SET status=#{status} WHERE id=#{id}")
    boolean updateAuthorizeStatus(@Param("id") Integer id, @Param("status")String status);
}
