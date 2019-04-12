package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.AccountBank;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.Map;

/**
 * <p>
 * 用户银行卡 Mapper 接口
 * </p>
 *
 * @author wangjunsheng
 * @since 2018-11-30
 */
public interface AccountBankMapper extends BaseMapper<AccountBank> {
      AccountBank getAccountBank(Map map);
     boolean updateAccountBank(Map map);
}
