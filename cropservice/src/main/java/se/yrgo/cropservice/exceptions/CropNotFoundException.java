package se.yrgo.cropservice.exceptions;

public class CropNotFoundException extends RuntimeException {
    public CropNotFoundException(String s) {
        super(s);
    }
}
