package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.StatCommand.MESSAGE_NO_PERSON;
import static seedu.address.logic.commands.StatCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class StatCommandTest {
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_emptyAddressBook_throwsCommandException() {
        Model model = new ModelManager();
        assertCommandFailure(new StatCommand(), model, commandHistory, MESSAGE_NO_PERSON);
    }

    @Test
    public void execute_statCommand_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false,
                true, false);
        assertCommandSuccess(new StatCommand(), model, commandHistory, expectedCommandResult, expectedModel);
    }
}
