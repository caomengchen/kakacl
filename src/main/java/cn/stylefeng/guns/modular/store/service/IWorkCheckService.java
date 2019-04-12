package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.WorkCheck;
import com.baomidou.mybatisplus.service.IService;

import java.util.*;

public interface IWorkCheckService extends IService<WorkCheck> {
    List<WorkCheck> getList(Map map);
    List<Map> getPositionList(Integer userid);
    List<Map> getCompanyList(Integer userid);
    boolean insertWorkCheck(WorkCheck workCheck);
    //逻辑删除
    boolean updateWorkCheckById(Integer id);

}
