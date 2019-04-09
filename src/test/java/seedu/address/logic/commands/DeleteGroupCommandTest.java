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
import seedu.address.model.grouping.Group;

public class DeleteGroupCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalAddressBookWithGroupHouse(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new DeleteGroupCommand(null);
    }

    @Test
    public void execute_nonexistentGroup_throwsCommandException() {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand("G1");
        assertCommandFailure(deleteGroupCommand, model, commandHistory, DeleteGroupCommand.MESSAGE_NONEXISTENT_GROUP);
    }

    @Test
    public void execute_nonemptyGroup_throwsCommandException() {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand("R1");
        assertCommandFailure(deleteGroupCommand, model, commandHistory, DeleteGroupCommand.MESSAGE_NOT_EMPTY_GROUP);
    }

    @Test
    public void execute_deleteGroupSuccessful() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand("B1");
        String expectedMessage = String.format(DeleteGroupCommand.MESSAGE_DELETE_GROUP_SUCCESS, "B1");

        expectedModel.deleteGroup(new Group("B1", "Blue"));
        expectedModel.commitAddressBook();
        assertCommandSuccess(deleteGroupCommand, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(2, model.sizeGroupList());
    }
}
