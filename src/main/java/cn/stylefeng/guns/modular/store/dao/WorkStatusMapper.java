package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.WorkStatus;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

public interface WorkStatusMapper extends BaseMapper<WorkStatus> {
  List<Map> getList(Map map);
  List<Map> getPositionList(Integer companyid);
  List<Map> getStaffList(@Param("cid") Integer cid, @Param("pid") Integer pid);
  List<Map>  findWorkCheckList(Integer userid);
  List<Map> getWorkAttendance(Map map);


}
