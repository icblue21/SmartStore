package exceptions;

public class InputRangeException extends RuntimeException{

    public InputRangeException(){
        super("입력값의 범위가 유효하지 않습니다. 다시 입력해주세요");
    }

    public InputRangeException(String message){
        super(message);
    }

}
