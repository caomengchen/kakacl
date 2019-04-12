package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.config.GlobalEnums;
import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.modular.store.model.Settlement;
import cn.stylefeng.guns.modular.store.dao.SettlementMapper;
import cn.stylefeng.guns.modular.store.service.ISettlementService;
import cn.stylefeng.guns.modular.store.vo.SettlementVo;
import cn.stylefeng.guns.modular.system.dao.UserMapper;
import cn.stylefeng.guns.modular.system.model.User;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 结算表 服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-30
 */
@Service
public class SettlementServiceImpl extends ServiceImpl<SettlementMapper, Settlement> implements ISettlementService {

    @Resource
    private SettlementMapper mapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public List<SettlementVo> getSettlementPersonViewList(Map<String, Object> params) {
        List<SettlementVo> data = mapper.getSettlementPersonViewList(params);
        for(int i = GlobalNumber.INT_FIX_0; i < data.size(); i ++) {
            int status = data.get(i).getWorkStatus();
            data.get(i).setWorkStatusView(GlobalEnums.getName(status));
            data.get(i).setBillingCycleView(GlobalEnums.getName(data.get(i).getBillingCycle()));
            // 店员主键
            Integer sysUserId =  data.get(i).getSysUserId();
            User u = new User();
            u.setId(sysUserId);
            u = userMapper.selectOne(u);
            if(u.getRoleid().contains(GlobalNumber.INT_FIX_10 + "")) {
                data.get(i).setOriginView(u.getName());
            } else {
                data.get(i).setOriginView(u.getName());
            }

            Long createById = data.get(i).getCreateBy();
        }
        return data;
    }

    @Override
    public Integer selectCount(Map<String, Object> params) {
        return mapper.selectCount(params);
    }
}
