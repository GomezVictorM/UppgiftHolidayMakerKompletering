package DatabasKurs_VictorGomez;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Registration {

    private Connection conn;
    private PreparedStatement statement;

    public Registration(Connection mainConn) {
        conn = mainConn;
    }

    public String registerNewCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer's name please ");
        String first_name = scanner.nextLine();
        System.out.println("Enter customer's last name ");
        String last_name = scanner.nextLine();
        System.out.println("Enter customer's email please ");
        String email = scanner.nextLine();
        System.out.println("Now please enter the customer's swedish personal number ");
        String personal_number = scanner.nextLine();
        try {
            statement = conn.prepareStatement("INSERT INTO users (first_name, last_name, email, personal_number) VALUES (?, ?, ?, ?)");
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setString(4, personal_number);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something got wrong " + e.getMessage());
        }
        return personal_number;
    }

    public ResultSet SearchCustomer(String personal_number) {
        try {
            statement = conn.prepareStatement("SELECT * FROM users WHERE personal_number=?");
            statement.setString(1, personal_number);
            return statement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}