package main.java.business.Exceptions;

public class EmptyDeckException extends Exception {
    public static String msg = "Deck of cards is Empty";
    public EmptyDeckException(){
        super(msg);
    }

    public EmptyDeckException(String errorMsg){
        super(errorMsg);
    }
}
