package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.dao.TestLimitMapper;
import cn.stylefeng.guns.modular.system.model.TestLimit;
import cn.stylefeng.guns.modular.system.service.TestLimitService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestLimitServiceImpl extends ServiceImpl<TestLimitMapper,TestLimit> implements TestLimitService {

    @Override
    public List<TestLimit> lists() {

        return this.baseMapper.lists();
    }

    @Override
    public List<Map<String, Object>> selectTests(String bbb) {

        return baseMapper.selectTests(bbb);
    }
}
