package com.dropbox;

import com.dropbox.config.ConfigService;
import com.dropbox.listener.DirectoryListener;
import com.dropbox.mail.Email;
import com.dropbox.mail.EmailService;
import com.dropbox.mail.client.MailJetClient;
import com.dropbox.upload.DropBoxUploader;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

import java.io.IOException;

import static com.dropbox.config.Keys.*;


public class Main {
    private static final int PROPERTIES_INDEX = 0;


    public static void main(String[] args) throws IOException, MailjetSocketTimeoutException, MailjetException {


    ConfigService cfg=  new ConfigService(args[PROPERTIES_INDEX]);
    cfg.load();
    //DropBoxUploader dropBoxUploader=new DropBoxUploader(cfg);
   // new DirectoryListener(cfg.get(DIRECTORY),dropBoxUploader).listen();
    Email email=new Email(EMAIL_TO,EMAIL_SUBJECT,EMAIL_CONTENT);
    MailJetClient mailJetClient=new MailJetClient(cfg);
    mailJetClient.send(email);







    }


}

