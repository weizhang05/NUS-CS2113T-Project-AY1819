package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.Group;
import seedu.address.model.participant.Participant;

/**
 * Deletes a group identified by the group name.
 * Does not allow groups with participants in it to be deleted.
 */
public class DeleteGroupCommand extends Command {

    public static final String COMMAND_WORD = "delete_g";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a group by group name.\n"
            + "Parameters: groupName\n";

    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted Group: %1$s";
    public static final String MESSAGE_NONEXISTENT_GROUP = "Group does not exist.";
    public static final String MESSAGE_NOT_EMPTY_GROUP = "Group is not empty. "
            + "Remove all participants from the group before deleting the group!";

    private final String groupName;

    public DeleteGroupCommand(String groupName) {
        requireNonNull(groupName);
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        ObservableList<Group> groupList = model.getFilteredGroupList();
        ObservableList<Participant> participantList = model.getFilteredPersonList();

        Group toDelete = new Group (groupName);

        boolean groupExists = false;
        for (Group group : groupList) {
            if (group.getGroupName().equals(groupName)) {
                groupExists = true;
                toDelete.setHouseName(group.getHouseName());
                break;
            }
        }
        if (!groupExists) {
            throw new CommandException(MESSAGE_NONEXISTENT_GROUP);
        }

        for (Participant participant : participantList) {
            if (participant.getGroup().getGroupName().equals(groupName)) {
                throw new CommandException(MESSAGE_NOT_EMPTY_GROUP);
            }
        }

        model.deleteGroup(toDelete);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, groupName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteGroupCommand // instanceof handles nulls
                && this.getGroupName().equals(((DeleteGroupCommand) other).getGroupName()));
    }
}
