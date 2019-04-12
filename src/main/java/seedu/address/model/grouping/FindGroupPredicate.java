package seedu.address.model.grouping;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.participant.Participant;

/**
* Tests that a {@code Participant}'s {@code Group} group name matches the group name given.
*/
public class FindGroupPredicate implements Predicate<Participant> {

    private final List<String> keywords;
    private List<String> emptyString = Arrays.asList("EMPTY");

    public FindGroupPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Participant participant) {
        if (keywords.equals(emptyString)) {
            return participant.getGroup().getGroupName().isEmpty();
        }
        return keywords.stream()
                .allMatch(keyword-> StringUtil.containsWordIgnoreCase(participant.getGroup().getGroupName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindGroupPredicate // instanceof handles nulls
                && keywords.equals(((FindGroupPredicate) other).keywords)); // state check
    }
}
