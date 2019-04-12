package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.dao.AgentMapper;
import cn.stylefeng.guns.modular.store.model.Agent;
import cn.stylefeng.guns.modular.store.service.AgentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author wangjunsheng
 * @version v1.0.0
 * @description
 * @date
 */
@Service
public class AgentServiceImple extends ServiceImpl<AgentMapper, Agent> implements AgentService {
     @Autowired
     private AgentMapper agentMapper;
    @Override
    public List<Agent> getList(Map map) {
        return agentMapper.getList(map);
    }

    @Override
    public void insertAgent(Map map) {
        agentMapper.insertAgent(map);
    }

    @Override
    public void updateAgent(Map map) {
        agentMapper.updateAgent(map);
    }

    @Override
    public void deleteAgent(Map map) {
        agentMapper.deleteAgent(map);
    }

    @Override
    public Agent getAgnet(String name) {
        return agentMapper.getAgnet(name);
    }


}
