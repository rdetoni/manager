package br.com.wallet.manager.controller.advice;

import br.com.wallet.manager.domain.exceptions.AssetNotFoundException;
import br.com.wallet.manager.domain.exceptions.BrapiErrorException;
import br.com.wallet.manager.domain.exceptions.CreateAssetException;
import br.com.wallet.manager.domain.exceptions.FiiCrawlerErrorException;
import br.com.wallet.manager.domain.exceptions.FinnHubErrorException;
import br.com.wallet.manager.domain.exceptions.UpdateAssetException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body("Validation failed: " + errorMessage);
    }

    @ExceptionHandler(FinnHubErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleFinnHubError(FinnHubErrorException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(BrapiErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleBrapiError(BrapiErrorException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(FiiCrawlerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleFiiCrawlerError(FiiCrawlerErrorException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(CreateAssetException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleFiiCrawlerError(CreateAssetException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(UpdateAssetException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUpdateAssetError(UpdateAssetException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(AssetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleFindAssetError(AssetNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
