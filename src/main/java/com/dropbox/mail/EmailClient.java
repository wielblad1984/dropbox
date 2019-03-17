package com.dropbox.mail;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface EmailClient {

    void send(Email email)throws MailjetSocketTimeoutException, MailjetException, IOException, TimeoutException;
}
