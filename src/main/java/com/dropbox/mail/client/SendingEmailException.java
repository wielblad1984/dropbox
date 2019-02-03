package com.dropbox.mail.client;

public class SendingEmailException extends RuntimeException {
    public SendingEmailException(String message, Throwable couse){
        super(message, couse);
    }

}
