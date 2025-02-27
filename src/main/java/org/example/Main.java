package main.java.org.example;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            DBService db = new DBService();
            Connection conn = db.getConnection();
            System.out.println("Successfully connected to DB");
            db.createJobTable();
            System.out.println("Table job created");
            db.createEmployeeTable();
            System.out.println("Table employee created\n");

            db.addJob("Официант");
            db.addJob("Мясник");

            db.addEmployee("Иван", 2);
            db.addEmployee("Елена", 1);
            db.addEmployee("Владимир", 2);

            System.out.println("Job table:");
            ArrayList<String> res = db.getJobData();
            for (String s: res) {
                System.out.println(s);
            }

            System.out.println("\nEmployee table:");
            res = db.getEmployeeData();
            for (String s: res) {
                System.out.println(s);
            }
        }
        catch (SQLException ex) {
            System.out.println("Error in db: " + ex.getMessage());
        }
    }
}