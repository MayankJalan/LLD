package libraryManagementSystem;

public class Book {
    private final String isbn;
    private final String name;
    private final String author;
    private final int publicationYear;
    private boolean available;


    public Book(String isbn, String name, String author,int publicationYear) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.publicationYear=publicationYear;
        available=true;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
