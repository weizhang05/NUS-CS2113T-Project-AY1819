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
        if (splitArgs.length != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListGroupCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = splitArgs[0].split("\\s+");

        return new ListGroupCommand(new FindGroupPredicate(Arrays.asList(nameKeywords)));
    }
}
