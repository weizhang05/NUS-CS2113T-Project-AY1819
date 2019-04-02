package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.grouping.FindGroupPredicate;

public class ListGroupCommand extends Command {
    public static final String COMMAND_WORD = "list_g";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all participants in group. "
            + "Parameters: groupName";

    public static final String MESSAGE_SUCCESS = "Listed all participants in group $1%s";

    private final FindGroupPredicate predicate;

    public ListGroupCommand(FindGroupPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
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
