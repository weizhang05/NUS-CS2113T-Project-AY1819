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
import seedu.address.model.participant.FindingParticipantPredicate;

public class ListParticipantCommandTest {
    //private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    //private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs()); //no Freshman

    private Model model = new ModelManager(getAddressBookWithOneFreshmanAndOgl(), new UserPrefs()); //no Freshman
    private Model expectedModel = new ModelManager(getAddressBookWithOneFreshmanAndOgl(), new UserPrefs());

    private CommandHistory commandHistory = new CommandHistory();

    private FindingParticipantPredicate preparePredicate() {

        return new FindingParticipantPredicate(Arrays.asList("Freshman".split("\\s+")));
    }

    @Test
    public void execute_listHasOneFreshman() {
        model = new ModelManager(getAddressBookWithOneFreshmanAndOgl(), new UserPrefs()); //no Freshman
        expectedModel = new ModelManager(getAddressBookWithOneFreshmanAndOgl(), new UserPrefs()); //no Freshman
        FindingParticipantPredicate predicate = preparePredicate();
        ListParticipantCommand command = new ListParticipantCommand(predicate);
        expectedModel.updateFilteredParticipantList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, commandHistory,
                expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MARK), model.getFilteredParticipantList());
    }


    @Test
    public void execute_listHasNoFreshman() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs()); //no Freshman
        FindingParticipantPredicate predicate = preparePredicate();
        ListParticipantCommand command = new ListParticipantCommand(predicate);
        expectedModel.updateFilteredParticipantList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        assertCommandSuccess(command, model, commandHistory,
                expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredParticipantList());
    }
}
