package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Participant;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PARTICIPANT = "Participants list contains duplicate participant(s).";

    private final List<JsonAdaptedParticipant> participants = new ArrayList<>();
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();
    private final List<JsonAdaptedHouse> houses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given participants, groups and houses.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("participants") List<JsonAdaptedParticipant> participants,
                                       @JsonProperty("groups") List<JsonAdaptedGroup> groups,
                                       @JsonProperty("houses") List<JsonAdaptedHouse> houses) {
        this.participants.addAll(participants);
        this.groups.addAll(groups);
        this.houses.addAll(houses);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        participants.addAll(source.getParticipantList().stream()
                .map(JsonAdaptedParticipant::new).collect(Collectors.toList()));
        groups.addAll(source.getGroupList().stream().map(JsonAdaptedGroup::new).collect(Collectors.toList()));
        houses.addAll(source.getHouseList().stream().map(JsonAdaptedHouse::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedParticipant jsonAdaptedParticipant : participants) {
            Participant participant = jsonAdaptedParticipant.toModelType();
            if (addressBook.hasParticipant(participant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PARTICIPANT);
            }
            addressBook.addParticipant(participant);
        }

        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group group = jsonAdaptedGroup.toModelType();
            if (addressBook.hasGroup(group)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PARTICIPANT);
            }
            addressBook.addGroup(group);
        }

        for (JsonAdaptedHouse jsonAdaptedHouse : houses) {
            House house = jsonAdaptedHouse.toModelType();
            if (addressBook.hasHouse(house)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PARTICIPANT);
            }
            addressBook.addHouse(house);
        }

        return addressBook;
    }

}
