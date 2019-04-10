package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.DeleteHouseCommand;

public class DeleteHouseCommandParserTest {
    private DeleteHouseCommandParser parser = new DeleteHouseCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteGroupCommand() {
        assertParseSuccess(parser, "Red", new DeleteHouseCommand("Red"));
    }

    @Test
    public void parse_validArgsDifferentCaps_returnsDeleteGroupCommand() {
        assertParseSuccess(parser, "bLuE", new DeleteHouseCommand("Blue"));
    }

    @Test
    public void parse_emptyArgs() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteHouseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_longArgs() {
        assertParseFailure(parser, "Red bLuE",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteHouseCommand.MESSAGE_USAGE));
    }
}
