package seedu.address.model.grouping;

/**
 * Represents a house in a camp.
 */

public class House {

    private String houseName;

    /**
     * Constructs an {@code House}.
     */
    public House (String name) {
        houseName = name;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    /**
     * Returns true if both houses of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two houses.
     */
    public boolean isSameHouse(House otherHouse) {
        if (otherHouse == this) {
            return true;
        }

        return otherHouse != null
                && otherHouse.equals(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof House // instanceof handles nulls
                && this.getHouseName().equals(((House) obj).getHouseName())); // state check
    }
}
