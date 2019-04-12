package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.Settlement;

import java.util.List;
import java.util.Map;

import cn.stylefeng.guns.modular.store.vo.SettlementVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 结算表 Mapper 接口
 * </p>
 *
 * @author wangwei
 * @since 2018-11-30
 */
public interface SettlementMapper extends BaseMapper<Settlement> {

	Integer selectCount(Map<String, Object> params);

	List<SettlementVo> getSettlementPersonViewList(Map<String, Object> params);
	
}
