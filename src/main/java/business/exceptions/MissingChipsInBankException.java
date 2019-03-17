package main.java.business.exceptions;

public class MissingChipsInBankException extends Exception {
    public static String msg = "The desired chips are not available at b right now.";
    public MissingChipsInBankException(){
        super(msg);
    }

    public MissingChipsInBankException(String errorMsg){
        super(errorMsg);
    }
}
