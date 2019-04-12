package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.model.Position;
import cn.stylefeng.guns.modular.store.dao.PositionMapper;
import cn.stylefeng.guns.modular.store.service.IPositionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职位 服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-12-18
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

    @Resource
    private PositionMapper positionMapper;

    @Override
    public List<Position> selectList(Map<String, Object> params) {
        return positionMapper.selectListEntity(params);
    }

    @Override
    public boolean updateDeleteFlag(Map<String, Object> params) {
        return positionMapper.updateDeleteFlag(params);
    }
}
