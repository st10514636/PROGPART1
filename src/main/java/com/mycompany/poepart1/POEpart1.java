/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart1; 

/**
 *
 * @author Student
 */
import java.util.Scanner;

public class POEpart1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login app = new Login();

        System.out.println("--- ACCOUNT REGISTRATION ---");

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Cell Phone Number (e.g. +27685398441): ");
        String cellPhone = scanner.nextLine();

        String registrationResult = app.registerUser(
                username, password, cellPhone, firstName, lastName
        );

        System.out.println("\n" + registrationResult);

        // Fixed: registrationResult instead of registerationResult
        if (registrationResult.contains("successfully captured")) {

            System.out.println("\n--- LOGIN ---");

            System.out.print("Enter Username: ");
            String loginUser = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPass = scanner.nextLine();

            boolean isAuthenticated = app.loginUser(loginUser, loginPass);

            System.out.println(app.returnLoginStatus(isAuthenticated));
            if (isAuthenticated) {
            System.out.println("\nWelcome to QuickChat.");
            int option = 0;

            while (option != 3) {
                System.out.println("\nPlease choose an option from the menu:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choice: ");

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();
                    scanner.nextLine(); // clear newline
                } else {
                    scanner.nextLine();
                    continue;
                }

                if (option == 1) {
                    System.out.print("\nHow many messages would you like to enter? ");
                    int numMessages = scanner.nextInt();
                    scanner.nextLine(); // clear buffer

                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");

                        System.out.print("Enter Recipient Cell Number: ");
                        String recipient = scanner.nextLine();

                        System.out.print("Enter Message Text: ");
                        String text = scanner.nextLine();

                        Message msg = new Message(recipient, text, i);

                        // Validate cell format
                        String cellCheck = msg.checkRecipientCell(recipient);
                        if (!cellCheck.equals("Cell phone number successfully captured.")) {
                            System.out.println(cellCheck);
                            continue;
                        }

                        // Validate message length
                        String lenCheck = msg.checkMessageLength(text);
                        System.out.println(lenCheck);
                        if (!lenCheck.equals("Message ready to send.")) {
                            continue;
                        }

                        // Option menu for sending, disregarding, or storing
                        System.out.println("Choose action:\n1) Send Message\n2) Disregard Message\n3) Store Message");
                        System.out.print("Action choice: ");
                        int action = scanner.nextInt();
                        scanner.nextLine();

                        String actionResult = msg.SentMessage(action);
                        System.out.println(actionResult);

                        if (action == 1) {
                            System.out.println("\n--- Message Details ---");
                            System.out.println(msg.printMessages());
                        }
                    }

                    Message tracker = new Message();
                    System.out.println("\nTotal messages sent so far: " + tracker.returnTotalMessagess());

                } else if (option == 2) {
                    System.out.println("Coming Soon.");
                } else if (option == 3) {
                    System.out.println("Thank you for using QuickChat. Goodbye!");
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }

        scanner.close();
    }
    }
}

       