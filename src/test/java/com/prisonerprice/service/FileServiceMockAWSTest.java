package com.prisonerprice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.prisonerprice.init.AppInitializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class FileServiceMockAWSTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS) private AmazonS3 amazonS3;   // allow to use get chains: get().get()...

    @Autowired
    @Spy
    private Logger logger;

    @InjectMocks
    private FileServiceImpl fileService;

    private String bucketName = "my-test-bucket-ryo-is-awesome-49e7603b-0";

    private String fileName = "test.txt";

    private URL fakeFileUrl;

    @Mock
    private MultipartFile multipartFile;

    @Before
    public void setup() throws MalformedURLException, FileNotFoundException, IOException{
        logger.info(">>>>>>>>>> Start testing...");

        //Mocks are initialized before each test method
        MockitoAnnotations.initMocks(this);

        fakeFileUrl = new URL("http://www.fakeQueueUrl.com/abc/123/fake");

        //Stubbing for the methods in the object multipartFile
        when(multipartFile.getOriginalFilename()).thenReturn("anyFileName");
        when(multipartFile.getContentType()).thenReturn("Application");
        when(multipartFile.getSize()).thenReturn(9999L);
        when(multipartFile.getInputStream()).thenReturn(mock(InputStream.class));

        //Stubbing for the methods doesObjectExist and generatePresignedUrl in the object amazonS3
        when(amazonS3.generatePresignedUrl(any())).thenReturn(fakeFileUrl);
    }

    @After
    public void tearDown() {
        logger.info(">>>>>>>>>> End test");
    }

    @Test
    public void uploadFile() throws IOException{
        fileService.uploadFile(bucketName, multipartFile);
        verify(amazonS3, times(1)).doesObjectExist(anyString(), anyString());
        verify(amazonS3, times(1)).putObject(anyString(), anyString(), any(), any());
    }

    @Test
    public void getFileUrl() {
        String fileUrl = fileService.getFileUrl(bucketName, fileName);
        assertEquals(fileUrl, fakeFileUrl.toString());
        verify(amazonS3, times(1)).generatePresignedUrl(any());
    }

    @Test
    public void saveFile() throws IOException, FileNotFoundException {
        //Dummies
        MultipartFile multipartFile = new MockMultipartFile(" ", new byte[1]);
        String path = " ";

        //Annotation @Mock can only be used for calls variables
        //create mocked object fshttps://tomcat.apache.org/download-80.cgi
        FileServiceImpl fs = Mockito.mock(FileServiceImpl.class);

        //Stubbing
        //when(fs.saveFile(any(), anyString())).thenReturn(true);
        doReturn(true).when(fs).saveFile(any(), anyString());

        //Exercise - call method
        //Use dummies as parameters
        boolean isSuccess = fs.saveFile(multipartFile, path);

        //Verify state
        Assert.assertTrue(isSuccess);

        //Verify behavior
        verify(fs, times(1)).saveFile(any(), anyString());
    }

    @Test
    public void createBucketTest(){
        when(amazonS3.createBucket(bucketName + "123")).thenReturn(null);
        fileService.createBucket(bucketName + "123");
        verify(amazonS3, times(1)).createBucket(anyString());
    }

    @Test
    public void deleteFileTest(){
        when(amazonS3.doesObjectExist(anyString(), anyString())).thenReturn(true);
        fileService.deleteFile(bucketName, fileName);
        verify(amazonS3, times(1)).deleteObject(bucketName, fileName);
    }


}
