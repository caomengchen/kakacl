package cn.stylefeng.guns.core;

import cn.stylefeng.guns.config.properties.GunsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;

/**
 * @author wangjunsheng
 * @version v1.0.0
 * @description 公用的文件下载类
 * @date
 */
public class DownLoadUtil {
    @Autowired
    private GunsProperties gunsProperties;


    //下载本地文件
    public String download(String filename, HttpServletResponse response){
        //String downloadpath ="D:\\tmp\\"+filename;
        String downloadpath =gunsProperties.getFileDownloadPath()+filename;//文件在服务器上的地址
        DataInputStream in = null;
        OutputStream out = null;
        response.reset();//清空输出流
        try {
            filename= URLEncoder.encode(filename,"UTF-8");//将文件名的格式转换成UTF-8,防止乱码
            response.setHeader("Content-disposition","attachment;filename="+filename);//告知浏览器是要下载文件
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  //2.response.setContentType("application/x-download");//设置文件类型,先用下面的方法进行设置
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("UTF-8");
            in = new DataInputStream(new FileInputStream(new File(downloadpath)));//将本地文件读入输入流
            out = response.getOutputStream();//输出流
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while((bytes =in.read(bufferOut))!=-1){//用字节输出流读取文件内容
                out.write(bufferOut,0,bytes);//将文件输出
            }
            out.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "下载完成，请注意查收！";
    }



//下载网络文件(还没有测试过)
public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
    URL url = new URL(urlStr);
    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    //设置超时间为3秒
    conn.setConnectTimeout(3*1000);
    //防止屏蔽程序抓取而返回403错误
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

    //得到输入流
    InputStream inputStream = conn.getInputStream();
    //获取自己数组
    byte[] getData = readInputStream(inputStream);
    //文件保存位置
    File saveDir = new File(savePath);
    if(!saveDir.exists()){
        saveDir.mkdir();
    }
    File file = new File(saveDir+File.separator+fileName);
    FileOutputStream fos = new FileOutputStream(file);
    fos.write(getData);
    if(fos!=null){
        fos.close();
    }
    if(inputStream!=null){
        inputStream.close();
    }
    System.out.println("info:"+url+" download success");
}
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


}
