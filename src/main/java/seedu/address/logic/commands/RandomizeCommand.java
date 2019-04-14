package seedu.address.logic.commands;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.Value;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.Group;
import seedu.address.model.participant.Participant;
import seedu.address.model.tag.Tag;

/**
 * Randomly assigns all participants to all available groups.
 *
 * Evenly distributes the participants (number differs from either 1 or 2).
 */
public class RandomizeCommand extends Command {

    public static final String COMMAND_WORD = "randomize";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Evenly distribute all participants across all groups.";

    public static final String MESSAGE_SUCCESS = "Participants are evenly distributed";

    public static final String MESSAGE_FAILURE = "Unable to distribute participants";
    public static final String MESSAGE_INSUFFICIENT_PARTICIPANTS = MESSAGE_FAILURE + ": Insufficient participants";
    public static final String MESSAGE_INSUFFICIENT_OGLS = MESSAGE_FAILURE + ": Insufficient OGLs";
    public static final String MESSAGE_INSUFFICIENT_GROUPS = MESSAGE_FAILURE + ": Insufficient groups";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        List<Group> groups = model.getFilteredGroupList();

        if (groups.size() < 2) {
            throw new CommandException(MESSAGE_INSUFFICIENT_GROUPS);
        }

        List<Participant> participants = model.getFilteredParticipantList();
        List<Participant> freshmen = new ArrayList<>();
        List<Participant> ogls = new ArrayList<>();

        for (int i = 0; i < participants.size(); i++) {
            Participant p = participants.get(i);
            if (p.getTags().contains(new Tag(Value.FRESHMAN))) {
                freshmen.add(p);
            } else if (p.getTags().contains(new Tag(Value.OGL))) {
                ogls.add(p);
            }
        }

        if (freshmen.size() < 2) {
            throw new CommandException(MESSAGE_INSUFFICIENT_PARTICIPANTS);
        } else if (ogls.size() < groups.size()) {
            throw new CommandException(MESSAGE_INSUFFICIENT_OGLS);
        }

        // SecureRandom is used as a seed for better randomness
        Collections.shuffle(freshmen, new SecureRandom());
        Collections.shuffle(ogls, new SecureRandom());


        // Required for retrieving group information
        int counter = 0;

        Participant originalParticipant;
        Participant editedParticipant;

        for (Participant freshman : freshmen) {
            originalParticipant = freshman;
            editedParticipant = getParticipantUpdatedGroup(originalParticipant,
                    groups.get(counter % groups.size()));

            model.setParticipant(originalParticipant, editedParticipant);

            ++counter;
        }
        for (Participant ogl : ogls) {
            originalParticipant = ogl;
            editedParticipant = getParticipantUpdatedGroup(originalParticipant,
                    groups.get(counter % groups.size()));

            model.setParticipant(originalParticipant, editedParticipant);

            ++counter;
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates and returns a {@code Participant} with the updated {@code group}.
     */
    private static Participant getParticipantUpdatedGroup(Participant participantToEdit, Group group) {
        return new Participant(participantToEdit.getName(), participantToEdit.getSex(),
                participantToEdit.getBirthday(), participantToEdit.getPhone(), participantToEdit.getEmail(),
                participantToEdit.getMajor(), group, participantToEdit.getTags());
    }
}
