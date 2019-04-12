package cn.stylefeng.guns.modular.staffmanager.sdm.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwei
 * @version v1.0.0
 * @description 测试控制器
 * @date 2019-02-23
 */
@RestController
@RequestMapping("/sdm/testSdm")
public class TestSdmController extends BaseController {

    @RequestMapping("/test")
    public Object auth() {
        return "success";
    }

}
