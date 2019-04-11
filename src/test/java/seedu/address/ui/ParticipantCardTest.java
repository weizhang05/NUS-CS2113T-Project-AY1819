package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysPerson;

import org.junit.Test;

import guitests.guihandles.PersonCardHandle;
import seedu.address.model.participant.Participant;
import seedu.address.testutil.PersonBuilder;

public class ParticipantCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no tags
        Participant participantWithNoTags = new PersonBuilder().withTags(new String[0]).build();
        PersonCard personCard = new PersonCard(participantWithNoTags, 1);
        uiPartRule.setUiPart(personCard);
        assertCardDisplay(personCard, participantWithNoTags, 1);

        // with tags
        Participant participantWithTags = new PersonBuilder().build();
        personCard = new PersonCard(participantWithTags, 2);
        uiPartRule.setUiPart(personCard);
        assertCardDisplay(personCard, participantWithTags, 2);
    }

    @Test
    public void equals() {
        Participant participant = new PersonBuilder().build();
        PersonCard personCard = new PersonCard(participant, 0);

        // same participant, same index -> returns true
        PersonCard copy = new PersonCard(participant, 0);
        assertTrue(personCard.equals(copy));

        // same object -> returns true
        assertTrue(personCard.equals(personCard));

        // null -> returns false
        assertFalse(personCard.equals(null));

        // different types -> returns false
        assertFalse(personCard.equals(0));

        // different participant, same index -> returns false
        Participant differentParticipant = new PersonBuilder().withName("differentName").build();
        assertFalse(personCard.equals(new PersonCard(differentParticipant, 0)));

        // same participant, different index -> returns false
        assertFalse(personCard.equals(new PersonCard(participant, 1)));
    }

    /**
     * Asserts that {@code personCard} displays the details of {@code expectedParticipant} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(PersonCard personCard, Participant expectedParticipant, int expectedId) {
        guiRobot.pauseForHuman();

        PersonCardHandle personCardHandle = new PersonCardHandle(personCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", personCardHandle.getId());

        // verify participant details are displayed correctly
        assertCardDisplaysPerson(expectedParticipant, personCardHandle);
    }
}
