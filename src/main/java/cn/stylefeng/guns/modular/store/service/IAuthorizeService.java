package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.Authorize;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 门店授权表 服务类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-27
 */
public interface IAuthorizeService extends IService<Authorize> {

    Authorize selectByStoreId(Integer storeId);

    boolean insertAuthorize(Authorize entity);

    boolean updateAuthorizeStatus(Integer id, String status);
}
