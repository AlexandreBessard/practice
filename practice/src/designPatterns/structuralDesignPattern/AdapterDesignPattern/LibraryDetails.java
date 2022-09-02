package designPatterns.structuralDesignPattern.AdapterDesignPattern;

public class LibraryDetails {
    private String LibraryName;
    private String BookHolderName;
    private long BookHolderID;

    public String getLibraryName() {
        return LibraryName;
    }

    public void setLibraryName(String LibraryName) {
        this.LibraryName = LibraryName;
    }

    public String getAccHolderName() {
        return BookHolderName;
    }

    public void setAccHolderName(String BookHolderName) {
        this.BookHolderName = BookHolderName;
    }

    public long getAccNumber() {
        return BookHolderID;
    }

    public void setAccNumber(long BookHolderID) {
        this.BookHolderID = BookHolderID;
    }
}
