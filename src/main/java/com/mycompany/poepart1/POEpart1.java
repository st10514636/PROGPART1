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
        }

        scanner.close();
    }
}
