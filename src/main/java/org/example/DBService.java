package main.java.org.example;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.*;
import java.util.ArrayList;

public class DBService {
    private final Connection CONNECTION;


    public DBService() throws SQLException {
        String url = "jdbc:h2:mem:";
        String user = "admin";
        String password = "password";
        CONNECTION = DriverManager.getConnection(url, user, password);
    }
    public Connection getConnection() throws SQLException {
        return CONNECTION;
    }

    public void createJobTable() throws SQLException {
        String jobTableQuery = "CREATE TABLE job " +
                "(id INTEGER PRIMARY KEY AUTO_INCREMENT, name TEXT)";

        executeCreateStatement(jobTableQuery);
    }

    public void createEmployeeTable() throws SQLException {
        String employeeTableQuery = "CREATE TABLE employee " +
                "(id INTEGER PRIMARY KEY AUTO_INCREMENT, name TEXT, job_id INTEGER, " +
                "CONSTRAINT FK_JOB_ID FOREIGN KEY (job_id) REFERENCES job(id))";
        executeCreateStatement(employeeTableQuery);
    }

    private void executeCreateStatement(String query) throws SQLException {
        try(Statement st = CONNECTION.createStatement()) {
            st.execute(query);
        }
    }

    public void addJob(String name) throws SQLException {
        String query = "INSERT INTO job (name) VALUES (?)";
        try(PreparedStatement ps = CONNECTION.prepareStatement(query)) {
            ps.setString(1, name);
            ps.execute();
        }
    }

    public ArrayList<String> getJobData() throws SQLException {
        String query = "SELECT * FROM job";
        try(Statement st = CONNECTION.createStatement()) {
            st.execute(query);

            ResultSet rs = st.getResultSet();
            ArrayList<String> res = new ArrayList<>();
            while(rs.next()) {
                res.add(rs.getString("id") + "; " +
                        rs.getString("name"));
            }

            return res;
        }
    }

    public void addEmployee(String name, int jobId) throws SQLException {
        String query = "INSERT INTO employee (name, job_id) VALUES (?, ?)";
        try(PreparedStatement ps = CONNECTION.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, jobId);
            ps.execute();
        }
    }

    public ArrayList<String> getEmployeeData() throws SQLException {
        String query = "SELECT * FROM employee " +
                "JOIN job ON employee.job_id = job.id";
        try(Statement st = CONNECTION.createStatement()) {
            st.execute(query);

            ResultSet rs = st.getResultSet();
            ArrayList<String> res = new ArrayList<>();
            while(rs.next()) {
                res.add(rs.getString("employee.id") + "; " +
                        rs.getString("employee.name") + "; " +
                        rs.getString("job.name"));
            }

            return res;
        }
    }

}
