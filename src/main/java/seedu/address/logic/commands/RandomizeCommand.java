package seedu.address.logic.commands;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.Value;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
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
    public static final String MESSAGE_INSUFFICIENT_PARTICIPANTS = MESSAGE_FAILURE + ": Insufficient particiapnts";
    public static final String MESSAGE_INSUFFICIENT_OGLS = MESSAGE_FAILURE + ": Insufficient OGLs";
    public static final String MESSAGE_INSUFFICIENT_GROUPS = MESSAGE_FAILURE + ": Insufficient groups";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        List<Participant> participants = new ArrayList<>(model.getFilteredParticipantList());
        List<Group> groups = model.getFilteredGroupList();
        List<Participant> freshmen = new ArrayList<>();
        List<Participant> ogls = new ArrayList<>();

        /**
         * Integer list is to simulate shuffling of freshmen.
         *
         * Algorithm is applied to subsequent objects (i.e. same steps are applied to OGLs as well).
         */
        List<Integer> jumbledOrderFreshmen = new ArrayList<>();
        List<Integer> jumbledOrderOgls = new ArrayList<>();
        for (int i = 1; i <= participants.size(); i++) {
            Participant p = participants.get(i - 1);
            if (p.getTags().contains(new Tag(Value.FRESHMAN))) {
                jumbledOrderFreshmen.add(i);
                freshmen.add(p);
            } else if (p.getTags().contains(new Tag(Value.OGL))) {
                jumbledOrderOgls.add(i);
                ogls.add(p);
            }
        }

        /**
         * Exception is thrown under the following conditions:
         * - Less than 2 groups
         * - Less than 2 people
         * - Insufficient OGLs to be in-charge of all groups
         */
        if (freshmen.size() < 2) {
            throw new CommandException(MESSAGE_INSUFFICIENT_PARTICIPANTS);
        } else if (groups.size() < 2) {
            throw new CommandException(MESSAGE_INSUFFICIENT_GROUPS);
        } else if (ogls.size() < groups.size()) {
            throw new CommandException(MESSAGE_INSUFFICIENT_OGLS);
        }

        /**
         * Shuffling of the index to have a random order of group allocation.
         *
         * SecureRandom is used as a seed to ensure better randomness.
         */
        Collections.shuffle(jumbledOrderFreshmen, new SecureRandom());
        Collections.shuffle(jumbledOrderOgls, new SecureRandom());

        /**
         * command: programmatically execute command
         * counter: required for retrieving group information
         */
        Command command;
        int counter = 0;

        // Programmatically calls the 'edit' to update groups of participants
        for (int i = 0; i < freshmen.size(); i++) {
            try {
                if (freshmen.get(i).getTags().contains(new Tag(Value.FRESHMAN))) {
                    command = new EditCommandParser().parse(jumbledOrderFreshmen.get(i)
                            + " g/" + groups.get(counter % groups.size()).getGroupName());
                    command.execute(model, history);
                    ++counter;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < ogls.size(); i++) {
            try {
                if (ogls.get(i).getTags().contains(new Tag(Value.OGL))) {
                    command = new EditCommandParser().parse(jumbledOrderOgls.get(i)
                            + " g/" + groups.get(counter % groups.size()).getGroupName());
                    command.execute(model, history);
                    ++counter;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
