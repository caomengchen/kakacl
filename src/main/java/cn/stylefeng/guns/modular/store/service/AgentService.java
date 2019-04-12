package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.Agent;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

public interface AgentService extends IService<Agent> {
    List<Agent> getList(Map map);
    void insertAgent(Map map);
    void updateAgent(Map map);
    void deleteAgent(Map map);
    Agent getAgnet(String name);
}
