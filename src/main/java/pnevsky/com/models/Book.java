package pnevsky.com.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty (message = "The field should not be empty")
    @Size(max = 100, message = "The title should not be more than 100 characters" )
    private String title;

    @NotEmpty (message = "The field should not be empty")
    @Size(max = 100, message = "The title should not be more than 100 characters" )
    private String author;

    @Min(value = 1500, message = "The year should be more than 1500")
    private int year;

    public Book() { }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "id " + id + ", " + title + ", " + author + ", " + year;
    }
}
