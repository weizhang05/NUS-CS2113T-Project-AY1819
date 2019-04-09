package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ListHouseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.grouping.FindHousePredicate;

public class ListHouseCommandParser implements Parser<ListHouseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListGroupCommand
     * and returns an ListGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListHouseCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ");
        if (splitArgs.length != 1 || args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListHouseCommand.MESSAGE_USAGE));
        }

        splitArgs[0] = splitArgs[0].substring(0, 1).toUpperCase() + splitArgs[0].substring(1).toLowerCase();

        return new ListHouseCommand(new FindHousePredicate(Arrays.asList(splitArgs[0])), splitArgs[0]);
    }
}
