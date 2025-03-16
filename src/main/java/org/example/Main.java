package main.java.org.example;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            DBService db = new DBService();
            Printer printer = new Printer(db);

            System.out.println("Successfully connected to DB");
            db.createJobTable();
            System.out.println("Table \"job\" created");
            db.createEmployeeTable();
            System.out.println("Table \"employee\" created\n");

            db.addJob("Официант");
            db.addJob("Таксист");

            db.addEmployee("Иван", 2);
            db.addEmployee("Елена", 1);
            db.addEmployee("Владимир", 2);

            printer.printAll();

            db.updateJob("Лошпедос", 1);
            db.updateEmployee("Олег", 2);

            printer.printAll();

            db.deleteEmployee(2);
            db.deleteJob(1);

            printer.printAll();
        }
        catch (SQLException ex) {
            System.out.println("Error in db: " + ex.getMessage());
        }
    }


}