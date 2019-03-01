package seedu.address.model.role;


/**
 * Determines the level of privilege of the student based on the role assigned.
 * Smaller number means more privilege.
 *
 * e.g. HOUSE_HEAD will have more privilege compared to OGL (1 is less than 2).
 */
public enum Role {
    PROJECT_DIRECTOR(0),
    HOUSE_HEAD(1),
    OGL(2),
    PARTICIPANTS(3);

    private final int privilegeNumber;

    Role(int privilegeNumber){
        this.privilegeNumber = privilegeNumber;
    }

    public int getPrivilegeNumber(){
        return this.privilegeNumber;
    }

}
