package se.yrgo.cropservice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import se.yrgo.cropservice.exceptions.CropNotFoundException;

import java.util.Map;

@RestControllerAdvice
public class CropExceptionHandler {

    @ExceptionHandler(CropNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCropNotFound(CropNotFoundException ex) {
        return Map.of(
                "error", ex.getMessage(),
                "type", "RESOURCE_NOT_FOUND"
        );
    }
}
