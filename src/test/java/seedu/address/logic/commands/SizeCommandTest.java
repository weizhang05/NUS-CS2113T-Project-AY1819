package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroupHousePersonList.getTypicalAddressBookWithGroupHouse;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SizeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithGroupHouse(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_successfulSize() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        SizeCommand sizeCommand = new SizeCommand();
        String expectedMessage = String.format(sizeCommand.MESSAGE_SUCCESS, 4, 1, 1, 4, 3);
        assertCommandSuccess(sizeCommand, model, commandHistory, expectedMessage, expectedModel);
    }
}
