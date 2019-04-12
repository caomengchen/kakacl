/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.store.model.Authorize;
import cn.stylefeng.guns.modular.store.service.IAuthorizeService;
import cn.stylefeng.guns.modular.system.model.Notice;
import cn.stylefeng.guns.modular.system.service.INoticeService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 总览信息
 *
 * @author fengshuonan
 * @Date 2017年3月4日23:05:54
 */
@Controller
@RequestMapping("/blackboard")
public class BlackboardController extends BaseController {

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private IAuthorizeService authorizeService;

    /**
     * 跳转到黑板
     */
    @RequestMapping("")
    public String blackboard(Model model) {
        List<Map<String, Object>> notices = noticeService.list(null);
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = GlobalNumber.INT_FIX_0; i < notices.size(); i++) {
            Map tmp = notices.get(i);
            if(!tmp.get("del_flag").equals(GlobalNumber.INT_FIX_1)) {
                try{
                    Integer cont_type = Integer.parseInt(tmp.get("cont_type").toString());
                    String strHex = Integer.toHexString(cont_type);
                    tmp.put("contTypeHex", "&#x" + strHex);
                } catch (Exception e){
                    String strHex = Integer.toHexString(GlobalNumber.INT_57393);
                    tmp.put("cont_type", GlobalNumber.INT_57393);
                    tmp.put("contTypeHex", "&#x" + strHex);
                }


                result.add(tmp);
                if(result.size() == GlobalNumber.INT_FIX_10) {
                    break;
                }
            }
        }
        model.addAttribute("noticeList", result);
        // 根据当前登录的用户进行提醒加盟商商户信息查询。
        ShiroUser u =  ShiroKit.getUser();
        Authorize authorize = authorizeService.selectByStoreId(u.getId());
        if(authorize == null){
            authorize = new Authorize();
        }
        model.addAttribute("authorize", authorize);
        return "/blackboard.html";
    }
}
