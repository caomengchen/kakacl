package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.system.model.User;

import java.util.List;

public interface ClerkManagerService {

    List<User> selectClerk(java.util.Map<String, Object> params);
}
