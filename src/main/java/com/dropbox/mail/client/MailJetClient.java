package com.dropbox.mail.client;

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


public class MailJetClient implements EmailClient {

    @Override
    public void send(com.dropbox.mail.Email email) {


        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response ;
        client = new MailjetClient(System.getenv("ac6e5c18a2fe79e53c45cfe589a89318 "), System.getenv("7c78e8a0928fc42d8d4a2a228e076bae "), new ClientOptions("v3.1"));
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "1b7edceb6e@mailboxy.fun")
                                        .put("Name", "Mailjet Pilot"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", "pjakubczyk1984@gmail.com")
                                                .put("Name", "passenger 1")))
                                .put(Emailv31.Message.SUBJECT, "Your email flight plan!")
                                .put(Emailv31.Message.TEXTPART, "Dear passenger 1, welcome to Mailjet! May the delivery force be with you!")
                                .put(Emailv31.Message.HTMLPART, "<h3>Dear passenger 1, welcome to Mailjet!</h3><br />May the delivery force be with you!")));
        try {
            response = client.post(request);
            System.out.println(response.getStatus());
            System.out.println(response.getData());
        } catch (MailjetException e) {
            e.printStackTrace();
        } catch (MailjetSocketTimeoutException e) {
            e.printStackTrace();
        }

    }
}


