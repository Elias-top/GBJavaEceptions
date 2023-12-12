package exceptions;


public class MyDataFormatException extends IllegalAccessError{
    public MyDataFormatException(){
        super("No date types in user data");
    }
}
