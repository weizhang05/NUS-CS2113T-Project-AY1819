package seedu.address.model.grouping;

/**
 * Represents a person's camp grouping
 * Can only be created within a house that has already been created.
 */
public class Group {

    public static final String MESSAGE_CONSTRAINTS = "Group can take any value";

    private String groupName;
    private String houseName;

    public Group(String groupName) {
        this(groupName, null);
    }

    public Group(String groupName, String houseName) {
        this.houseName = houseName;
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Returns true if it's a valid group. Always true (can be empty).
     * @param test
     * @return
     */
    public static boolean isValidGroup(String test) {
        return true;
    }

    /**
     * Returns true if both groups of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two groups.
     */
    public boolean isSameGroup(Group otherGroup) {
        return this.getGroupName().equals(otherGroup.getGroupName());
    }

    // ========= House Operations =========
    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    // ========= Participants Operations =========
    @Override
    public String toString() {
        return this.groupName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Group // instanceof handles nulls
                && this.getGroupName().equals(((Group) other).getGroupName())); // state check
    }
}
