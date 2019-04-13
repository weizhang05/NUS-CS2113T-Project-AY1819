package guitests.guihandles;

import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;

/**
 * Provides a handle for {@code RedoListPanel} containing the list of {@code RedoCard}.
 */
public class RedoListPanelHandle extends NodeHandle<ListView<String>> {
    public static final String REDO_LIST_VIEW_ID = "#redoListView";

    private static final String REDO_CARD_PANE_ID = "#redoCardPane";

    public RedoListPanelHandle(ListView<String> redoListPanelNode) {
        super(redoListPanelNode);
    }

    /**
     * Navigates the listview to command.
     */
    public void navigateToCard(String redoableCommand) {
        if (!getRootNode().getItems().contains(redoableCommand)) {
            throw new IllegalArgumentException("Command does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(redoableCommand);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Returns the redo card handle of a command associated with the {@code index} in the list.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public RedoCardHandle getRedoCardHandle(int index) {
        return getAllCardNodes().stream()
                .map(RedoCardHandle::new)
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
        return guiRobot.lookup(REDO_CARD_PANE_ID).queryAll();
    }

    private String getCommand(int index) {
        return getRootNode().getItems().get(index);
    }
}
