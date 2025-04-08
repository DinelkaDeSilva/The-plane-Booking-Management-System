//To read user input, import the Scanner class.
import java.util.Scanner;

public class w2053991_PlaneManagement {
    public static final char[][] seats = new char[4][14]; // Create a 2D array to show the arrangement of seats.
    public static final Ticket[][] tickets = new Ticket[4][14]; // Create a 2D array for keeping ticket data.


    // Set 'O' for open seats and 'X' for reserved seats to begin the seating arrangement.

    static {
        seats[0] = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        seats[1] = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        seats[2] = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        seats[3] = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
    }

    // Explain how to start the program using the main method
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Welcome to the Plane Management application");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int option;
        //print menu page
        do {
            System.out.println("****************************************************");
            System.out.println("\n*                 MENU OPTIONS                    *");
            System.out.println("\n****************************************************");
            System.out.println();
            System.out.println("1. Buy a seat");
            System.out.println("2. Cancel a seat");
            System.out.println("3. Find first available seat");
            System.out.println("4. Show seating plan");
            System.out.println("5. Print tickets information and total sales"); // Task 10
            System.out.println("6. Search ticket");
            System.out.println("0. Quit");
            System.out.println();
            System.out.println("****************************************************\n");
            System.out.print("please Select an option: ");
            option = scanner.nextInt();

            // Use a switch statement to process the user's menu selection.
            switch (option) {
                case 1 -> buySeat();
                case 2 -> cancelSeat();
                case 3 -> findFirstAvailable();
                case 4 -> showSeatingPlan();
                case 5 -> print_tickets_info(); // Task 10
                case 6 -> searchTicket();
                case 0 -> System.out.println("Exiting program.");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0); // Continue until the user decides to give up.
    }

    public static void buySeat() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter row letter (A-D): ");
        char row = scanner.next().charAt(0);
        System.out.print("Enter seat number (1-14): ");
        int seat = scanner.nextInt();

        int rowIndex = Character.toUpperCase(row)  - 'A';

        if (rowIndex < 0 || rowIndex >= seats.length || seat < 1 || seat > seats[rowIndex].length) {
            System.out.println("Invalid row or seat number.");
            return;
        }

        if (seats[rowIndex][seat - 1] == 'O') {
            seats[rowIndex][seat - 1] = 'X';

            // Using user input, create a new Person object.
            Person person = Person.createPersonFromInput(scanner);

            // Use the given data to create a new Ticket object.
            Ticket ticket = new Ticket(row, seat, calculateTicketPrice(seat - 1), person);

            ticket.print();// Print the ticket details.
            ticket.save();// Save the ticket details.

            System.out.println("Seat booked successfully.");

            //  keep the reserved ticket
            tickets[rowIndex][seat - 1] = ticket;
        } else {
            System.out.println("Seat is not available.");
        }

    }



    public static void cancelSeat() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter row letter (A-D): ");
        char row = scanner.next().charAt(0);
        System.out.print("Enter seat number (1-14): ");
        int seat = scanner.nextInt();

        int rowIndex = row - 'A';

        //Test if the seat number or row is incorrect.
        if (rowIndex < 0 || rowIndex >= seats.length || seat < 1 || seat > seats[rowIndex].length) {
            System.out.println("Invalid row or seat number.");
            return;
        }

        // Test that there is a seat available.
        if (seats[rowIndex][seat - 1] == 'X') {
            seats[rowIndex][seat - 1] = 'O';
            System.out.println("Seat canceled successfully.");
        } else {
            System.out.println("Seat is not booked.");
        }
    }

    public static void findFirstAvailable() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 'O') {
                    System.out.printf("First available seat: Row %c, Seat %d\n", 'A' + i, j + 1);
                    return;
                }
            }
        }
        System.out.println("No available seats.");
    }

    public static void showSeatingPlan() {

        // Print the seating plan's header.
        System.out.println("\n=== Seating Plan ===");
        System.out.println("     1   2   3   4   5   6   7   8   9   10  11  12  13  14");
        char rowName = 'A';
        int i = 0;
        while (i < seats.length) {
            System.out.print(rowName + "    ");
            rowName++;
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + "   ");
            }
            System.out.println();
            i++;
        }
    }

    public static void print_tickets_info() {
        double totalSales = 0;

        System.out.println("\n=== Tickets Information ===");

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 'X') { // If a seat is reserved
                    double ticketPrice = calculateTicketPrice(j);

                    // Show ticket details with row, seat, and price
                    System.out.printf("Ticket: Row %c, Seat %d - £%.2f\n", 'A' + i, j + 1, ticketPrice);
                    totalSales += ticketPrice;
                }
            }
        }

        // Display the total amount sold.
        System.out.println("\nTotal Sales: £" + totalSales);
    }

    public static double calculateTicketPrice(int seatIndex) {
        if (seatIndex < 5) {
            return 200.0; // The first five seats are £200.
        } else if (seatIndex < 9) {
            return 150.0; // Next four seats are £150
        } else {
            return 180.0;  // The rest cost £18
        }
    }

    public static void searchTicket() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter row letter (A-D): ");
        char row = scanner.next().charAt(0);
        System.out.print("Enter seat number (1-14): ");
        int seat = scanner.nextInt();

        int rowIndex = row - 'A';

        if (rowIndex < 0 || rowIndex >= seats.length || seat < 1 || seat > seats[rowIndex].length) {
            System.out.println("Invalid row or seat number.");
            return;
        }

        // Test if the seat is booked
        if (seats[rowIndex][seat - 1] == 'X') {
            for (Ticket[] rowTickets : tickets) {
                for (Ticket ticket : rowTickets) {
                    if (ticket != null && ticket.getRow() == row && ticket.getSeat() == seat) {
                        System.out.println("\nTicket and Person Information:");
                        ticket.print();
                        return;
                    }
                }
            }
        }

        System.out.println("This seat is available.");
    }
}