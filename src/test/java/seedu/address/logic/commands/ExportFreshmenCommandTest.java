package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.MARK;
import static seedu.address.testutil.TypicalPersons.getAddressBookWithOneFreshmanAndOgl;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FindingParticipantPredicate;

public class ExportFreshmenCommandTest {
    private CommandHistory commandHistory = new CommandHistory();

    private FindingParticipantPredicate preparePredicate() {

        return new FindingParticipantPredicate(Arrays.asList("Freshman".split("\\s+")));
    }

    /**
     * Opening the excel sheet should give an Empty file.
     */
    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs()); //no Freshman
        FindingParticipantPredicate predicate = preparePredicate();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ExportFreshmenCommand(), model, commandHistory,
                Messages.MESSAGE_EXCEL_FILE_WRITTEN_SUCCESSFULLY, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /**
     * Opening the excel sheet should give an non-empty file.
     */
    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getAddressBookWithOneFreshmanAndOgl(), new UserPrefs());
        Model expectedModel = new ModelManager(getAddressBookWithOneFreshmanAndOgl(), new UserPrefs()); //no Freshman
        FindingParticipantPredicate predicate = preparePredicate();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ExportFreshmenCommand(), model, commandHistory,
                Messages.MESSAGE_EXCEL_FILE_WRITTEN_SUCCESSFULLY, expectedModel);
        assertEquals(Arrays.asList(MARK), model.getFilteredPersonList());
    }
}
