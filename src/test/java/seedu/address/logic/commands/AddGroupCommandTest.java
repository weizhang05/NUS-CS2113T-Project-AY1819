package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;

public class AddGroupCommandTest {
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddGroupCommand(null, "Red");
    }

    @Test
    public void constructor_nullHouse_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddGroupCommand("G1", null);
    }

    @Test
    public void execute_nonexistentHouse_throwsCommandException() throws Exception {
        AddGroupCommand addGroupCommand = new AddGroupCommand("1", "2");
        ModelManager modelManager = new ModelManager();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddGroupCommand.MESSAGE_NONEXISTENT_HOUSE);
        addGroupCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_duplicateGroup_throwsCommandException() throws Exception {
        AddGroupCommand addGroupCommand = new AddGroupCommand("G1", "Green");
        ModelManager modelManager = new ModelManager();

        modelManager.addHouse(new House("Green"));
        modelManager.addGroup(new Group("G1", "Green"));

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddGroupCommand.MESSAGE_DUPLICATE_GROUP);
        addGroupCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_addGroupSuccessful() throws CommandException {
        ModelManager modelManager = new ModelManager();
        House validHouse = new House("Red");
        Group validGroup = new Group("R1", "Red");

        modelManager.addHouse(validHouse);

        CommandResult commandResult =
                new AddGroupCommand("R1", "Red").execute(modelManager, commandHistory);

        assertEquals(String.format(AddGroupCommand.MESSAGE_SUCCESS, "R1"), commandResult.getFeedbackToUser());
        assertEquals(validGroup, modelManager.getFilteredGroupList().get(0));
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }
}
