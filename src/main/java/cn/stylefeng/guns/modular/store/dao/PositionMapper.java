package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.Position;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职位 Mapper 接口
 * </p>
 *
 * @author wangwei
 * @since 2018-12-18
 */
public interface PositionMapper extends BaseMapper<Position> {

    public List<Position> selectListEntity(Map<String, Object> params);
    boolean updateDeleteFlag(Map<String, Object> params);
}
