package cn.stylefeng.guns.core.util;

import cn.stylefeng.guns.config.HttpURLConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author wangwei<br/>
 * @Description: <br/>
 * @date 2018/12/14 11:59<br/>
 * ${TAGS}
 */
public class BackUtils {
    /**
     *
     * @param cardNo 银行卡卡号
     * @return {"bank":"CMB","validated":true,"cardType":"DC","key":"(卡号)","messages":[],"stat":"ok"}
     * 2018年12月14日 12:35:23
     */
    public static String getCardDetail(String cardNo) throws Exception {
        // 创建HttpClient实例
        String url = HttpURLConfig.VALIDATE_AND_CACHE_CARD_INFO_URL;
        url+=cardNo;
        url+="&cardBinCheck=true";
        StringBuilder sb = new StringBuilder();
        URL urlObject = new URL(url);
        URLConnection uc = urlObject.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine = null;
        while ( (inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();
        return sb.toString();
    }
}
