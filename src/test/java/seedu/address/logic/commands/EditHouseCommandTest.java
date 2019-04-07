package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.grouping.House;
import seedu.address.model.grouping.Group;

public class EditHouseCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullOldHouse_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new EditHouseCommand(null, "Red");
    }

    @Test
    public void constructor_nullNewHouse_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new EditHouseCommand("Green", null);
    }

    @Test
    public void execute_repeatHouse_throwsCommandException() throws Exception {
        EditHouseCommand editHouseCommand = new EditHouseCommand("Red", "Red");
        ModelManager modelManager = new ModelManager();

        thrown.expect(CommandException.class);
        thrown.expectMessage(EditHouseCommand.MESSAGE_REPEAT_HOUSE);
        editHouseCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_nonexistentOldHouse_throwsCommandException() throws Exception {
        EditHouseCommand editHouseCommand = new EditHouseCommand ("Red", "Green");
        ModelManager modelManager = new ModelManager();

        thrown.expect(CommandException.class);
        thrown.expectMessage(EditHouseCommand.MESSAGE_NONEXISTENT_OLD_HOUSE);
        editHouseCommand.execute(modelManager, commandHistory);
    }
    @Test
    public void execute_existentNewHouse_throwsCommandException() throws Exception {
        EditHouseCommand editHouseCommand = new EditHouseCommand ("Red", "Green");
        ModelManager modelManager = new ModelManager();

        modelManager.addHouse(new House("Red"));
        modelManager.addHouse(new House("Green"));

        thrown.expect(CommandException.class);
        thrown.expectMessage(EditHouseCommand.MESSAGE_EXISTENT_NEW_HOUSE);
        editHouseCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_editHouseWithGroup() throws Exception {
        ModelManager modelManager = new ModelManager();

        modelManager.addHouse(new House("Red"));
        modelManager.addGroup(new Group("R1", "Red"));

        CommandResult commandResult =
                new EditHouseCommand ("Red", "Green").execute(modelManager, commandHistory);

        assertEquals(String.format(EditHouseCommand.MESSAGE_SUCCESS, "Red", "Green"),
                commandResult.getFeedbackToUser());
        assertEquals("Green",modelManager.getFilteredGroupList().get(0).getHouseName());
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }
}
