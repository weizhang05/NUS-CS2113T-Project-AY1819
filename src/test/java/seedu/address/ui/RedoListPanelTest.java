package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static seedu.address.ui.testutil.GuiTestAssert.assertRedoCardDisplay;

import org.junit.Test;

import guitests.guihandles.RedoCardHandle;
import guitests.guihandles.RedoListPanelHandle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RedoListPanelTest extends GuiUnitTest {
    private RedoListPanelHandle redoListPanelHandle;

    @Test
    public void display() {
        initUi(createRedoList(10));

        for (int i = 0; i < 10; i++) {
            redoListPanelHandle.navigateToCard("Add " + i);
            String expectedCommand = "Add " + i;
            RedoCardHandle actualCard = redoListPanelHandle.getRedoCardHandle(i);

            assertRedoCardDisplay(expectedCommand, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    /**
     * Returns a list of commands containing {@code commandCount} commands that is used to populate the
     * {@code RedoListPanel}.
     */
    private ObservableList<String> createRedoList (int commandCount) {
        ObservableList<String> redoList = FXCollections.observableArrayList();
        for (int i = 0; i < commandCount; i++) {
            String command = "Add " + i;
            redoList.add(command);
        }
        return redoList;
    }

    /**
     * Initializes {@code redoListPanelHandle} with a {@code redoListPanel} of {@code commandList}
     * Also shows the {@code Stage} that displays only {@code RedoListPanle}
     * @param commandList
     */
    private void initUi(ObservableList<String> commandList) {
        RedoListPanel redoListPanel = new RedoListPanel(commandList);
        uiPartRule.setUiPart(redoListPanel);

        redoListPanelHandle = new RedoListPanelHandle(getChildNode(redoListPanel.getRoot(),
                RedoListPanelHandle.REDO_LIST_VIEW_ID));
    }
}
