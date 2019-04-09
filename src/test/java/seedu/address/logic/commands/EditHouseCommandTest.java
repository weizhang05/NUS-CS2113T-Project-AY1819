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
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Person;
import seedu.address.testutil.PersonBuilder;

public class EditHouseCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalAddressBookWithGroupHouse(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullOldHouse_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new EditHouseCommand(null, "Red");
    }

    @Test
    public void constructor_nullNewHouse_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new EditHouseCommand("Green", null);
    }

    @Test
    public void execute_repeatHouse_throwsCommandException() throws Exception {
        EditHouseCommand editHouseCommand = new EditHouseCommand("Red", "Red");
        assertCommandFailure(editHouseCommand, model, commandHistory, EditHouseCommand.MESSAGE_REPEAT_HOUSE);
    }

    @Test
    public void execute_nonexistentOldHouse_throwsCommandException() throws Exception {
        EditHouseCommand editHouseCommand = new EditHouseCommand ("Green", "Brown");
        assertCommandFailure(editHouseCommand, model, commandHistory, EditHouseCommand.MESSAGE_NONEXISTENT_OLD_HOUSE);
    }
    @Test
    public void execute_existentNewHouse_throwsCommandException() throws Exception {
        EditHouseCommand editHouseCommand = new EditHouseCommand ("Red", "Blue");
        assertCommandFailure(editHouseCommand, model, commandHistory, EditHouseCommand.MESSAGE_EXISTENT_NEW_HOUSE);
    }

    @Test
    public void execute_editHouseWithGroupSuccess() throws Exception {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        EditHouseCommand editHouseCommand = new EditHouseCommand("Red", "Green");
        String expectedMessage = String.format(EditHouseCommand.MESSAGE_SUCCESS, "Red", "Green");

        Person editedPerson = new PersonBuilder().withName("Alicia Alice")
                .withSex("F").withBirthday("07081994").withMajor("CS").withEmail("alicia@example.com")
                .withPhone("94351253").withGroup("R1", "Green").build();
        Person toEdit = model.getFilteredPersonList().get(0);

        expectedModel.setGroup(new Group("R1", "Red"),
                new Group("R1", "Green"));
        expectedModel.setGroup(new Group("R2", "Red"),
                new Group("R2", "Green"));
        expectedModel.setHouse(new House("Red"), new House("Green"));
        expectedModel.setPerson(toEdit, editedPerson);
        expectedModel.commitAddressBook();
        assertCommandSuccess(editHouseCommand, model, commandHistory, expectedMessage, expectedModel);
    }
}
