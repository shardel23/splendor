package main.java.business.Exceptions;

public class CantPayCardPriceException extends Exception {
    public static String msg = "You can't buy this card yet.";
    public CantPayCardPriceException(){
        super(msg);
    }

    public CantPayCardPriceException(String errorMsg){
        super(errorMsg);
    }
}
