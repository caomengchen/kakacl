package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.TestLimit;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TestLimitMapper extends BaseMapper<TestLimit> {
    public List<TestLimit> lists();

    /**
     * 根据条件查询test列表
     *
     *
     */
    List<Map<String, Object>> selectTests(@Param("bbb") String bbb);
}
