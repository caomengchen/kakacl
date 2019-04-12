package cn.stylefeng.guns.modular.store.service;

import cn.stylefeng.guns.modular.store.model.Company;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公司信息 服务类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-29
 */
public interface ICompanyService extends IService<Company> {

    List<Company> selectList(Map<String, Object> params);

    boolean insertCompany(Map<String, Object> params);

    boolean updateDeleteFlag(Map<String, Object> params);

    boolean updateImgPath(String companyImg, String companyName);
    List<Map>getList();
    Map getCompanyByName(String companyname);


}
