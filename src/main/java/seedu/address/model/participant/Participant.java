package seedu.address.model.participant;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.grouping.Group;
import seedu.address.model.tag.Tag;

/**
 * Represents a Participant in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Participant {

    // Identity fields
    private final Name name;
    private final Sex sex;
    private final Birthday birthday;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Major major;
    private final Group group;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Participant(Name name, Sex sex, Birthday birthday, Phone phone, Email email,
                       Major major, Group group, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, major, tags);
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.major = major;
        this.group = group;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Major getMajor() {
        return major;
    }

    public Group getGroup() {
        return group;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public String getStringTags () {
        return getTags().toString().replace("[" , "").replace("]" , "")
                .replace("," , "");
    }
    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameParticipant(Participant otherParticipant) {
        if (otherParticipant == this) {
            return true;
        }

        return otherParticipant != null
                && otherParticipant.getName().equals(getName())
                && (otherParticipant.getPhone().equals(getPhone())
                || otherParticipant.getEmail().equals(getEmail())
                || otherParticipant.getBirthday().equals(getBirthday()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Participant)) {
            return false;
        }

        Participant otherParticipant = (Participant) other;
        return otherParticipant.getName().equals(getName())
                && otherParticipant.getSex().equals(getSex())
                && otherParticipant.getBirthday().equals((getBirthday()))
                && otherParticipant.getPhone().equals(getPhone())
                && otherParticipant.getEmail().equals(getEmail())
                && otherParticipant.getMajor().equals(getMajor())
                && otherParticipant.getGroup().equals(getGroup())
                && otherParticipant.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, sex, birthday, phone, email, major, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Sex: ")
                .append(getSex())
                .append(" Birthday: ")
                .append(getBirthday())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Major: ")
                .append(getMajor())
                .append(" Group: ")
                .append(getGroup())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
