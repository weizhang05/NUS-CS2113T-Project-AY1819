package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EditGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditGroupCommand object
 */
public class EditGroupCommandParser implements Parser<EditGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditGroupCommand
     * and returns an EditGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public EditGroupCommand parse(String args) throws ParseException {
        String[] splitArg = args.trim().split("\\s+");

        if (splitArg.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGroupCommand.MESSAGE_USAGE));
        }

        String oldGroup = splitArg[0].toUpperCase();
        String newGroup = splitArg[1].toUpperCase();

        return new EditGroupCommand(oldGroup, newGroup);
    };
}
