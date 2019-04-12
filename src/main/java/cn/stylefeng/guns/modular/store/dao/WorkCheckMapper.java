package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.WorkCheck;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.*;

public interface WorkCheckMapper extends BaseMapper<WorkCheck> {
    List<WorkCheck> getList(Map map);
    List<Map> getPositionList(Integer companyid);
    List<Map> getCompanyList(Integer userid);
    boolean insertWorkCheck(WorkCheck workCheck);
    //逻辑删除
    boolean updateWorkCheckById(Integer id);
}
