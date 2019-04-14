package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.participant.Participant;

/**
 * Displays the size of all lists (Participant, OGL, Freshman, Group and House) in the Command result box.
 */
public class SizeCommand extends Command {
    public static final String COMMAND_WORD = "size";

    public static final String MESSAGE_SUCCESS = "Total Participants: %1$s\n"
            + "OGLs: %2$s            Freshmen: %3$s\n"
            + "Groups: %4$s         Houses: %5$s";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        List<Participant> participantList = model.getFilteredParticipantList();
        int numOgl = 0;
        int numFreshman = 0;

        for (Participant participant : participantList) {
            if (participant.getStringTags().contains("OGL")) {
                numOgl++;
            } else if (participant.getStringTags().contains("Freshman")) {
                numFreshman++;
            }
        }

        int groupSize = model.sizeGroupList();
        int houseSize = model.sizeHouseList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, Integer.toString(participantList.size()),
                Integer.toString(numOgl), Integer.toString(numFreshman), Integer.toString(groupSize),
                Integer.toString(houseSize)));
    }
}
