package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.dao.StationedMapper;
import cn.stylefeng.guns.modular.store.service.StationedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangwei
 * @version v1.0.0
 * @description
 * @date
 */
@Service
public class StationedServiceImple  implements StationedService {
    @Autowired
    private StationedMapper stationedMapper;
    @Override
    public List<Map> getList(String name) {
        return stationedMapper.getList(name);
    }

    @Override
    public void insertRelation(Map map) {
      stationedMapper.insertRelation(map);
    }

    @Override
    public void deleteRelation(Integer id) {
        stationedMapper.deleteRelation(id);
    }

    @Override
    public void insertAccountIdCard(Map map) {
        stationedMapper.insertAccountIdCard(map);
    }
}
