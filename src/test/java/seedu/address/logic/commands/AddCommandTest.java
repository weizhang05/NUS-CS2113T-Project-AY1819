package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Participant;
import seedu.address.testutil.PersonBuilder;


public class AddCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddCommand(null);
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Participant validParticipant = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validParticipant).execute(modelStub, commandHistory);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validParticipant), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validParticipant), modelStub.personsAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() throws Exception {
        Participant validParticipant = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validParticipant);
        ModelStub modelStub = new ModelStubWithPerson(validParticipant);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddCommand.MESSAGE_DUPLICATE_PERSON);
        addCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Participant alice = new PersonBuilder().withName("Alice").build();
        Participant bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different participant -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getChartStoragePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isEmpty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableMap<String, Integer> getAgeData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableMap<String, Integer> getSexData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableMap<String, Integer> getMajorData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getFileName() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFileName(String fileName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Participant participant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Participant participant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Participant target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Participant target, Participant editedParticipant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Participant> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<String> getUndoList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<String> getRedoList() {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void updateFilteredPersonList(Predicate<Participant> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Participant> selectedPersonProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Participant getSelectedPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedPerson(Participant participant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGroup(Group target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGroup(Group target, Group editedGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group getGroup(Group group) {
            return new Group("", null);
        }

        @Override
        public ObservableList<Group> getFilteredGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int sizeGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public House getHouse(House house) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasHouse(House house) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteHouse(House target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addHouse(House house) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHouse(House target, House editedHouse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<House> getFilteredHouseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredHouseList(Predicate<House> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int sizeHouseList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single participant.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Participant participant;

        ModelStubWithPerson(Participant participant) {
            requireNonNull(participant);
            this.participant = participant;
        }

        @Override
        public boolean hasPerson(Participant participant) {
            requireNonNull(participant);
            return this.participant.isSamePerson(participant);
        }
    }

    /**
     * A Model stub that always accept the participant being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Participant> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Participant participant) {
            requireNonNull(participant);
            return personsAdded.stream().anyMatch(participant::isSamePerson);
        }

        @Override
        public void addPerson(Participant participant) {
            requireNonNull(participant);
            personsAdded.add(participant);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
