package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.FindHousePredicate;
import seedu.address.model.grouping.House;

/**
 * Lists all participants belonging to a house to the user.
 */
public class ListHouseCommand extends Command {
    public static final String COMMAND_WORD = "list_h";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all participants in house. "
            + "Parameters: houseName";

    public static final String MESSAGE_SUCCESS = "Listed all participants in house $1%s";
    public static final String MESSAGE_NONEXISTENT_HOUSE = "House does not exist.";

    private final String houseName;
    private final FindHousePredicate predicate;

    public ListHouseCommand(FindHousePredicate predicate, String houseName) {
        this.predicate = predicate;
        this.houseName = houseName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        ObservableList<House> houseList = model.getFilteredHouseList();
        boolean contains = false;

        for (House house: houseList) {
            if (house.getHouseName().equals(houseName)) {
                contains = true;
            }
        }

        if (!contains) {
            throw new CommandException(MESSAGE_NONEXISTENT_HOUSE);
        }

        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListHouseCommand // instanceof handles nulls
                && predicate.equals(((ListHouseCommand) other).predicate)); // state check
    }
}
