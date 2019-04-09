package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddHouseCommand;
import seedu.address.logic.commands.AddOglCommand;
import seedu.address.logic.commands.AddParticipantCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.commands.DeleteHouseCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditGroupCommand;
import seedu.address.logic.commands.EditHouseCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.ExportFreshmenCommand;
import seedu.address.logic.commands.ExportOglCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListGroupCommand;
import seedu.address.logic.commands.ListOglCommand;
import seedu.address.logic.commands.ListParticipantCommand;
import seedu.address.logic.commands.RandomizeCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SaveChartCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.SizeCommand;
import seedu.address.logic.commands.StatCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewGroupsCommand;
import seedu.address.logic.commands.ViewHousesCommand;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddGroupCommand.COMMAND_WORD:
            return new AddGroupCommandParser().parse(arguments);

        case AddHouseCommand.COMMAND_WORD:
            return new AddHouseCommandParser().parse(arguments);

        case AddOglCommand.COMMAND_WORD:
            return new AddOglCommandParser().parse(arguments);

        case AddParticipantCommand.COMMAND_WORD:
            return new AddParticipantCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case EditGroupCommand.COMMAND_WORD:
            return new EditGroupCommandParser().parse(arguments);

        case EditHouseCommand.COMMAND_WORD:
            return new EditHouseCommandParser().parse(arguments);

        case ExportCommand.COMMAND_WORD:
            return new ExportCommand();

        case ExportFreshmenCommand.COMMAND_WORD:
            return new ExportFreshmenCommand();


        case ExportOglCommand.COMMAND_WORD:
            return new ExportOglCommand();

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteGroupCommand.COMMAND_WORD:
            return new DeleteGroupCommandParser().parse(arguments);

        case DeleteHouseCommand.COMMAND_WORD:
            return new DeleteHouseCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListOglCommand.COMMAND_WORD:
            return new ListOglCommandParser().parse(arguments);

        case ListParticipantCommand.COMMAND_WORD:
            return new ListParticipantCommandParser().parse(arguments);

        case ListGroupCommand.COMMAND_WORD:
            return new ListGroupCommandParser().parse(arguments);

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case RandomizeCommand.COMMAND_WORD:
            return new RandomizeCommand();

        case SizeCommand.COMMAND_WORD:
            return new SizeCommand();

        case ViewGroupsCommand.COMMAND_WORD:
            return new ViewGroupsCommand();

        case ViewHousesCommand.COMMAND_WORD:
            return new ViewHousesCommand();

        case StatCommand.COMMAND_WORD:
            return new StatCommand();

        case SaveChartCommand.COMMAND_WORD:
            return new SaveChartCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
