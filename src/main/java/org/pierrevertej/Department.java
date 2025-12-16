package org.pierrevertej;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class Department {
    private String departmentId;
    @Setter private String departmentName;
    static int nextId = 0;

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
            this.departmentName = departmentName;
            this.departmentId = String.format("%02d", ++nextId);
        } else {
            this.departmentName = null;
            this.departmentId = null;
        }
    }
}
