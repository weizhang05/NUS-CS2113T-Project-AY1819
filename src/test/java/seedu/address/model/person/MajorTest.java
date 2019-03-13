package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class MajorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Major(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Major(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> Major.isValidAddress(null));

        // invalid addresses
        assertFalse(Major.isValidAddress("")); // empty string
        assertFalse(Major.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Major.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Major.isValidAddress("-")); // one character
        assertTrue(Major.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
