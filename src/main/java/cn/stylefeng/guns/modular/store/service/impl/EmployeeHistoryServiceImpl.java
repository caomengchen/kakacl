package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.model.EmployeeHistory;
import cn.stylefeng.guns.modular.store.dao.EmployeeHistoryMapper;
import cn.stylefeng.guns.modular.store.service.IEmployeeHistoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雇员就职状态历史更新表 服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-12-12
 */
@Service
public class EmployeeHistoryServiceImpl extends ServiceImpl<EmployeeHistoryMapper, EmployeeHistory> implements IEmployeeHistoryService {

}
