package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.Value;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Birthday;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Major;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.Phone;
import seedu.address.model.participant.Sex;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Participant[] getSampleFreshmen() {
        return new Participant[] {
            new Participant(new Name("Alex Yeoh"), new Sex("M"), new Birthday("01021996"),
                    new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Major("Computer Science"), new Group(""),
                    getTagSet(Value.FRESHMAN)),
            new Participant(new Name("Bernice Yu"), new Sex("M"), new Birthday("01021996"),
                    new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Major("Information System"), new Group(""),
                    getTagSet(Value.FRESHMAN)),
            new Participant(new Name("Charlotte Oliveiro"), new Sex("M"), new Birthday("01021996"),
                    new Phone("93210283"),
                    new Email("charlotte@example.com"), new Major("Computer Engineering"), new Group(""),
                    getTagSet(Value.FRESHMAN)),
            new Participant(new Name("David Li"), new Sex("M"), new Birthday("01021996"),
                    new Phone("91031282"),
                    new Email("lidavid@example.com"), new Major("Business Analytic"), new Group(""),
                    getTagSet(Value.FRESHMAN)),
            new Participant(new Name("Irfan Ibrahim"), new Sex("M"), new Birthday("01021996"),
                    new Phone("92492021"),
                    new Email("irfan@example.com"), new Major("Information Security"), new Group(""),
                    getTagSet(Value.FRESHMAN)),
            new Participant(new Name("Roy Balakrishnan"), new Sex("M"), new Birthday("01021996"),
                    new Phone("92624417"),
                    new Email("royb@example.com"), new Major("Computer Science"), new Group(""),
                    getTagSet(Value.FRESHMAN))
        };
    }

    public static Participant[] getSampleOgls() {
        return new Participant[] {
                new Participant(new Name("OGL 1"), new Sex("M"), new Birthday("01021996"),
                        new Phone("91234567"),
                        new Email("ogl1@example.com"), new Major("Computer Science"), new Group(""),
                        getTagSet(Value.OGL)),
                new Participant(new Name("OGL 2"), new Sex("F"), new Birthday("01021996"),
                        new Phone("98765432"),
                        new Email("ogl2@example.com"), new Major("Information Security"), new Group(""),
                        getTagSet(Value.OGL)),
                new Participant(new Name("OGL 3"), new Sex("M"), new Birthday("01021996"),
                        new Phone("81234567"),
                        new Email("ogl3@example.com"), new Major("Computer Engineering"), new Group(""),
                        getTagSet(Value.OGL)),
                new Participant(new Name("OGL 4"), new Sex("F"), new Birthday("01021996"),
                        new Phone("87654321"),
                        new Email("ogl4@example.com"), new Major("Business Analytic"), new Group(""),
                        getTagSet(Value.OGL))
        };
    }

    public static House[] getSampleHouses() {
        return new House[]{
            new House("Red"),
            new House("Blue"),
        };
    }

    public static Group[] getSampleGroups() {
        return new Group[]{
            new Group("R1", "Red"),
            new Group("R2", "Red"),
            new Group("B1", "Blue"),
            new Group("B2", "Blue"),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Participant sampleParticipant : getSampleFreshmen()) {
            sampleAb.addParticipant(sampleParticipant);
        }

        for (Participant sampleParticipant : getSampleOgls()) {
            sampleAb.addParticipant(sampleParticipant);
        }

        for (House sampleHouse : getSampleHouses()) {
            sampleAb.addHouse(sampleHouse);
        }

        for (Group sampleGroup : getSampleGroups()) {
            sampleAb.addGroup(sampleGroup);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
