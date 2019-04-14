package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteHouseCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteHouseCommand object
 */
public class DeleteHouseCommandParser implements Parser<DeleteHouseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteHouseCommand
     * and returns an DeleteHouseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public DeleteHouseCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteHouseCommand.MESSAGE_USAGE));
        }

        String[] splitArg = args.trim().split("\\s+");

        if (splitArg.length != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteHouseCommand.MESSAGE_USAGE));
        }
        String houseName = splitArg[0].trim();
        houseName = houseName.substring(0, 1).toUpperCase() + houseName.substring(1).toLowerCase();

        return new DeleteHouseCommand(houseName);
    };
}
