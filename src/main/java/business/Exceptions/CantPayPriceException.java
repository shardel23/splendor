package main.java.business.Exceptions;

public class CantPayPriceException extends Exception {
    public static String msg = "You don't have enough chips in your hand in order to pay the price";

    public CantPayPriceException(){
        super(msg);
    }

    public CantPayPriceException(String errorMsg){
        super(errorMsg);
    }
}
