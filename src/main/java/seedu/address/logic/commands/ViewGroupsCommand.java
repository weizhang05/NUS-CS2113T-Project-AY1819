package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.Group;

/**
 * Views all Groups added to the address book.
 */
public class ViewGroupsCommand extends Command {
    public static final String COMMAND_WORD = "view_g";

    public static final String MESSAGE_SUCCESS = "Groups: \n%1$s";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        ObservableList<Group> groupList = model.getFilteredGroupList();

        ArrayList<String> groupArrayList = new ArrayList<String>();

        for (Group toList : groupList) {
            String groupHouse = "(" + toList.getGroupName() + ", " + toList.getHouseName() + ")";
            groupArrayList.add(groupHouse);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, groupArrayList));
    }
}
