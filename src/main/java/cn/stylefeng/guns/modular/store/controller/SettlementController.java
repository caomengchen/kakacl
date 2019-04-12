package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalEnums;
import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.common.exception.StoreExceptionEnum;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.store.model.Account;
import cn.stylefeng.guns.modular.store.model.AccountIdCard;
import cn.stylefeng.guns.modular.store.service.IAccountIdCardService;
import cn.stylefeng.guns.modular.store.service.IAccountService;
import cn.stylefeng.guns.modular.store.vo.SettlementVo;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.store.model.Settlement;
import cn.stylefeng.guns.modular.store.service.ISettlementService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门店结算控制器
 *
 * @author fengshuonan
 * @Date 2018-11-30 14:01:57
 */
@Controller
@RequestMapping("/settlement")
public class SettlementController extends BaseController {

    private String PREFIX = "/store/settlement/";

    @Autowired
    private ISettlementService settlementService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IAccountIdCardService iAccountIdCardService;

    /**
     * 跳转到门店结算首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "settlement.html";
    }

    /**
     * 跳转到添加门店结算
     */
    @RequestMapping("/settlement_add")
    public String settlementAdd() {
        return PREFIX + "settlement_add.html";
    }

    /**
     * 跳转到修改门店结算
     * 店长修改数据
     */
    @RequestMapping("/store_settlement_update/{settlementId}")
    public String settlementUpdate(@PathVariable Integer settlementId, Model model) {
        Settlement settlement = settlementService.selectById(settlementId);
        Account account = accountService.selectById(settlement.getStoreAccountId());
        if(!account.getWorkStatus().equals(GlobalEnums.EMPLOYMENT_STATUS_52109.getIndex())) {
            throw new ServiceException(StoreExceptionEnum.AUTH_REQUEST_ERROR_40001);
        }
        model.addAttribute("item",settlement);
        LogObjectHolder.me().set(settlement);
        return PREFIX + "settlement_store_edit.html";
    }

    @RequestMapping("/settlement_update_status/{settlementId}")
    public String settlementUpdateStatus(@PathVariable Integer settlementId, Model model) {
        Settlement settlement = settlementService.selectById(settlementId);
        model.addAttribute("item",settlement);
        LogObjectHolder.me().set(settlement);
        return PREFIX + "settlement_update_status.html";
    }

    /**
     * 获取门店结算列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String username, String workStatus, String billingCycle, String units) {
        ShiroUser user = ShiroKit.getUser();
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", user.getId());
        params.put("userName", username);
        params.put("workStatus", workStatus);
        params.put("billingCycle", billingCycle);
        params.put("units", units);
        List<Integer> roleList = user.getRoleList();
        if(roleList.contains(GlobalNumber.INT_ROLE_10)) {
           // 录入员查询信息
            params.remove("storeId");
        } else if(roleList.contains(GlobalNumber.INT_CLERK_ROLE_7)) {
            // 店员
            params.remove("storeId");
            params.put("clerkId", user.getId());
        }
        List<SettlementVo> resultData = settlementService.getSettlementPersonViewList(params);
        return resultData;
    }

    /**
     * 新增门店结算
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Settlement settlement) {
        settlementService.insert(settlement);
        return SUCCESS_TIP;
    }

    /**
     * 删除门店结算
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long settlementId) {
        // settlementService.deleteById(settlementId);
        Settlement entity = new Settlement();
        entity.setId(settlementId);
        entity.setDelFlag(GlobalNumber.INT_DEL_FLAG_1);
        boolean flag = settlementService.updateById(entity);
        if(flag)
            return SUCCESS_TIP;
        throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR);
    }

    /**
     * 修改门店结算
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Settlement settlement) {
        settlementService.updateById(settlement);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户状态
     * <pre>
     *     这个方法仅支持平台调用，修改员工的状态，
     *     此方法涉及到对加盟商的费用和员工的就职轨迹进行计算，对加盟商的二次转换进行统计，请格外小心调用。
     * </pre>
     * @param settlement
     * @return
     */
    @RequestMapping(value = "/updateAccountStatus")
    @ResponseBody
    public Object updateAccountStatus(Settlement settlement) {
        //settlement, settlement.getStoreAccountId(), settlement.getAccountStatus()
        Map<String, Object> params = new HashMap<>();
        params.put("settlement", settlement);
        accountService.updateAccountStatus(params);
        return SUCCESS_TIP;
    }

    /**
     * 门店结算详情
     */
    @RequestMapping(value = "/detail/{settlementId}")
    @ResponseBody
    public Object detail(@PathVariable("settlementId") Integer settlementId) {
        return settlementService.selectById(settlementId);
    }
}
