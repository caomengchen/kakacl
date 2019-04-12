package cn.stylefeng.guns.modular.store.service;

import java.util.List;
import java.util.Map;

public interface StationedService {
    List<Map> getList(String name);
    void insertRelation(Map map);
    void  deleteRelation(Integer id);
    void insertAccountIdCard(Map map);
}
