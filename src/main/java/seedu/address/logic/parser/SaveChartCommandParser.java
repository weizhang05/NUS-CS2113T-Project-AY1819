package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.SaveChartCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddGroupCommand object
 */
public class SaveChartCommandParser implements Parser<SaveChartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SaveChartCommand
     * and returns an SaveChartCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public SaveChartCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String fileName;

        if (args.isEmpty()) {
            fileName = "";
        } else {
            String[] splitArg = args.trim().split(" ", 1);

            fileName = splitArg[0];
        }

        return new SaveChartCommand(fileName);
    }
}
