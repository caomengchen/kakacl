package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.WorkCheck;
import cn.stylefeng.guns.modular.store.model.WorkStatus;
import com.baomidou.mybatisplus.service.IService;

import java.util.*;

public interface IWorkStatusService extends IService<WorkStatus> {

    List<Map> getlist(Map map);
    List<Map>  findWorkCheckList(Integer userid);
    List<Map> getPositionList(Integer companyid);
    //查询某个公司某个岗位下面的所有员工
    List<Map> getStaffList(Integer cid,Integer pid);
    //查询上个月，该驻厂管辖下的所有员工
    List<Map> getWorkAttendance(Map map);



}
