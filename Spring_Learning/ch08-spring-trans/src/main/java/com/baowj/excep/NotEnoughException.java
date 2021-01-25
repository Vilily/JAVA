package com.baowj.excep;

public class NotEnoughException extends RuntimeException{
    public NotEnoughException(){
        super();
    }

    public NotEnoughException(String message){
        super(message);
    }
}
