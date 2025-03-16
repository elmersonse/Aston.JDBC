package main.java.org.example;

import java.sql.SQLException;
import java.util.List;

public class Printer {
    private final DBService DB;

    public Printer(DBService db) {
        DB = db;
    }

    public void printJobTable() throws SQLException {
        System.out.println("\nJob table:");
        List<String> res = DB.getJobData();
        for (String s: res) {
            System.out.println(s);
        }
    }

    public void printEmployeeTable() throws SQLException {
        System.out.println("\nEmployee table:");
        List<String> res = DB.getEmployeeData();
        for (String s: res) {
            System.out.println(s);
        }
    }

    public void printAll() throws SQLException {
        printJobTable();
        printEmployeeTable();
    }
}
