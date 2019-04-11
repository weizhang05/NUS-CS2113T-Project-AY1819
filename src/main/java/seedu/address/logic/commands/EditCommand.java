package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import seedu.address.model.grouping.Group;
import seedu.address.model.participant.Birthday;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Major;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.Phone;
import seedu.address.model.participant.Sex;
import seedu.address.model.tag.Tag;


/**
 * Edits the details of an existing participant in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the participant identified "
            + "by the index number used in the displayed participant list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_SEX + "SEX] "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_MAJOR + "ADDRESS] "
            + "[" + PREFIX_GROUP + "GROUP] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Participant: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This participant already exists in the address book.";
    public static final String MESSAGE_NONEXISTENT_GROUP = "This group does not exist. "
            + "A participant must be added to an existent group!";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the participant in the filtered participant list to edit
     * @param editPersonDescriptor details to edit the participant with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Participant> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Participant participantToEdit = lastShownList.get(index.getZeroBased());
        Participant editedParticipant = createEditedPerson(participantToEdit, editPersonDescriptor);

        if (!participantToEdit.isSamePerson(editedParticipant) && model.hasPerson(editedParticipant)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (!editedParticipant.getGroup().getGroupName().equals("") && !model.hasGroup(editedParticipant.getGroup())) {
            throw new CommandException(MESSAGE_NONEXISTENT_GROUP);
        }

        if (!editedParticipant.getGroup().getGroupName().isEmpty()) {
            Group updatedGroup = model.getGroup(editedParticipant.getGroup());
            editedParticipant = new Participant(editedParticipant.getName(), editedParticipant.getSex(),
                    editedParticipant.getBirthday(), editedParticipant.getPhone(), editedParticipant.getEmail(),
                    editedParticipant.getMajor(), updatedGroup, editedParticipant.getTags());
        }

        model.setPerson(participantToEdit, editedParticipant);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedParticipant));
    }

    /**
     * Creates and returns a {@code Participant} with the details of {@code participantToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Participant createEditedPerson(Participant participantToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert participantToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(participantToEdit.getName());
        Sex updatedSex = editPersonDescriptor.getSex().orElse(participantToEdit.getSex());
        Birthday updatedBirthday = editPersonDescriptor.getBirthday().orElse(participantToEdit.getBirthday());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(participantToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(participantToEdit.getEmail());
        Major updatedMajor = editPersonDescriptor.getMajor().orElse(participantToEdit.getMajor());
        Group updatedGroup = editPersonDescriptor.getGroup().orElse(participantToEdit.getGroup());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(participantToEdit.getTags());

        return new Participant(updatedName, updatedSex, updatedBirthday, updatedPhone, updatedEmail,
                updatedMajor, updatedGroup, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the participant with. Each non-empty field value will replace the
     * corresponding field value of the participant.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Sex sex;
        private Birthday birthday;
        private Phone phone;
        private Email email;
        private Major major;
        private Group group;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setSex(toCopy.sex);
            setBirthday(toCopy.birthday);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setMajor(toCopy.major);
            setGroup(toCopy.group);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, sex, phone, birthday, email, major, group, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setSex(Sex sex) {
            this.sex = sex;
        }

        public Optional<Sex> getSex() {
            return Optional.ofNullable(sex);
        }

        public void setBirthday(Birthday birthday) {
            this.birthday = birthday;
        }

        public Optional<Birthday> getBirthday() {
            return Optional.ofNullable(birthday);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setMajor(Major major) {
            this.major = major;
        }

        public Optional<Major> getMajor() {
            return Optional.ofNullable(major);
        }

        public void setGroup(Group group) {
            this.group = group;
        }

        public Optional<Group> getGroup() {
            return Optional.ofNullable(group);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getSex().equals((e.getSex()))
                    && getBirthday().equals((e.getBirthday()))
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getMajor().equals(e.getMajor())
                    && getGroup().equals(e.getGroup())
                    && getTags().equals(e.getTags());
        }
    }
}
