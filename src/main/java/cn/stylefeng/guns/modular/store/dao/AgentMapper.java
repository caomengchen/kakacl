package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.Agent;
import com.baomidou.mybatisplus.mapper.BaseMapper;


import java.util.List;
import java.util.Map;

public interface AgentMapper extends BaseMapper<Agent> {
    List<Agent>getList(Map map);
    void insertAgent(Map map);
    void updateAgent(Map map);
    void deleteAgent(Map map);
    Agent getAgnet(String name);
}
