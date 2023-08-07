package hiFes.hiFes;
import hiFes.hiFes.domain.FestivalTable;
import net.bytebuddy.asm.Advice;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.font.NumericShaper;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static List<FestivalTable> readFestivalTable(InputStream inputStream) throws IOException {
        try(Workbook workbook = new XSSFWorkbook(inputStream)){
            Sheet sheet = workbook.getSheetAt(0);

            List<FestivalTable> festivalTables = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                System.out.println(row);
                if (row == null) {
                    System.out.println("Row " + i + " is empty");
                    continue;
                }
                DataFormatter formatter = new DataFormatter();
// 엑셀 데이터를 사용하여 FestivalTable 객체 생성 및 설정
                String programTitle = row.getCell(0).getStringCellValue();
                String programOutline = row.getCell(1).getStringCellValue();
                double startTimeAsNumber = row.getCell(2).getNumericCellValue();
                LocalDateTime startTime = convertExcelDateToLocalDateTime(startTimeAsNumber);
                double endTimeAsNumber = row.getCell(3).getNumericCellValue();
                LocalDateTime endTime = convertExcelDateToLocalDateTime(endTimeAsNumber);


                FestivalTable festivalTable = FestivalTable.builder()
                        .programTitle(programTitle)
                        .programOutline(programOutline)
                        .startTime(startTime)
                        .endTime(endTime)
                        .build();

                // 설정이 완료된 festivalTable을 목록에 추가
                festivalTables.add(festivalTable);
            }

            workbook.close();
            return festivalTables;

        }

    }

        public static LocalDateTime convertExcelDateToLocalDateTime(double excelDate) {
            Instant instant = DateUtil.getJavaDate(excelDate).toInstant();
            ZoneId zoneId = ZoneId.systemDefault();

            return LocalDateTime.ofInstant(instant, zoneId);
        }
}
