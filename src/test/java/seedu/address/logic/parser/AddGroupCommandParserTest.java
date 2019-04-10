package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddGroupCommandParserTest {

    private AddGroupCommandParser parser = new AddGroupCommandParser();

    @Test
    public void parse_validArgs_returnsAddGroupCommand() {
        assertParseSuccess(parser, "R1 Red", new AddGroupCommand("R1", "Red"));
    }

    @Test
    public void parse_validArgsDifferentCapsWithSpaces_returnsAddGroupCommand() {
        assertParseSuccess(parser, "red1    rEd", new AddGroupCommand("RED1", "Red"));
    }

    @Test
    public void parse_shorterArgs_throwsParseException() {
        assertParseFailure(parser, "r1 ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_longerArgs_throwsParseException() {
        assertParseFailure(parser, "R1 Red Blue",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE));
    }
}
