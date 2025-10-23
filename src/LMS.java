import java.util.Scanner;
public class LMS {
    // Arrays and Variables used for Cities
    public static String[] Cities = new String[30];
    public static int cityCounter = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;
        do{
            System.out.println("-----Welcome to Logistics Management System-----");
            System.out.println("1. Manage Cities");
            System.out.println("2. Manage Distances");
            System.out.println("3. View Vehicle Details");
            System.out.println("4. Handle Delivery Request");
            System.out.println("5. View Delivery Records");
            System.out.println("6. Show Performance Reports");
            System.out.println("0. Save and Exit ");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1 -> manageCities(input);
                case 2 -> manageDistances(input);
                case 3 -> vehicleDetails(input);
                case 4 -> deliveryRequests(input);
                case 5 -> deliveryRecords(input);
                case 6 -> performanceReport(input);
                case 0 -> System.out.println("Save and Exit ");
                default -> System.out.println("Invalid choice");
            }
        }while(choice !=0);

    }

    // Cities management
    public static void manageCities(Scanner input) {
        //This is the sub menu including the adding,removing and renaming cities
        //This is the sub menu including the adding,removing and renaming cities
        int cityChoice;

        //New line
        input.nextLine();
        do{
            System.out.println("\n-----Manage Cities-----");
            System.out.println("1. Add a new city");
            System.out.println("2. Rename a city");
            System.out.println("3. Remove a city");
            System.out.println("4. View all cities");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            cityChoice = input.nextInt();
            input.nextLine();// Fix: programme jumps out without taking user inputs

            switch(cityChoice){
                case 1 -> addNewcity(input);
                case 2 -> renameCity(input);
                case 3 -> removeCity(input);
                case 4 -> allCities(input);
                case 0 -> System.out.println("Back to the Main Menu... ");
                default -> System.out.println("Invalid choice!!!");
            }
        }while(cityChoice != 0);

    }

    public static void addNewcity(Scanner input) {
        //Method of adding new city to the system
    }

    public static void renameCity(Scanner input) {
        //Method of renaming an existing city from the system
    }

    public static void removeCity(Scanner input) {
        //Method of removing an existing city from the system
    }

    private static void allCities(Scanner input) {
        //Method of displaying all the cities in the system
    }

    public static void manageDistances(Scanner input) {
        //This is the sub menu including the intercity distances
    }
    //-------------------------------------------------------------------------------------------

    //Vehicle management
    public static void vehicleDetails(Scanner input){
        //This is the sub menu including the Vehicle type, Capacity (kg), Rate per km (LKR), Avg Speed (km/h) and Fuel Efficiency (km/l)
    }

    //Delivery Request Handling
    public static void deliveryRequests(Scanner input){
        //This is the sub menu handling the requests received from the user and display the charge for the relevant delivery
    }

    //Delivery records
    public static void deliveryRecords(Scanner input){
        //This is the sub menu to view the history of deliveries
    }

    //Performance report
    public static void performanceReport(Scanner input){
        //This is the sub menu can be used to obtain report of the process at the time
    }

}
