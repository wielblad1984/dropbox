package com.dropbox.mail;

import com.dropbox.config.ConfigService;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.dropbox.config.Keys.*;

public class EmailService {


    private final ConfigService cfg;
    private final EmailClient client;

    public EmailService(ConfigService cfg) {
        this.cfg = cfg;
        this.client = new ClientProvider(cfg).create(cfg);
    }

    public void sendMail(String name) throws IOException, MailjetSocketTimeoutException, MailjetException, TimeoutException {
        String content = cfg.get((EMAIL_CONTENT)) + ", " + name;
        client.send(new Email(cfg.get(EMAIL_TO), cfg.get(EMAIL_SUBJECT), content));
    }
}
