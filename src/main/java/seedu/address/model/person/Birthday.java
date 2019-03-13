package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's birthday in the address book
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should contain six numbers in the format of DDMMYYYY";
    public static final String VALIDATION_REGEX = "[\\d{6}]";
    public final String value;

    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthdya(birthday), MESSAGE_CONSTRAINTS);
        value = birthday;
    }

    /**
     * Return true if a given string is a valid sex
     */
    public static boolean isValidBirthdya(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }
}
