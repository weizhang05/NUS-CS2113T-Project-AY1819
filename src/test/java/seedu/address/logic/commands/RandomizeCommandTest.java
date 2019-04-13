package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.model.util.SampleDataUtil.getTagSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.Value;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Birthday;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Major;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.Phone;
import seedu.address.model.participant.Sex;

public class RandomizeCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_randomizeSuccessful() throws CommandException {
        ModelManager modelManager = new ModelManager();

        House validHouse = new House("Red");
        modelManager.addHouse(validHouse);
        validHouse = new House("Blue");
        modelManager.addHouse(validHouse);

        Group validGroup = new Group("R1", "Red");
        modelManager.addGroup(validGroup);
        validGroup = new Group("B1", "Blue");
        modelManager.addGroup(validGroup);

        Participant validParticipant = new Participant(new Name("Alex Yeoh"), new Sex("M"), new Birthday("01021996"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Major("Computer Science"), new Group(""),
                getTagSet(Value.FRESHMAN));
        modelManager.addParticipant(validParticipant);
        validParticipant = new Participant(new Name("Bernice Yu"), new Sex("M"), new Birthday("01021996"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"), new Major("Information System"), new Group(""),
                getTagSet(Value.FRESHMAN));
        modelManager.addParticipant(validParticipant);

        Participant validOgl = new Participant(new Name("OGL 1"), new Sex("M"), new Birthday("01021996"),
                new Phone("91234567"),
                new Email("ogl1@example.com"), new Major("Computer Science"), new Group(""),
                getTagSet(Value.OGL));
        modelManager.addParticipant(validOgl);
        validOgl = new Participant(new Name("OGL 2"), new Sex("F"), new Birthday("01021996"),
                new Phone("98765432"),
                new Email("ogl2@example.com"), new Major("Information Security"), new Group(""),
                getTagSet(Value.OGL));
        modelManager.addParticipant(validOgl);

        CommandResult commandResult = new RandomizeCommand().execute(modelManager, commandHistory);

        assertEquals(String.format(RandomizeCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_insufficientParticipants_throwsCommandException() throws Exception {
        thrown.expect(CommandException.class);
        thrown.expectMessage(RandomizeCommand.MESSAGE_INSUFFICIENT_PARTICIPANTS);

        ModelManager modelManager = new ModelManager();

        House validHouse = new House("Red");
        modelManager.addHouse(validHouse);
        validHouse = new House("Blue");
        modelManager.addHouse(validHouse);

        Group validGroup = new Group("R1", "Red");
        modelManager.addGroup(validGroup);
        validGroup = new Group("B1", "Blue");
        modelManager.addGroup(validGroup);

        Participant validParticipant = new Participant(new Name("Alex Yeoh"), new Sex("M"), new Birthday("01021996"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Major("Computer Science"), new Group(""),
                getTagSet(Value.FRESHMAN));
        modelManager.addParticipant(validParticipant);

        new RandomizeCommand().execute(modelManager, commandHistory);
    }

    @Test
    public void execute_insufficientGroups_throwsCommandException() throws Exception {
        thrown.expect(CommandException.class);
        thrown.expectMessage(RandomizeCommand.MESSAGE_INSUFFICIENT_GROUPS);

        ModelManager modelManager = new ModelManager();

        House validHouse = new House("Red");
        modelManager.addHouse(validHouse);

        Group validGroup = new Group("R1", "Red");
        modelManager.addGroup(validGroup);

        Participant validParticipant = new Participant(new Name("Alex Yeoh"), new Sex("M"), new Birthday("01021996"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Major("Computer Science"), new Group(""),
                getTagSet(Value.FRESHMAN));
        modelManager.addParticipant(validParticipant);
        validParticipant = new Participant(new Name("Bernice Yu"), new Sex("M"), new Birthday("01021996"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"), new Major("Information System"), new Group(""),
                getTagSet(Value.FRESHMAN));
        modelManager.addParticipant(validParticipant);

        Participant validOgl = new Participant(new Name("OGL 1"), new Sex("M"), new Birthday("01021996"),
                new Phone("91234567"),
                new Email("ogl1@example.com"), new Major("Computer Science"), new Group(""),
                getTagSet(Value.OGL));
        modelManager.addParticipant(validOgl);
        validOgl = new Participant(new Name("OGL 2"), new Sex("F"), new Birthday("01021996"),
                new Phone("98765432"),
                new Email("ogl2@example.com"), new Major("Information Security"), new Group(""),
                getTagSet(Value.OGL));
        modelManager.addParticipant(validOgl);

        new RandomizeCommand().execute(modelManager, commandHistory);
    }

    @Test
    public void execute_insufficientOgls_throwsCommandException() throws Exception {
        thrown.expect(CommandException.class);
        thrown.expectMessage(RandomizeCommand.MESSAGE_INSUFFICIENT_OGLS);

        ModelManager modelManager = new ModelManager();

        House validHouse = new House("Red");
        modelManager.addHouse(validHouse);
        validHouse = new House("Blue");
        modelManager.addHouse(validHouse);

        Group validGroup = new Group("R1", "Red");
        modelManager.addGroup(validGroup);
        validGroup = new Group("B1", "Blue");
        modelManager.addGroup(validGroup);

        Participant validParticipant = new Participant(new Name("Alex Yeoh"), new Sex("M"), new Birthday("01021996"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Major("Computer Science"), new Group(""),
                getTagSet(Value.FRESHMAN));
        modelManager.addParticipant(validParticipant);
        validParticipant = new Participant(new Name("Bernice Yu"), new Sex("M"), new Birthday("01021996"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"), new Major("Information System"), new Group(""),
                getTagSet(Value.FRESHMAN));
        modelManager.addParticipant(validParticipant);

        Participant validOgl = new Participant(new Name("OGL 1"), new Sex("M"), new Birthday("01021996"),
                new Phone("91234567"),
                new Email("ogl1@example.com"), new Major("Computer Science"), new Group(""),
                getTagSet(Value.OGL));
        modelManager.addParticipant(validOgl);

        new RandomizeCommand().execute(modelManager, commandHistory);
    }
}
