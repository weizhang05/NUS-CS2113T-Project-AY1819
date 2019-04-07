package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Person;
import seedu.address.testutil.PersonBuilder;

public class EditGroupCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullOldGroup_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new EditGroupCommand(null, "Red");
    }

    @Test
    public void constructor_nullNewGroup_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new EditGroupCommand("Green", null);
    }

    @Test
    public void execute_repeatGroup_throwsCommandException() throws Exception {
        EditGroupCommand editGroupCommand = new EditGroupCommand("G1", "G1");
        ModelManager modelManager = new ModelManager();

        thrown.expect(CommandException.class);
        thrown.expectMessage(EditGroupCommand.MESSAGE_REPEAT_GROUP);
        editGroupCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_nonexistentOldGroup_throwsCommandException() throws Exception {
        EditGroupCommand editGroupCommand = new EditGroupCommand("G1", "G2");
        ModelManager modelManager = new ModelManager();

        thrown.expect(CommandException.class);
        thrown.expectMessage(EditGroupCommand.MESSAGE_NONEXISTENT_OLD_GROUP);
        editGroupCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_existentNewGroup_throwsCommandException() throws Exception {
        EditGroupCommand editGroupCommand = new EditGroupCommand("G1", "G2");
        ModelManager modelManager = new ModelManager();

        modelManager.addHouse(new House("Green"));
        modelManager.addGroup(new Group("G1", "Green"));
        modelManager.addGroup(new Group("G2", "Green"));

        thrown.expect(CommandException.class);
        thrown.expectMessage(EditGroupCommand.MESSAGE_EXISTENT_NEW_GROUP);
        editGroupCommand.execute(modelManager, commandHistory);
    }

    @Test
    public void execute_editGroupWithPersonSuccessful() throws Exception {
        ModelManager modelManager = new ModelManager();
        Person validPersonWithGroup = new PersonBuilder().withGroup("G1").build();

        modelManager.addHouse(new House("Green"));
        modelManager.addGroup(new Group("G1", "Green"));
        modelManager.addPerson(validPersonWithGroup);

        CommandResult commandResult =
                new EditGroupCommand("G1", "G2").execute(modelManager, commandHistory);

        assertEquals(String.format(EditGroupCommand.MESSAGE_SUCCESS, "G1", "G2"), commandResult.getFeedbackToUser());
        assertEquals(new Group("G2", "Green"), modelManager.getFilteredGroupList().get(0));
        assertEquals("G2", modelManager.getFilteredPersonList().get(0).getGroup().getGroupName());
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }
}
