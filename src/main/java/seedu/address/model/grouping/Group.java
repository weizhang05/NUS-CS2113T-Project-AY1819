package seedu.address.model.grouping;

/**
 * Represents a person's camp grouping
 * Can only be created within a house that has already been created.
 */

public class Group {

    private String groupName;
    private String houseName;

    public Group (String groupName, String houseName) {
        this.houseName = houseName;
        this.groupName = groupName;
    }

    public String getGroupName () {
        return groupName;
    }

    public String getHouseName() {
        return houseName;
    }
}
