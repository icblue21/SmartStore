package exceptions;

public class InputEmptyException extends RuntimeException {

    public InputEmptyException(){
        super("입력값이 유효하지 않습니다. 다시 입력해주세요");
    }

    public InputEmptyException(String message){
        super(message);
    }
}
