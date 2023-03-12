package cs5800.softwareEngineering.hw1.model.q3;

public class Course {

    private String courseName;
    private Instructor instructor;
    private TextBook textBook;

    public Course(String courseName, Instructor instructor, TextBook textBook) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.textBook = textBook;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public TextBook getTextBook() {
        return textBook;
    }

    public void setTextBook(TextBook textBook) {
        this.textBook = textBook;
    }

    public void print(){
        System.out.println("Course Name : " + this.courseName);
        System.out.println("Instructor : " + this.instructor.getFirstName() + ", " + this.instructor.getLastName());
        System.out.println("TextBook Title : " + this.textBook.getTitle());
        System.out.println("TextBook Author : " + this.textBook.getAuthor());
    }
}
