package guitests.guihandles;

import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;

/**
 * Provides a handle for {@code UndoListPanel} containing the list of {@code UndoCard}.
 */
public class UndoListPanelHandle extends NodeHandle<ListView<String>> {
    public static final String UNDO_LIST_VIEW_ID = "#undoListView";

    private static final String UNDO_CARD_PANE_ID = "#undoCardPane";

    public UndoListPanelHandle(ListView<String> undoListPanelNode) {
        super(undoListPanelNode);
    }

    /**
     * Navigates the listview to command.
     */
    public void navigateToCard(String undoableCommand) {
        if (!getRootNode().getItems().contains(undoableCommand)) {
            throw new IllegalArgumentException("Command does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(undoableCommand);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Returns the undo card handle of a command associated with the {@code index} in the list.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public UndoCardHandle getUndoCardHandle(int index) {
        return getAllCardNodes().stream()
                .map(UndoCardHandle::new)
                .filter(handle -> handle.equals(getCommand(index)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    /**
     * Returns all card nodes in the scene graph.
     * Card nodes that are visible in the listview are definitely in the scene graph, while some nodes that are not
     * visible in the listview may also be in the scene graph.
     */
    private Set<Node> getAllCardNodes() {
        return guiRobot.lookup(UNDO_CARD_PANE_ID).queryAll();
    }

    private String getCommand(int index) {
        return getRootNode().getItems().get(index);
    }
}
