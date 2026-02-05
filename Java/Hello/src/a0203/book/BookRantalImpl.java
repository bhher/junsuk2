package a0203.book;

public class BookRantalImpl {
    public static void main(String[] args) {
        //도서대여시스템 객체로 만든후 시작
        BookService bookService = new BookService();
        bookService.start();
    }
}
