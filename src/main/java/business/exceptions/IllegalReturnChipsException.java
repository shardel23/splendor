package main.java.business.exceptions;

public class IllegalReturnChipsException extends Exception {
    public static String msg = "You Tried to return illegal amount of chips to bank.";
    public IllegalReturnChipsException(){
        super(msg);
    }

    public IllegalReturnChipsException(String errorMsg){
        super(errorMsg);
    }
}
