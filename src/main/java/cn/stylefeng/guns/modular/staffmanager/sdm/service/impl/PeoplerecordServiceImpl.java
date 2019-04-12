package cn.stylefeng.guns.modular.staffmanager.sdm.service.impl;

import cn.stylefeng.guns.modular.staffmanager.sdm.dao.PeoplerecordMapper;
import cn.stylefeng.guns.modular.staffmanager.sdm.service.PeoplerecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangwei
 * @version v1.0.0
 * @description 用户纪录实现类
 * @date 2019-02-25
 */
@Service
public class PeoplerecordServiceImpl
        implements PeoplerecordService {

    @Autowired
    private PeoplerecordMapper peoplerecordMapper;


    @Override
    public List<Map> selectList(Map params) {
        return peoplerecordMapper.selectList(params);
    }

    @Override
    public boolean save(Map params) {
        return peoplerecordMapper.save(params);
    }

    @Override
    public boolean update(Map params) {
        return peoplerecordMapper.update(params);
    }

    @Override
    public Map selectById(String id) {
        return peoplerecordMapper.selectById(id);
    }

    @Override
    public boolean delete(Map params) {
        return peoplerecordMapper.delete(params);
    }
}
