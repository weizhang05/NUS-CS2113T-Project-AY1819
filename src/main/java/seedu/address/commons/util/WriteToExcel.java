package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.grouping.Group;
import seedu.address.model.participant.*;
import seedu.address.model.participant.Name;
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
     * Write the excel sheet into Directory.
     */
    public static void writeExcelSheetFreshmen(List<Person>persons) {
        try {
            String excelFileName = WORKING_DIRECTORY_STRING
                    + System.getProperty("file.separator")
                    + "FOP_MANAGER_FRESHMEN_LIST.xls";
            HSSFWorkbook wb = new HSSFWorkbook();
            String sheetName = "FOP_CONTACTS_FRESHMEN_ONLY";
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
     * Write the excel sheet into Directory.
     */
    public static void writeExcelSheetOgl(List<Person>persons) {
        try {
            String excelFileName = WORKING_DIRECTORY_STRING
                    + System.getProperty("file.separator")
                    + "FOP_MANAGER_OGL_LIST.xls";
            HSSFWorkbook wb = new HSSFWorkbook();
            String sheetName = "FOP_CONTACTS_OGL_ONLY";
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
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Reading Data.
     */
    public static List<Person> readFromExcel() {
        String excelFileName = WORKING_DIRECTORY_STRING
                + System.getProperty("file.separator")
                + "FOP_MANAGER_LIST.xls";
        List<Person> person = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(excelFileName));
            HSSFWorkbook wb = new HSSFWorkbook(file);
            HSSFSheet sheet = wb.getSheetAt(0);


            //check if the cell headings match
            Row row = sheet.getRow(0);
            int titleCheck = 1;
            System.out.println(row.getCell(1).getStringCellValue());
            System.out.println(row.getCell(2).getStringCellValue());
            System.out.println(row.getCell(3).getStringCellValue());
            System.out.println(row.getCell(4).getStringCellValue());
            System.out.println(row.getCell(5).getStringCellValue());
            System.out.println(row.getCell(6).getStringCellValue());
            System.out.println(row.getCell(7).getStringCellValue());
            if ((row.getCell(0).getStringCellValue() == NAME_TITLE)
                    && (row.getCell(1).getStringCellValue() == SEX_TITLE)
                    && (row.getCell(2).getStringCellValue() == BIRTHDAY_TITLE)
                    && (row.getCell(3).getStringCellValue() == PHONE_TITLE)
                    && (row.getCell(4).getStringCellValue() == EMAIL_TITLE)
                    && (row.getCell(5).getStringCellValue() == MAJOR_TITLE)
                    && (row.getCell(6).getStringCellValue() == GROUP_TITLE)
                    && (row.getCell(7).getStringCellValue() == TAG_TITLE)) {
                titleCheck = 1;
            } else {
                titleCheck = 0;
            }

            String nameString;
            String sexString;
            String birthdayString;
            String phoneString;
            String emailString;
            String majorString;
            String groupString;
            String tagString;

            //System.out.println(titleCheck);
            if (titleCheck == 1) {
                //continue
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row rowStart = sheet.getRow(i);
                    int colNum = 0;
                    Cell cell = rowStart.getCell(0);
                    nameString = cell.getStringCellValue();
                    System.out.println(nameString);
                    cell = rowStart.getCell(1);
                    sexString = cell.getStringCellValue();
                    System.out.println(sexString);
                    cell = rowStart.getCell(2);
                    int birthdayInt = (int) cell.getNumericCellValue();
                    birthdayString = String.valueOf(birthdayInt);
                    System.out.println(birthdayString);
                    cell = rowStart.getCell(3);
                    int phoneInt = (int) cell.getNumericCellValue();
                    phoneString = String.valueOf(phoneInt);
                    System.out.println(phoneString);
                    cell = rowStart.getCell(4);
                    emailString = cell.getStringCellValue();
                    System.out.println(emailString);
                    cell = rowStart.getCell(5);
                    majorString = cell.getStringCellValue();
                    System.out.println(majorString);
                    cell = rowStart.getCell(6);
                    if (cell == null) {
                        groupString = " ";
                    } else {
                        groupString = cell.getStringCellValue();
                    }
                    System.out.println(groupString);
                    cell = rowStart.getCell(7);
                    tagString = cell.getStringCellValue();
                    System.out.println(tagString);
                    if (nameString == null || sexString == null || birthdayString == null || phoneString == null
                            || emailString == null || majorString == null) {
                        throw new ParseException(Messages.MESSAGE_UNSUCCESSFUL_IMPORT);
                    } else {
                        person.add(createPerson(nameString, sexString, birthdayString, phoneString, emailString,
                                majorString, groupString, tagString));
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(person.size());

        return person;
    }

    /**
     * Creates a person.
     */
    private static Person createPerson(String nameString, String sexString, String birthdayString, String phoneString,
                                       String emailString, String majorString, String groupString, String tagString)
            throws ParseException {
        requireNonNull(nameString);
        Name nameParse = ParserUtil.parseName(nameString);
        Sex sexParse = ParserUtil.parseSex(sexString);
        Birthday birthdayParse = ParserUtil.parseBirthday(birthdayString);
        Phone phoneParse = ParserUtil.parsePhone(phoneString);
        Email emailParse = ParserUtil.parseEmail(emailString);
        Major majorParse = ParserUtil.parseMajor(majorString);
        Group groupParse = ParserUtil.parseGroup(groupString);
        Set<Tag> tagList = new HashSet<>();
        String processedTags = tagString.replace(TAG_SEPARATOR, " " + PREFIX_TAG);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                " " + PREFIX_TAG + processedTags, PREFIX_TAG);
        tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        return new Person(nameParse, sexParse, birthdayParse, phoneParse, emailParse, majorParse, groupParse, tagList);
    }

}
