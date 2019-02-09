package main.java.business.Exceptions;

public class NotImplementedException extends Exception {
    public static String msg = "This function is not implemented! Must implement it first!";
    public NotImplementedException(){
        super(msg);
    }

    public NotImplementedException(String errorMsg){
        super(errorMsg);
    }
}
