package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroupHousePersonList.getTypicalAddressBookWithGroupHouse;

import java.util.ArrayList;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ViewGroupHouseCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithGroupHouse(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_viewGroup_successful() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        ViewGroupsCommand viewGroupsCommand = new ViewGroupsCommand();

        ArrayList<String> groupList = new ArrayList<>();
        groupList.add("(R1, Red)");
        groupList.add("(R2, Red)");
        groupList.add("(B1, Blue)");
        groupList.add("(B2, Blue)");

        String expectedMessage = String.format(ViewGroupsCommand.MESSAGE_SUCCESS, groupList);
        assertCommandSuccess(viewGroupsCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_viewHouse_successful() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        ViewHousesCommand viewHousesCommand = new ViewHousesCommand();

        ArrayList<String> houseList = new ArrayList<>();
        houseList.add("Red");
        houseList.add("Blue");
        houseList.add("Yellow");

        String expectedMessage = String.format(ViewHousesCommand.MESSAGE_SUCCESS, houseList);
        assertCommandSuccess(viewHousesCommand, model, commandHistory, expectedMessage, expectedModel);
    }
}
