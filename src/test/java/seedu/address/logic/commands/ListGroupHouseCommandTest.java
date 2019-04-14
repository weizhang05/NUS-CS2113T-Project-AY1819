package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroupHousePersonList.ALICIA;
import static seedu.address.testutil.TypicalGroupHousePersonList.BENEDICT;
import static seedu.address.testutil.TypicalGroupHousePersonList.COCO;
import static seedu.address.testutil.TypicalGroupHousePersonList.getTypicalAddressBookWithGroupHouse;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.grouping.FindGroupPredicate;
import seedu.address.model.grouping.FindHousePredicate;

/**
 * List house and group tested in the same class due to similar implementation.
 */
public class ListGroupHouseCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroupHouse(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    private FindGroupPredicate prepareGroupPredicate(String group) {
        return new FindGroupPredicate(Arrays.asList(group));
    }
    private FindHousePredicate prepareHousePredicate(String house) {
        return new FindHousePredicate(Arrays.asList(house));
    }

    @Test
    public void execute_noParticipantsInGroup() {
        String toFind = "B2";
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        FindGroupPredicate predicate = prepareGroupPredicate(toFind);
        ListGroupCommand listGroupCommand = new ListGroupCommand(predicate, toFind);
        expectedModel.updateFilteredParticipantList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        assertCommandSuccess(listGroupCommand, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredParticipantList());
    }

    @Test
    public void execute_oneParticipantInGroup_commandSuccess() {
        String toFind = "R1";
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        FindGroupPredicate predicate = prepareGroupPredicate(toFind);
        ListGroupCommand listGroupCommand = new ListGroupCommand(predicate, toFind);
        expectedModel.updateFilteredParticipantList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(listGroupCommand, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICIA), model.getFilteredParticipantList());
    }

    @Test
    public void execute_findParticipantsWithEmptyGroup_commandSuccess() {
        String toFind = "EMPTY";
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        FindGroupPredicate predicate = prepareGroupPredicate(toFind);
        ListGroupCommand listGroupCommand = new ListGroupCommand(predicate, toFind);
        expectedModel.updateFilteredParticipantList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(listGroupCommand, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENEDICT), model.getFilteredParticipantList());
    }

    @Test
    public void execute_noParticipantsInHouse_commandSuccess() {
        String toFind = "Yellow";
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        FindHousePredicate predicate = prepareHousePredicate(toFind);
        ListHouseCommand listHouseCommand = new ListHouseCommand(predicate, toFind);
        expectedModel.updateFilteredParticipantList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        assertCommandSuccess(listHouseCommand, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredParticipantList());
    }

    @Test
    public void execute_twoParticipantsDifferentGroupsInHouse_commandSuccess() {
        String toFind = "Red";
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        FindHousePredicate predicate = prepareHousePredicate(toFind);
        ListHouseCommand listHouseCommand = new ListHouseCommand(predicate, toFind);
        expectedModel.updateFilteredParticipantList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        assertCommandSuccess(listHouseCommand, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICIA, COCO), model.getFilteredParticipantList());
    }
}
