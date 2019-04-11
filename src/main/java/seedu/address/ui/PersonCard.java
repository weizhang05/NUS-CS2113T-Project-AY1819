package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.participant.Participant;

/**
 * An UI component that displays information of a {@code Participant}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Participant participant;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label sex;
    @FXML
    private Label birthday;
    @FXML
    private Label phone;
    @FXML
    private Label major;
    @FXML
    private Label email;
    @FXML
    private Label group;
    @FXML
    private FlowPane tags;

    public PersonCard(Participant participant, int displayedIndex) {
        super(FXML);
        this.participant = participant;
        id.setText(displayedIndex + ". ");
        name.setText(participant.getName().fullName);
        sex.setText("Sex: " + participant.getSex().value);
        birthday.setText("Birthday: " + participant.getBirthday().getFormattedBirthday());
        phone.setText("Phone Number: " + participant.getPhone().value);
        major.setText("Major: " + participant.getMajor().getFullMajor());
        email.setText("Email: " + participant.getEmail().value);
        group.setText("Group: " + participant.getGroup().getGroupName());
        participant.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && participant.equals(card.participant);
    }
}
