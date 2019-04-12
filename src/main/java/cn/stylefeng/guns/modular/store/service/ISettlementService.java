package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.Settlement;
import cn.stylefeng.guns.modular.store.vo.SettlementVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 结算表 服务类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-30
 */
public interface ISettlementService extends IService<Settlement> {

    List<SettlementVo> getSettlementPersonViewList(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);
}
