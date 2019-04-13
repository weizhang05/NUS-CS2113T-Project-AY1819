package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SaveChartCommand.MESSAGE_NO_PERSON;
import static seedu.address.logic.commands.SaveChartCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SaveChartCommandTest {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Integer FILE_NAME_MAX_LENGTH = 1000;

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_emptyAddressBook_throwsCommandException() {
        Model model = new ModelManager();
        assertCommandFailure(new SaveChartCommand(), model, commandHistory, MESSAGE_NO_PERSON);
    }

    @Test
    public void execute_emptyFileName_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false,
                false, true);
        assertCommandSuccess(new SaveChartCommand(), model, commandHistory, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_anyFileName_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false,
                false, true);
        String fileName = generateRandomString((int) (Math.random() * FILE_NAME_MAX_LENGTH + 1));
        assertCommandSuccess(new SaveChartCommand(fileName), model, commandHistory, expectedCommandResult,
                expectedModel);
    }

    /**
     * Generate a random alphanumeric string
     */
    public static String generateRandomString(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
