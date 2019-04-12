package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.dao.ClerkManagerMapper;
import cn.stylefeng.guns.modular.store.service.ClerkManagerService;
import cn.stylefeng.guns.modular.system.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ClerkManagerServiceImpl implements ClerkManagerService {

    @Resource
    private ClerkManagerMapper clerkManagerMapper;

    @Override
    public List<User> selectClerk(Map<String, Object> params) {
        return clerkManagerMapper.selectClerk(params);
    }
}
