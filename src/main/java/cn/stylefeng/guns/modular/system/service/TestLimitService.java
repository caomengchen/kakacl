package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.TestLimit;
import com.baomidou.mybatisplus.service.IService;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Map;

public interface TestLimitService extends IService<TestLimit> {

    public List<TestLimit> lists();

    /**
     * 根据条件查询test列表
     *
     *
     */
    List<Map<String, Object>> selectTests(String condition);
}
