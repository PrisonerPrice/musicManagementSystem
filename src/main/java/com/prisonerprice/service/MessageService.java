package com.prisonerprice.service;

import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public interface MessageService {
    String createQueue(String queueName);
    String getQueueUrl(String queueName);
    void sendMessage(String queueName, String message);
    void deleteQueue(String queueName);
    List<Message> getMessages(String queueName);
}
