package exceptions;

public class PhoneFormatException extends IllegalArgumentException{
    public PhoneFormatException(){
        super("Phone is not correct for Belarus!!!");
    }
    
}
