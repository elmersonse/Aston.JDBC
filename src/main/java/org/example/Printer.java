package main.java.org.example;

import java.sql.SQLException;
import java.util.List;

public class Printer {
    private final DBService dbService;

    public Printer(DBService db) {
        dbService = db;
    }

    public void printJobTable() throws SQLException {
        System.out.println("\nJob table:");
        List<String> res = dbService.getJobData();
        for (String s: res) {
            System.out.println(s);
        }
    }

    public void printEmployeeTable() throws SQLException {
        System.out.println("\nEmployee table:");
        List<String> res = dbService.getEmployeeData();
        for (String s: res) {
            System.out.println(s);
        }
    }

    public void printAll() throws SQLException {
        printJobTable();
        printEmployeeTable();
    }
}
