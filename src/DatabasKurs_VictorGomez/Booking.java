package DatabasKurs_VictorGomez;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Booking {
    private Connection conn;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public void findRoom(Connection mainConn) {
        conn = mainConn;
    }

    public ArrayList<String> searchingCriteria(String criteria) {
        ArrayList<String> searchingParameters = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of guests");
        try {
            int numberOfGuests = Integer.parseInt(scanner.nextLine());

        } catch ()
    }
}
