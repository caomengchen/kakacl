package cn.stylefeng.guns.core.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 * @author wangwei<br/>
 * @Description: <br/>
 * @date 2018/12/19 16:04<br/>
 * ${TAGS}
 */
public class StrUtils {

    /**
     * 转义的字符串转换
     * @param paramStr 已经转义的字符串， 将 & gt p 的格式转换为 <p></p> 的格式</>
     * @return
     */
    public static String htmlUnescape(String paramStr) {
        String cont = StringUtils.replaceAll(paramStr, " ", "");
        cont = HtmlUtils.htmlUnescape(cont);
        return cont;
    }


}
