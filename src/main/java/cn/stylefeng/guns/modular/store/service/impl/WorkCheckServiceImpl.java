package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.dao.WorkCheckMapper;
import cn.stylefeng.guns.modular.store.model.WorkCheck;

import cn.stylefeng.guns.modular.store.service.IWorkCheckService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author wangwjunsheng
 * @version v1.0.0
 * @description
 * @date
 */
@Service
public class WorkCheckServiceImpl extends ServiceImpl<WorkCheckMapper, WorkCheck>  implements IWorkCheckService {
    @Autowired
    private WorkCheckMapper workCheckMapper;

    @Override
    public List<WorkCheck> getList(Map map) {
        return workCheckMapper.getList(map);
    }

    @Override
    public List<Map> getPositionList(Integer userid) {
        return workCheckMapper.getPositionList(userid);
    }

    @Override
    public List<Map> getCompanyList(Integer userid) {
        return workCheckMapper.getCompanyList(userid);
    }

    @Override
    public boolean insertWorkCheck(WorkCheck workCheck) {
        return workCheckMapper.insertWorkCheck(workCheck);
    }

    @Override
    public boolean updateWorkCheckById(Integer id) {
        return workCheckMapper.updateWorkCheckById(id);
    }
}
