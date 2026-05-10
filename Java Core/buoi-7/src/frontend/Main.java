package frontend;

import backend.QLAccount;
import backend.QLDepartment;
import backend.QLPosition;
import backend.QLTK;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //QLDepartment
        QLDepartment.showDepartment();
        QLDepartment.findByNameAndId("Sale", 2);
        QLDepartment.showDepartmentHasAtLeast2Employees();

        //QLPosition
        QLPosition.showAllPosition();
        QLPosition.findByPositionName("Dev");

        //QLAccount
        QLAccount.showAllAccount();
    }
}