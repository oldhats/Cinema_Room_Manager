package cinema;

import java.util.Scanner;



public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        int numOfPurchasedTickets = 0;
        int currentIncome = 0;

//Ask the user how many seats and rows there
        //are in the theater
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt() + 1;
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt() + 1;

        //Creating a 2d array depending on the number of
        //rows and seats

        System.out.println();

        char[][] seating = new char[rows][seats];
        char ch = 'S';
        char ch1 = '0';

        int optionMenu = -1;

        //Prints out the options for the user
        //when user enters 0 will exit program
        while (optionMenu != 0) {
            //Print out the options for the user
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            //Take the user input
            optionMenu = scanner.nextInt();

            switch (optionMenu) {
                case 1:
                    seatingArrangement(seating, rows, seats, numOfPurchasedTickets);
                    break;
                case 2:
                    buyingTicket(seating, rows, seats, numOfPurchasedTickets);
                    break;
                case 3:
                    statistics(seating, rows, seats, numOfPurchasedTickets);
                    break;
                default:
                    return;

            }

        }


    }


    public static void seatingArrangement(char[][] seating, int rows, int seats, int numOfPurchasedTickets) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cinema:");
        // populate first row and column with seat numbers
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                if (i == 0 && j > 0) {
                    // populate first row with seat numbers
                    seating[i][j] = (char) (j + '0');
                } else if (j == 0 && i > 0) {
                    // populate first column with seat numbers
                    seating[i][j] = (char) (i + '0');
                } else {
                    // populate rest of the seats with 'S'
                    if (seating[i][j] == 'B') {
                        seating[i][j] = 'B';
                        numOfPurchasedTickets++;
                    } else {
                        seating[i][j] = 'S';
                        seating[0][0] = ' ';
                    }

                }
            }
        }


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                System.out.print(seating[i][j] + " ");
            }
            System.out.println();
        }


    }


    public static void buyingTicket(char[][] seating, int rows, int seats,int numOfPurchasedTickets) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();


        while (true) {
            if (row <= 0 | seat <= 0 | row > seating.length - 1 || seat > seating[0].length - 1) {
                System.out.println();
                System.out.println("Wrong input!");
                System.out.println();
                System.out.println("Enter a row number:");
                row = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                seat = scanner.nextInt();
            } else if (seating[row][seat] == 'B') {
                System.out.println();
                System.out.println("That ticket has already been purchased!");
                System.out.println();
                System.out.println("Enter a row number:");
                row = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                seat = scanner.nextInt();
            } else {
                seating[row][seat] = 'B';
                break; // exit the loop if input is valid
            }
        }






        // populate first row and column with seat numbers

        int totalSeats = (rows - 1) * (seats - 1);

        int ticketPrice = 0;

        //Calculation for front rows and total
        int frontRows = rows / 2;
        int frontSeats = frontRows * seats;

        //Calculation for back rows and total
        //income from those rows
        int backRows = rows - frontRows;
        int backSeats = backRows * seats;



        //if else statement to calculate ticket price
        //depending on number of seats
        //and total income
        if (totalSeats < 60) {
        //if the total amount of seats is less than 60
        //then the ticket price is $10
        ticketPrice = 10;
        System.out.println("Ticket price: $" + ticketPrice);
        } else if (row < rows / 2) {
        ticketPrice = 10;

        System.out.println("Ticket price: $" + ticketPrice);
        } else  {
        ticketPrice = 8;

        System.out.println("Ticket price: $" + ticketPrice);
        }


        }


        public static void statistics(char[][] seating, int rows, int seats, int numOfPurchasedTickets) {

        //initializing variables
        int currentIncome = 0;
        double totalAmountOfSeats = (rows - 1) * (seats - 1);
        int totalIncome = ((((rows - 1)/2) * (seats - 1)) * 10) + ((((rows - 1) - ((rows - 1)/2)) * (seats - 1)) * 8);

            //for loop that iterates through the seating array and adds 1 to numOfPurchasedTickets
            //if a B character is found also adds 10 to local income amount using logic
            //from the seatingArrangement function
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seats; j++) {
                    if (seating[i][j] == 'B') {
                        numOfPurchasedTickets++;
                    }
                    if (i < (rows)/2 && seating[i][j] == 'B' | totalAmountOfSeats < 60) {
                        currentIncome += 10;
                    } else if (i >= (rows)/2  && seating[i][j] == 'B') {
                        currentIncome += 8;
                    }



                }


            }


            double percentOfSoldTickets = (double) numOfPurchasedTickets / totalAmountOfSeats * 100;


            System.out.printf("Number of purchased tickets: %d %n", numOfPurchasedTickets);
            System.out.printf("Percentage: %.2f%% %n", percentOfSoldTickets);
            System.out.printf("Current income: $%d %n", currentIncome);
            System.out.printf("Total income: $%d %n", totalIncome);


        }
}








