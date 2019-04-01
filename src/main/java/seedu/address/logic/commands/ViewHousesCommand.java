package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.House;

/**
 * Views all Houses added to the address book.
 */
public class ViewHousesCommand extends Command {
    public static final String COMMAND_WORD = "view_h";

    public static final String MESSAGE_SUCCESS = "Houses added: \n%1$s";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        ObservableList<House> houseList = model.getFilteredHouseList();

        ArrayList<String> houseArrayList = new ArrayList<String>();

        for (House toList : houseList) {
            String house = toList.getHouseName();
            houseArrayList.add(house);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, houseArrayList));
    }
}
