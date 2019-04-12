package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.Company;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公司信息 Mapper 接口
 * </p>
 *
 * @author wangwei
 * @since 2018-11-29
 */
public interface CompanyMapper extends BaseMapper<Company> {

    public boolean insertCompany(Map<String, Object> params);

    List<Company> selectListEntity(Map<String, Object> params);

    boolean updateDeleteFlag(Map<String, Object> params);

    @Update("UPDATE store_company SET image=#{companyImg} WHERE company_name = #{companyName}")
    boolean updateImgPath(@Param("companyImg") String companyImg, @Param("companyName")String companyName);

    List<Map> getList();
    Map getCompanyByName(String companyname);

}
