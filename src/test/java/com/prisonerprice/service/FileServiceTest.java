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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class FileServiceTest {

    private MultipartFile file;
    @Autowired private FileService fileService;
    @Autowired private Logger logger;

    @Before
    public void init(){
        file = new MultipartFile() {
            @Override
            public String getName() {
                return "file";
            }

            @Override
            public String getOriginalFilename() {
                return "file";
            }

            @Override
            public String getContentType() {
                return "text";
            }

            @Override
            public boolean isEmpty() {
                return true;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {

            }
        };
    }

    @After
    public void tearDown(){

    }

    @Test
    public void uploadFileTest() throws IOException {
        String returnMsg = fileService.uploadFile("Music_project_bucket", file);
        logger.debug(returnMsg);
        Assert.assertTrue(returnMsg.length() > 10);
    }
}
