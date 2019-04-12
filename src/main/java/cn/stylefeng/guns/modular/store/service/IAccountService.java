package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.Account;
import cn.stylefeng.guns.modular.store.model.Settlement;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-27
 */
public interface IAccountService extends IService<Account> {

    boolean updateAccountStatus(Map<String, Object> params);

    List<Map> getstaffList(Map map);
    boolean updateAccount(Map map);
    Long insertAccount(Account account);
    List<Map> getallstaffList(Map map);
}
