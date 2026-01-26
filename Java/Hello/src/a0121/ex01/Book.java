package a0121.ex01;

public class Book {
    String title; //도서제목
    String author; //저자
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    @Override
    public String toString() {
        return "Book {title=" + title + ", author=" + author + "}";
    }

    
    
}
