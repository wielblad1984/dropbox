package com.dropbox.mail;

public class Email {
    private String adress;
    private String subject;
    private String content;


    public Email(String adress, String subject, String content) {
        this.adress = adress;
        this.content = content;
        this.subject= subject;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getAdress() {
        return adress;
    }
}
