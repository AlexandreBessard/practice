package objectOrientedProgramming.composition;

import java.util.List;

public class Library {
    final List<Book> books;

    Library(List<Book> books) {
        this.books = books;
    }
    public List<Book> getListsOfBooksInLibrary() {
        return this.books;
    }
}
