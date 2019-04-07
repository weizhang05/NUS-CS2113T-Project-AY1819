package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.grouping.House;

public class AddHouseCommandTest {
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullHouse_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddHouseCommand(null);
    }

    @Test
    public void execute_duplicateHouse_throwsCommandException() throws Exception {
        AddHouseCommand addHouseCommand = new AddHouseCommand("1");
        ModelManager modelManager = new ModelManager();

        modelManager.addHouse(new House("1"));

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddHouseCommand.MESSAGE_DUPLICATE_HOUSE);
        addHouseCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_addHouseSuccessful() throws CommandException {
        ModelManager modelManager = new ModelManager();
        House validHouse = new House("Red");

        CommandResult commandResult = new AddHouseCommand("Red").execute(modelManager, commandHistory);

        assertEquals(String.format(AddHouseCommand.MESSAGE_SUCCESS, "Red"), commandResult.getFeedbackToUser());
        assertEquals(validHouse, modelManager.getFilteredHouseList().get(0));
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }
}
