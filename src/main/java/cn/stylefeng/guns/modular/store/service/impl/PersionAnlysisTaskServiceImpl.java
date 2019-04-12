package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.dao.PersionAnlysisTaskMapper;
import cn.stylefeng.guns.modular.store.service.PersionAnlysisTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangwei
 * @version v1.0.0
 * @description 人员信息分析实现
 * @date 2019-02-22
 */
@Service
public class PersionAnlysisTaskServiceImpl implements PersionAnlysisTaskService {

    @Autowired
    private PersionAnlysisTaskMapper persionAnlysisTaskMapper;

    @Override
    public List<Map> findListAuthorize(Map params) {
        return persionAnlysisTaskMapper.findListAuthorize(params);
    }

    @Override
    public List<Map> findStoreManagers(Map params) {
        return persionAnlysisTaskMapper.findStoreManagers(params);
    }

    @Override
    public List<Map> findPersion(Map params) {
        return persionAnlysisTaskMapper.findPersion(params);
    }

    @Override
    public List<Map> findZPNumByDay(Map params) {
        return persionAnlysisTaskMapper.findZPNumByDay(params);
    }

    @Override
    public Map findUserById(Map params) {
        return persionAnlysisTaskMapper.findUserById(params);
    }

    @Override
    public Map findSysUserById(Map params) {
        return persionAnlysisTaskMapper.findSysUserById(params);
    }

    @Override
    public Map findJobById(Map params) {
        return persionAnlysisTaskMapper.findJobById(params);
    }

    @Override
    public Map findJobDetailById(Map params) {
        return persionAnlysisTaskMapper.findJobDetailById(params);
    }

    @Override
    public List<Map> findUsersByManagerId(Map params) {
        return persionAnlysisTaskMapper.findUsersByManagerId(params);
    }

    @Override
    public Map findCompanyById(Map params) {
        return persionAnlysisTaskMapper.findCompanyById(params);
    }

    @Override
    public boolean insertUserAnalysisDetail(Map params) {
        return persionAnlysisTaskMapper.insertUserAnalysisDetail(params);
    }
}
