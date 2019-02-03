package com.dropbox.mail;

import com.dropbox.config.ConfigService;
import com.dropbox.config.Keys;
import com.dropbox.mail.client.MailJetClient;
import com.dropbox.mail.client.SendGridClient;

public class ClientProvider  {
    private static final String MAILGET="mailjet";
    private static final String SEBDGRID="sendgrid";


    public EmailClient create(ConfigService cfg){
        String client=cfg.get(Keys.EMAIL_CLIENT);
        if(client.equals(Keys.MAILJET))
            return new MailJetClient();
        else if(client.equals(Keys.SENDGRID))
            return new SendGridClient(cfg);
        else throw new RuntimeException("No such a client available");

    }

}

