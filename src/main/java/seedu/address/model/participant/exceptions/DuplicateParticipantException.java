package seedu.address.model.participant.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateParticipantException extends RuntimeException {
    public DuplicateParticipantException() {
        super("Operation would result in duplicate persons");
    }
}
