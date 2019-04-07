package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.participant.Birthday;
import seedu.address.testutil.Assert;

public class BirthdayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_throwsIllegalArgumentException() {
        String invalidBirthday = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthday));
    }

    @Test
    public void isValidBirthday() {
        // null birthday
        Assert.assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // invalid birthdays
        assertFalse(Birthday.isValidBirthday("")); // empty string
        assertFalse(Birthday.isValidBirthday(" ")); // spaces only
        assertFalse(Birthday.isValidBirthday("120198")); // less than 8 numbers
        assertFalse(Birthday.isValidBirthday("120199898")); // more than 8 numbers
        assertFalse(Birthday.isValidBirthday("firstmay")); // non-numeric
        assertFalse(Birthday.isValidBirthday("12Jan98")); // alphabets within digits
        assertFalse(Birthday.isValidBirthday("1201 1998")); // spaces within digits
        assertFalse(Birthday.isValidBirthday("32011998")); // invalid date
        assertFalse(Birthday.isValidBirthday("00011998")); // invalid date
        assertFalse(Birthday.isValidBirthday("01131998")); // invalid month
        assertFalse(Birthday.isValidBirthday("13001998")); // invalid month
        assertFalse(Birthday.isValidBirthday("10012111")); // invalid year

        // valid birthday
        assertTrue(Birthday.isValidBirthday("12011998")); // exactly 8 numbers
    }
}
