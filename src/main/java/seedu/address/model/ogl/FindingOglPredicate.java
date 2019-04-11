package seedu.address.model.ogl;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.participant.Participant;

/**
 * Tests that a {@code Participant}'s {@code Name} matches any of the keywords given.
 */
public class FindingOglPredicate implements Predicate<Participant> {
    private final List<String> keywords;

    public FindingOglPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Participant participant) {
        return keywords.stream()
                .allMatch(keyword-> StringUtil.containsWordIgnoreCase(participant.getStringTags(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindingOglPredicate // instanceof handles nulls
                && keywords.equals(((FindingOglPredicate) other).keywords)); // state check
    }

}
