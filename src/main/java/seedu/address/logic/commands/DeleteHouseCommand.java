package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;

/**
 * Deletes a house identified by the house name.
 * Does not allow houses with groups in it to be deleted.
 */
public class DeleteHouseCommand extends Command {
    public static final String COMMAND_WORD = "delete_h";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a house by house name.\n"
            + "Parameters: houseName\n";

    public static final String MESSAGE_DELETE_HOUSE_SUCCESS = "Deleted House: %1$s";
    public static final String MESSAGE_NONEXISTENT_HOUSE = "House does not exist.";
    public static final String MESSAGE_NOT_EMPTY_HOUSE = "House is not empty. "
            + "Remove all groups from the house before deleting the house!";

    private final String houseName;

    public DeleteHouseCommand(String houseName) {
        requireNonNull(houseName);
        this.houseName = houseName;
    }

    public String getHouseName() {
        return houseName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        ObservableList<Group> groupList = model.getFilteredGroupList();
        ObservableList<House> houseList = model.getFilteredHouseList();

        boolean houseExists = false;
        for (House house : houseList) {
            if (house.getHouseName().equals(houseName)) {
                houseExists = true;
                break;
            }
        }
        if (!houseExists) {
            throw new CommandException(MESSAGE_NONEXISTENT_HOUSE);
        }

        for (Group group : groupList) {
            if (group.getHouseName().equals(houseName)) {
                throw new CommandException(MESSAGE_NOT_EMPTY_HOUSE);
            }
        }

        model.deleteHouse(new House(houseName));
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_DELETE_HOUSE_SUCCESS, houseName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteHouseCommand // instanceof handles nulls
                && this.getHouseName().equals(((DeleteHouseCommand) other).getHouseName())); // state check
    }
}
