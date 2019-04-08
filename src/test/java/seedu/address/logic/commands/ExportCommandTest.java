package seedu.address.logic.commands;

import org.junit.Test;
import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getAddressBookWithOneFreshman;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class ExportCommandTest {
    private CommandHistory commandHistory = new CommandHistory();

    /**
     * Opening the excel sheet should give an Empty file.
     */
    @Test
    public void execute_emptyAddressBook_success() {
        Model expectedModel = new ModelManager();
        expectedModel.commitAddressBook();

        assertCommandSuccess(new ExportCommand(), expectedModel, commandHistory,
                Messages.MESSAGE_EXCEL_FILE_WRITTEN_SUCCESSFULLY, expectedModel);
    }
    /**
     * Opening the excel sheet should give an non-empty file.
     */
    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(new ExportCommand(), model, commandHistory,
                Messages.MESSAGE_EXCEL_FILE_WRITTEN_SUCCESSFULLY, model);
    }
}
