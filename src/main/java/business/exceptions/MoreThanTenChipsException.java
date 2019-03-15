package main.java.business.exceptions;

public class MoreThanTenChipsException extends Exception {
    public static String msg = "You can't have more than 10 chips in your wallet";
    public MoreThanTenChipsException(){
        super(msg);
    }

    public MoreThanTenChipsException(String errorMsg){
        super(errorMsg);
    }
}
