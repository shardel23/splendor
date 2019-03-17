package main.java.business.exceptions;

public class IllegalTakeChipsException extends Exception {
    public static String msg = "You Tried to take illegal combination of chips from the b.";
    public IllegalTakeChipsException(){
        super(msg);
    }

    public IllegalTakeChipsException(String errorMsg){
        super(errorMsg);
    }
}
