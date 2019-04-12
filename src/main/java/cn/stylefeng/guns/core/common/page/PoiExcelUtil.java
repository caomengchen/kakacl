package cn.stylefeng.guns.core.common.page;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjunsheng
 * @version v1.0.0
 * @description wangjunsheng
 * @date 2019/3/2
 */
public class PoiExcelUtil {

    public static void main(String[] args) {

        PoiExcelUtil poiExcelUtil = new PoiExcelUtil();
        List<List> list =  new ArrayList<List>();
        for(int i = 0;i<20;i++){
           List<String> list1 = new ArrayList<String>();
            list1.add("tom"+i);
            list1.add("男");
            list1.add(""+i);
            list1.add(""+i);
            list.add(list1);
        }

       // poiExcelUtil.getHSSFWorkbook();
        poiExcelUtil.exportExcel(list,"E:/excel/test.xlsx","E:/excel/test2.xlsx");
        File file = new File("E:/excel/test.xlsx");
        List<Map> list1 =  new ArrayList<Map>();
        list1 = poiExcelUtil.importData(file);
        for (int i = 0;i<list1.size();i++){
            System.out.println(list1.get(i));
        }
           }
//先判断版本信息
    public static boolean isExcel2003(String filepath){
        return filepath.matches("^.+\\\\.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath){
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

//    这里有两点需要注意
//（1）getLastRowNum()并非获取实际行数。因此，需要coder自行判断，是否已经到了最后一行（有效行）。
//（2）这里的类型都是字符串类型，如果需要判断的类型，可根据字段名来和实体类中的属性来进行对比，在控制层转换成你想要的类型

    //导入Excel，将Excel中的数据导出至List中，代码如下(调用此方法得到一个Map类型的List集合，在遍历这个集合，存入数据库)
    public List<Map> importData(File file){
        List<String> flags = new ArrayList<String>();//这个集合用来存储表头，作为字段属性
        Workbook wb = null;
        String cellValue="";
        List<Map> excelList = new ArrayList<Map>();

        if(PoiExcelUtil.isExcel2003(file.getPath())){
            try {
                wb = new HSSFWorkbook(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        else{
            try {
                wb = new XSSFWorkbook(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
                return  null;
            }
        }
      //获取第一张表(待优化{可将表头信息提取出来，用作字段属性对应，再来用作判断，防止取的值和键不对应})
        Sheet sheet = wb.getSheetAt(0);
        for(int i = 0;i<sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);//获取所以为i的行，以0开始
            //下面的两行代码是获取第i行的索引为0的单元格数据
            //循环获取每个单元格的数据
            HashMap map = new HashMap();
        for(int j = 0;j<row.getPhysicalNumberOfCells();j++){
            //获取每一行中每个Cell的数据
            Cell cell = row.getCell(j);
            if(i==0){
                String flag = row.getCell(j).getStringCellValue();
                 flags.add(flag);
            }
            //判断数据的类型
            switch (cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC: //数字
                    cellValue = String.valueOf(cell.getNumericCellValue());
                     map.put(flags.get(j),cellValue);
                    break;
                case Cell.CELL_TYPE_STRING: //字符串
                    cellValue = String.valueOf(cell.getStringCellValue());
                    map.put(flags.get(j),cellValue);
                    break;
                case Cell.CELL_TYPE_BOOLEAN: //Boolean
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    map.put(flags.get(j),cellValue);
                    break;
                case Cell.CELL_TYPE_FORMULA: //公式
                    cellValue = String.valueOf(cell.getCellFormula());
                    map.put(flags.get(j),cellValue);
                    break;
                case Cell.CELL_TYPE_BLANK: //空值
                    cellValue = "";
                    map.put(flags.get(j),cellValue);
                    break;
                case Cell.CELL_TYPE_ERROR: //故障
                    cellValue = "非法字符";
                    map.put(flags.get(j),cellValue);
                    break;
                default:
                    cellValue = "未知类型";
                    map.put(flags.get(j),cellValue);
                    break;
            }
      }
            excelList.add(map);
        }
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        excelList.remove(0);
        return excelList;
    };


    //导出Excel
    //这里我们将纪录，以模板的形式导出Excel，即具有某种格式化。
    //这里将以固定模板模式导出Excel，
    public  void exportExcel(List<List>excelList,String templetFilePath,String exportFilePath){
        try{
            File exportFile = new File(exportFilePath);
            File templetFile = new File(templetFilePath);
            Workbook workBook;
            if(!exportFile.exists()){
                exportFile.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(exportFile);
            FileInputStream fis = new FileInputStream(templetFile);
            if(isExcel2007(templetFile.getPath())){
                workBook = new XSSFWorkbook(fis);
            }else{
                workBook = new HSSFWorkbook(fis);
            }

            Sheet sheet =workBook.getSheetAt(0);
            int rowIndex = 1;
            for(int i =0;i<excelList.size();i++){
                Row row = sheet.createRow(i+1);
                for(int j = 0;j<excelList.get(i).size();j++){
                    row.createCell(j).setCellValue((String)excelList.get(i).get(j));
                }
            }
            workBook.write(out);
            out.flush();
            out.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    //没有模板的导出Excel
        /**
         * 导出Excel
         * @param sheetName sheet名称
         * @param title 标题
         * @param values 内容
         * @param wb HSSFWorkbook对象
         * @return
         */
        public  void getHSSFWorkbook(String sheetName,String []title,List<List> values, HSSFWorkbook wb){

            // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
            if(wb == null){
                wb = new HSSFWorkbook();
            }

            // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(sheetName);

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
            HSSFRow row = sheet.createRow(0);

            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

            //声明列对象
            HSSFCell cell = null;

            //创建标题
            for(int i=0;i<title.length;i++){
                cell = row.createCell(i);
                cell.setCellValue(title[i]);
                cell.setCellStyle(style);
            }

            //创建内容
            for(int i=0;i<values.size();i++){
                row = sheet.createRow(i + 1);
                for(int j=0;j<values.get(i).size();j++){
                    //将内容按顺序赋给对应的列对象
                    row.createCell(j).setCellValue((String) values.get(i).get(j));
                }
            }
            String filePath="E:/excel/test3.xlsx";
            outputExcel(filePath,wb);
        }

         //将Excel表格输出到指定的文件夹位置
         public void outputExcel (String filePath,HSSFWorkbook hssfWorkbook){
            File exportExcel = new File(filePath);

                 try {
                     if(!exportExcel.exists()){
                     exportExcel.createNewFile();
                     };
                     FileOutputStream out = new FileOutputStream(exportExcel);
                     hssfWorkbook.write(out);
                     out.flush();
                     out.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

         }



}
