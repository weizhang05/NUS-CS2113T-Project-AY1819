package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Provides a handle to a command card in the redo or redo list panel.
 */
public class RedoCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String COMMAND_FIELD_ID = "#redoableCommand";

    private final Label idLabel;
    private final Label commandLabel;

    public RedoCardHandle(Node cardNode) {
        super(cardNode);
        idLabel = getChildNode(ID_FIELD_ID);
        commandLabel = getChildNode(COMMAND_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getCommand() {
        return commandLabel.getText();
    }

    /**
     * Return true if this handle contains {@code command}
     */
    public boolean equals(String command) {
        return getCommand().equals(command);
    }
}
