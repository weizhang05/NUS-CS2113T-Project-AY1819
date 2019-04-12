package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.EditGroupCommand;

public class EditGroupCommandParserTest {
    private EditGroupCommandParser parser = new EditGroupCommandParser();

    @Test
    public void parse_validArgs_returnsEditGroupCommand() {
        assertParseSuccess(parser, "R1 R2", new EditGroupCommand("R1", "R2"));
    }

    @Test
    public void parse_validArgsDifferentCapsWithSpaces_returnsAddGroupCommand() {
        assertParseSuccess(parser, "r1    R3",
                new EditGroupCommand("R1", "R3"));
    }

    @Test
    public void parse_shorterArgs_throwsParseException() {
        assertParseFailure(parser, "r1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_longerArgs_throwsParseException() {
        assertParseFailure(parser, "R1 R2 R3",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGroupCommand.MESSAGE_USAGE));
    }
}
