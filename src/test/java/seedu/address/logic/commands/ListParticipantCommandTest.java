package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.MARK;
import static seedu.address.testutil.TypicalPersons.getAddressBookWithOneFreshmanOrOgl;
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

public class ListParticipantCommandTest {
    //private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    //private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs()); //no Freshman

    private Model model = new ModelManager(getAddressBookWithOneFreshmanOrOgl(), new UserPrefs()); //no Freshman
    private Model expectedModel = new ModelManager(getAddressBookWithOneFreshmanOrOgl(), new UserPrefs()); //no Freshman

    private CommandHistory commandHistory = new CommandHistory();

    private FindingParticipantPredicate preparePredicate() {

        return new FindingParticipantPredicate(Arrays.asList("Freshman".split("\\s+")));
    }

    @Test
    public void execute_listHasOneFreshman() {
        model = new ModelManager(getAddressBookWithOneFreshmanOrOgl(), new UserPrefs()); //no Freshman
        expectedModel = new ModelManager(getAddressBookWithOneFreshmanOrOgl(), new UserPrefs()); //no Freshman
        FindingParticipantPredicate predicate = preparePredicate();
        ListParticipantCommand command = new ListParticipantCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, commandHistory,
                expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MARK), model.getFilteredPersonList());
    }


    @Test
    public void execute_listHasNoFreshman() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs()); //no Freshman
        FindingParticipantPredicate predicate = preparePredicate();
        ListParticipantCommand command = new ListParticipantCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        assertCommandSuccess(command, model, commandHistory,
                expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }
}
