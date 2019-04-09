package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.FindGroupPredicate;
import seedu.address.model.grouping.Group;

/**
 * Lists all participants belonging to a group to the user.
 */
public class ListGroupCommand extends Command {
    public static final String COMMAND_WORD = "list_g";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all participants in group. "
            + "Parameters: groupName";

    public static final String MESSAGE_SUCCESS = "Listed all participants in group $1%s";
    public static final String MESSAGE_NONEXISTENT_GROUP = "Group does not exist.";

    private final String groupName;
    private final FindGroupPredicate predicate;

    public ListGroupCommand(FindGroupPredicate predicate, String groupName) {
        this.predicate = predicate;
        this.groupName = groupName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        ObservableList<Group> groupList = model.getFilteredGroupList();
        boolean contains = false;

        for (Group group : groupList) {
            if (group.getGroupName().equals(groupName)) {
                contains = true;
            }
        }

        if (!groupName.equals("EMPTY") && !contains) {
            throw new CommandException(MESSAGE_NONEXISTENT_GROUP);
        }

        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListGroupCommand // instanceof handles nulls
                && predicate.equals(((ListGroupCommand) other).predicate)); // state check
    }
}
