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
    @Test
    public void testCheckMessageLengthFailure() {
        Message msg = new Message();
        String longMessage = "A".repeat(260);
        String actual = msg.checkMessageLength(longMessage);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", actual);
    }
    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message();
        String actual = msg.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", actual);
    }
    @Test
    public void testCheckRecipientCellFailure() {
        Message msg = new Message();
        String actual = msg.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", actual);
    }
@Test
    public void testCreateMessageHash() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        msg.messageID = "0012345678"; // Set fixed ID for deterministic test
        String actual = msg.createMessageHash();
        assertEquals("00:0:HITONIGHT", actual);
    }
    @Test
    public void testCheckMessageID() {
        Message msg = new Message();
        msg.messageID = "1234567890";
        assertTrue(msg.checkMessageID());
    }
    @Test
    public void testSentMessageSend() {
        Message msg = new Message();
        String actual = msg.SentMessage(1);
        assertEquals("Message successfully sent.", actual);
    }
    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message();
        String actual = msg.SentMessage(2);
        assertEquals("Press 0 to delete the message.", actual);
    }
    }