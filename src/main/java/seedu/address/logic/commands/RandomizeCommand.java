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

/**
 * Randomly assigns all participants to all available groups.
 * Evenly distributes the participants (number differs from either 1 or 2).
 */
public class RandomizeCommand extends Command {

    public static final String COMMAND_WORD = "randomize";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Evenly distribute all participants across all groups.";

    public static final String MESSAGE_SUCCESS = "Participants are evenly distributed";
    public static final String MESSAGE_FAILURE = "Unable to distribute participants "
            + "(min 2 groups and 2 participants)";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        List<Participant> participants = new ArrayList<>(model.getFilteredPersonList());
        List<Group> groups = model.getFilteredGroupList();
        List<Participant> freshmen = new ArrayList<>(), ogls = new ArrayList<>();

        for (Participant p : participants) {
            if (p.getTags().contains(Value.FRESHMAN)) {
                freshmen.add(p);
            } else if (p.getTags().contains(Value.OGL)) {
                ogls.add(p);
            }
        }

        /**
         * Exception is thrown under the following conditions:
         * - Less than 2 groups
         * - Less than 2 people
         * - Insufficient OGLs to be in-charge of all groups
         */
        if (freshmen.size() < 2 || groups.size() < 2 || ogls.size() < groups.size()) {
            throw new CommandException(MESSAGE_FAILURE + ":"
                    + "\nNumber of participants: " + participants.size()
                    + "\nNumber of OGLs:" + ogls.size()
                    + "\nNumber of groups: " + groups.size());
        }

        /**
         * command: programmatically execute command
         * counter: required for retrieving group information
         */
        Command command;
        int counter = 0;

        // Integer list is to simulate shuffling of freshmen (applies to subsequent shuffling)
        List<Integer> jumbledOrderFreshmen = new ArrayList<>();
        for (int i = 1; i <= freshmen.size(); i++) {
            jumbledOrderFreshmen.add(i);
        }
        Collections.shuffle(jumbledOrderFreshmen, new SecureRandom());
        // Programmatically calls the 'edit' to update groups of participants
        for (int i = 0; i < freshmen.size(); i++) {
            try {
                if (participants.get(i).getTags().contains(Value.FRESHMAN)) {
                    command = new EditCommandParser().parse(jumbledOrderFreshmen.get(i)
                            + " g/" + groups.get(counter % groups.size()).getGroupName());
                    command.execute(model, history);
                    ++counter;
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Resets the counter
        counter = 0;

        // Algorithm is applied to subsequent objects (i.e. same steps are applied to OGLs as well)
        List<Integer> jumbledOrderOgls = new ArrayList<>();
        for (int i = 1; i <= freshmen.size(); i++) {
            jumbledOrderOgls.add(i);
        }
        Collections.shuffle(jumbledOrderOgls, new SecureRandom());
        for (int i = 0; i < ogls.size(); i++) {
            try {
                if (participants.get(i).getTags().contains(Value.FRESHMAN)) {
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
