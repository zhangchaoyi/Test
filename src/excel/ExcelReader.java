package excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chaoyi.zhang
 * @Date: 2020/8/21 10:24
 */
public class ExcelReader {

    public static List<List<String>> readXlsx(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        return getSheet(xssfSheet);
    }

    private static List<List<String>> getSheet(XSSFSheet xssfSheet){
        if (xssfSheet==null) return null;

        List<List<String>> result = new ArrayList<>();

        //处理当前页，循环读取每一行
        for(int rowNum=1; rowNum<=xssfSheet.getLastRowNum();rowNum++){
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);

            int minColIx = xssfRow.getFirstCellNum();
            int maxColIx = xssfRow.getLastCellNum();

            List<String> rowList = new ArrayList<String>();
            //遍历该行获取处理每个cell元素
            for(int colIx=minColIx;colIx<maxColIx; colIx++){
                XSSFCell cell= xssfRow.getCell(colIx);
                rowList.add(getStringVal(cell));
            }

            result.add(rowList);
        }
        return result;
    }

    public static String getStringVal (XSSFCell cell){
        if (cell==null){
            return "";
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            case Cell.CELL_TYPE_FORMULA://公式格式
                return cell.getCellFormula();
            case Cell.CELL_TYPE_NUMERIC://数字格式
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            default:
                return "????";
        }
    }

    public static void main(String[] args){
        try {
            List<List<String>> result = ExcelReader.readXlsx("C:\\Users\\chaoyi.zhang\\Downloads\\workbench_user.xlsx");
            System.out.println(result);
        } catch(Exception e){
            new RuntimeException(e);
        }
    }
}
