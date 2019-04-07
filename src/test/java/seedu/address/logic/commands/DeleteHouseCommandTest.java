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

public class DeleteHouseCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Test
    public void constructor_nullHouse_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new DeleteHouseCommand(null);
    }

    @Test
    public void execute_nonexistentHouse_throwsCommandException() throws Exception {
        DeleteHouseCommand deleteHouseCommand = new DeleteHouseCommand("Red");
        ModelManager modelManager = new ModelManager();

        thrown.expect(CommandException.class);
        thrown.expectMessage(DeleteHouseCommand.MESSAGE_NONEXISTENT_HOUSE);
        deleteHouseCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_nonemptyHouse_throwsCommandException() throws Exception {
        DeleteHouseCommand deleteHouseCommand = new DeleteHouseCommand("Red");
        ModelManager modelManager = new ModelManager();

        modelManager.addHouse(new House("Red"));
        modelManager.addGroup(new Group("R1", "Red"));

        thrown.expect(CommandException.class);
        thrown.expectMessage(DeleteHouseCommand.MESSAGE_NOT_EMPTY_HOUSE);
        deleteHouseCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_deleteGroupSuccessful() throws Exception {
        ModelManager modelManager = new ModelManager();

        modelManager.addHouse(new House("Green"));

        CommandResult commandResult = new DeleteHouseCommand("Green").execute(modelManager, commandHistory);

        assertEquals(String.format(DeleteHouseCommand.MESSAGE_DELETE_HOUSE_SUCCESS, "Green"),
                commandResult.getFeedbackToUser());
        assertEquals(0, modelManager.getFilteredHouseList().size());
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }
}
