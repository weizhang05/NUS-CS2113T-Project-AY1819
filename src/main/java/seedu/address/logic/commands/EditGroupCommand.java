package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.grouping.Group;
import seedu.address.model.participant.Person;

/**
 * Edits the name of a group.
 * Old group must exist, new group must not, the old and new group must not have the same name.
 * Updates each person in the address book in the old group to the new group.
 */
public class EditGroupCommand extends Command {
    public static final String COMMAND_WORD = "edit_g";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an existing group name. Parameters: "
            + "oldGroupName + newGroupName";
    public static final String MESSAGE_SUCCESS = "Group edited: from %1$s to %2$s";
    public static final String MESSAGE_NONEXISTENT_OLD_GROUP = "Old group does not exist.";
    public static final String MESSAGE_REPEAT_GROUP = "Enter a group name that is different!";
    public static final String MESSAGE_EXISTENT_NEW_GROUP = "This group name already exists! Choose a unique name";

    private static String oldGroupName;
    private static String newGroupName;

    public EditGroupCommand(String oldGroupName, String newGroupName) {
        requireNonNull(oldGroupName);
        requireNonNull(newGroupName);
        this.oldGroupName = oldGroupName;
        this.newGroupName = newGroupName;
    }

    public static String getNewGroupName() {
        return newGroupName;
    }

    public static String getOldGroupName() {
        return oldGroupName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (newGroupName.equals(oldGroupName)) {
            throw new CommandException(MESSAGE_REPEAT_GROUP);
        }

        ReadOnlyAddressBook existingAddressBook = model.getAddressBook();
        ObservableList<Group> groupList = existingAddressBook.getGroupList();

        Group oldGroup = new Group(oldGroupName);
        Group newGroup = new Group(newGroupName);

        boolean nonexistent = true;
        boolean existentNew = false;

        for (Group toList : groupList) {
            if (toList.getGroupName().equals(oldGroupName)) {
                oldGroup.setHouseName(toList.getHouseName());
                newGroup.setHouseName(toList.getHouseName());
                nonexistent = false;
            }
            if (toList.getGroupName().equals(newGroupName)) {
                existentNew = true;
                break;
            }
        }

        if (nonexistent) {
            throw new CommandException(MESSAGE_NONEXISTENT_OLD_GROUP);
        }
        if (existentNew) {
            throw new CommandException(MESSAGE_EXISTENT_NEW_GROUP);
        }

        //updates persons with old group to new group
        List<Person> personList = existingAddressBook.getPersonList();
        for (Person person : personList) {
            if (person.getGroup().equals(oldGroup)) {
                Person editedPerson = new Person(person.getName(), person.getSex(), person.getBirthday(),
                        person.getPhone(), person.getEmail(), person.getMajor(), newGroup, person.getTags());
                model.setPerson(person, editedPerson);
            }
        }
        model.setGroup(oldGroup, newGroup);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_SUCCESS, oldGroupName, newGroupName));
    }
}
