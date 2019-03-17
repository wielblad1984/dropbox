package com.dropbox.mail.client;

import com.dropbox.config.ConfigService;
import com.dropbox.mail.Email;
import com.dropbox.mail.EmailClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.concurrent.TimeoutException;

import static com.dropbox.config.Keys.*;


public class MailJetClient implements EmailClient {
    private final ConfigService cfg;

    public MailJetClient(ConfigService cfg) {
        this.cfg = cfg;
    }

    @Override
    public void send(Email email) throws MailjetSocketTimeoutException, MailjetException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(System.getenv(cfg.get(MJ_APIKEY_PUBLIC)), System.getenv(cfg.get(MJ_APIKEY_PRIVATE
        )), new ClientOptions("v3.1"));
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", cfg.get(EMAIL_CLIENT))
                                        .put("Name", cfg.get(EMAIL_CLIENT)))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", cfg.get(EMAIL_TO))
                                                .put("Name", cfg.get(EMAIL_TO))
                                                .put(Emailv31.Message.SUBJECT, cfg.get(EMAIL_SUBJECT))
                                                .put(Emailv31.Message.TEXTPART, cfg.get(EMAIL_CONTENT))))));
                                                //.put(Emailv31.Message.HTMLPART, "<h3>Dear passenger 1, welcome to Mailjet!</h3><br />May the delivery force be with you!")))));


        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());


    }


}


