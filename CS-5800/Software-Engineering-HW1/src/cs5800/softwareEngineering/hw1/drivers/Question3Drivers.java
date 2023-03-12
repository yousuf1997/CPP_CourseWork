package cs5800.softwareEngineering.hw1.drivers;

import cs5800.softwareEngineering.hw1.model.q3.Course;
import cs5800.softwareEngineering.hw1.model.q3.Course2;
import cs5800.softwareEngineering.hw1.model.q3.Instructor;
import cs5800.softwareEngineering.hw1.model.q3.TextBook;

/**
 * This file consists drivers for Question 3
 */
public class Question3Drivers {

    public static void main(String[] args) {

        Instructor instructor = new Instructor("Nima", "Davarpanah", "office 3-2636");
        TextBook textBook = new TextBook("Clean Code", "John Patirck", "Peterson");
        Course course = new Course("Software Engineering", instructor, textBook);

        course.print();



        /// Modified course object
        Instructor instructor1 = new Instructor("Kay", "Zemodeh", "CS 510");
        Instructor instructor2 = new Instructor("Owen", "Murphy", "CS 225");

        TextBook textBook1 = new TextBook("Kyle Rodriguez", "Intro to Algorithms", "Boston");
        TextBook textBook2 = new TextBook("Garcia Ortega", "Algorthim Expert", "Peterson");

        Course2 course2 = new Course2("Algorithm Analysis");

        try{
            course2.addInstructor(instructor1);
            course2.addInstructor(instructor2);

            course2.addTextBook(textBook1);
            course2.addTextBook(textBook2);

            course2.print();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
