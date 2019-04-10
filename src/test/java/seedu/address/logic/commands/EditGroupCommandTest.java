package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroupHousePersonList.getTypicalAddressBookWithGroupHouse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.grouping.Group;
import seedu.address.model.participant.Person;
import seedu.address.testutil.PersonBuilder;

public class EditGroupCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalAddressBookWithGroupHouse(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullOldGroup_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new EditGroupCommand(null, "R1");
    }

    @Test
    public void constructor_nullNewGroup_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new EditGroupCommand("R1", null);
    }

    @Test
    public void execute_editGroupWithPersonSuccessful() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        EditGroupCommand editGroupCommand = new EditGroupCommand("R1", "R3");
        String expectedMessage = String.format(EditGroupCommand.MESSAGE_SUCCESS, "R1", "R3");

        Person editedPerson = new PersonBuilder().withName("Alicia Alice")
                .withSex("F").withBirthday("07081994").withMajor("CS").withEmail("alicia@example.com")
                .withPhone("94351253").withGroup("R3", "Red").build();
        Person toEdit = model.getFilteredPersonList().get(0);

        expectedModel.setGroup(new Group("R1", "Red"),
                new Group("R3", "Red"));
        expectedModel.setPerson(toEdit, editedPerson);
        expectedModel.commitAddressBook();
        assertCommandSuccess(editGroupCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_repeatGroup_throwsCommandException() {
        EditGroupCommand editGroupCommand = new EditGroupCommand("R1", "R1");
        assertCommandFailure(editGroupCommand, model, commandHistory, EditGroupCommand.MESSAGE_REPEAT_GROUP);
    }

    @Test
    public void execute_nonexistentOldGroup_throwsCommandException() {
        EditGroupCommand editGroupCommand = new EditGroupCommand("G1", "B2");
        assertCommandFailure(editGroupCommand, model, commandHistory, EditGroupCommand.MESSAGE_NONEXISTENT_OLD_GROUP);
    }

    @Test
    public void execute_existentNewGroup_throwsCommandException() {
        EditGroupCommand editGroupCommand = new EditGroupCommand("R1", "B1");
        assertCommandFailure(editGroupCommand, model, commandHistory, EditGroupCommand.MESSAGE_EXISTENT_NEW_GROUP);
    }
}
