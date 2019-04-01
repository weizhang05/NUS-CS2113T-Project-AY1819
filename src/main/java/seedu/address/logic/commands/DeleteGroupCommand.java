package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.Group;
import seedu.address.model.participant.Person;

public class DeleteGroupCommand extends Command {

    public static final String COMMAND_WORD = "delete_g";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a group by groupName.\n"
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

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        ObservableList<Group> groupList = model.getFilteredGroupList();
        ObservableList<Person> personList = model.getFilteredPersonList();

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

        for (Person person : personList) {
            if (person.getGroup().getGroupName().equals(groupName)) {
                throw new CommandException(MESSAGE_NOT_EMPTY_GROUP);
            }
        }

        model.deleteGroup(toDelete);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, groupName));
    }
}
