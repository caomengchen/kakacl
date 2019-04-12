package cn.stylefeng.guns.system;

import cn.stylefeng.guns.base.BaseJunit;
import cn.stylefeng.guns.modular.store.dao.AccountMapper;
import cn.stylefeng.guns.modular.store.dao.CompanyMapper;
import cn.stylefeng.guns.modular.store.model.Account;
import cn.stylefeng.guns.modular.store.model.Company;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class CompanyTest extends BaseJunit {

    @Resource
    AccountMapper mapper;

    @Resource
    CompanyMapper cMapper;

    @Test
    public void listTest() {
        List<Company> list = this.cMapper.selectList(null);
        for(Company tmp : list) {
            Long id = tmp.getId();
            String name = tmp.getCompanyName();
            System.out.println("INSERT INTO `guns`.`sys_dict` (`num`, `pid`, `name`, `tips`, `code`) VALUES ('"+ id +"', '69', '"+name+"', '', '"+ id +"');");
        }
        assertTrue(list.size() > 0);
    }
}
