package seedu.address.model.participant;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Participant}'s {@code Tag} matches any of the keywords given.
 */
public class FindingParticipantPredicate implements Predicate<Participant> {
    private final List<String> keywords;

    public FindingParticipantPredicate(List<String> keywords) {
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
                || (other instanceof FindingParticipantPredicate // instanceof handles nulls
                && keywords.equals(((FindingParticipantPredicate) other).keywords)); // state check
    }

}
