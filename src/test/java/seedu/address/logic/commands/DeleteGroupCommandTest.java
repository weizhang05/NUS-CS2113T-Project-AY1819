package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.testutil.PersonBuilder;

public class DeleteGroupCommandTest {
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new DeleteGroupCommand(null);
    }

    @Test
    public void execute_nonexistentGroup_throwsCommandException() throws Exception {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand("G1");
        ModelManager modelManager = new ModelManager();

        thrown.expect(CommandException.class);
        thrown.expectMessage(DeleteGroupCommand.MESSAGE_NONEXISTENT_GROUP);
        deleteGroupCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_nonemptyGroup_throwsCommandException() throws Exception {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand("G1");
        ModelManager modelManager = new ModelManager();

        modelManager.addHouse(new House("Green"));
        modelManager.addGroup(new Group("G1", "Green"));
        modelManager.addPerson(new PersonBuilder().withGroup("G1").build());

        thrown.expect(CommandException.class);
        thrown.expectMessage(DeleteGroupCommand.MESSAGE_NOT_EMPTY_GROUP);
        deleteGroupCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_deleteGroupSuccessful() throws Exception {
        ModelManager modelManager = new ModelManager();

        modelManager.addHouse(new House("Green"));
        modelManager.addGroup(new Group("G1", "Green"));

        CommandResult commandResult = new DeleteGroupCommand("G1").execute(modelManager, commandHistory);

        assertEquals(String.format(DeleteGroupCommand.MESSAGE_DELETE_GROUP_SUCCESS, "G1"),
                commandResult.getFeedbackToUser());
        assertEquals(0, modelManager.getFilteredGroupList().size());
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }
}
