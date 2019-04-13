package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.participant.Participant;

/**
 * A utility class containing a list of {@code Participant} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Participant ALICE = new PersonBuilder().withName("Alice Pauline")
            .withSex("F").withBirthday("07081994")
            .withMajor("CS").withEmail("alice@example.com")
            .withPhone("94351253")
            .withGroup("").withTags("friends").build();
    public static final Participant BENSON = new PersonBuilder().withName("Benson Meier").withSex("M")
            .withBirthday("08071993").withMajor("IS")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withGroup("").withTags("owesMoney", "friends").build();
    public static final Participant CARL = new PersonBuilder().withName("Carl Kurz")
            .withSex("M").withBirthday("01021990")
            .withPhone("95352563").withEmail("heinz@example.com").withMajor("CS").withGroup("").build();
    public static final Participant DANIEL = new PersonBuilder().withName("Daniel Meier").withSex("M")
            .withBirthday("01021900").withPhone("87652533")
            .withEmail("cornelia@example.com").withMajor("BZA")
            .withGroup("").withTags("friends").build();
    public static final Participant ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822243")
            .withEmail("werner@example.com").withSex("M").withBirthday("12121985").withMajor("CS")
            .withGroup("").build();
    public static final Participant FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824273")
            .withEmail("lydia@example.com").withSex("M").withBirthday("12121985").withMajor("ISC")
            .withGroup("").build();
    public static final Participant GEORGE = new PersonBuilder().withName("George Best").withPhone("94824423")
            .withEmail("anna@example.com").withSex("F").withBirthday("12121985").withMajor("CS")
            .withGroup("").build();

    // Manually added
    public static final Participant HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84824243")
            .withEmail("stefan@example.com").withSex("F").withBirthday("01021999").withMajor("CEG").build();
    public static final Participant IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821311")
            .withEmail("hans@example.com").withSex("F").withBirthday("01021999").withMajor("cEG").build();

    // Manually added - Participant's details found in {@code CommandTestUtil}
    public static final Participant AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withSex(VALID_SEX_AMY).withBirthday(VALID_BIRTHDAY_AMY)
            .withMajor(VALID_MAJOR_AMY).withGroup(VALID_GROUP_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Participant BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withSex(VALID_SEX_BOB).withBirthday(VALID_BIRTHDAY_BOB)
            .withMajor(VALID_MAJOR_BOB).withGroup(VALID_GROUP_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final Participant MARK = new PersonBuilder().withName("Mark Morgan").withSex("M")
            .withBirthday("11091998").withPhone("91291000")
            .withEmail("m.morgan@example.com").withMajor("CS")
            .withGroup("").withTags("Freshman").build();

    public static final Participant SONIA = new PersonBuilder().withName("Sonia Sia").withSex("F")
            .withBirthday("11111998").withPhone("91291234")
            .withEmail("soniasia@example.com").withMajor("CS")
            .withGroup("").withTags("OGL").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Participant participant : getTypicalPersons()) {
            ab.addParticipant(participant);
        }
        return ab;
    }

    public static AddressBook getAddressBookWithOneFreshmanAndOgl() {
        AddressBook ab = new AddressBook();
        for (Participant participant : getTypicalPersonsWithOneFreshmanAndOgl()) {
            ab.addParticipant(participant);
        }
        return ab;
    }

    public static List<Participant> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Participant> getTypicalPersonsWithOneFreshmanAndOgl() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, MARK, SONIA));
    }
}
