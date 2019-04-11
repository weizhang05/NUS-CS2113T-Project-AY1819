package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The participant index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_EXCEL_FILE_WRITTEN_SUCCESSFULLY = "The Excel Sheet is written successfully";
    public static final String MESSAGE_EXPORT_COMMAND_ERRORS = "Excel Writing was unsuccessful";
    public static final String MESSAGE_UNSUCCESSFUL_IMPORT = "Excel import was unsuccessful.\n Check that the name of "
            + "the file is 'FOP_MANAGER_LIST.xls' and that the Headings are NAME, SEX, BIRTHDAY, PHONE, EMAIL, MAJOR, "
            + "GROUP, TAG.\n The file should also be in the same User Directory as the FOP Manager.";
    public static final String MESSAGE_MISSING_VALUES_IMPORT = "Check that the NAME, SEX, BIRTHDAY, PHONE, EMAIL and "
            + "MAJOR values are not empty.";
}
