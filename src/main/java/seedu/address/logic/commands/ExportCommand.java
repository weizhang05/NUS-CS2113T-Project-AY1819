package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.WriteToExcel;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.participant.Person;

/**
 * Export the data of the FOP Manager into ExcelSheets.
 */
public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the contacts into Excel file.\n";

    @Override
    public CommandResult execute (Model model, CommandHistory commandHistory) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person>personList = model.getFilteredPersonList();

        String message;
        if (exportData(personList)) {
            message = String.format(Messages.MESSAGE_EXCEL_FILE_WRITTEN_SUCCESSFULLY);
        } else {
            message = Messages.MESSAGE_EXPORT_COMMAND_ERRORS;
        }
        return new CommandResult(message);
    }
    /**
    * Export the contacts into Excel File.
    */
    private static Boolean exportData(List <Person> personList) {
        if (personList.size() > 0) {
            WriteToExcel.writeExcelSheet(personList);
            return true;
        } else {
            return false;
        }
    }
}
