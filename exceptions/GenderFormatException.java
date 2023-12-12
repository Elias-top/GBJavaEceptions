package exceptions;

public class GenderFormatException extends IllegalArgumentException{
    public GenderFormatException(){
        super("Illegal gender type. You should write 'f' or 'm' ");
    }
}
