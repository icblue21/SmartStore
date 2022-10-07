package exceptions;

public class InputFormatException extends RuntimeException{

    public InputFormatException(){
        super("입력값의 형식이 유효하지 않습니다. 다시 입력해주세요");
    }

    public InputFormatException(String message){
        super(message);
    }
}
