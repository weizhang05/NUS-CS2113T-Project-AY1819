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

    public static final String MESSAGE_CONSTRAINTS = "Major must be one of the five major of NUS School of Computing:"
            + " Computer Science, Information System, Computer Engineering, Business Analytics or Information Security";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private static final ArrayList<String> validCSMajor = new ArrayList<>(Arrays.asList("COMPUTER SCIENCE",
            "INFORMATION SYSTEM", "INFORMATION SYSTEMS", "COMPUTER ENGINEERING", "BUSINESS ANALYTICS",
            "INFORMATION SECURITY", "CS", "IS", "CEG", "BZA", "BA", "ISC"));
    public final String value;

    /**
     * Constructs an {@code Major}.
     *
     * @param major A valid address.
     */
    public Major(String major) {
        requireNonNull(major);
        checkArgument(isValidMajor(major), MESSAGE_CONSTRAINTS);
        value = major;
    }

    /**
     * Returns true if a given string is a valid major.
     */
    public static boolean isValidMajor(String test) {
        return test.matches(VALIDATION_REGEX) && validCSMajor.contains(test.toUpperCase());
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
