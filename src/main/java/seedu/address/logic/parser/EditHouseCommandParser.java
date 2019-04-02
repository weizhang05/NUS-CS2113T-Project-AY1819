package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EditHouseCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditHouseCommand object
 */
public class EditHouseCommandParser implements Parser<EditHouseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditHouseCommand
     * and returns an EditHouseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public EditHouseCommand parse(String args) throws ParseException {
        String[] splitArg = args.trim().split(" ");

        if (splitArg.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditHouseCommand.MESSAGE_USAGE));
        }

        String oldHouse = splitArg[0];
        oldHouse = oldHouse.substring(0, 1).toUpperCase() + oldHouse.substring(1).toLowerCase();
        String newHouse = splitArg[1];
        newHouse = newHouse.substring(0, 1).toUpperCase() + newHouse.substring(1).toLowerCase();

        return new EditHouseCommand(oldHouse, newHouse);
    };
}
