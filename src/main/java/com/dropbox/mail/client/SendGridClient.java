package com.dropbox.mail.client;


import com.dropbox.config.ConfigService;
import com.dropbox.config.Keys;
import com.sendgrid.*;
import com.dropbox.mail.EmailClient;

import java.io.IOException;

public class SendGridClient implements EmailClient {
    private final ConfigService cfg;

    public SendGridClient(ConfigService cfg) {
        this.cfg = cfg;
    }

    @Override
    public void send(com.dropbox.mail.Email email) {
        Email from = new Email(Keys.EMAIL_FROM);
        Email to = new Email(Keys.EMAIL_TO);
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, email.getSubject(), to, content);

        SendGrid sg = new SendGrid(cfg.get(Keys.SENDGRID_KEY));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
           throw new SendingEmailException("Could not send email to " + email.getTo(),ex);
        }
    }

    }

