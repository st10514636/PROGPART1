
package com.mycompany.poepart1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.*;


/**
 *
 * @author Student
 */
public class Message {
    
    //field
    public String messageID;
    String recipientCell;
    String messageText;
    int messageNumber;
    static int totalMessagesSent = 0;
    
   File myjson = new File("message.json");
   
   String senderName = "Developer";

    static String[] sentMessages = new String[50];
    static String[] sentRecipients = new String[50];
    static String[] sentSenders = new String[50];
    static String[] sentHashes = new String[50];
    static String[] sentIDs = new String[50];
    static int sentCount = 0;

    static String[] disregardedMessages = new String[50];
    static int disregardedCount = 0;

    static String[] storedMessages = new String[50];
    static String[] storedRecipients = new String[50];
    static String[] storedSenders = new String[50];
    static String[] storedHashes = new String[50];
    static String[] storedIDs = new String[50];
    static int storedCount = 0;

    static String[] messageHashes = new String[50];
    static String[] messageIDs = new String[50];
    static String[] allMessages = new String[50];
    static String[] allRecipients = new String[50];
    static String[] allSenders = new String[50];
    static int totalTrackedCount = 0;
    
    public Message() {}
    public Message(String recipientCell, String messageText, int messageNumber) {
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
    }

    public String generateMessageID() {
        Random ran = new Random();
        String id = "";
    for (int i = 0; i < 10; i++) {
        id = id + ran.nextInt(10);
    }
    return id;
    }

    
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    public String checkRecipientCell(String cellNumber) {
        if (cellNumber == null || cellNumber.length() > 12 || cellNumber.length() < 2 || cellNumber.charAt(0) != '+') {
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

    public String SentMessage(int choice) {
      String currentHash = createMessageHash();

        if (choice == 1) {
            totalMessagesSent++;
            sentMessages[sentCount] = this.messageText;
            sentRecipients[sentCount] = this.recipientCell;
            sentSenders[sentCount] = this.senderName;
            sentHashes[sentCount] = currentHash;
            sentIDs[sentCount] = this.messageID;
            sentCount++;

            recordInMasterArrays(currentHash);
            return "Message successfully sent.";

        } else if (choice == 2) {
            disregardedMessages[disregardedCount] = this.messageText;
            disregardedCount++;
            return "Press 0 to delete the message.";

        } else if (choice == 3) {
            storedMessages[storedCount] = this.messageText;
            storedRecipients[storedCount] = this.recipientCell;
            storedSenders[storedCount] = this.senderName;
            storedHashes[storedCount] = currentHash;
            storedIDs[storedCount] = this.messageID;
            storedCount++;

            recordInMasterArrays(currentHash);
            return storeMessage();

        } else {
            return "Invalid selection.";
        }
    }
        public void recordInMasterArrays(String hash) {
        messageHashes[totalTrackedCount] = hash;
        messageIDs[totalTrackedCount] = this.messageID;
        allMessages[totalTrackedCount] = this.messageText;
        allRecipients[totalTrackedCount] = this.recipientCell;
        allSenders[totalTrackedCount] = this.senderName;
        totalTrackedCount++;
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
        String newEntry = " {\n" +
                "  \"messageID\": \"" + this.messageID + "\",\n" +
                "  \"messageHash\": \"" + createMessageHash() + "\",\n" +
                "  \"recipientCell\": \"" + this.recipientCell + "\",\n" +
                "  \"messageText\": \"" + this.messageText + "\",\n" +
                "  \"messageNumber\": " + this.messageNumber + "\n" +
                "}";

       try {
        String existingContent = "";
        if (myjson.exists() && myjson.length() > 0) {
            Scanner reader = new Scanner(myjson);
            while (reader.hasNextLine()) {
                existingContent += reader.nextLine() + "\n";
            }

            reader.close();
            existingContent = existingContent.trim();
            if (existingContent.endsWith("]")) {
                existingContent = existingContent.substring(0, existingContent.length() - 1).trim();
            }
        }

        FileWriter file = new FileWriter(myjson);
        if (existingContent.isEmpty() || existingContent.equals("[")) {
            file.write("[\n" + newEntry + "\n]");
        } else {
            file.write(existingContent + ",\n" + newEntry + "\n]");
        }
        file.close();
        return "Message successfully stored.";
    } catch (IOException e) {
        return "Error saving message to JSON file.";
    }
    }
    public void readJSONIntoStoredArray() {
        try {
            if (!myjson.exists()) {
                return;
            }
            Scanner fileReader = new Scanner(myjson);
            storedCount = 0;

            String id = "", hash = "", recipient = "", text = "";

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine().trim();
                if (line.startsWith("\"messageID\":")) {
                    id = extractJsonValue(line);
                } else if (line.startsWith("\"messageHash\":")) {
                    hash = extractJsonValue(line);
                } else if (line.startsWith("\"recipientCell\":")) {
                    recipient = extractJsonValue(line);
                } else if (line.startsWith("\"messageText\":")) {
                    text = extractJsonValue(line);
                    storedIDs[storedCount] = id;
                    storedHashes[storedCount] = hash;
                    storedSenders[storedCount] = this.senderName;
                    storedRecipients[storedCount] = recipient;
                    storedMessages[storedCount] = text;
                    storedCount++;
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Could not read JSON file: " + e.getMessage());
        }
    }

    public String extractJsonValue(String line) {
        int colonIndex = line.indexOf(":");
        String value = line.substring(colonIndex + 1).trim();
        if (value.endsWith(",")) {
            value = value.substring(0, value.length() - 1);
        }
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }
        return value;
    }
}
public String displaySendersAndRecipients() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalTrackedCount; i++) {
            sb.append((i + 1) + ". Sender: " + allSenders[i]
                    + " | Recipient: " + allRecipients[i] + "\n");
        }
        return sb.length() > 0 ? sb.toString().trim() : "No messages recorded.";
    }

    public String getLongestMessage() {
        if (totalTrackedCount == 0) {
            return "No messages available.";
        }
        int longestIndex = 0;
        for (int i = 1; i < totalTrackedCount; i++) {
            if (allMessages[i] != null && allMessages[i].length() > allMessages[longestIndex].length()) {
                longestIndex = i;
            }
        }
        return "Message ID: " + messageIDs[longestIndex]
                + "\nRecipient: " + allRecipients[longestIndex]
                + "\nMessage (" + allMessages[longestIndex].length() + " chars): " + allMessages[longestIndex];
    }

    public String searchByMessageID(String targetID) {
        for (int i = 0; i < totalTrackedCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equalsIgnoreCase(targetID)) {
                return "Found Message:\nID: " + messageIDs[i]
                        + "\nHash: " + messageHashes[i]
                        + "\nRecipient: " + allRecipients[i]
                        + "\nMessage: " + allMessages[i];
            }
        }
        return "Message ID not found: " + targetID;
    }

    public String searchByRecipient(String targetRecipient) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalTrackedCount; i++) {
            if (allRecipients[i] != null && allRecipients[i].equalsIgnoreCase(targetRecipient)) {
                sb.append("ID: " + messageIDs[i]
                        + " | Message: " + allMessages[i] + "\n");
            }
        }
        return sb.length() > 0 ? sb.toString().trim() : "No messages found for recipient: " + targetRecipient;
    }

    public String deleteByMessageHash(String targetHash) {
        for (int i = 0; i < totalTrackedCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equalsIgnoreCase(targetHash)) {
                String deletedText = allMessages[i];
                for (int j = i; j < totalTrackedCount - 1; j++) {
                    messageHashes[j] = messageHashes[j + 1];
                    messageIDs[j] = messageIDs[j + 1];
                    allMessages[j] = allMessages[j + 1];
                    allRecipients[j] = allRecipients[j + 1];
                    allSenders[j] = allSenders[j + 1];
                }
                totalTrackedCount--;
                return "Deleted message: \"" + deletedText + "\"";
            }
        }
        return "No message found with hash: " + targetHash;
    }

    public String displaySentReport() {
        if (sentCount == 0) {
            return "No sent messages to report.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentCount; i++) {
            sb.append((i + 1) + ") ID: " + sentIDs[i]
                    + " | Hash: " + sentHashes[i]
                    + " | To: " + sentRecipients[i]
                    + " | Msg: " + sentMessages[i] + "\n");
        }
        return sb.toString().trim();
    }
} 
}


