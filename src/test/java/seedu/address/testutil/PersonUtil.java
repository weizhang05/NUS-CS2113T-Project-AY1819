package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.participant.Participant;
import seedu.address.model.tag.Tag;


/**
 * A utility class for Participant.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code participant}.
     */
    public static String getAddCommand(Participant participant) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(participant);
    }

    /**
     * Returns the part of command string for the given {@code participant}'s details.
     */
    public static String getPersonDetails(Participant participant) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + participant.getName().fullName + " ");
        sb.append(PREFIX_SEX + participant.getSex().value + " ");
        sb.append(PREFIX_BIRTHDAY + participant.getBirthday().value + " ");
        sb.append(PREFIX_PHONE + participant.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + participant.getEmail().value + " ");
        sb.append(PREFIX_MAJOR + participant.getMajor().value + " ");
        sb.append(PREFIX_GROUP + participant.getGroup().getGroupName() + " ");
        participant.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getSex().ifPresent(sex -> sb.append(PREFIX_SEX).append(sex.value).append(" "));
        descriptor.getBirthday().ifPresent(birthday -> sb.append(PREFIX_BIRTHDAY).append(birthday.value).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getMajor().ifPresent(major -> sb.append(PREFIX_MAJOR).append(major.value).append(" "));
        descriptor.getGroup().ifPresent(group -> sb.append(PREFIX_GROUP).append(group).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
