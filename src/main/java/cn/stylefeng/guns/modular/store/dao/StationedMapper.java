package cn.stylefeng.guns.modular.store.dao;
import java.util.*;
public interface StationedMapper {
    List<Map> getList(String name);
    void insertRelation(Map map);
    void  deleteRelation(Integer id);
    void insertAccountIdCard(Map map);


}
