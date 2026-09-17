/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author Student
 */
public class Login {
    
    String username;
    String password;
    String cellPhoneNumber;
    String firstName;
    String lastName;
    
    public Login(){}
    
    public boolean checkUserName(String username){
        return username != null && username.contains("-") && username.length() <= 5;
    }
    
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8){
            return false;
        }
        boolean hasCapital = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (int i = 0; i< password.length(); i++) {
            char ch = password.charAt(i);
            
            if (Character.isUpperCase(ch)){
                hasCapital = true;
            } else if (Character.isDigit(ch)){
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)){
                hasSpecial = true;
            }
        }
        return hasCapital && hasDigit && hasSpecial;
    }
    public boolean checkCellPhoneNumber(String cellPhoneNumber){
        if (this.cellPhoneNumber== null){ return false;}
        String cellRegex = "^\\+27[0-9]{9}$";
        return Pattern.matches(cellRegex,this.cellPhoneNumber);
        
    } 
       



    public String registerUser(String username, String password, String cellPhoneNumber, String firstName, String lastName){
        if (!checkUserName(username)){
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)){
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhoneNumber)){
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        
        return "Cell number successfully captured.";
    }

public boolean loginUser(String enteredUsername, String enteredPassword){
            if (enteredUsername == null || enteredPassword == null ){
                return false;
            }
            return enteredUsername.equals(this.username) && enteredPassword.equals (this.password);
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn){
            return "elcome" + this.firstName + ", " + this.lastName + "it is great to see you again.";
        }else {
            return "Username or password incorrect, please try again. ";
            
        }

    }
    
}
