package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private static ObservableMap<String, Integer> ageData = FXCollections.observableHashMap();
    private static ObservableMap<String, Integer> majorData = FXCollections.observableHashMap();
    private static ObservableMap<String, Integer> sexData = FXCollections.observableHashMap();

    private final UniquePersonList persons;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
        indicateModified();
    }

    /**
     * Add data of a new person
     * @param toAdd
     */
    public void addData (Person toAdd) {
        ageData.put(toAdd.getBirthday().getAge(), (!ageData.containsKey(toAdd.getBirthday().getAge())) ? 1
                : ageData.get(toAdd.getBirthday().getAge()) + 1);
        majorData.put(toAdd.getMajor().value, (!majorData.containsKey(toAdd.getMajor().value)) ? 1
                : majorData.get(toAdd.getMajor().value) + 1);
        sexData.put(toAdd.getSex().value, (!sexData.containsKey(toAdd.getSex().value)) ? 1
                : sexData.get(toAdd.getSex().value) + 1);
    }

    /**
     * Delete data of a person
     */
    public void deleteData (Person toDelete) {
        ageData.put(toDelete.getBirthday().getAge(), ageData.get(toDelete.getBirthday().getAge()) - 1);
        majorData.put(toDelete.getMajor().value, majorData.get(toDelete.getMajor().value) - 1);
        sexData.put(toDelete.getSex().value, sexData.get(toDelete.getSex().value) - 1);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());

        ageData.putAll(newData.getAgeData());
        majorData.putAll(newData.getMajorData());
        sexData.putAll(newData.getSexData());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        addData(p);
        indicateModified();
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        addData(editedPerson);
        deleteData(target);

        persons.setPerson(target, editedPerson);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        deleteData(key);
        indicateModified();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the address book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    public ObservableMap<String, Integer> getAgeData() {
        return FXCollections.unmodifiableObservableMap(ageData);
    }

    public ObservableMap<String, Integer> getMajorData() {
        return FXCollections.unmodifiableObservableMap(majorData);
    }

    public ObservableMap<String, Integer> getSexData() {
        return FXCollections.unmodifiableObservableMap(sexData);
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
