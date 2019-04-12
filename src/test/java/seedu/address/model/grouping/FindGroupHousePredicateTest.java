package seedu.address.model.grouping;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.participant.Participant;
import seedu.address.testutil.PersonBuilder;

public class FindGroupHousePredicateTest {
    private List<String> emptyString = Collections.singletonList("EMPTY");

    private Participant withGroupHouse = new PersonBuilder().withGroup("R1", "Red").build();
    private Participant withEmptyGroup = new PersonBuilder().build();

    @Test
    public void test_groupContainsKeywords_returnsTrue() {
        FindGroupPredicate firstPredicate = new FindGroupPredicate(Collections.singletonList("R1"));
        FindHousePredicate firstPredicateHouse = new FindHousePredicate(Collections.singletonList("Red"));
        assertTrue(firstPredicate.test(withGroupHouse));

        FindGroupPredicate secondPredicate = new FindGroupPredicate(emptyString);
        assertTrue(secondPredicate.test(withEmptyGroup));
    }

    @Test
    public void test_groupContainsKeywords_returnsFalse() {
        // participant with empty group
        FindGroupPredicate secondPredicate = new FindGroupPredicate(Collections.singletonList("R1"));
        FindHousePredicate secondPredicateHouse = new FindHousePredicate(Collections.singletonList("Red"));
        assertFalse(secondPredicate.test(withEmptyGroup));
        assertFalse(secondPredicateHouse.test(withEmptyGroup));

        // participant with non-empty group
        FindGroupPredicate thirdPredicate = new FindGroupPredicate(emptyString);
        assertFalse(thirdPredicate.test(withGroupHouse));

        // wrong keywords
        FindGroupPredicate fourthPredicate = new FindGroupPredicate(Collections.singletonList("G1"));
        FindHousePredicate fourthPredicateHouse = new FindHousePredicate(Collections.singletonList("Green"));
        assertFalse(fourthPredicate.test(withGroupHouse));
        assertFalse(fourthPredicateHouse.test(withGroupHouse));
    }

}
