package cinema;

import java.util.Scanner;

public class Room {

    private final char[][] grid;
    private final int rows;
    private final int seats;
    private final Scanner sc;
    private final int total;
    private int purchased;
    private int income;

    public Room() {

        System.out.println("Enter the number of rows:");
        this.sc = new Scanner(System.in);
        this.rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        this.seats = sc.nextInt();
        this.total = rows * seats;
        this.purchased = 0;
        this.income = 0;

        this.grid = new char[rows][seats];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                this.grid[i][j] = 'S';
            }
        }
    }

    private void printTheater() {

        System.out.println("\nCinema:");
        for (int i = 0; i <= this.seats; i++) {
            System.out.print(i == 0 ? "  " : i + " ");
        }
        System.out.println();

        int idx = 1;
        for (char[] row : this.grid) {
            System.out.print(idx + " ");
            for (char seat : row) {
                System.out.print(seat + " ");
            }
            System.out.println();
            idx++;
        }
        System.out.println();
    }

    private int calculateRevenue() {
        return this.total <= 60 ? this.total * 10
                : (this.rows / 2 * this.seats * 10 + (int) Math.ceil(this.rows / 2.0) * this.seats * 8);
    }

    private void buySeat() {

        while (true) {

            System.out.println("\nEnter a row number:");
            int row = this.sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = this.sc.nextInt() - 1;

            try {

                if (this.grid[row - 1][seat] == 'B') {
                    System.out.println("\nThat ticket has already been purchased!");
                } else {
                    int price = this.seatPrice(row);
                    System.out.println("\nTicket price: $" + price);
                    this.grid[row - 1][seat] = 'B';
                    this.income += price;
                    this.purchased++;
                    break;
                }
            } catch (Exception e) {
                System.out.println("\nWrong input!");
            }
        }

    }

    private int seatPrice(int row) {
        return this.total <= 60 ? 10 : row <= this.rows / 2 ? 10 : 8;
    }

    private void statistics() {

        System.out.println("\nNumber of purchased tickets: " + this.purchased);
        System.out.printf("Percentage: %.2f%%\n", (double) this.purchased / this.total * 100);
        System.out.println("Current income: $" + this.income);
        System.out.println("Total income: $" + this.calculateRevenue());
    }

    public void start() {

        while (true) {

            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int choice = sc.nextInt();

            if (choice == 1) {
                this.printTheater();
            } else if (choice == 2) {
                this.buySeat();
            } else if (choice == 3) {
                this.statistics();
            } else if (choice == 0) {
                break;
            }
        }
    }
}