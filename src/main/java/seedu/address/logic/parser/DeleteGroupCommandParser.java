package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteGroupCommand object
 */
public class DeleteGroupCommandParser implements Parser<DeleteGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGroupCommand
     * and returns an DeleteGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public DeleteGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));
        }

        String[] splitArg = args.trim().split(" ");

        if (splitArg.length != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));
        }
        String groupName = splitArg[0].trim().toUpperCase();

        return new DeleteGroupCommand(groupName);
    };
}
