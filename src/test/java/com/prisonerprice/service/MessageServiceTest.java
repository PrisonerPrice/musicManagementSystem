package com.prisonerprice.service;

import com.amazonaws.services.sqs.model.Message;
import com.prisonerprice.init.AppInitializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class MessageServiceTest {

    @Autowired private MessageService messageService;
    @Autowired private Logger logger;
    private String testQueueName;
    private static final String TEST_STRING = "This is the test string!";

    @Before
    public void init(){
        testQueueName = "Ryo-is-really-awesome" + UUID.randomUUID().toString().substring(0, 10);
    }

    @After
    public void tearDown(){

    }

    @Test
    public void createQueueTest(){
        String resultUrl = messageService.createQueue(testQueueName);
        logger.info("The result Url is: " + resultUrl);
        Assert.assertTrue(resultUrl.length() > 10);
    }

    @Test
    public void getQueueUrl(){
        messageService.createQueue(testQueueName);
        String resultUrl = messageService.getQueueUrl(testQueueName);
        logger.info("The result Url is: " + resultUrl);
        Assert.assertTrue(resultUrl.length() > 10);
    }

    @Test
    public void sendMessageAndGetMessagesTest(){
        messageService.createQueue(testQueueName);
        messageService.sendMessage(testQueueName, TEST_STRING);
        List<Message> resultStrings = messageService.getMessages(testQueueName);
        Assert.assertTrue(resultStrings.size() == 1);
    }

    @Test
    public void deleteQueueTest(){

    }
}
