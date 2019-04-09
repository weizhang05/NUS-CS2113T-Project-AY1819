package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Person;

/**
 * Edits the name of a House.
 * Old House must exist, new House must not, the old and new House must not have the same name.
 * Updates the groups in that house in the group list.
 */
public class EditHouseCommand extends Command {
    public static final String COMMAND_WORD = "edit_h";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an existing House name. Parameters: "
            + "oldHouseName + newHouseName";
    public static final String MESSAGE_SUCCESS = "House edited: from %1$s to %2$s";
    public static final String MESSAGE_NONEXISTENT_OLD_HOUSE = "Old House does not exist.";
    public static final String MESSAGE_REPEAT_HOUSE = "Enter a House name that is different!";
    public static final String MESSAGE_EXISTENT_NEW_HOUSE = "This House name already exists! Choose a unique name";

    private static String oldHouseName;
    private static String newHouseName;

    public EditHouseCommand(String oldHouseName, String newHouseName) {
        requireNonNull(oldHouseName);
        requireNonNull(newHouseName);
        this.oldHouseName = oldHouseName;
        this.newHouseName = newHouseName;
    }

    public static String getNewHouseName() {
        return newHouseName;
    }

    public static String getOldHouseName() {
        return oldHouseName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (newHouseName.equals(oldHouseName)) {
            throw new CommandException(MESSAGE_REPEAT_HOUSE);
        }

        ObservableList<House> houseList = model.getFilteredHouseList();

        House oldHouse = new House(oldHouseName);
        House newHouse = new House(newHouseName);

        boolean nonexistent = true;
        boolean existentNew = false;

        //check for existing houses
        for (House toList : houseList) {
            if (toList.getHouseName().equals(oldHouseName)) {
                nonexistent = false;
            }
            if (toList.getHouseName().equals(newHouseName)) {
                existentNew = true;
                break;
            }
        }

        if (nonexistent) {
            throw new CommandException(MESSAGE_NONEXISTENT_OLD_HOUSE);
        }
        if (existentNew) {
            throw new CommandException(MESSAGE_EXISTENT_NEW_HOUSE);
        }

        //updates groups in old House to new houseName
        ObservableList<Group> groupList = model.getFilteredGroupList();
        for (Group group : groupList) {
            if (group.getHouseName().equals(oldHouseName)) {
                Group newGroup = new Group(group.getGroupName(), newHouseName);
                model.setGroup(group, newGroup);
            }
        }
        //updates participants with old house name to new house name
        List<Person> personList = model.getAddressBook().getPersonList();
        for (Person person : personList) {
            if (person.getGroup().getHouseName().equals(oldHouseName)) {
                Person editedPerson = new Person(person.getName(), person.getSex(), person.getBirthday(),
                        person.getPhone(), person.getEmail(), person.getMajor(),
                        new Group(person.getGroup().getGroupName(), newHouseName), person.getTags());
                model.setPerson(person, editedPerson);
            }
        }

        model.setHouse(oldHouse, newHouse);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_SUCCESS, oldHouseName, newHouseName));
    }
}
