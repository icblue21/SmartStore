package exception;

public class InputEmptyException extends RuntimeException {

    public InputEmptyException(){
        super("입력이 되지 않았습니다. 다시 입력해주세요");
    }

    public InputEmptyException(String message){
        super(message);
    }
}
