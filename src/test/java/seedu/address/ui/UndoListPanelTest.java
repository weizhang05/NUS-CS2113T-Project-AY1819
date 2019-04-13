package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static seedu.address.ui.testutil.GuiTestAssert.assertUndoCardDisplay;

import org.junit.Test;

import guitests.guihandles.UndoCardHandle;
import guitests.guihandles.UndoListPanelHandle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UndoListPanelTest extends GuiUnitTest {
    private UndoListPanelHandle undoListPanelHandle;

    @Test
    public void display() {
        initUi(createUndoList(10));

        for (int i = 0; i < 10; i++) {
            undoListPanelHandle.navigateToCard("Add " + i);
            String expectedCommand = "Add " + i;
            UndoCardHandle actualCard = undoListPanelHandle.getUndoCardHandle(i);

            assertUndoCardDisplay(expectedCommand, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    /**
     * Returns a list of commands containing {@code commandCount} commands that is used to populate the
     * {@code UndoListPanel}.
     */
    private ObservableList<String> createUndoList (int commandCount) {
        ObservableList<String> undoList = FXCollections.observableArrayList();
        for (int i = 0; i < commandCount; i++) {
            String command = "Add " + i;
            undoList.add(command);
        }
        return undoList;
    }

    /**
     * Initializes {@code undoListPanelHandle} with a {@code undoListPanel} of {@code commandList}
     * Also shows the {@code Stage} that displays only {@code UndoListPanle}
     * @param commandList
     */
    private void initUi(ObservableList<String> commandList) {
        UndoListPanel undoListPanel = new UndoListPanel(commandList);
        uiPartRule.setUiPart(undoListPanel);

        undoListPanelHandle = new UndoListPanelHandle(getChildNode(undoListPanel.getRoot(),
                UndoListPanelHandle.UNDO_LIST_VIEW_ID));
    }
}
