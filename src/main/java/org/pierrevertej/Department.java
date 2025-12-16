package org.pierrevertej;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static org.pierrevertej.Util.toTitleCase;

@ToString
@EqualsAndHashCode
@Getter
public class Department {
    private String departmentId;
    private String departmentName;
    static int nextId = 1;

    /**
     * checks if a department name is valid or not, a department name should only contain letters or space
     * @param departmentName department name to check
     * @return true or false depending on if its valid
     */
    public static boolean isDepartmentNameValid(String departmentName) {
        for (int i = 0; i < departmentName.length(); i++) {
            if (!Character.isLetter(departmentName.charAt(i)) || departmentName.charAt(i) != ' ') {
                return false;
            }
        }

        return true;
    }

    public Department(String departmentName) {
        if (isDepartmentNameValid(departmentName)) {
            this.departmentName = toTitleCase(departmentName);
            this.departmentId = String.format("D%02d", nextId++);
        } else {
            this.departmentName = null;
            this.departmentId = null;
        }
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = toTitleCase(departmentName);
    }
}
