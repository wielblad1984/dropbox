package com.dropbox.mail;

import com.dropbox.config.ConfigService;
import com.dropbox.config.Keys;
import com.dropbox.mail.client.MailJetClient;


public class ClientProvider  {

    private final ConfigService cfg;

    public ClientProvider(ConfigService cfg) {
        this.cfg = cfg;
    }


    public EmailClient create(ConfigService cfg){
        String client=cfg.get(Keys.EMAIL_CLIENT);
        if(client.equals(Keys.MAILJET))
            return new MailJetClient(cfg);
        else throw new RuntimeException("No such a client available");

    }

}

