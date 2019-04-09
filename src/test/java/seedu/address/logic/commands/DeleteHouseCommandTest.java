package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroupHousePersonList.getTypicalAddressBookWithGroupHouse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.grouping.House;

public class DeleteHouseCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalAddressBookWithGroupHouse(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullHouse_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new DeleteHouseCommand(null);
    }

    @Test
    public void execute_nonexistentHouse_throwsCommandException() {
        DeleteHouseCommand deleteHouseCommand = new DeleteHouseCommand("Green");
        assertCommandFailure(deleteHouseCommand, model, commandHistory, DeleteHouseCommand.MESSAGE_NONEXISTENT_HOUSE);
    }

    @Test
    public void execute_nonemptyHouse_throwsCommandException() throws Exception {
        DeleteHouseCommand deleteHouseCommand = new DeleteHouseCommand("Red");
        assertCommandFailure(deleteHouseCommand, model, commandHistory, DeleteHouseCommand.MESSAGE_NOT_EMPTY_HOUSE);
    }

    @Test
    public void execute_deleteGroupSuccessful() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        DeleteHouseCommand deleteHouseCommand = new DeleteHouseCommand("Yellow");
        String expectedMessage = String.format(DeleteHouseCommand.MESSAGE_DELETE_HOUSE_SUCCESS, "Yellow");

        expectedModel.deleteHouse(new House("Yellow"));
        expectedModel.commitAddressBook();
        assertCommandSuccess(deleteHouseCommand, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(2, model.sizeHouseList());
    }
}
