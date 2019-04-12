package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.modular.store.model.Company;
import cn.stylefeng.guns.modular.store.dao.CompanyMapper;
import cn.stylefeng.guns.modular.store.service.ICompanyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公司信息 服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-29
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Override
    public List<Company> selectList(Map<String, Object> params) {
        return companyMapper.selectListEntity(params);
    }

    @Override
    public boolean insertCompany(Map<String, Object> params) {
        return companyMapper.insertCompany(params);
    }

    @Override
    public boolean updateDeleteFlag(Map<String, Object> params) {
        return companyMapper.updateDeleteFlag(params);
    }

    @Override
    public boolean updateImgPath(String companyImg, String companyName) {
        return companyMapper.updateImgPath(companyImg, companyName);
    }

    @Override
    public List<Map> getList() {
        return companyMapper.getList();
    }

    @Override
    public Map getCompanyByName(String companyname) {
        return companyMapper.getCompanyByName(companyname);
    }
}
