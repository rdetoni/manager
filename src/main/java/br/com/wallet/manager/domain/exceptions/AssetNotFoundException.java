package br.com.wallet.manager.domain.exceptions;

public class AssetNotFoundException extends Exception {
    public AssetNotFoundException(String message) {
        super(message);
    }
}
