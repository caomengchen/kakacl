package cn.stylefeng.guns.modular.store.controller;

import cn.stylefeng.guns.config.GlobalNumber;
import cn.stylefeng.guns.config.GlobalResponse;
import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.POIUtil;
import cn.stylefeng.guns.core.common.StrUtils;
import cn.stylefeng.guns.core.common.WriteExcel;
import cn.stylefeng.guns.core.common.exception.StoreExceptionEnum;
import cn.stylefeng.guns.modular.store.model.Company;
import cn.stylefeng.guns.modular.store.service.ICompanyService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.store.model.Position;
import cn.stylefeng.guns.modular.store.service.IPositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 职位信息控制器
 *
 * @author fengshuonan
 * @Date 2018-12-18 13:36:36
 */
@Controller
@RequestMapping("/position")
public class PositionController extends BaseController {

    private String PREFIX = "/store/position/";


    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private ICompanyService companyService;

    /**
     * 跳转到职位信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "position.html";
    }

    /**
     * 跳转到添加职位信息
     */
    @RequestMapping("/position_add")
    public String positionAdd() {
        return PREFIX + "position_add.html";
    }

    /**
     * 跳转到修改职位信息
     */
    @RequestMapping("/position_update/{positionId}")
    public String positionUpdate(@PathVariable Integer positionId, Model model) {
        Position position = positionService.selectById(positionId);
        model.addAttribute("item",position);
        LogObjectHolder.me().set(position);
        return PREFIX + "position_edit.html";
    }

    /**
     * 获取职位信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition, String companyName) {
        Map<String, Object> params = new HashMap<>();
        params.put("positionName", condition);
        params.put("companyName", companyName);
        List<Position> data = positionService.selectList(params);
        return data;
    }

    /**
     * 新增职位信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Position position) {
        if(position.getCompanyId() == null) {
            throw new ServiceException(StoreExceptionEnum.PARAM_ERROR);
        }
        // 相同企业相同职位不可重复
        List<Position> positionList = positionService.selectList(new EntityWrapper<Position>()
                .eq("company_id",position.getCompanyId())
                .eq("name",position.getName())
                .eq("is_deleted",GlobalNumber.INT_DEL_FLAG_0));
        if(positionList.size() != GlobalNumber.INT_FIX_0) {
            throw new ServiceException(StoreExceptionEnum.DATABASE_ERROR_60009);
        }
        position.setCreateTime(new Date());
        position.setIsDeleted(GlobalNumber.INT_DEL_FLAG_0);
        position.setInfo(StrUtils.htmlUnescape(position.getInfo()));
        position.setWelfare(StrUtils.htmlUnescape(position.getWelfare()));
        positionService.insert(position);
        return SUCCESS_TIP;
    }

    /**
     * 删除职位信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer positionId) {
        Map<String, Object> params = new HashedMap();
        params.put("id", positionId);
        params.put("isDeleted", GlobalNumber.INT_DEL_FLAG_1);
        positionService.updateDeleteFlag(params);
        return SUCCESS_TIP;
    }

    /**
     * 修改职位信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Position position) {
        positionService.updateById(position);
        return SUCCESS_TIP;
    }

    /**
     * 职位信息详情
     */
    @RequestMapping(value = "/detail/{positionId}")
    @ResponseBody
    public Object detail(@PathVariable("positionId") Integer positionId) {
        return positionService.selectById(positionId);
    }

    /**
     * 根据职位主键集合查询职位和公司信息，导出到表格
     * @param positionIds
     * @return
     */
    @RequestMapping(value = "/download/{positionIds}")
    @ResponseBody
    public String download(@PathVariable("positionIds") String positionIds,
                           HttpServletRequest req, HttpServletResponse response) {
        String[] paramsIds = positionIds.split(",");
        List<String> result = new ArrayList<>();
        boolean flag;
        for(int i=0;i<paramsIds.length;i++){
            flag = false;
            for(int j=0;j<result.size();j++){
                if(paramsIds[i].equals(result.get(j))){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                result.add(paramsIds[i]);
            }
        }
        paramsIds = (String[]) result.toArray(new String[result.size()]);

        List<Position> positionList = positionService.selectList(new EntityWrapper<Position>().in("id",paramsIds));

        Map<String, String> dataMap = new HashMap<String, String>();
        List<Map> list = new ArrayList<Map>();
        for (int i = 0; i < positionList.size(); i++) {
            dataMap = new HashMap<String, String>();
            Company company = companyService.selectById(positionList.get(i).getCompanyId());
            if(company != null) {
                positionList.get(i).setCompanyName(company.getCompanyName());
            } else {
                positionList.get(i).setCompanyName("未知");
            }
            dataMap.put("companyName", positionList.get(i).getCompanyName());
            dataMap.put("positionName", positionList.get(i).getName());
            list.add(dataMap);
            System.out.println(JSON.toJSONString(company));
        }

        String filedisplay = req.getParameter("filename");
        WriteExcel.writeExcel(list, 2,  gunsProperties.getFileDownloadPath() + filedisplay);

        response.setContentType("application/x-download");
        String filedownload = gunsProperties.getFileDownloadPath() + filedisplay;
        try {
            filedisplay = URLEncoder.encode(filedisplay,"UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        response.addHeader("Content-Disposition","attachment;filename=" + filedisplay);
        OutputStream outp = null;
        FileInputStream in = null;
        try{
            outp = response.getOutputStream();
            in = new FileInputStream(filedownload);
            byte[] b = new byte[1024];
            int i = 0;
            while((i = in.read(b)) > 0)  {
                outp.write(b, 0, i);
            }
            outp.flush();
        } catch(Exception e){
            System.out.println("Error!");
            e.printStackTrace();
        }finally{
            if(in != null)
            {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if(outp != null)
            {
                try {
                    outp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outp = null;
            }
        }
        return "";
    }
}
