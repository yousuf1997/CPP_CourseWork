package cs5800.softwareEngineering.hw5.model;

import java.util.Iterator;

public interface IterableByUser {
    Iterator<Message> iterator(User userToSearchWith);
}
