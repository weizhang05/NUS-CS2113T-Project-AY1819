package seedu.address.model.grouping;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.participant.Person;

/**
 * Tests that a {@code Participant}'s {@code Group} house name matches the house name given.
 */
public class FindHousePredicate implements Predicate<Person> {

    private final List<String> keywords;

    public FindHousePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (person.getGroup().getGroupName().isEmpty()) {
            return false;
        }
        return keywords.stream()
                .allMatch(keyword-> StringUtil.containsWordIgnoreCase(person.getGroup().getHouseName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindHousePredicate // instanceof handles nulls
                && keywords.equals(((FindHousePredicate) other).keywords)); // state check
    }
}

