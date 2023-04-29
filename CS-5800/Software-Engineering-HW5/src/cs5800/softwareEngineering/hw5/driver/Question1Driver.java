package cs5800.softwareEngineering.hw5.driver;

import cs5800.softwareEngineering.hw5.model.ChatServer;
import cs5800.softwareEngineering.hw5.model.Message;
import cs5800.softwareEngineering.hw5.model.User;
import java.util.Arrays;
import java.util.Iterator;

public class Question1Driver {
    public static void main(String[] args) {

        ChatServer mediator = new ChatServer();

        User user1 = new User("John Doe", "jdoe", mediator);
        User user2 = new User("Jane Smith", "jsmith", mediator);
        User user3 = new User("Jacob Benard", "jbernard1", mediator);
        User user4 = new User("Emily Wong", "ewong", mediator);
        User user5 = new User("Sam Patel", "spatel", mediator);

        // register all the users
        mediator.registerUser(user1);
        mediator.registerUser(user2);
        mediator.registerUser(user3);
        mediator.registerUser(user4);
        mediator.registerUser(user5);

        System.out.println("");

        // user two will send message to user3,user5
        user2.sendMessage("Hello! Jacob, and Patel!", Arrays.asList(user3, user5));
        // user2 undo the message
        user2.undoMessage();

        System.out.println("");

        // user1 blocks user 5 from sending messages
        user1.blockUser(user5);
        // user 5 sending message to user 1 and 3
        user5.sendMessage("Hello! Jhon, and Jacob!", Arrays.asList(user1, user3));
        // user 5 sending message to user 2, 4
        user5.sendMessage("Hello! Jane, and Emily!", Arrays.asList(user2, user4));

        // user 2 sends message to user 5
        user2.sendMessage("Hey! Patel!, how are you?", Arrays.asList(user5));

        System.out.println("");

        // to view chat history of user 5
        System.out.println("Chat History of User 5..");
        mediator.getUserChatHistory(user5.getUserId()).forEach(message -> {
            String[] userList = {""};
            message.getRecipients().forEach(user -> userList[0] = userList[0] + user.getUserName() + ",");
            System.out.println("Message: " + message.getMessageContent() + " , Recipents : " + userList[0]);
        });

        System.out.println("");
        System.out.println("Printing all the message sent and received by the user 5 (Sam Patel)");
        Iterator<Message> messageIterator = user5.iterator();
        while(messageIterator.hasNext()) {
            System.out.println("(User5) Iterator >> " + messageIterator.next().getMessageContent());
        }

    }

}
