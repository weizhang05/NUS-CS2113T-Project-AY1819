package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Participant;

/**
 * A utility class containing a list of {@code Participant}, {@code Group} and {@House} objects to be used in tests.
 */
public class TypicalGroupHousePersonList {
    public static final Participant ALICIA = new PersonBuilder().withName("Alicia Alice")
            .withSex("F").withBirthday("07081994").withMajor("CS").withEmail("alicia@example.com")
            .withPhone("94351253").withGroup("R1", "Red").build();
    public static final Participant BENEDICT = new PersonBuilder().withName("Benedict Ben").withSex("M")
            .withBirthday("08071993").withMajor("IS").withEmail("benny@example.com").withPhone("98765432")
            .withGroup("").withTags("OGL").build();
    public static final Participant COCO = new PersonBuilder().withName("Coco Nut").withSex("F")
            .withBirthday("08081996").withMajor("CEG").withEmail("cocothenut@example.com").withPhone("98765432")
            .withGroup("R2", "Red").withTags("Vegetarian").build();
    public static final Participant DODO = new PersonBuilder().withName("Dodo Burt").withSex("M")
            .withBirthday("10081995").withMajor("BA").withEmail("dodoburt@example.com").withPhone("98765432")
            .withGroup("B1", "Blue").withTags("Freshman").build();

    public static final Group R1 = new Group("R1", "Red"); // 1 participant in group
    public static final Group R2 = new Group("R2", "Red"); // 1 participant in group
    public static final Group B1 = new Group("B1", "Blue"); // 1 participant in group
    public static final Group B2 = new Group("B2", "Blue"); // empty group

    public static final House RED = new House("Red"); // house with participants in 2 groups
    public static final House BLUE = new House("Blue"); // house with participants in 1 group
    public static final House YELLOW = new House("Yellow"); // empty house

    /**
     * Returns an {@code AddressBook} with typical persons, groups and houses.
     */
    public static AddressBook getTypicalAddressBookWithGroupHouse() {
        AddressBook ab = new AddressBook();
        for (Participant participant : getTypicalPersons()) {
            ab.addPerson(participant);
        }
        for (Group group : getTypicalGroups()) {
            ab.addGroup(group);
        }
        for (House house : getTypicalHouses()) {
            ab.addHouse(house);
        }
        return ab;
    }

    public static List<Participant> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICIA, BENEDICT, COCO, DODO));
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(R1, R2, B1, B2));
    }

    public static List<House> getTypicalHouses() {
        return new ArrayList<>(Arrays.asList(RED, BLUE, YELLOW));
    }
}
