package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.exceptions.ParticipantNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final UserPrefs userPrefs;

    private final FilteredList<Participant> filteredParticipants;
    private final SimpleObjectProperty<Participant> selectedParticipant = new SimpleObjectProperty<>();

    private final FilteredList<Group> filteredGroups;
    private final SimpleObjectProperty<Group> selectedGroups = new SimpleObjectProperty<>();

    private final FilteredList<House> filteredHouses;
    private final SimpleObjectProperty<House> selectedHouses = new SimpleObjectProperty<>();

    private String undoableCommand;
    private String fileName;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredParticipants = new FilteredList<>(versionedAddressBook.getParticipantList());
        filteredParticipants.addListener(this::ensureSelectedParticipantIsValid);

        filteredGroups = new FilteredList<>(versionedAddressBook.getGroupList());
        filteredGroups.addListener(this::ensureSelectedGroupIsValid);

        filteredHouses = new FilteredList<>(versionedAddressBook.getHouseList());
        filteredHouses.addListener(this::ensureSelectedHouseIsValid);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getChartStoragePath() {
        return userPrefs.getChartStoragePath();
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public boolean isEmpty() {
        return versionedAddressBook.isEmpty();
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        versionedAddressBook.resetData(addressBook);
        undoableCommand = "Clear all participants";
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    @Override
    public boolean hasParticipant(Participant participant) {
        requireNonNull(participant);
        return versionedAddressBook.hasParticipant(participant);
    }

    @Override
    public void deleteParticipant(Participant target) {
        versionedAddressBook.removeParticipant(target);
        undoableCommand = "Delete " + target.getName().fullName;
    }

    @Override
    public void addParticipant(Participant participant) {
        versionedAddressBook.addParticipant(participant);
        updateFilteredParticipantList(PREDICATE_SHOW_ALL_PARTICIPANTS);
        undoableCommand = "Add " + participant.getName().fullName;
    }

    @Override
    public void setParticipant(Participant target, Participant editedParticipant) {
        requireAllNonNull(target, editedParticipant);

        versionedAddressBook.setParticipant(target, editedParticipant);
        undoableCommand = "Edit " + editedParticipant.getName().fullName;
    }

    //=========== Charts Related =============================================================================

    @Override
    public ObservableMap<String, Integer> getAgeData() {
        return versionedAddressBook.getAgeData();
    }

    @Override
    public ObservableMap<String, Integer> getMajorData() {
        return versionedAddressBook.getMajorData();
    }

    @Override
    public ObservableMap<String, Integer> getSexData() {
        return versionedAddressBook.getSexData();
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //=========== Filtered Participant List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Undoable Command} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<String> getUndoList() {
        return FXCollections.observableArrayList(versionedAddressBook.getUndoList());
    }

    /**
     * Returns an unmodifiable view of the list of {@code Redoable Command} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<String> getRedoList() {
        return FXCollections.observableArrayList(versionedAddressBook.getRedoList());
    }

    /**
     * Returns an unmodifiable view of the list of {@code Undoable Command} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Participant> getFilteredParticipantList() {
        return filteredParticipants;
    }

    @Override
    public void updateFilteredParticipantList(Predicate<Participant> predicate) {
        requireNonNull(predicate);
        filteredParticipants.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
        versionedAddressBook.addUndoableCommand(undoableCommand);
    }

    //=========== Selected participant ===========================================================================

    @Override
    public ReadOnlyProperty<Participant> selectedParticipantProperty() {
        return selectedParticipant;
    }

    @Override
    public Participant getSelectedParticipant() {
        return selectedParticipant.getValue();
    }

    @Override
    public void setSelectedParticipant(Participant participant) {
        if (participant != null && !filteredParticipants.contains(participant)) {
            throw new ParticipantNotFoundException();
        }
        selectedParticipant.setValue(participant);
    }

    // ================ Group Operations ======================
    @Override
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return versionedAddressBook.hasGroup(group);
    }

    @Override
    public void deleteGroup(Group target) {
        versionedAddressBook.removeGroup(target);
        undoableCommand = "Delete Group " + target.getGroupName();
    }

    @Override
    public void addGroup(Group group) {
        versionedAddressBook.addGroup(group);
        updateFilteredParticipantList(PREDICATE_SHOW_ALL_PARTICIPANTS);
        undoableCommand = "Add Group " + group.getGroupName();
    }

    @Override
    public void setGroup(Group target, Group editedGroup) {
        requireAllNonNull(target, editedGroup);

        versionedAddressBook.setGroup(target, editedGroup);
        undoableCommand = "Edit Group " + editedGroup.getGroupName();
    }

    @Override
    public Group getGroup(Group toGet) {
        requireNonNull(toGet);
        return versionedAddressBook.getGroup(toGet);
    }

    @Override
    public ObservableList<Group> getFilteredGroupList() {
        return filteredGroups;
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        requireNonNull(predicate);
        filteredGroups.setPredicate(predicate);
    }

    @Override
    public int sizeGroupList () {
        return filteredGroups.size();
    }

    // ================ House Operations ======================
    @Override
    public House getHouse(House house) {
        return null;
    }

    @Override
    public boolean hasHouse(House house) {
        requireNonNull(house);
        return versionedAddressBook.hasHouse(house);
    }

    @Override
    public void deleteHouse(House target) {
        versionedAddressBook.removeHouse(target);
        undoableCommand = "Delete House " + target.getHouseName();
    }

    @Override
    public void addHouse(House house) {
        versionedAddressBook.addHouse(house);
        updateFilteredParticipantList(PREDICATE_SHOW_ALL_PARTICIPANTS);
        undoableCommand = "Add House " + house.getHouseName();
    }

    @Override
    public void setHouse(House target, House editedHouse) {
        requireAllNonNull(target, editedHouse);

        versionedAddressBook.setHouse(target, editedHouse);
        undoableCommand = "Edit House " + editedHouse.getHouseName();

    }

    @Override
    public ObservableList<House> getFilteredHouseList() {
        return filteredHouses;
    }

    @Override
    public void updateFilteredHouseList(Predicate<House> predicate) {
        requireNonNull(predicate);
        filteredHouses.setPredicate(predicate);
    }

    @Override
    public int sizeHouseList() {
        return filteredHouses.size();
    }

    /**
     * Ensures {@code selectedParticipant} is a valid participant in {@code filteredParticipants}.
     */
    private void ensureSelectedParticipantIsValid(ListChangeListener.Change<? extends Participant> change) {
        while (change.next()) {
            if (selectedParticipant.getValue() == null) {
                // null is always a valid selected participant, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedParticipantReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedParticipant.getValue());
            if (wasSelectedParticipantReplaced) {
                // Update selectedParticipant to its new value.
                int index = change.getRemoved().indexOf(selectedParticipant.getValue());
                selectedParticipant.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedParticipantRemoved = change.getRemoved().stream()
                    .anyMatch(removedParticipant -> selectedParticipant.getValue().isSameParticipant(removedParticipant));
            if (wasSelectedParticipantRemoved) {
                // Select the participant that came before it in the list,
                // or clear the selection if there is no such participant.
                selectedParticipant.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    /**
     * Ensures {@code selectedGroup} is a valid group in {@code filteredGroups}.
     */
    private void ensureSelectedGroupIsValid(ListChangeListener.Change<? extends Group> change) {
        while (change.next()) {
            if (selectedGroups.getValue() == null) {
                // null is always a valid selected group, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedGroupReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedGroups.getValue());
            if (wasSelectedGroupReplaced) {
                // Update selectedGroups to its new value.
                int index = change.getRemoved().indexOf(selectedGroups.getValue());
                selectedGroups.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedGroupRemoved = change.getRemoved().stream()
                    .anyMatch(removedGroup -> selectedGroups.getValue().isSameGroup(removedGroup));
            if (wasSelectedGroupRemoved) {
                // Select the group that came before it in the list,
                // or clear the selection if there is no such group.
                selectedGroups.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    /**
     * Ensures {@code selectedHouse} is a valid house in {@code filteredHouses}.
     */
    private void ensureSelectedHouseIsValid(ListChangeListener.Change<? extends House> change) {
        while (change.next()) {
            if (selectedHouses.getValue() == null) {
                // null is always a valid selected house, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedHouseReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedHouses.getValue());
            if (wasSelectedHouseReplaced) {
                // Update selectedHouses to its new value.
                int index = change.getRemoved().indexOf(selectedHouses.getValue());
                selectedHouses.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedHouseRemoved = change.getRemoved().stream()
                    .anyMatch(removedHouse -> selectedHouses.getValue().isSameHouse(removedHouse));
            if (wasSelectedHouseRemoved) {
                // Select the house that came before it in the list,
                // or clear the selection if there is no such house.
                selectedHouses.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredParticipants.equals(other.filteredParticipants)
                && Objects.equals(selectedParticipant.get(), other.selectedParticipant.get());
    }

}
