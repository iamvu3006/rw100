package frontend;

import backend.QLDepartment;
import backend.QLTK;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //QLDepartment.showDepartment();
        QLDepartment.findByNameAndId("Sale", 2);
        //QLTK.run();
    }
}