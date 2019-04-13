package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertUndoCardDisplay;

import org.junit.Test;

import guitests.guihandles.UndoCardHandle;

public class UndoCardTest extends GuiUnitTest {

    @Test
    public void display() {
        String undoableCommand = "undoable command";
        UndoCard undoCard = new UndoCard(undoableCommand, 1);
        uiPartRule.setUiPart(undoCard);
        assertCardDisplay(undoCard, undoableCommand, 1);
    }

    @Test
    public void equals() {
        String undoableCommand = "undoable command";
        UndoCard undoCard = new UndoCard(undoableCommand, 0);

        // same undoableCommand, same index -> returns true
        UndoCard copy = new UndoCard(undoableCommand, 0);
        assertTrue(undoCard.equals(copy));

        // same object -> returns true
        assertTrue(undoCard.equals(undoCard));

        // null -> returns false
        assertFalse(undoCard.equals(null));

        // different types -> returns false
        assertFalse(undoCard.equals(0));

        // different undoableCommand, same index -> returns false
        String differentUndoableCommand = "different";
        assertFalse(undoCard.equals(new UndoCard(differentUndoableCommand, 0)));

        // same undoableCommand, different index -> returns false
        assertFalse(undoCard.equals(new UndoCard(undoableCommand, 1)));
    }

    /**
     * Asserts that {@code undoCard} displays the details of {@code expectedString} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(UndoCard undoCard, String expectedString, int expectedId) {
        guiRobot.pauseForHuman();

        UndoCardHandle undoCardHandle = new UndoCardHandle(undoCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", undoCardHandle.getId());

        // verify undoableCommand details are displayed correctly
        assertUndoCardDisplay(expectedString, undoCardHandle);
    }
}
