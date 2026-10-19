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
public class MessageTester {
    
    public MessageTester() {
    }
    // 1. Test message length success (<= 250 chars)
    @Test
    public void testCheckMessageLengthSuccess() {
        Message msg = new Message();
        String actual = msg.checkMessageLength("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", actual);
    }

    // 2. Test message length failure (> 250 chars)
    @Test
    public void testCheckMessageLengthFailure() {
        Message msg = new Message();
        String longMessage = "A".repeat(260);
        String actual = msg.checkMessageLength(longMessage);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", actual);
    }

    // 3. Test recipient cell number success
    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message();
        String actual = msg.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", actual);
    }

    // 4. Test recipient cell number failure
    @Test
    public void testCheckRecipientCellFailure() {
        Message msg = new Message();
        String actual = msg.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", actual);
    }

    // 5. Test message hash generation
    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        msg.messageID = "0012345678"; // Set fixed ID for deterministic test
        String actual = msg.createMessageHash();
        assertEquals("00:0:HITONIGHT", actual);
    }

    // 6. Test message ID length validation
    @Test
    public void testCheckMessageID() {
        Message msg = new Message();
        msg.messageID = "1234567890";
        assertTrue(msg.checkMessageID());
    }

    // 7. Test user selection - Send Message
    @Test
    public void testSentMessageSend() {
        Message msg = new Message();
        String actual = msg.SentMessage(1);
        assertEquals("Message successfully sent.", actual);
    }

    // 8. Test user selection - Disregard Message
    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message();
        String actual = msg.SentMessage(2);
        assertEquals("Press 0 to delete the message.", actual);
    }

    // 9. Test user selection - Store Message
    @Test
    public void testSentMessageStore() {
        Message msg = new Message();
        String actual = msg.SentMessage(3);
        assertEquals("Message successfully stored.", actual);
    }
    
}
