package com.prisonerprice.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import com.prisonerprice.init.AppInitializer;
import com.prisonerprice.util.StringsRes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class MessageServiceMockAWSTest {

    @Autowired
    // @Spy // when use injectMocks, you need to use Spy to inject the logger into injected Mock object
    private Logger logger;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private AmazonSQS amazonSQS;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DeleteQueueResult deleteQueueResult;

    // @InjectMocks
    // private MessageServiceImpl messageService;

    private MessageService messageService;

    private String queueName = "Awesome_queue";
    private String fakeQueueUrl = "www.fakeQueueUrl.com/abc/123/fake";
    private String msg = "This is a message for testing";
    private List<Message> messages;

    @Before
    public void setUp() {
        logger.info(">>>>>>>>>> Start testing...");
        messages = new ArrayList();
        messages.add(new Message());

        //Mocks are initialized before each test method
        MockitoAnnotations.initMocks(this);
        messageService = new MessageServiceImpl(logger, amazonSQS);

        logger.info(StringsRes.LOGGER_PREFIX + "Start testing...");
        when(amazonSQS.getQueueUrl(anyString()).getQueueUrl()).thenReturn(fakeQueueUrl);
        when(amazonSQS.receiveMessage(any(ReceiveMessageRequest.class)).getMessages()).thenReturn(messages);
        when(amazonSQS.createQueue(any(CreateQueueRequest.class)).getQueueUrl()).thenReturn(fakeQueueUrl);
        when(amazonSQS.deleteQueue(anyString())).thenReturn(deleteQueueResult);
    }

    @After
    public void tearDown() {
        logger.info(StringsRes.LOGGER_PREFIX + "End testing...");
    }

    @Test
    public void creatQueueTest() {
        when(amazonSQS.getQueueUrl(anyString()).getQueueUrl()).thenThrow(new QueueDoesNotExistException("The queue doesn't exist."));
        String queueUrl = messageService.createQueue(queueName);
        assertEquals(fakeQueueUrl, queueUrl);
        verify(amazonSQS, timeout(10).times(1)).createQueue(any(CreateQueueRequest.class));
    }

    @Test
    public void getQueueUrlTest() {
        String queueUrl = messageService.getQueueUrl(queueName);
        assertEquals(queueUrl, fakeQueueUrl);

        //Verify the method getQueueUrl is called 2 times
        verify(amazonSQS, times(2)).getQueueUrl(anyString());

        //The logger is spied object, so, the real methods are called,
        //The purpose of spy an object is to assert the result (state verification) returned by a called method
        //or verify the method is called (behavior verification)
        //verify(logger, atLeast(1)).info(anyString());
    }

    @Test
    public void sendMessageTest() {
        messageService.sendMessage(queueName, msg);
        verify(amazonSQS, times(1)).sendMessage(any());
    }

    @Test
    public void deleteMessageTest(){
        messageService.deleteQueue(queueName);
        verify(amazonSQS, times(1)).deleteQueue(anyString());
    }

    @Test
    public void getMessagesTest() {
        List<Message> messages = messageService.getMessages(queueName);
        assertNotNull(messages);
        assertEquals(1, messages.size());

        //verify the method receiveMessage is called one times
        verify(amazonSQS, times(1)).receiveMessage(any(ReceiveMessageRequest.class));
    }
}
