package seedu.address.commons.util;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import seedu.address.model.participant.Person;
import seedu.address.model.tag.Tag;

/**
 * Write the excel sheet.
 */
public class WriteToExcel {

    public static final String WORKING_DIRECTORY_STRING = System.getProperty("user.dir");

    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;
    private static final int THIRD_COLUMN = 2;
    private static final int FOURTH_COLUMN = 3;
    private static final int FIFTH_COLUMN = 4;
    private static final int SIXTH_COLUMN = 5;
    private static final int SEVENTH_COLUMN = 6;
    private static final int EIGHTH_COLUMN = 7;
    private static final int LEFT_OUT_CHARACTER = 4;
    private static final int STARTING_INDEX = 0;
    private static final int RECORD_EMPTY = 0;
    private static final String NAME_TITLE = "NAME";
    private static final String SEX_TITLE = "SEX";
    private static final String BIRTHDAY_TITLE = "BIRTHDAY";
    private static final String PHONE_TITLE = "PHONE";
    private static final String EMAIL_TITLE = "EMAIL";
    private static final String MAJOR_TITLE = "MAJOR";
    private static final String GROUP_TITLE = "GROUP";
    private static final String TAG_TITLE = "TAGS";
    private static final String TAG_SEPARATOR = "---";

    /**
    * Write the excel sheet into Directory.
    */
    public static void writeExcelSheet(List<Person>persons) {
        try {
            String excelFileName = WORKING_DIRECTORY_STRING
                    + System.getProperty("file.separator")
                    + "FOP_MANAGER_LIST.xls";
            HSSFWorkbook wb = new HSSFWorkbook();
            String sheetName = "FOP_CONTACTS";
            HSSFSheet sheet = wb.createSheet(sheetName);
            writeDataIntoExcelSheet(persons, sheet);

            FileOutputStream out = new FileOutputStream(excelFileName);
            wb.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    *Write data into ExcelSheet.
    */
    private static void writeDataIntoExcelSheet(List<Person>persons, HSSFSheet sheet) {

        int rowNum = STARTING_INDEX;
        Row startingRow = sheet.createRow(rowNum);

        writeDataIntoCell(startingRow, FIRST_COLUMN, NAME_TITLE);
        writeDataIntoCell(startingRow, SECOND_COLUMN, SEX_TITLE);
        writeDataIntoCell(startingRow, THIRD_COLUMN, BIRTHDAY_TITLE);
        writeDataIntoCell(startingRow, FOURTH_COLUMN, PHONE_TITLE);
        writeDataIntoCell(startingRow, FIFTH_COLUMN, EMAIL_TITLE);
        writeDataIntoCell(startingRow, SIXTH_COLUMN, MAJOR_TITLE);
        writeDataIntoCell(startingRow, SEVENTH_COLUMN, GROUP_TITLE);
        writeDataIntoCell(startingRow, EIGHTH_COLUMN, TAG_TITLE);

        for (Person person : persons) {
            Row row = sheet.createRow(++rowNum);
            StringBuilder stringBuilder = new StringBuilder();

            writeDataIntoCell(row, FIRST_COLUMN, person.getName().fullName);
            writeDataIntoCell(row, SECOND_COLUMN, person.getSex().value);
            writeDataIntoCell(row, THIRD_COLUMN, person.getBirthday().value);
            writeDataIntoCell(row, FOURTH_COLUMN, person.getPhone().value);
            writeDataIntoCell(row, FIFTH_COLUMN, person.getEmail().value);
            writeDataIntoCell(row, SIXTH_COLUMN, person.getMajor().value);
            writeDataIntoCell(row, SEVENTH_COLUMN, person.getGroup().getGroupName());
            if (person.getTags().size() > RECORD_EMPTY) {
                for (Tag tag : person.getTags()) {
                    stringBuilder.append(tag.tagName + TAG_SEPARATOR);
                }
                writeDataIntoCell(row, EIGHTH_COLUMN, stringBuilder.toString()
                        .substring(STARTING_INDEX, stringBuilder.toString().length() - LEFT_OUT_CHARACTER));
            }
        }
    }
    /**
    * Write data into cell.
    */
    private static void writeDataIntoCell(Row row, int colNum, Object object) {
        if (object instanceof String) {
            row.createCell(colNum).setCellValue((String) object);
        } else {
            row.createCell(colNum).setCellValue((Double) object);
        }
    }
}
