package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.WriteToExcel;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.participant.Participant;

import seedu.address.model.person.FindingOglPredicate;

/**
 * Export the data of the OGL FOP Manager into ExcelSheets.
 */
public class ExportOglCommand extends Command {
    public static final String COMMAND_WORD = "export_o";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports all the ogl in the contacts into Excel file.\n";

    private FindingOglPredicate preparePredicate() {
        return new FindingOglPredicate(Arrays.asList("Ogl".split("\\s+")));
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        FindingOglPredicate predicate = preparePredicate();
        model.updateFilteredPersonList(predicate);
        List<Participant> participantList = model.getFilteredPersonList();
        String message;
        if (exportData(participantList)) {
            message = String.format(Messages.MESSAGE_EXCEL_FILE_WRITTEN_SUCCESSFULLY);
        } else {
            message = Messages.MESSAGE_EXPORT_COMMAND_ERRORS;
        }
        return new CommandResult(message);
    }

    /**
     * Export the contacts into Excel File.
     */
    private static Boolean exportData(List<Participant> participantList) {
        if (participantList.size() >= 0) {
            WriteToExcel.writeExcelSheetOgl(participantList);
            return true;
        } else {
            return false;
        }
    }

}
