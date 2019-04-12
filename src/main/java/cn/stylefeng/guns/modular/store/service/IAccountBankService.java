package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.AccountBank;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户银行卡 服务类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-30
 */
public interface IAccountBankService extends IService<AccountBank> {
    AccountBank getAccountBank(Map map);
    boolean updateAccountBank(Map map);
}
