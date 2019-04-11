package seedu.address.logic.commands;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        /**
         * Exception is thrown where there is less than 2 groups or 2 people in the system.
         */
        if (participants.size() < 2 || groups.size() < 2) {
            throw new CommandException(MESSAGE_FAILURE + ":"
                    + "\nNum participants: " + participants.size()
                    + "\nNum groups: " + groups.size());
        }

        List<Integer> jumbledOrder = new ArrayList<>();
        for (int i = 1; i <= participants.size(); i++) {
            jumbledOrder.add(i);
        }
        Collections.shuffle(jumbledOrder, new SecureRandom());

        Command command;
        int counter = 0;

        for (int i = 0; i < participants.size(); i++) {
            try {
                command = new EditCommandParser().parse(jumbledOrder.get(i)
                        + " g/" + groups.get(counter % groups.size()).getGroupName());
                command.execute(model, history);
                ++counter;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
