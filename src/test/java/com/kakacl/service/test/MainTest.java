package com.kakacl.service.test;

import cn.stylefeng.guns.core.util.BackUtils;
import cn.stylefeng.guns.core.util.WorkDateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author wangwei<br/>
 * @Description: <br/>
 * @date 2018/12/14 11:33<br/>
 * ${TAGS}
 */
public class MainTest {

    // https://apimg.alipay.com/combo.png?d=cashier&t=ICBC 返回银行LOGO图片



    public static void main(String[] args) throws Exception {
        String str = BackUtils.getCardDetail("62155811160069813924");
        JSONObject obj = JSON.parseObject(str);
        if((boolean)obj.get("validated")) {
            if(obj.get("cardType").equals("DC")) {
                System.out.println("DC");
                String bank = (String)obj.get("bank");
                if(bank.equalsIgnoreCase("ICBC")
                        || bank.equalsIgnoreCase("CCB")
                        || bank.equalsIgnoreCase("ABC")
                        || bank.equalsIgnoreCase("BOCM")
                        || bank.equalsIgnoreCase("BOC")) {
                    System.out.println("支持...");
                } else {
                    System.out.println("不支持");
                }
            } else {
                System.out.println("Not DC");
            }
        } else {
            System.out.println("validated false");
        }
        System.out.println(str);

        //new WorkDateUtil().getWorkDays();
    }
}
