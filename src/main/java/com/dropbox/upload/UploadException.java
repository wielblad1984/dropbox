package com.dropbox.upload;

public class UploadException extends RuntimeException {

    public UploadException(String message, Throwable cause){

        super(message,cause);
    }
}
