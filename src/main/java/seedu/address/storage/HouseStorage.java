package seedu.address.storage;

import java.util.ArrayList;

import seedu.address.model.grouping.House;

public class HouseStorage {

    //Stores house names, and houses of type House.
    //Indices of the same houses will always match.
    public static ArrayList<String> houseNames = new ArrayList<>();
    public static ArrayList<House> houses = new ArrayList<>();

    public HouseStorage() {
    }

    public static void addHouse(String nameToAdd) {
        houseNames.add(nameToAdd);
        houses.add(new House(nameToAdd));
    }

    public static boolean hasHouse (String toFind) {
        return houseNames.contains(toFind);
    }

    public static House getHouse(String toGet) {
        return houses.get(houseNames.indexOf(toGet));
    }

    public static void addGroup(String groupName, String houseName) {
        int houseIndex = houseNames.indexOf(houseName);
        House tempStorage = houses.get(houseIndex);
        tempStorage.addGroup(groupName);
        houses.set(houseIndex, tempStorage);
    }
}
