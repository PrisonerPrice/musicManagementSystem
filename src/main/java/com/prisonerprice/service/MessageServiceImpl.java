package com.prisonerprice.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MessageServiceImpl implements MessageService{

    private Logger logger;
    private AmazonSQS amazonSQS;

    @Autowired
    public MessageServiceImpl(Logger logger, AmazonSQS amazonSQS) {
        this.logger = logger;
        this.amazonSQS = amazonSQS;
    }

    @Override
    public String createQueue(String queueName) {
        String queueUrl;
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        queueUrl = amazonSQS.createQueue(createQueueRequest).getQueueUrl();
        logger.info(">>>>>> Queue " + queueName + " URL is: " + queueUrl);
        return queueUrl;
    }

    @Override
    public String getQueueUrl (String queueName) throws QueueDoesNotExistException{

        String queueUrl = null;
        logger.info(">>>>> " + queueName);
        GetQueueUrlResult getQueueUrlResult = amazonSQS.getQueueUrl(queueName);
        queueUrl = getQueueUrlResult.getQueueUrl();
        logger.info(">>>>>> Queue " + queueName + " URL is: " + queueUrl);
        return queueUrl;
    }

    @Override
    public void sendMessage(String queueName, String message) {
        Map<String, MessageAttributeValue> messageAttributes = new HashMap();
        MessageAttributeValue messageAttributeValue = new MessageAttributeValue();
        messageAttributeValue.withDataType("String").withStringValue("File URL in S3");
        messageAttributes.put("message", messageAttributeValue);
        String queueUrl = getQueueUrl(queueName);
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.withQueueUrl(queueUrl)
                .withMessageBody(message)
                .withMessageAttributes(messageAttributes);
        amazonSQS.sendMessage(sendMessageRequest);
    }

    @Override
    public void deleteQueue(String queueName) throws QueueDoesNotExistException {
        DeleteQueueResult deleteQueueRequest = amazonSQS.deleteQueue(queueName);
        logger.info("Delete result isL " + deleteQueueRequest.toString());
    }

    @Override
    public List<Message> getMessages(String queueName) {
        String queueUrl = getQueueUrl(queueName);
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
        return messages;
    }
}
