package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.EditHouseCommand;

public class EditHouseCommandParserTests {
    private EditHouseCommandParser parser = new EditHouseCommandParser();

    @Test
    public void parse_validArgs_returnsEditGroupCommand() {
        assertParseSuccess(parser, "Red Green",
                new EditHouseCommand("Red", "Green"));
    }

    @Test
    public void parse_validArgsDifferentCapsWithSpaces_returnsAddGroupCommand() {
        assertParseSuccess(parser, "reD   bLue",
                new EditHouseCommand("Red", "Green"));
    }

    @Test
    public void parse_shorterArgs_throwsParseException() {
        assertParseFailure(parser, "Red",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditHouseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_longerArgs_throwsParseException() {
        assertParseFailure(parser, "Red Green Blue",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditHouseCommand.MESSAGE_USAGE));
    }
}
