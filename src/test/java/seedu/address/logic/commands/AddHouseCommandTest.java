package seedu.address.logic.commands;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;

public class AddHouseCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullHouse_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddHouseCommand(null);
    }

    //Updates to model needed
    /*
    @Test
    public void execute_duplicateHouse_throwsCommandException() throws Exception {
        AddHouseCommand addHouseCommand = new AddHouseCommand("1");
        HouseStorage houseStorage = new HouseStorage();
        ModelManager modelManager = new ModelManager();

        modelManager.addHouse("1");

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddHouseCommand.MESSAGE_DUPLICATE_HOUSE);
        addHouseCommand.execute(modelManager, commandHistory);
    }
    */
}
