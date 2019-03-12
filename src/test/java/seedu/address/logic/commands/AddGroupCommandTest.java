package seedu.address.logic.commands;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.storage.HouseStorage;

public class AddGroupCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddGroupCommand(null, null);
    }
    @Test
    public void execute_nonexistentHouse_throwsCommandException() throws Exception {
        AddGroupCommand addGroupCommand = new AddGroupCommand("1", "2");
        HouseStorage houseStorage = new HouseStorage();
        ModelManager modelManager = new ModelManager();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddGroupCommand.MESSAGE_NONEXISTENT_HOUSE);
        addGroupCommand.execute(modelManager, commandHistory);
    }

    //Updates to model needed
    /*
    @Test
    public void execute_duplicateGroup_throwsCommandException() throws Exception {
        AddGroupCommand addGroupCommand = new AddGroupCommand("1", "2");
        HouseStorage houseStorage = new HouseStorage();
        ModelManager modelManager = new ModelManager();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddGroupCommand.MESSAGE_NONEXISTENT_HOUSE);
        addGroupCommand.execute(modelManager, commandHistory);
    }
    */
}
