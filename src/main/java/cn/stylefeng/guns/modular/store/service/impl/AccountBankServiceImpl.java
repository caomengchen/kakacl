package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.model.AccountBank;
import cn.stylefeng.guns.modular.store.dao.AccountBankMapper;
import cn.stylefeng.guns.modular.store.service.IAccountBankService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 用户银行卡 服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-30
 */
@Service
public class AccountBankServiceImpl extends ServiceImpl<AccountBankMapper, AccountBank> implements IAccountBankService {

    @Autowired
    private AccountBankMapper accountBankMapper;
    @Override
    public AccountBank getAccountBank(Map map) {
        return accountBankMapper.getAccountBank(map);
    }

    @Override
    public boolean updateAccountBank(Map map) {
        return accountBankMapper.updateAccountBank(map);
    }
}
