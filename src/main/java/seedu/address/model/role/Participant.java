package seedu.address.model.role;

import java.util.Set;

import seedu.address.model.person.*;
import seedu.address.model.person.Major;
import seedu.address.model.privilege.Privilege;
import seedu.address.model.tag.Tag;

/**
 * Participant for FOP.
 *
 * By default everybody will be a participant unless assigned.
 */
public class Participant extends Person {

    private Privilege privilege;

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param major
     * @param tags
     */
    public Participant(Name name, Phone phone, Email email, Major major, Set<Tag> tags) {
        super(name, , , phone, email, major, tags);
        // By default everybody is a participant
        this.privilege = Privilege.PARTICIPANTS;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Privilege getPrivilege() {
        return this.privilege;
    }
}
