package com.dropbox.mail;

public class Email {
    private String subject;
    private String content;
    private String to;

    public Email(String subject, String content, String to) {
        this.subject = subject;
        this.content = content;
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getTo() {
        return to;
    }
}
