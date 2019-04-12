package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.model.Authorize;
import cn.stylefeng.guns.modular.store.dao.AuthorizeMapper;
import cn.stylefeng.guns.modular.store.service.IAuthorizeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-12-04
 */
@Service
public class AuthorizeServiceImpl extends ServiceImpl<AuthorizeMapper, Authorize> implements IAuthorizeService {

    @Resource
    private AuthorizeMapper mapper;

    @Override
    public Authorize selectByStoreId(Integer storeId) {
        return mapper.selectByStoreId(storeId);
    }

    @Override
    public boolean insertAuthorize(Authorize entity) {
        return mapper.insertAuthorize(entity);
    }

    @Override
    public boolean updateAuthorizeStatus(Integer id, String status) {
        return mapper.updateAuthorizeStatus(id, status);
    }
}
