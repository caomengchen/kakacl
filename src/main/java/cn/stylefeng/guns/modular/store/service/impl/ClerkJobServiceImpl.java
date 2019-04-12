package cn.stylefeng.guns.modular.store.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.modular.store.model.Account;
import cn.stylefeng.guns.modular.store.model.AccountBank;
import cn.stylefeng.guns.modular.store.model.AccountIdCard;
import cn.stylefeng.guns.modular.store.model.ClerkJob;
import cn.stylefeng.guns.modular.store.model.Settlement;
import cn.stylefeng.guns.config.GlobalEnums;
import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.store.dao.AccountBankMapper;
import cn.stylefeng.guns.modular.store.dao.AccountIdCardMapper;
import cn.stylefeng.guns.modular.store.dao.AccountMapper;
import cn.stylefeng.guns.modular.store.dao.ClerkJobMapper;
import cn.stylefeng.guns.modular.store.dao.SettlementMapper;
import cn.stylefeng.guns.modular.store.service.IClerkJobService;
import cn.stylefeng.guns.modular.store.vo.Employee;
import cn.stylefeng.guns.modular.store.vo.Job;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 店员职位表 服务实现类
 * </p>
 *
 * @author wangwei
 * @since 2018-11-29
 */
@Service
public class ClerkJobServiceImpl extends ServiceImpl<ClerkJobMapper, ClerkJob> implements IClerkJobService {

	@Resource
    private ClerkJobMapper clerkJobmapper;
	
	@Resource
	private AccountIdCardMapper accountIdCardMapper;
	
	@Resource
	private AccountBankMapper accountBankMapper;
	
	@Resource
	private AccountMapper accountMapper;
	
	@Resource
	private SettlementMapper settlementMapper;
	
	
	
	@Override
	public List<Job> getClerkJobViewList(Map<String, Object> params) {
		List<cn.stylefeng.guns.modular.store.vo.Job> data = clerkJobmapper.getClerkJobViewList(params);
		for(int i = GlobalNumber.INT_FOR_0; i < data.size(); i ++) {
        	// 单位
        	String unit = data.get(i).getUnit();
        	String unitName = GlobalEnums.getName(Integer.parseInt(unit));
        	data.get(i).setUnit(unitName);
        	// 发薪模式
        	String employee_cycle = data.get(i).getEmployeeCycle();
        	String employeeCycleName = GlobalEnums.getName(Integer.parseInt(employee_cycle));
        	data.get(i).setEmployeeCycle(employeeCycleName);
        	// 补贴方式
			String billing_cycle = data.get(i).getBillingCycle();
			String billingCycle = GlobalEnums.getName(Integer.parseInt(billing_cycle));
			data.get(i).setBillingCycle(billingCycle);

        	// 招聘总人数
        	String number_people = data.get(i).getPeopreNum();
        	// 已入职多少人
        	Map<String, Object> paramsMap = new HashMap<>();
        	paramsMap.put("jobId", data.get(i).getId());
        	Integer seeedingNum = settlementMapper.selectCount(paramsMap);
        	data.get(i).setSeeedingNum(seeedingNum.toString());
        	StringBuffer buffer = new StringBuffer(String.valueOf(seeedingNum)).append('/').append(number_people);
        	data.get(i).setPeopreNum(buffer.toString());
        	// 状态
        	data.get(i).setStatus(GlobalEnums.getName(Integer.parseInt(data.get(i).getStatus())));
        }
		return data;
	}

	@Override
	@Transactional
	public boolean addEmployeeSignIn(Employee employee) {
		boolean flag = false;
		try {
			AccountIdCard accountidcard = new AccountIdCard();
			String cardNo = employee.getIdCard();
			String name = employee.getName();
			String gender = employee.getGender();
			String birthplace = employee.getBirthplace();
			String bankNo = employee.getBackNum();
			String phone = employee.getPhone();
			String cardAddress = employee.getAddress();
			Long jobId = employee.getJobId();
			// 民族
			String nation = employee.getNationality();
			// 结算价格
			String billPrice = employee.getBillPrice();
			// 结算周期
			String billCycle = employee.getBillCycle();
			// 结算方式
			String billingCycle = employee.getBillingCycle();
			
			// 身份证
			accountidcard.setCardNo(cardNo);
			accountidcard = accountIdCardMapper.selectOne(accountidcard);
			// 如果不存在插入身份证信息 如果存在 不做任何操作。
			if(accountidcard == null) {
				accountidcard = new AccountIdCard();
				accountidcard.setCardNo(cardNo);
				accountidcard.setCardAddress(cardAddress);
				accountidcard.setName(name);
				accountidcard.setGender(gender);
				accountidcard.setBirthplace(birthplace);
				accountidcard.setAccountId(0L);
				accountidcard.setNation(nation);
				accountIdCardMapper.insert(accountidcard);
			}
			
			// 检查账户是否存在 如果不存在则新增账户 - 
			Account account = new Account();
			account.setId(accountidcard.getAccountId());
			account = accountMapper.selectOne(account);
			// 增加用户信息
			if(account == null) {
				account = new Account();
				account.setNickname(name);
				account.setPhone(phone);
				account.setWorkStatus(GlobalNumber.INT_52109);
				accountMapper.insert(account);
			} else {
				accountMapper.updateAccountPhineNum(account.getId(), phone);
			}
			// 更新身份证信息的账户主键
			accountIdCardMapper.changeAccountId(accountidcard.getId(), account.getId());
			
			// 查询用户是否有银行卡 如果有则删除  新增银行卡信息
			AccountBank bank = new AccountBank();
			bank.setAccountId(account.getId());
			bank = accountBankMapper.selectOne(bank);
			if(bank == null) {
				bank = new AccountBank();
				bank.setAccountId(account.getId());
				bank.setName(name);
				bank.setBankCard(bankNo);
				accountBankMapper.insert(bank);
			} else {
				accountBankMapper.updateAllColumnById(bank);
			}
			
			// 新增结算信息 store_settlement
			Settlement settlement = new Settlement();
			ShiroUser shiroUser = ShiroKit.getUser();
			settlement.setNo(UUID.randomUUID().toString().replaceAll("-", "") + System.nanoTime());
			settlement.setStoreAccountId(account.getId());
			settlement.setSysUserId(Long.valueOf(shiroUser.getId()));
			settlement.setBillPrice(billPrice);
			settlement.setBillCycle(billCycle);
			settlement.setBillingCycle(Integer.parseInt(billingCycle));
			settlement.setJobId(jobId);
			settlement.setCreateTime(new Date());
			settlement.setDelFlag(0);
			settlement.setCreateBy(Long.valueOf(shiroUser.getId()));
			settlementMapper.insert(settlement);
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		// 这里不增加补贴信息 补贴信息在去人入职后进行增加。
		return flag;
	}

	@Override
	public ClerkJob selectByJobId(Long clerkJobId) {
		return clerkJobmapper.selectByJobId(clerkJobId);
	}

}
