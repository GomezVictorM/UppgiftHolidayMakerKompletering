package DatabasKurs_VictorGomez;

import java.sql.*;

public class Query {
    private Connection conn = null;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public String addNewCustomer(String firstName, String lastName, String email, String personalNumber){
        connectToDatabase();
        try {
            this.statement = this.conn.prepareStatement("INSERT INTO users (first_name, last_name, email, personal_number) VALUES (?, ?, ?, ?);");
            this.statement.setString(1, firstName);
            this.statement.setString(2, lastName);
            this.statement.setString(2, email);
            this.statement.setString(3, personalNumber);
            this.statement.executeUpdate();
            System.out.printf("Customer: %s %s with nhs number %s was added to the database.\n", firstName, lastName, email, personalNumber);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        disconnectFromDatabase();
        return getPersonalNumber(personalNumber);
    }

    private String getPersonalNumber(String personalNumber){
        String customerID = null;
        connectToDatabase();
        try {
            this.statement = this.conn.prepareStatement("SELECT * FROM users WHERE personal_number = ?;");
            this.statement.setString(1, personalNumber);
            this.resultSet = statement.executeQuery();
            this.resultSet.next();
            customerID = resultSet.getString("user_id");
        } catch (Exception ex){
            ex.printStackTrace();
        }
        disconnectFromDatabase();
        return customerID;
    }

    public String addBookingToDatabase(String bookingId, String userId, String totalGuests, String checkIn, String checkOut, String roomID){
        connectToDatabase();
        try {
            this.statement = this.conn.prepareStatement("INSERT INTO bookings (booking_id, user_id, total_guests, check_in, check_out, room_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?);");
            this.statement.setString(1, bookingId);
            this.statement.setString(2, userId);
            this.statement.setString(3, totalGuests);
            this.statement.setString(4, checkIn);
            this.statement.setString(5, checkOut);
            this.statement.setString(6, roomID);
            this.statement.executeUpdate();
            System.out.println("Booking was successfully added to the database.");
            return getIDOfBookingFrom(userId, checkIn, checkOut, roomID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        disconnectFromDatabase();
        return null;
    }

    public String getIDOfBookingFrom(String userID, String checkIn, String checkOut, String roomID){
        try{
            connectToDatabase();
            this.statement = this.conn.prepareStatement("SELECT * FROM bookings WHERE check_in=? AND check_out=? AND user_id=? AND room_id=?");
            this.statement.setString(1, checkIn);
            this.statement.setString(2, checkOut);
            this.statement.setString(3, userID);
            this.statement.setString(4, roomID);
            this.resultSet = statement.executeQuery();
            this.resultSet.next();
            String bookingID = resultSet.getString("booking_id");
            disconnectFromDatabase();
            return bookingID;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        disconnectFromDatabase();
        return null;
    }

    public void addonsToBooking(String bookingID, String addonID){
        connectToDatabase();
        try {
            this.statement = this.conn.prepareStatement("INSERT INTO bookings_and_addons (booking_id, addon_id) VALUES (?, ?);");
            this.statement.setString(1, bookingID);
            this.statement.setString(2, addonID);
            this.statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        disconnectFromDatabase();
    }


    public String deleteBookingWithID(String bookingID){
        connectToDatabase();
        try {
            this.statement = this.conn.prepareStatement("SELECT user_id FROM bookings WHERE booking_id=?;");
            this.statement.setString(1, bookingID);
            this.resultSet = statement.executeQuery();
            this.resultSet.next();
            String customerID = resultSet.getString("user_id");

            this.statement = this.conn.prepareStatement("DELETE FROM bookings WHERE booking_id=?;");
            this.statement.setString(1, bookingID);
            this.statement.executeUpdate();
            disconnectFromDatabase();
            return customerID;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        disconnectFromDatabase();
        return null;
    }

    public void deleteCustomerWithID(String customerID){
        connectToDatabase();
        try {
            this.statement = this.conn.prepareStatement("DELETE FROM users WHERE user_id=?;");
            this.statement.setString(1, customerID);
            this.statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        disconnectFromDatabase();
    }

    public void displayAllBookings(){
        connectToDatabase();
        try {
            this.statement = this.conn.prepareStatement("SELECT bookings.bookings_id, check_in, check_out, users.* FROM bookings JOIN users ON user_id=users.user_id;");
            this.resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.printf(
                        "%d. %s-%s - FullName: %s %s - Swedish Personal Number number: %s\n",
                        resultSet.getInt("booking_id"),
                        resultSet.getString("check_in"),
                        resultSet.getString("check_out"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("personal_number")
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        disconnectFromDatabase();
    }

    public void showAddons(){
        connectToDatabase();
        try {
            this.statement = this.conn.prepareStatement("SELECT * FROM addons");
            this.resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.printf(
                        "%d. %s - %s kr\n",
                        resultSet.getInt("addon_id"),
                        resultSet.getString("description"),
                        resultSet.getString("price_per_day")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnectFromDatabase();
    }

    public void selectHotel(){
        connectToDatabase();
        try {
            this.statement = this.conn.prepareStatement("SELECT hotels.hotel_id, hotels.hotel_name, hotels.city, hotels.country;");
            this.resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.printf(
                        "%d. %s (%s %s, %s, %s).\n",
                        resultSet.getInt("hotel_id"),
                        resultSet.getString("hotel_name"),
                        resultSet.getString("city"),
                        resultSet.getString("country")
                );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        disconnectFromDatabase();
    }


//    public void includedAtLocation(String hotelID){
//        connectToDatabase();
//        try {
//            "%d. %s (%s %s, %s).\n",
//            this.statement = this.conn.prepareStatement("SELECT hotel_name, children_activities, night_entertainment, pool;");
//            this.statement.setString(1, hotelID);
//            this.resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                System.out.printf(
//                        "%s\n",
//                        resultSet.getString("hotel_name")
//                );
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        disconnectFromDatabase();
//    }



    public void findAvailableRooms(String arrivalDate, String departureDate, String placeID, String totalGuests){
        connectToDatabase();
        try {
            int intPlaceID = Integer.parseInt(placeID);
            int intTotalGuests = Integer.parseInt(totalGuests);
            this.statement = this.conn.prepareStatement("SELECT rooms.id, rooms.room_number, places.name, sizes.size_name, sizes.max_guests " +
                    "FROM bookings INNER JOIN rooms ON room_id = rooms.id INNER JOIN places ON bookings.place_id = places.id " +
                    "INNER JOIN sizes ON rooms.size_id = sizes.id " +
                    "WHERE (? < bookings.begin_date OR ? > bookings.end_date AND ? < bookings.begin_date OR ? > bookings.end_date) " +
                    "AND (bookings.place_id = ?) AND (? <= (sizes.max_guests + 1)) GROUP BY bookings.room_id;");
            this.statement.setString(1, arrivalDate);
            this.statement.setString(2, arrivalDate);
            this.statement.setString(3, departureDate);
            this.statement.setString(4, departureDate);
            this.statement.setInt(5, intPlaceID);
            this.statement.setInt(6, intTotalGuests);
            this.resultSet = statement.executeQuery();
            while (resultSet.next()){
                int totalMaxGuests = resultSet.getInt("max_guests") + 1;
                System.out.printf(
                        "%d. Room #: %s - Size: %s - Max guests: %d (%6$d with an extra bed) - Place: %s\n",
                        resultSet.getInt("rooms.id"),
                        resultSet.getString("room_number"),
                        resultSet.getString("size_name"),
                        resultSet.getInt("max_guests"),
                        resultSet.getString("name"),
                        totalMaxGuests
                );
            }
        } catch (SQLException ex) {
            System.out.println("Something went wrong " + ex.getMessage());
        }
        disconnectFromDatabase();
    }

    private void connectToDatabase() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:/Users/victorgomez/Desktop/holidaymaker_uppgift.db");
        } catch (SQLException ex) {
            System.out.println("Something went wrong " + ex.getMessage());
        }
    }

    private void disconnectFromDatabase(){
        try{
            this.conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
