package DatabasKurs_VictorGomez;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Console {

    private Connection conn = null;
    public Console() {
        connect();
    }

    public Connection getConn() {
        return conn;
    }

    public void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:/Users/victorgomez/Desktop/holidaymaker_uppgift.db");
        } catch (SQLException e) {
            System.out.println("Something got wrong " + e.getMessage());
        }
    }