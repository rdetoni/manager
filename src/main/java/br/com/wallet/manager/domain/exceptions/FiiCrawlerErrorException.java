package br.com.wallet.manager.domain.exceptions;

public class FiiCrawlerErrorException extends Exception{
    public FiiCrawlerErrorException(String message){
        super(message);
    }
}
