package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.dao.WorkCheckMapper;
import cn.stylefeng.guns.modular.store.dao.WorkStatusMapper;
import cn.stylefeng.guns.modular.store.model.WorkCheck;
import cn.stylefeng.guns.modular.store.model.WorkStatus;
import cn.stylefeng.guns.modular.store.service.IWorkCheckService;
import cn.stylefeng.guns.modular.store.service.IWorkStatusService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author wangwjunsheng
 * @version v1.0.0
 * @description
 * @date
 */
@Service
public class WorkStatusServiceImpl extends ServiceImpl<WorkStatusMapper, WorkStatus>  implements IWorkStatusService {
  @Autowired
    private WorkStatusMapper workStatusMapper;
    @Override
    public List<Map> getlist(Map map) {
        return workStatusMapper.getList(map);
    }

  @Override
  public List<Map> findWorkCheckList(Integer userid) {
    return workStatusMapper.findWorkCheckList(userid);
  }

  @Override
  public List<Map> getPositionList(Integer companyid) {
    return workStatusMapper.getPositionList(companyid);
  }

  @Override
  public List<Map> getStaffList(Integer cid, Integer pid) {
    return workStatusMapper.getStaffList(cid,pid);
  }

  @Override
  public List<Map> getWorkAttendance(Map map) {
    return workStatusMapper.getWorkAttendance(map);
  }
}
