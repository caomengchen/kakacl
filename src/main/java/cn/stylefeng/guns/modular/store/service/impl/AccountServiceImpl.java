package cn.stylefeng.guns.modular.store.service.impl;

import cn.stylefeng.guns.config.GlobalEnums;
import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.common.exception.StoreExceptionEnum;
import cn.stylefeng.guns.modular.store.model.*;
import cn.stylefeng.guns.modular.store.dao.AccountMapper;
import cn.stylefeng.guns.modular.store.service.*;
import cn.stylefeng.guns.modular.system.model.Subsidy;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-27
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private ISettlementService settlementService;

    @Autowired
    private IEmployeeHistoryService employeeHistoryService;

    @Autowired
    private ISubsidyService subsidyService;

    @Autowired
    private IAccountBankService accountBankService;

    @Autowired
    private IJobService jobService;

    @Autowired
    private IJobDetailService jobDetailService;

    @Override
    @Transactional
    public boolean updateAccountStatus(Map<String, Object> params) {
        boolean flag = false;
         Settlement settlement = (Settlement)params.get("settlement");
         // 就状态
        Account account = accountMapper.selectById(settlement.getStoreAccountId());
        try {
            flag = accountMapper.updateAccountStatus(settlement.getStoreAccountId(), settlement.getAccountStatus());
        } catch (Exception e) {
            flag = false;
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR);
        }

        /**
         * TODO
         * 1.公司给店长的补贴进度
         * 2.计算加盟商的利润计算业务
         * 3.员工的就职轨迹业务
         */
        if(settlement.getAccountStatus() == GlobalEnums.EMPLOYMENT_STATUS_52100.getIndex()) {
            // 查询店长主键 增加员工历史数据
            Settlement settlement1 = settlementService.selectById(settlement.getId());
            EmployeeHistory entity = new EmployeeHistory();
            entity.setCreateTime(new Date());
            entity.setJobId(settlement1.getJobId());
            entity.setStoreManagerId(settlement1.getSysUserId());
            entity.setSysUserNewWorkStatus(settlement.getAccountStatus());
            entity.setSysUserOldWorkStatus(account.getWorkStatus());
            entity.setUserAccountId(settlement1.getStoreAccountId());
            entity.setDelFlag(GlobalNumber.INT_DEL_FLAG_0);
            employeeHistoryService.insert(entity);

            // 增加利润核算表中数据
            Subsidy sub = new Subsidy();
            sub.setType(GlobalNumber.INT_FIX_1);
            sub.setNo(System.currentTimeMillis() + "");
            sub.setPartyA(Long.valueOf(settlement1.getSysUserId()));
            // 根据用户查询用户的银行卡
            List<AccountBank> aList = accountBankService.selectList(new EntityWrapper<AccountBank>().eq("account_id", settlement1.getStoreAccountId()));
            if(aList != null && aList.size() > GlobalNumber.INT_FIX_0) {
                sub.setPartyBIdCardId(aList.get(GlobalNumber.INT_FIX_0).getId());
                sub.setPartBBackCard(aList.get(GlobalNumber.INT_FIX_0).getBankCode());
            } else {
                sub.setPartyBIdCardId(Long.valueOf(GlobalNumber.INT_FIX_0));
                sub.setPartBBackCard("");
            }
            sub.setPartyBPhoneNum(account.getPhone());
            List<Job> jList = jobService.selectList(new EntityWrapper<Job>().eq("id", settlement1.getJobId()));
            Job job = null;
            if(jList != null && jList.size() > GlobalNumber.INT_FIX_0) {
                job = jList.get(GlobalNumber.INT_FIX_0);
                sub.setCompanyId(jList.get(GlobalNumber.INT_FIX_0).getCompanyId());
                // 职位
                sub.setWorkType(jList.get(GlobalNumber.INT_FIX_0).getPositionId());
            } else {
                throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR);
            }
            sub.setStartWarkDate(settlement.getStartWarkDate());
            sub.setEmployeeCycle(GlobalEnums.B_50706.getIndex());
            List<JobDetail> jdList = jobDetailService.selectList(new EntityWrapper<JobDetail>().eq("job_id",job.getId()));
            JobDetail jd = null;
            if(jdList != null && jdList.size() > GlobalNumber.INT_FIX_0) {
                jd = jdList.get(GlobalNumber.INT_FIX_0);
            } else {
                throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR);
            }
            // 补贴方式
            sub.setBillingCycle(jd.getBillingCycle() + "");
            // 公司支付给门店的价格
            sub.setSubsidyPrice(jd.getAllowancePrice());
            // 补贴单位
            sub.setWorkPeriod(jd.getUnit());
            // 补贴周期
            sub.setPeriod(jd.getAllowanceCycle());
            sub.setDelFlag(GlobalNumber.INT_DEL_FLAG_0);
            sub.setCreateTime(new Date());
            subsidyService.insert(sub);
        }
        return flag;
    }

    //获取当前驻厂管理的员工信息
    @Override
    public List<Map> getstaffList(Map map ) {
        return accountMapper.getstaffList(map);
    }

    @Override
    public boolean updateAccount(Map map) {
        return accountMapper.updateAccount(map);
    }

    @Override
    public Long insertAccount(Account account) {
        accountMapper.insertAccount(account);
        return account.getId();
    }


    @Override
    /*
     *[0 0/5 14,18 * * ?] 每天14点整和18点整，每隔5分钟执行一次
     * 【0 15 10 ? * 1-6】每个月的周一至周六10:15分执行一次
     * 【0 0】
     *//*
    Scheduled(cron = "0 0 0 ? * 1-7")//秒，分，时，天，月，星期*/
    public List<Map> getallstaffList(Map map) {

        return accountMapper.getallstaffList(map);
    }
}
