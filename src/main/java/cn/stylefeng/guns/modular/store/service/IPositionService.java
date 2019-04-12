package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.Position;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职位 服务类
 * </p>
 *
 * @author wangwei
 * @since 2018-12-18
 */
public interface IPositionService extends IService<Position> {

    public List<Position> selectList(Map<String, Object> params);

    boolean updateDeleteFlag(Map<String, Object> params);

}
