package seedu.address.model.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.participant.Participant;
import seedu.address.model.participant.UniqueParticipantList;
import seedu.address.model.participant.exceptions.DuplicateParticipantException;
import seedu.address.model.participant.exceptions.ParticipantNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniqueParticipantListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueParticipantList uniqueParticipantList = new UniqueParticipantList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.contains(null);
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueParticipantList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueParticipantList.add(ALICE);
        assertTrue(uniqueParticipantList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueParticipantList.add(ALICE);
        Participant editedAlice = new PersonBuilder(ALICE).withMajor(VALID_MAJOR_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueParticipantList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.add(null);
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueParticipantList.add(ALICE);
        thrown.expect(DuplicateParticipantException.class);
        uniqueParticipantList.add(ALICE);
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.setParticipant(null, ALICE);
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.setParticipant(ALICE, null);
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        thrown.expect(ParticipantNotFoundException.class);
        uniqueParticipantList.setParticipant(ALICE, ALICE);
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueParticipantList.add(ALICE);
        uniqueParticipantList.setParticipant(ALICE, ALICE);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(ALICE);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueParticipantList.add(ALICE);
        Participant editedAlice = new PersonBuilder(ALICE).withMajor(VALID_MAJOR_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueParticipantList.setParticipant(ALICE, editedAlice);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(editedAlice);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueParticipantList.add(ALICE);
        uniqueParticipantList.setParticipant(ALICE, BOB);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(BOB);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueParticipantList.add(ALICE);
        uniqueParticipantList.add(BOB);
        thrown.expect(DuplicateParticipantException.class);
        uniqueParticipantList.setParticipant(ALICE, BOB);
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.remove(null);
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        thrown.expect(ParticipantNotFoundException.class);
        uniqueParticipantList.remove(ALICE);
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueParticipantList.add(ALICE);
        uniqueParticipantList.remove(ALICE);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.setParticipants((UniqueParticipantList) null);
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueParticipantList.add(ALICE);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(BOB);
        uniqueParticipantList.setParticipants(expectedUniqueParticipantList);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.setParticipants((List<Participant>) null);
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueParticipantList.add(ALICE);
        List<Participant> participantList = Collections.singletonList(BOB);
        uniqueParticipantList.setParticipants(participantList);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(BOB);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Participant> listWithDuplicateParticipants = Arrays.asList(ALICE, ALICE);
        thrown.expect(DuplicateParticipantException.class);
        uniqueParticipantList.setParticipants(listWithDuplicateParticipants);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueParticipantList.asUnmodifiableObservableList().remove(0);
    }
}
