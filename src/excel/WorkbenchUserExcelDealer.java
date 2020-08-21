package excel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: chaoyi.zhang
 * @Date: 2020/8/21 10:56
 */
public class WorkbenchUserExcelDealer {

    public static List<WorkbenchUser> getWorkbenchUser(){
        try {
            List<List<String>> result = ExcelReader.readXlsx("C:\\Users\\chaoyi.zhang\\Downloads\\workbench_user.xlsx");

            Map<String, List<WorkbenchUserRecord>> rawRecord = result.stream().map(l -> {
                WorkbenchUserRecord record = new WorkbenchUserRecord();
                record.setId(l.get(0));
                record.setUserName(l.get(1));
                record.setUserRealName(l.get(2));
                record.setEmail(l.get(3));
                record.setRoleCode(l.get(4));
                record.setRoleName(l.get(5));
                record.setResourceCode(l.get(6));
                record.setResourceName(l.get(7));
                record.setResourceType(l.get(8));
                return record;
            }).collect(Collectors.groupingBy(WorkbenchUserRecord::getUserName));

            //数据聚合
            List<WorkbenchUser> users = rawRecord.entrySet().stream().map(raw -> {
                WorkbenchUser user = new WorkbenchUser();

                List<WorkbenchUserRecord> records = raw.getValue();

                //取首条获取基本信息
                WorkbenchUserRecord base = records.get(0);

                user.setId(base.getId());
                user.setEmail(base.getEmail());
                user.setUserName(base.getUserName());
                user.setUserRealName(base.getUserRealName());
                user.setRoleNames(records.stream().map(WorkbenchUserRecord::getRoleName).collect(Collectors.toSet()));
                user.setResourceNames(records.stream().map(WorkbenchUserRecord::getResourceName).collect(Collectors.toSet()));
                return user;
            }).collect(Collectors.toList());

            return users;

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void exportToExcel(List<WorkbenchUser> users) {
        if (CollectionUtils.isEmpty(users)) {
            return;
        }
        try {
            //创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("sheet0");
            //表头
            HSSFRow rowHeader = sheet.createRow(0);
            rowHeader.createCell(0).setCellValue("id");
            rowHeader.createCell(1).setCellValue("userName");
            rowHeader.createCell(2).setCellValue("userRealName");
            rowHeader.createCell(3).setCellValue("email");
            rowHeader.createCell(4).setCellValue("roleNames");
            rowHeader.createCell(5).setCellValue("permissions");

            HSSFRow row = null;
            HSSFCell cell = null;
            int valueStartRow = 1;

            for (WorkbenchUser user : users) {
                row = sheet.createRow(valueStartRow);
                cell = row.createCell(0);
                cell.setCellValue(user.getId());
                cell = row.createCell(1);
                cell.setCellValue(user.getUserName());
                cell = row.createCell(2);
                cell.setCellValue(user.getUserRealName());
                cell = row.createCell(3);
                cell.setCellValue(user.getEmail());
                cell = row.createCell(4);
                cell.setCellValue(StringUtils.join(user.getRoleNames(), ","));
                cell = row.createCell(5);
                cell.setCellValue(StringUtils.join(user.getResourceNames(), ","));

                valueStartRow++;
            }

            wb.write(new FileOutputStream("C:\\Users\\chaoyi.zhang\\work\\tmp\\workbench_user.xls"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        List<WorkbenchUser> users = getWorkbenchUser();

        exportToExcel(users);

        System.out.println("success");
    }
}
