package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.Group;

public class EditGroupCommand extends Command {
    public static final String COMMAND_WORD = "edit_g";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an existing group name. Parameters: "
            + "oldGroupName + newGroupName";
    public static final String MESSAGE_SUCCESS = "Group edited: from %1$s to %2$s";
    public static final String MESSAGE_NONEXISTENT_OLD_GROUP = "Old group does not exist.";
    public static final String MESSAGE_REPEAT_GROUP = "Enter a group name that is different!";
    public static final String MESSAGE_EXISTENT_NEW_GROUP = "This group name already exists! Choose a unique name";

    private static String oldGroupName;
    private static String newGroupName;

    public EditGroupCommand(String oldGroupName, String newGroupName) {
        requireNonNull(oldGroupName, newGroupName);
        this.oldGroupName = oldGroupName;
        this.newGroupName = newGroupName;
    }

    public static String getNewGroupName() {
        return newGroupName;
    }

    public static String getOldGroupName() {
        return oldGroupName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (newGroupName.equals(oldGroupName)) {
            throw new CommandException(MESSAGE_REPEAT_GROUP);
        }
        ObservableList<Group> groupList = model.getFilteredGroupList();

        Group oldGroup = new Group(oldGroupName);
        Group newGroup = new Group(newGroupName);

        boolean nonexistent = true;

        for(Group toList : groupList) {
            if (toList.getGroupName().equals(oldGroupName)) {
                oldGroup.setHouseName(toList.getHouseName());
                newGroup.setHouseName(toList.getHouseName());
                nonexistent = false;
                break;
            }
            if (toList.getGroupName().equals(newGroupName)) {
                throw new CommandException(MESSAGE_EXISTENT_NEW_GROUP);
            }
        }

        if (nonexistent) {
            throw new CommandException(MESSAGE_NONEXISTENT_OLD_GROUP);
        }

        model.setGroup(oldGroup, newGroup);

        return new CommandResult(String.format(MESSAGE_SUCCESS, oldGroupName, newGroupName));
    }
}
