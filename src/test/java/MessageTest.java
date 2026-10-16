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
public class MessageTest {
    
  
    @Test
    public void testCheckMessageLengthSuccess() {
        Message msg = new Message();
        String actual = msg.checkMessageLength("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", actual);
    }  
}