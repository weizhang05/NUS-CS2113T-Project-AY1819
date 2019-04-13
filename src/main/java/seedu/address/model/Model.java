package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Participant;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Participant> PREDICATE_SHOW_ALL_PARTICIPANTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' chart storage path.
     */
    Path getChartStoragePath();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if there are no participant in the address book.
     */
    boolean isEmpty();

    /**
     * Returns the number of participants by age
     */
    ObservableMap<String, Integer> getAgeData();

    /**
     * Returns the number of participants by sex
     */
    ObservableMap<String, Integer> getSexData();

    /**
     * Returns the number of participants by major
     */
    ObservableMap<String, Integer> getMajorData();

    /**
     * Get the name of image files
     */
    String getFileName();

    /**
     * Set the name of image files
     */
    void setFileName(String fileName);

    /**
     * Returns true if a participant with the same identity as {@code participant} exists in the address book.
     */
    boolean hasParticipant(Participant participant);

    /**
     * Deletes the given participant.
     * The participant must exist in the address book.
     */
    void deleteParticipant(Participant target);

    /**
     * Adds the given participant.
     * {@code participant} must not already exist in the address book.
     */
    void addParticipant(Participant participant);

    /**
     * Replaces the given participant {@code target} with {@code editedParticipant}.
     * {@code target} must exist in the address book.
     * The participant identity of {@code editedParticipant}
     * must not be the same as another existing participant in the address book.
     */
    void setParticipant(Participant target, Participant editedParticipant);

    /** Returns an unmodifiable view of the filtered participant list */
    ObservableList<Participant> getFilteredParticipantList();

    /**
     * Updates the filter of the filtered participant list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredParticipantList(Predicate<Participant> predicate);

    // ================ Undo/Redo functions ======================
    /** Returns an unmodifiable view of the undo list */
    ObservableList<String> getUndoList();

    /** Returns an unmodifiable view of the redo list */
    ObservableList<String> getRedoList();

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    /**
     * Selected participant in the filtered participant list.
     * null if no participant is selected.
     */
    ReadOnlyProperty<Participant> selectedParticipantProperty();

    /**
     * Returns the selected participant in the filtered participant list.
     * null if no participant is selected.
     */
    Participant getSelectedParticipant();

    /**
     * Sets the selected participant in the filtered participant list.
     */
    void setSelectedParticipant(Participant participant);

    // ================ Group ======================
    /**
     * Returns true if a group with the same identity as {@code group} exists in the address book.
     */
    boolean hasGroup(Group group);

    /**
     * Deletes the given group.
     * The group must exist in the address book.
     */
    void deleteGroup(Group target);

    /**
     * Adds the given group.
     * {@code group} must not already exist in the address book.
     */
    void addGroup(Group group);

    /**
     * Replaces the given group {@code target} with {@code editedGroup}.
     * {@code target} must exist in the address book.
     * The group identity of {@code editedGroup} must not be the same as another existing group in the address book.
     */
    void setGroup(Group target, Group editedGroup);

    Group getGroup(Group toGet);

    /** Returns an unmodifiable view of the filtered group list */
    ObservableList<Group> getFilteredGroupList();

    /**
     * Updates the filter of the filtered group list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGroupList(Predicate<Group> predicate);

    /**
     * @return number of groups in group list
     */
    int sizeGroupList();

    // ================ House ======================
    /**
     * Returns a house if it exists
     */
    House getHouse(House house);

    /**
     * Returns true if a house with the same identity as {@code house} exists in the address book.
     */
    boolean hasHouse(House house);

    /**
     * Deletes the given house.
     * The house must exist in the address book.
     */
    void deleteHouse(House target);

    /**
     * Adds the given house.
     * {@code house} must not already exist in the address book.
     */
    void addHouse(House house);

    /**
     * Replaces the given house {@code target} with {@code editedHouse}.
     * {@code target} must exist in the address book.
     * The house identity of {@code editedHouse} must not be the same as another existing house in the address book.
     */
    void setHouse(House target, House editedHouse);

    /** Returns an unmodifiable view of the filtered house list */
    ObservableList<House> getFilteredHouseList();

    /**
     * Updates the filter of the filtered house list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredHouseList(Predicate<House> predicate);

    int sizeHouseList();

}
