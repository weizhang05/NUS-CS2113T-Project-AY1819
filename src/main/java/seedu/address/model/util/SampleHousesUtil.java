package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.grouping.House;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleHousesUtil {

    public static House[] getSampleGroups() {
        return new House[]{
                new House("Red"),
                new House("Blue"),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (House sampleGroup : getSampleGroups()) {
            sampleAb.addHouse(sampleGroup);
        }
        return sampleAb;
    }

}
