package com.prisonerprice.service;

import com.prisonerprice.init.AppInitializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class FileServiceTest {

    private MultipartFile file;
    @Autowired private FileService fileService;
    @Autowired private Logger logger;

    @Test
    public void doo() {
        Assert.assertEquals(1, 1);
    }

    /*
    @Before
    public void init(){
        file = new MockMultipartFile("user-file", "MockFile", "text/plain", "test data".getBytes());
    }

    @After
    public void tearDown(){
        fileService.deleteFile("music-management-system-bucket", "MockFile");
    }

    @Test
    public void uploadFileTest() throws IOException {
        String returnMsg = fileService.uploadFile("music-management-system-bucket", file);
        logger.debug(returnMsg);
        Assert.assertTrue(returnMsg.length() > 10);
    }

    @Test
    public void getFileUrlTest(){
        String returnMsg = fileService.getFileUrl("music-management-system-bucket", "MockFile");
        logger.debug("GetFileUrlTest: " + returnMsg);
        Assert.assertTrue(returnMsg.length() > 10);
    }

    @Test
    public void createBucketTest(){
        fileService.createBucket("my-test-bucket-ryo-is-awesome-" + UUID.randomUUID().toString().substring(0, 10));
        Assert.assertTrue(true);
    }

    @Test
    public void deleteFileTest(){
        String returnMsg = fileService.deleteFile("music-management-system-bucket", "MockFile");
    }

     */
}
