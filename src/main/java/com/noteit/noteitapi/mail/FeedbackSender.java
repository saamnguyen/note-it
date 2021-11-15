package com.noteit.noteitapi.mail;

public interface FeedbackSender {
    void sendFeedback(String from, String name, String feedBack);
}
