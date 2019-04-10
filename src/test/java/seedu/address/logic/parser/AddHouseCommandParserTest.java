package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.AddHouseCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddHouseCommandParserTest {
    private AddHouseCommandParser parser = new AddHouseCommandParser();

    @Test
    public void parse_validArgs_returnsAddHouseCommand() {
        assertParseSuccess(parser, "Red", new AddHouseCommand("Red"));
    }

    @Test
    public void parse_validArgsDifferentCaps_returnsAddHouseCommand() {
        assertParseSuccess(parser, "red_HouSe", new AddHouseCommand("Red_house"));
    }

    @Test
    public void parse_emptyArgs() throws ParseException {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHouseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_longArgs() throws ParseException {
        assertParseFailure(parser, "Red Green",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHouseCommand.MESSAGE_USAGE));
    }
}
