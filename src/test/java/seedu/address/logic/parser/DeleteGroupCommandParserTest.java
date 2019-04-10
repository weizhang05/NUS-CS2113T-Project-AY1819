package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.DeleteGroupCommand;

public class DeleteGroupCommandParserTest {
    private DeleteGroupCommandParser parser = new DeleteGroupCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteGroupCommand() {
        assertParseSuccess(parser, "R1", new DeleteGroupCommand("R1"));
    }

    @Test
    public void parse_validArgsDifferentCaps_returnsDeleteGroupCommand() {
        assertParseSuccess(parser, "red1", new DeleteGroupCommand("RED1"));
    }

    @Test
    public void parse_emptyArgs() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_longArgs() {
        assertParseFailure(parser, "R1 red1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));
    }
}
