package objectOrientedProgramming.composition;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Book b1 = new Book("EffectiveJ Java", "Joshua Bloch");
        Book b2 = new Book("Thinking in Java", "Bruce Eckel");
        List<Book> books = new ArrayList<>(List.of(b1, b2));
        //If the library object is destroyed, then all book are destroyed
        //Library -> Book = Composition
        Library library = new Library(books);
        for(Book book : library.getListsOfBooksInLibrary()) {
            System.out.println(book.title + ", " + book.author);
        }
    }

}
