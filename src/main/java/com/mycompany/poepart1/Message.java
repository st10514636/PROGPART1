/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;
import java.util.Random;
/**
 *
 * @author Student
 */
public class Message {
   String messageID;
    String recipientCell;
    String messageText;
    int messageNumber;
    static int totalMessagesSent = 0;
    
    public Message() {}
    public Message(String recipientCell, String messageText, int messageNumber) {
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
    }

    public String generateMessageID() {
        Random ran = new Random();
        long number = 1000000000L + (long)(ran.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    // Verifies Message ID is not more than 10 characters
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    // Validates recipient cell number (max 10 chars, starts with international code '+')
    public String checkRecipientCell(String cellNumber) {
        if (cellNumber == null || cellNumber.length() > 10 || cellNumber.length() < 2 || cellNumber.charAt(0) != '+') {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        for (int i = 1; i < cellNumber.length(); i++) {
            if (!Character.isDigit(cellNumber.charAt(i))) {
                return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
            }
        }
        return "Cell phone number successfully captured.";
    }

    // Checks length of message (max 250 chars)
    public String checkMessageLength(String text) {
        if (text != null && text.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = (text != null) ? (text.length() - 250) : 0;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Creates Message Hash: [First 2 digits of ID]:[Message Number]:[First & Last Words in Caps]
    public String createMessageHash() {
        if (this.messageID == null || this.messageID.length() < 2 || this.messageText == null) {
            return "";
        }

        String firstTwoID = this.messageID.substring(0, 2);
        String trimmedText = this.messageText.trim();
        String[] words = trimmedText.split("\\s+");

        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");

        String hash = firstTwoID + ":" + this.messageNumber + ":" + (firstWord + lastWord).toUpperCase();
        return hash;
    }

    // Processes user sending choice
    public String SentMessage(int choice) {
        if (choice == 1) {
            totalMessagesSent++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            return "Message successfully stored.";
        } else {
            return "Invalid selection.";
        }
    }

    // Formats and returns full message details
    public String printMessages() {
        return "Message ID: " + this.messageID + "\n" +
               "Message Hash: " + createMessageHash() + "\n" +
               "Recipient: " + this.recipientCell + "\n" +
               "Message: " + this.messageText;
    }

    // Returns total messages sent during session
    public int returnTotalMessagess() {
        return totalMessagesSent;
    }

    // Research placeholder method for JSON storage requirement
    public String storeMessage() {
        return "JSON storage feature researched and ready for implementation.";
    }
}

