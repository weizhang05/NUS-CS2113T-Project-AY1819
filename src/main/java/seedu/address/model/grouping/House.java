package seedu.address.model.grouping;

import java.util.ArrayList;

public class House {

    public String houseName;
    public ArrayList<Group> groups = new ArrayList<>();

    public House (String name) {
        houseName = name;
    }

    public boolean addGroup (String groupName) {
        Group newGroup = new Group(groupName, houseName);
        return groups.add(newGroup); //returns true if group is added
    }

    public boolean hasGroup (String groupName) {
        return groups.contains(new Group(groupName,houseName));
    }
}
