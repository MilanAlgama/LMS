
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LMS {
    // Arrays and Variables used for Cities
    public static String[] Cities = new String[30];
    public static int cityCounter = 0;

    // 2D Array used for Cities
    public static int[][] distancesOfCities = new int[30][30];

    //Arrays used for the vehicle details
    public static String[] vehicleTypes = {"Van","Truck","Lorry"};
    public static int[] vehicleCapacity = {1000,5000,10000};
    public static int[] vehicleRate = {30,40,80};
    public static int[] vehicleAvgSpeed = {60,50,45};
    public static int[] vehicleFuelEff = {12,6,4};

    //Arrays used for the delivery handling
    public static String[] deliveryStart = new String[50];
    public static String[] deliveryEnd = new String[50];
    public static double[] deliveryWeights = new double[50];
    public static String[] deliveryVehicles = new String[50];
    public static double[] deliveryDistances = new double[50];
    public static double[] deliveryTimes = new double[50];
    public static double[] deliveryCustomerCharges = new double[50];
    public static double[] deliveryProfits = new double[50];
    public static int deliveryCounter = 0;

    //Price of the fuel
    public static double priceOfFuel = 310.0;


    public static void main(String[] args) {
        loadData();
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
            System.out.println("7. Reset All Data");
            System.out.println("0. Save and Exit ");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> manageCities(input);
                case 2 -> manageDistances(input);
                case 3 -> vehicleDetails();
                case 4 -> deliveryRequests(input);
                case 5 -> pastDeliveryRecords(input);
                case 6 -> performanceReport(input);
                case 7 -> resetAllData(input);
                case 0 -> System.out.println("Save and Exit ");
                default -> System.out.println("Invalid choice");
            }
        }while(choice !=0);
        saveData();

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

    public static boolean duplicateCheck(String cityName){
        //Methods use to check duplicates
        for(int i = 0; i<cityCounter; i++){
            if(cityName.equals(Cities[i])){
                return true;
            }
        }return false;
    }


    public static void addNewcity(Scanner input) {
        //Method of adding new city to the system
        if(cityCounter >= Cities.length){
            System.out.println("Error! City limit is exceeded");
            return;
        }
        System.out.println("Enter the name of the city");
        String newCity = input.nextLine();

        //check the capacity and the availability of the duplicates
        if(duplicateCheck(newCity)){
            System.out.println("Error! "+newCity+" already in use");
            return;
        }

        //Add the city
        Cities[cityCounter] = newCity;
        cityCounter++;
        System.out.println("City added successfully!");
    }

    public static void renameCity(Scanner input) {
        //Method of renaming an existing city from the system
        allCities(input);
        System.out.print("Enter the number of the city: ");
        int cityNumber = input.nextInt();
        input.nextLine();

        //Check the validity of the number
        if(cityNumber < 1 || cityNumber > cityCounter){
            System.out.println("Error! Invalid number");
            return;
        }

        String oldCity = Cities[cityNumber-1];
        System.out.print("Enter a new name for the "+oldCity+" :");
        String newCity = input.nextLine();

        //Duplicate checking....
        if(duplicateCheck(newCity)){
            System.out.println("Error! "+newCity+" already in use");
            return;
        }

        Cities[cityNumber-1] = newCity;
        System.out.println("City renamed successfully!");
    }

    public static void removeCity(Scanner input) {
        //Method of removing an existing city from the system
        allCities(input);
        System.out.print("Enter the number of the city you want to remove: ");
        int cityNumber = input.nextInt();
        input.nextLine();

        //Check the validity of the number
        if(cityNumber < 1 || cityNumber > cityCounter){
            System.out.println("Error! Invalid number");
            return;
        }
        for(int i = cityNumber-1; i < cityCounter-1; i++){
            Cities[i]=Cities[i+1];
        }cityCounter--;
        System.out.println("City removed successfully!");
    }

    public static void allCities(Scanner input) {
        //Method of displaying all the cities in the system
        System.out.println("\n---List of all cities---");
        if (cityCounter == 0) {
            System.out.println("No cities have been added yet.");
            return;
        }
        for(int i = 0; i < cityCounter; i++){
            System.out.println(i+1 + ":   "+Cities[i]);
        }
        System.out.println(".....................................");
    }

    //---------------------------------------------------------------------------------------------


    public static void manageDistances(Scanner input) {
        //This is the sub menu including the intercity distances
        int distanceChoice;

        //New line
        input.nextLine();
        do {
            System.out.println("\n-----Manage Distances-----");
            System.out.println("1. Input or Edit Distance");
            System.out.println("2. Display Distance Table");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            distanceChoice = input.nextInt();

            //Sub choices for distance management
            switch (distanceChoice) {
                case 1 -> editDistances(input);
                case 2 -> displayDistances(input);
                case 0 -> System.out.println("Back to the Main Menu... ");
                default -> System.out.println("Invalid choice");
            }
        }while(distanceChoice != 0);
    }

    public static void editDistances(Scanner input){
        //Method of Input or Edit distance between cities
        allCities(input);
        System.out.print("Enter the index of the source city: ");
        int startingCityindex =  input.nextInt();
        input.nextLine();

        System.out.print("Enter the index of the destination city: ");
        int endingCityindex =  input.nextInt();

        //Check the validity of the numbers entered by the user
        if (startingCityindex < 0 || startingCityindex > cityCounter || endingCityindex < 0 || endingCityindex > cityCounter) {
            System.out.println("Error: Invalid city index.");
            return;
        }

        //Prevent distance entering from city to itself
        if (startingCityindex == endingCityindex) {
            System.out.println("Error: You can't enter the same cities to enter distances.");
            distancesOfCities[startingCityindex][endingCityindex] = 0;
            return;
        }
        System.out.println("Enter the distance between "+startingCityindex+" and "+endingCityindex+" (in km): ");
        int interDistance = input.nextInt();
        //input.nextLine();

        distancesOfCities[startingCityindex-1][endingCityindex-1] = interDistance;
        distancesOfCities[endingCityindex-1][startingCityindex-1] = interDistance;

        System.out.println("Distance entered successfully!");
    }

    public static void displayDistances(Scanner input){
        //Method of displaying the Distances between cities
        if (cityCounter == 0) {
            System.out.println("No  cities have been added yet.");
            return;
        }

        System.out.println("\n--- Intercity Distance Table (Direct Routes) ---");

        // Chart to display the table of distances between cities
        System.out.print("\t");
        for (int i = 0; i < cityCounter; i++) {
            System.out.print(Cities[i] + "\t");
        }
        System.out.println();

        for (int i = 0; i < cityCounter; i++) {
            System.out.print(Cities[i] + "\t");


            for (int j = 0; j < cityCounter; j++) {
                System.out.print(distancesOfCities[i][j] + "\t");
            }
            System.out.println();
        }

    }

    //Vehicle management
    public static void vehicleDetails(){
        //This is the sub menu including the Vehicle type, Capacity (kg), Rate per km (LKR), Avg Speed (km/h) and Fuel Efficiency (km/l)
        System.out.println("\n---Vehicle Details---");

        //Print table
        System.out.printf("%-10s %-15s %-17s %-15s %-20s\n",
                "Type", "Capacity(kg)", "Rate(LKR)", "Speed(km/h)  ", "Efficiency(km/l)");
        System.out.println("-----------------------------------------------------------------------------------");

        for(int i = 0; i < vehicleTypes.length; i++){
            System.out.printf("%-10s %-15d %-15d %-15d %-20d\n",
                    vehicleTypes[i],
                    vehicleCapacity[i],
                    vehicleRate[i],
                    vehicleAvgSpeed[i],
                    vehicleFuelEff[i]
            );
        }
    }

    //Delivery Request Handling
    public static void deliveryRequests(Scanner input){
        //This is the sub menu handling the requests received from the user and display the charge for the relevant delivery
        //Check the availability for a delivery
        if (deliveryCounter >= 50) {
            System.out.println("Error: Delivery records are full. Cannot add more.");
            return;
        }

        allCities(input);
        System.out.println("Enter the number of the starting city: ");
        int startingCityIndex =  input.nextInt();

        System.out.println("Enter the number of the destination city: ");
        int endingCityIndex =  input.nextInt();
        input.nextLine();

        //Check the validity of cities
        if(startingCityIndex < 0 || startingCityIndex > cityCounter || endingCityIndex < 0 || endingCityIndex > cityCounter){
            System.out.println("Error: Invalid city indices.");
            return;
        }
        if(startingCityIndex == endingCityIndex){
            System.out.println("Error: You can't enter the same cities to enter distances..");
            return;
        }

        //Get the weight from the user
        System.out.println("Enter the weight of the goods in (kg): ");
        double weightOfGoods = input.nextDouble();
        input.nextLine();

        if(weightOfGoods >= 10000){
            System.out.println("Your weights can't delivery from these vehicles");
            return;
        }

        //Get the vehicle type from the user
        System.out.println("Select Vehicle Type: ");
        System.out.println("1. Van (max 1000kg)");
        System.out.println("2. Truck (max 5000kg)");
        System.out.println("3. Lorry (max 10000kg)");
        System.out.println("Enter the number of the vehicle type: ");
        int vehicleType = input.nextInt();
        input.nextLine();

        //check the validity of the user inputs
        if (vehicleType < 1 || vehicleType > 3) {
            System.out.println("Error: Invalid vehicle choice.");
            return;
        }

        int vehicleNumber = vehicleType-1;
        //Check the limit of the user input weights with the capacity of the vehicle
        if(weightOfGoods >= vehicleCapacity[vehicleNumber] ){
            System.out.println("Error: you entered "+weightOfGoods+"kg exceed the capacity of "+vehicleCapacity[vehicleNumber]+"kg.");
            return;
        }

        int D = distancesOfCities[startingCityIndex-1][endingCityIndex-1];

        //Assign variables for the calculation
        double W = weightOfGoods;
        int R = vehicleRate[vehicleNumber];
        int S = vehicleAvgSpeed[vehicleNumber];
        int E = vehicleFuelEff[vehicleNumber];

        //Call the methods used for the calculations
        double deliveryCost = calculateDeliveryCost(D,R,W);
        double deliveryTime = calculateDeliveryTime(D,S);
        double fuelConsumed = calculateFuelConsumed(D,E);
        double fuelCost = calculateFuelCost(fuelConsumed,priceOfFuel);
        double totalCost = calculateTotalCost(deliveryCost,fuelCost);
        double profit = profitCalculation(deliveryCost);
        double CustomerCharge = calculateFinalCharge(totalCost,profit);

        //Output of the delivery Estimation
        System.out.println("\n--- DELIVERY COST ESTIMATION ---");
        System.out.println("From: " + Cities[startingCityIndex-1]);
        System.out.println("To: " + Cities[endingCityIndex-1]);
        System.out.println("Minimum Distance: " + D + " km");
        System.out.println("Vehicle: " + vehicleTypes[vehicleNumber]);
        System.out.println("Weight: " + W + " kg");
        System.out.println("===========================================");

        System.out.printf("Base Cost:        %.2f LKR\n", deliveryCost);
        System.out.printf("Fuel Used:        %.2f L\n", fuelConsumed);
        System.out.printf("Fuel Cost:        %.2f LKR\n", fuelCost);
        System.out.printf("Operational Cost: %.2f LKR\n", totalCost);
        System.out.printf("Profit:           %.2f LKR\n", profit);
        System.out.printf("Customer Charge:  %.2f LKR\n", CustomerCharge);
        System.out.printf("Estimated Time:   %.2f hours\n", deliveryTime);
        System.out.println("========================================");

        System.out.print("Do you want to save the record? (Yes/No): ");
        String confirm = input.nextLine();

        if(confirm.equals("Yes")){
            deliveryRecords(startingCityIndex,endingCityIndex,W,vehicleNumber,D,deliveryTime,CustomerCharge,profit);
            System.out.println("Delivery Records saved successfully.");
        }else{
            System.out.println("Delivery has cancelled");
        }

    }

    public static double calculateDeliveryCost(double D, double R, double W){
        //Delivery cost calculation
        return D*R*(1+W*1/10000.0);
    }

    private static double calculateDeliveryTime(double D, double S) {
        // Delivery time calculation
        return (double) D/S;
    }

    private static double calculateFuelConsumed(double D, double E) {
        // Calculate the fuel consumption
        return (double) D / E;
    }

    private static double calculateFuelCost(double fuelConsumed, double priceOfFuel) {
        // Calculate the price of the fuel consumed
        return fuelConsumed*priceOfFuel ;
    }

    private static double calculateTotalCost(double deliveryCost, double fuelCost) {
        // Calculate the total cost spent for a delivery
        return deliveryCost + fuelCost;
    }

    private static double profitCalculation(double deliveryCost) {
        // Calculate the profit of the delivery calculation
        return deliveryCost * 0.25;
    }

    private static double calculateFinalCharge(double totalCost, double profit) {
        // Customer Charge = TotalCost + Profit
        return totalCost + profit;
    }

    public static void deliveryRecords(int startingCityIndex, int endingCityIndex, double weightOfGoods, int vehicleNumber, double distance, double deliveryTime, double charge, double profit) {
        //This method use to save all the delivery records
        deliveryStart[deliveryCounter]=Cities[startingCityIndex-1];
        deliveryEnd[deliveryCounter]=Cities[endingCityIndex-1];
        deliveryWeights[deliveryCounter]=weightOfGoods;
        deliveryVehicles[deliveryCounter]=vehicleTypes[vehicleNumber];
        deliveryDistances[deliveryCounter]=distance;
        deliveryTimes[deliveryCounter] = deliveryTime;
        deliveryCustomerCharges[deliveryCounter] = charge;
        deliveryProfits[deliveryCounter] = profit;

        deliveryCounter++;

    }

    //Delivery records
    public static void pastDeliveryRecords(Scanner input){
        //This is the sub menu to view the history of deliveries
        System.out.println("\n--- Delivery Records ---");

        if (deliveryCounter == 0) {
            System.out.println("Deliveries are not done yet.");
        } else {
            System.out.printf("%-12s %-12s %-10s %-10s %-10s %-15s\n",
                    "From", "To", "Weight(kg)", "Vehicle", "Distance(km)", "Charge(LKR)");
            System.out.println("=================================================================");

            for (int i = 0; i < deliveryCounter; i++) {
                // Print each row
                System.out.printf("%-12s %-12s %-10.1f %-10s %-10.1f %-15.2f\n", deliveryStart[i], deliveryEnd[i], deliveryWeights[i], deliveryVehicles[i], deliveryDistances[i], deliveryCustomerCharges[i]);
            }
        }
    }

    //Performance report
    public static void performanceReport(Scanner input){
        //This is the sub menu can be used to obtain report of the process at the tim

        //Check the possibility to create a report
        if (deliveryCounter == 0) {
            System.out.println("No deliveries to display a report..!");
        }else{
            double totDistance = 0;
            double totTime = 0;
            double totProfit = 0;
            double totRevenue = 0;

            //Assume and init arrays for shortest and longest routes
            double shortestDistance = deliveryDistances[0];
            double longestDistance = deliveryDistances[0];

            for(int i=0;i<deliveryCounter;i++){
                totDistance += deliveryDistances[i];
                totTime += deliveryTimes[i];
                totRevenue += deliveryCustomerCharges[i];
                totProfit += deliveryProfits[i];

                //Finding the longest route
                if(deliveryDistances[i]>longestDistance){
                    longestDistance = deliveryDistances[i];
                }

                //Finding the shortest route
                if(deliveryDistances[i]<shortestDistance){
                    shortestDistance = deliveryDistances[i];
                }

            }

            //Calculating the Average time
            double avgTime = totTime/deliveryCounter;

            //Final Report
            System.out.println("\n=====Performance Report======");
            System.out.printf("Total Deliveries Completed: %d\n", deliveryCounter);
            System.out.printf("Total Distance Covered:     %.2f km\n", totDistance);
            System.out.printf("Average Delivery Time:      %.2f hours\n", avgTime);
            System.out.printf("Total Revenue:              %.2f LKR\n", totRevenue);
            System.out.printf("Total Profit:               %.2f LKR\n", totProfit);
            System.out.printf("Shortest Route Completed:   %.2f km\n", shortestDistance);
            System.out.printf("Longest Route Completed:    %.2f km\n", longestDistance);

        }


    }
    public static void saveData() {

        // --- 1. Save routes.txt (Cities and Distances) ---
        try (PrintWriter writer = new PrintWriter(new File("routes.txt"))) {

            // Save the number of cities
            writer.println(cityCounter);

            // Save the list of city names
            for (int i = 0; i < cityCounter; i++) {
                writer.println(Cities[i]);
            }

            // Save the 2D distance matrix
            for (int i = 0; i < cityCounter; i++) {
                for (int j = 0; j < cityCounter; j++) {
                    writer.print(distancesOfCities[i][j] + " ");
                }
                writer.println(); // Newline after each row
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not save data to routes.txt. " + e.getMessage());
        }

        // --- 2. Save deliveries.txt (Delivery History) ---
        try (PrintWriter writer = new PrintWriter(new File("deliveries.txt"))) {

            // Save the number of deliveries
            writer.println(deliveryCounter);

            // Save each delivery record on a new line, separated by commas
            for (int i = 0; i < deliveryCounter; i++) {
                writer.println(
                        deliveryStart[i] + "," +
                                deliveryEnd[i] + "," +
                                deliveryWeights[i] + "," +
                                deliveryVehicles[i] + "," +
                                deliveryDistances[i] + "," +
                                deliveryTimes[i] + "," +
                                deliveryCustomerCharges[i] + "," +
                                deliveryProfits[i]
                );
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not save data to deliveries.txt. " + e.getMessage());
        }

        System.out.println("All data saved successfully.");
    }

    public static void loadData() {

        // --- 1. Load routes.txt (Cities and Distances) ---
        try {
            File routesFile = new File("routes.txt");
            if (routesFile.exists()) {
                Scanner fileScanner = new Scanner(routesFile);

                // Read the number of cities
                cityCounter = fileScanner.nextInt();
                fileScanner.nextLine(); // Consume newline

                // Read the list of city names
                for (int i = 0; i < cityCounter; i++) {
                    Cities[i] = fileScanner.nextLine();
                }

                // Read the 2D distance matrix
                for (int i = 0; i < cityCounter; i++) {
                    for (int j = 0; j < cityCounter; j++) {
                        distancesOfCities[i][j] = fileScanner.nextInt();
                    }
                }

                fileScanner.close();
                System.out.println("Loaded " + cityCounter + " cities and distances from routes.txt.");
            } else {
                System.out.println("routes.txt not found. Starting with new data.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading routes.txt: " + e.getMessage());
        }

        // --- 2. Load deliveries.txt (Delivery History) ---
        try {
            File deliveriesFile = new File("deliveries.txt");
            if (deliveriesFile.exists()) {
                Scanner fileScanner = new Scanner(deliveriesFile);

                // Read the number of deliveries
                deliveryCounter = fileScanner.nextInt();
                fileScanner.nextLine(); // Consume newline

                // Read each delivery record
                for (int i = 0; i < deliveryCounter; i++) {
                    String line = fileScanner.nextLine();
                    String[] data = line.split(","); // Split the line by comma

                    // Re-populate the arrays
                    deliveryStart[i] = data[0];
                    deliveryEnd[i] = data[1];
                    deliveryWeights[i] = Double.parseDouble(data[2]);
                    deliveryVehicles[i] = data[3];
                    deliveryDistances[i] = Double.parseDouble(data[4]);
                    deliveryTimes[i] = Double.parseDouble(data[5]);
                    deliveryCustomerCharges[i] = Double.parseDouble(data[6]);
                    deliveryProfits[i] = Double.parseDouble(data[7]);
                }

                fileScanner.close();
                System.out.println("Loaded " + deliveryCounter + " delivery records from deliveries.txt.");
            } else {
                System.out.println("deliveries.txt not found. Starting with new data.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading deliveries.txt: " + e.getMessage());
        }
    }

    public static void resetAllData(Scanner input) {
        //Method use to reset all the data entered to the system
        System.out.println("\n--- Reset All Data ---");
        System.out.print("Are you sure you want to erase ALL cities, distances, and delivery records? (Yes/No): ");
        String confirm = input.nextLine();
        input.nextLine();

        if (confirm.equals("Yes") || confirm.equals("Y")) {

            // 1. Reset the counters in memory
            cityCounter = 0;
            deliveryCounter = 0;
            saveData();

            System.out.println("All data has been reset.");
            System.out.println("Please restart the program to use the empty data.");

        } else {
            System.out.println("Reset cancelled.");
        }
    }
}
