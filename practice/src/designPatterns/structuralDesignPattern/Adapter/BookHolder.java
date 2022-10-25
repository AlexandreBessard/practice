package designPatterns.structuralDesignPattern.Adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BookHolder extends LibraryDetails implements LibraryCard {

    public void giveLibraryDetails(){
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the Library account holder name :");
            String readername=br.readLine();
            System.out.print("n");
            System.out.print("Enter the account number of the library:");
            long accno=Long.parseLong(br.readLine());
            System.out.print("n");
            System.out.print("Enter the Library name :");
            String libraryname=br.readLine();
            setAccHolderName(readername);
            setAccNumber(accno);
            setLibraryName(libraryname);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public String getLibraryCard() {
        long accno=getAccNumber();
        String accholdername=getAccHolderName();
        String lname=getLibraryName();
        return ("The Account number "+accno+" of "+accholdername+" in "+lname+ " Library is valid and authenticated for issuing the Library card. ");
    }

}
