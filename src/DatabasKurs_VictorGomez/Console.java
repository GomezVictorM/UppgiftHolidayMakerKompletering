package DatabasKurs_VictorGomez;

import com.sun.jdi.connect.spi.Connection;

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

        public void displayMenu(){
            System.out.println("---- MENU ----");
            System.out.print(
                    "1. Add a new booking\n" +
                            "2. Update a booking\n" +
                            "3. Delete a booking\n" +
                            "4. Search for a place with specific access to a location\n" +
                            "5. Quit\n"
            );
        }
    }