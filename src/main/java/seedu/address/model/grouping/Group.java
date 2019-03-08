package seedu.address.model.grouping;

public class Group {

    public String groupName;
    public String houseName;

    public Group (String groupName, String houseName) {
        this.houseName = houseName;
        this.groupName = groupName;
    }
}