package DatabasKurs_VictorGomez;

import java.util.Scanner;

public class Console {

    private Scanner scan = new Scanner(System.in);
    private Query query = new Query();
    public Console(){
    }

    public void displayMenu(){
            System.out.println("------- MENU -------");
            System.out.print(
                    "1. Add a new booking\n" +
                            "2. Update a booking\n" +
                            "3. Delete a booking\n" +
                            "4. Search for a place w/ specific search criteria\n" +
                            "5. Quit\n"
            );
    }

    private void addNewBooking(){
        String customerID = addCustomerToDatabase();
        String placeID = selectPlaceToStayAt();
        String[] dates = chooseDates();
        String beginDate = dates[0];
        String endDate = dates[1];
        String totalGuests = addHowManyGuestsAreStaying();
        this.query.checkIfARoomIsAvailableDuringTheDates(beginDate, endDate, placeID, totalGuests);
        String roomID = selectRoomToStayAt();
        String bookingID = this.query.addNewBookingToDatabase(beginDate, endDate, customerID, roomID, placeID, totalGuests);
        String addonID = addAddons();
        this.query.linkBookingWithAddon(bookingID, addonID);
    }

    private String addAddons(){
        System.out.println("Do you want to have some Addons?");
        this.query.displayAddons();
        System.out.print("Enter a number: ");
        return scan.nextLine();
    }

    private String addCustomerToDatabase(){
        System.out.print("Enter first name: ");
        String firstName = scan.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scan.nextLine();
        System.out.print("Enter a valid email number: ");
        String email = scan.nextLine();
        System.out.print("Enter clients swedish personal number: ");
        String personalNumber = scan.nextLine();
        return this.query.addNewCustomer(firstName, lastName, email, personalNumber);
    }

    private String addHowManyGuestsAreStaying(){
        System.out.println("How many guests are staying?");
        System.out.print("Enter a number between 1 to 5: ");
        String choice = scan.nextLine();
        return choice;
    }

    private String selectRoomToStayAt(){
        System.out.print("Enter a number: ");
        String choice = scan.nextLine();
        return choice;
    }

    private String selectPlaceToStayAt(){
        query.selectPlace();
        System.out.print("Enter a number: ");
        String choice = scan.nextLine();
        switch (choice){
            case "1": case "2": case "3": case "4": case "5":
                System.out.println("-- Location includes --");
                query.includedAtLocation(choice);
                System.out.println("-----------------------");
                return choice;
            default: System.out.println("Not a number between 1 and 5");
        }
        return null;
    }


    public boolean choiceFromMenu(){
        System.out.print("Enter a number: ");
        switch (scan.nextLine()){
            case "1": addNewBooking(); break;
            case "2": System.out.println("NOT AVAILABLE"); break;
            case "3": deleteBooking(); break;
            case "4": searchPlaceByWhatItOffersAccessTo(); break;
            case "5": System.out.println("QUIT"); return false;
            default: System.out.println("Not a number between 1 and 5.");
        }
        return true;
    }
}