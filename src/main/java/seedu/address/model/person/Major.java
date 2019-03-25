package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMajor(String)} (String)}
 */
public class Major {

    public static final String MESSAGE_CONSTRAINTS = "Major must be one of the five majors of NUS School of Computing:"
            + " Computer Science, Information System, Computer Engineering, Business Analytics or Information Security";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private static final ArrayList<String> validMajorCS = new ArrayList<>(Arrays.asList("COMPUTER SCIENCE",
            "COMSCI", "CS"));
    private static final ArrayList<String> validMajorIS = new ArrayList<>(Arrays.asList("INFORMATION SYSTEM",
            "INFOSYS", "IS"));
    private static final ArrayList<String> validMajorCEG = new ArrayList<>(Arrays.asList("COMPUTER ENGINEERING",
            "COMENG", "CEG"));
    private static final ArrayList<String> validMajorISC = new ArrayList<>(Arrays.asList("INFORMATION SECURITY",
            "INFOSEC", "ISC"));
    private static final ArrayList<String> validMajorBA = new ArrayList<>(Arrays.asList("BUSINESS ANALYTIC",
            "BUSINESS ANALYTICS", "BIZANA", "BIZANAL", "BA", "BZA"));

    public final String value;

    /**
     * Constructs an {@code Major}.
     *
     * @param major A valid address.
     */
    public Major(String major) {
        requireNonNull(major);
        checkArgument(isValidMajor(major), MESSAGE_CONSTRAINTS);
        if (validMajorBA.contains(major)) {
            major = "BA";
        } else if (validMajorCS.contains(major)) {
            major = "CS";
        } else if (validMajorCEG.contains(major)) {
            major = "CEG";
        } else if (validMajorIS.contains(major)) {
            major = "IS";
        } else if (validMajorISC.contains(major)) {
            major = "ISC";
        }
        value = major;
    }

    /**
     * Returns true if a given string is a valid major.
     */
    public static boolean isValidMajor(String test) {
        return test.matches(VALIDATION_REGEX) && (validMajorCS.contains(test.toUpperCase())
                || validMajorIS.contains(test.toUpperCase()) || validMajorISC.contains(test.toUpperCase())
                || validMajorCEG.contains(test.toUpperCase()) || validMajorBA.contains(test.toUpperCase()));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Major // instanceof handles nulls
                && value.equals(((Major) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
