/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */

public class LoginTester {
    
        @Test
    public void testCheckUserNameCorrectFormat() {
        Login login = new Login();
        boolean actual = login.checkUserName("Kyl_1");
        assertTrue(actual);
    }

    
    @Test
    public void testCheckUserNameIncorrectFormat() {
        Login login = new Login();
        boolean actual = login.checkUserName("kyle!!!!!!");
        assertFalse(actual);
    }


    
    @Test
    public void testCheckPasswordComplexitySuccess() {
        Login login = new Login();
        boolean actual = login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue(actual);
    }

    
    @Test
    public void testCheckPasswordComplexityFailure() {
        Login login = new Login();
        boolean actual = login.checkPasswordComplexity("password");
        assertFalse(actual);
    }


    
    @Test
    public void testCheckCellPhoneNumberSuccess() {
        Login login = new Login();
        boolean actual = login.checkCellPhoneNumber("+27685398441");
        assertTrue(actual);
    }

    
    @Test
    public void testCheckCellPhoneNumberFailure() {
        Login login = new Login();
        boolean actual = login.checkCellPhoneNumber("0685398441");
        assertFalse(actual);
    }


    
    @Test
    public void testRegisterUserSuccessMessage() {
        Login login = new Login();
        String expected = "Cell number successfully captured.";
        String actual = login.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Nomthandazo", "Nhlapo");
        assertEquals(expected, actual);
    }

   
    @Test
    public void testRegisterUserInvalidUsernameMessage() {
        Login login = new Login();
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = login.registerUser("kyle!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Nomthandazo", "Nhlapo");
        assertEquals(expected, actual);
    }

    
    @Test
    public void testRegisterUserInvalidPasswordMessage() {
        Login login = new Login();
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = login.registerUser("Kyl_1", "password", "+27838968976", "Nomthandazo", "Nhlapo");
        assertEquals(expected, actual);
    }

    
    @Test
    public void testRegisterUserInvalidCellMessage() {
        Login login = new Login();
        String expected = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        String actual = login.registerUser("Kyl_1", "Ch&&sec@ke99!", "08966553", "Nomthandazo", "Nhlapo");
        assertEquals(expected, actual);
    }

    @Test
    public void testLoginUserSuccess() {
        Login login = new Login();
        login.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Nomthandazo", "Nhlapo");
        boolean actual = login.loginUser("Kyl_1", "Ch&&sec@ke99!");
        assertTrue(actual);
    }

    @Test
    public void testLoginUserFailure() {
        Login login = new Login();
        login.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Nomthandazo", "Nhlapo");
        boolean actual = login.loginUser("wrong_user", "wrong_pass");
        assertFalse(actual);
    }



   
    @Test
    public void testReturnLoginStatusSuccess() {
        Login login = new Login();
        login.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Nomthandazo", "Nhlapo");
        String expected = "Welcome Nomthandazo, Nhlapo it is great to see you again.";
        String actual = login.returnLoginStatus(true);
        assertEquals(expected, actual);
    }

    @Test
    public void testReturnLoginStatusFailure() {
        Login login = new Login();
        String expected = "Username or password incorrect, please try again.";
        String actual = login.returnLoginStatus(false);
        assertEquals(expected, actual);
    }
}   

