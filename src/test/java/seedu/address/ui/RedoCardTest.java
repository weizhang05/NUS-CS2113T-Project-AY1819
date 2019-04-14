package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertRedoCardDisplay;

import org.junit.Test;

import guitests.guihandles.RedoCardHandle;

public class RedoCardTest extends GuiUnitTest {

    @Test
    public void display() {
        String redoableCommand = "redoable command";
        RedoCard redoCard = new RedoCard(redoableCommand, 1);
        uiPartRule.setUiPart(redoCard);
        assertCardDisplay(redoCard, redoableCommand, 1);
    }

    @Test
    public void equals() {
        String redoableCommand = "redoable command";
        RedoCard redoCard = new RedoCard(redoableCommand, 0);

        // same redoableCommand, same index -> returns true
        RedoCard copy = new RedoCard(redoableCommand, 0);
        assertTrue(redoCard.equals(copy));

        // same object -> returns true
        assertTrue(redoCard.equals(redoCard));

        // null -> returns false
        assertFalse(redoCard.equals(null));

        // different types -> returns false
        assertFalse(redoCard.equals(0));

        // different redoableCommand, same index -> returns false
        String differentRedoableCommand = "different";
        assertFalse(redoCard.equals(new RedoCard(differentRedoableCommand, 0)));

        // same redoableCommand, different index -> returns false
        assertFalse(redoCard.equals(new RedoCard(redoableCommand, 1)));
    }

    /**
     * Asserts that {@code redoCard} displays the details of {@code expectedString} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(RedoCard redoCard, String expectedString, int expectedId) {
        guiRobot.pauseForHuman();

        RedoCardHandle redoCardHandle = new RedoCardHandle(redoCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", redoCardHandle.getId());

        // verify redoableCommand details are displayed correctly
        assertRedoCardDisplay(expectedString, redoCardHandle);
    }
}
