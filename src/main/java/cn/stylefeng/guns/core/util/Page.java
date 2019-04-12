package cn.stylefeng.guns.core.util;

import java.util.*;
/**
 * @author wangjunsheng
 * @version v1.0.0
 * @description 分页实体类
 * @date
 */
public class Page {
    private int showCount; //每页显示记录数
    private int totalPage;        //总页数
    private int totalResult;    //总记录数
    private int currentPage;    //当前页
    private int currentResult;    //当前记录起始索引
    private Map<String, Object> params = new HashMap<String, Object>();// 传入查询参数
    public Map<String, Object> getParams() {
        return params;
    }
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    public Page() {
        try {
            this.showCount = 10;
        } catch (Exception e) {
            this.showCount = 20;
        }
    }

    public int getTotalPage() {
        if (totalResult % showCount == 0)
            totalPage = totalResult / showCount;
        else
            totalPage = totalResult / showCount + 1;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getCurrentPage() {
        if (currentPage <= 0)
            currentPage = 1;
        if (currentPage > getTotalPage())
            currentPage = getTotalPage();
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {

        this.showCount = showCount;
    }

    public int getCurrentResult() {
        currentResult = (getCurrentPage() - 1) * getShowCount();
        if (currentResult < 0)
            currentResult = 0;
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }


}
