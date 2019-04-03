package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ListGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.grouping.FindGroupPredicate;

public class ListGroupCommandParser implements Parser<ListGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListGroupCommand
     * and returns an ListGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListGroupCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ");
        if (splitArgs.length != 1 || args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListGroupCommand.MESSAGE_USAGE));
        }

        splitArgs[0] = splitArgs[0].toUpperCase();

        return new ListGroupCommand(new FindGroupPredicate(Arrays.asList(splitArgs[0])), splitArgs[0]);
    }
}
