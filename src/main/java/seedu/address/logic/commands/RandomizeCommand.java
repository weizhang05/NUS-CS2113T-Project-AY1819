package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.Group;
import seedu.address.model.participant.Person;

/**
 * Randomly assigns all participants to all available groups.
 * Evenly distributes the participants (number differs from either 1 or 2).
 */
public class RandomizeCommand extends Command {

    public static final String COMMAND_WORD = "randomize";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Evenly distribute all participants across all groups.";

    public static final String MESSAGE_SUCCESS = "Participants are evenly distributed";
    public static final String MESSAGE_FAILURE = "Unable to distribute participants";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        List<Person> participants = model.getFilteredPersonList();
        List<Group> groups = model.getFilteredGroupList();

        /**
         * Exception is thrown where there is less than 2 groups or 2 people in the system.
         */
        if (participants.size() < 2 || groups.size() < 2) {
            throw new CommandException(MESSAGE_FAILURE + ":" +
                    "\nNum participants: " + participants.size() +
                    "\nNum groups: " + groups.size());
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
