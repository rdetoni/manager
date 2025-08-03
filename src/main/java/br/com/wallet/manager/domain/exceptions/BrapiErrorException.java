package br.com.wallet.manager.domain.exceptions;

public class BrapiErrorException extends Exception{
    public BrapiErrorException(String message){
        super(message);
    }
}
