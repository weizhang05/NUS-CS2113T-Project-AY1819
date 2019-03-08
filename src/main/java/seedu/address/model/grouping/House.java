package seedu.address.model.grouping;

import java.util.ArrayList;

/**
 * Represents houses in a camp.
 * A House has multiple groups within it.
 */

public class House {

    private String houseName;
    private ArrayList<Group> groups = new ArrayList<>();

    /**
     * Constructs an {@code House}.
     */
    public House (String name) {
        houseName = name;
    }

    public String getHouseName() {
        return houseName;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addGroup (String groupName) {
        Group newGroup = new Group(groupName, houseName);
        groups.add(newGroup);
    }

    public boolean hasGroup (String groupName) {
        return groups.contains(new Group(groupName, houseName));
    }
}
