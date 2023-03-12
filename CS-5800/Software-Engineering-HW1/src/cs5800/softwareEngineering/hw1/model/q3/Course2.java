package cs5800.softwareEngineering.hw1.model.q3;

import java.util.ArrayList;
import java.util.List;

public class Course2 {

    private String courseName;
    private List<Instructor> instructors;
    private List<TextBook> textBooks;

    public Course2(String courseName) {
        instructors = new ArrayList<>();
        textBooks = new ArrayList<>();
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void addInstructor(Instructor instructor) throws Exception {
        if (instructors.size() == 2){
            throw new Exception("No more instructors allowed, max 2!");
        }
        if(instructor == null){
            throw new Exception("Null value is being passed");
        }
        instructors.add(instructor);
    }

    public void addTextBook(TextBook textBook) throws Exception {
        if (textBooks.size() == 2){
            throw new Exception("No more textbooks allowed, max 2!");
        }
        if (textBook == null){
            throw new Exception("Null value is being passed");
        }
        textBooks.add(textBook);
    }

    public void updateTextBookByIndex(TextBook textBook, int indexToBeUpdated) throws Exception {
        if (textBooks.isEmpty()){
            throw new Exception("List is empty, add it please!");
        }
        if (indexToBeUpdated >= textBooks.size()){
            throw new Exception("Invalid index!");
        }
        textBooks.get(indexToBeUpdated).setAuthor(textBook.getAuthor());
        textBooks.get(indexToBeUpdated).setTitle(textBook.getTitle());
        textBooks.get(indexToBeUpdated).setPublisher(textBook.getPublisher());
    }

    public void updateInstructorByIndex(Instructor instructor, int indexToBeUpdated) throws Exception {
        if (instructors.isEmpty()){
            throw new Exception("List is empty, add it please!");
        }
        if (indexToBeUpdated >= instructors.size()){
            throw new Exception("Invalid index!");
        }
        instructors.get(indexToBeUpdated).setFirstName(instructor.getFirstName());
        instructors.get(indexToBeUpdated).setLastName(instructor.getLastName());
        instructors.get(indexToBeUpdated).setOfficeNumber(instructor.getOfficeNumber());
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public List<TextBook> getTextBooks() {
        return textBooks;
    }

    public void setTextBooks(List<TextBook> textBooks) {
        this.textBooks = textBooks;
    }


    public void print(){
        System.out.println("Course Name : " + this.courseName);

        instructors.forEach(instructor -> System.out.println("Instructor : " + instructor.getFirstName() + ", " + instructor.getLastName()));
        textBooks.forEach(textBook -> {
            System.out.println("TextBook Title : " + textBook.getTitle());
            System.out.println("TextBook Author : " + textBook.getAuthor());
        });
    }
}
