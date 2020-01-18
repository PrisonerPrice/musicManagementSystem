package com.prisonerprice.controller;

import com.prisonerprice.model.User;
import com.prisonerprice.service.FileService;
import com.prisonerprice.service.MessageService;
import com.prisonerprice.service.UserService;
import com.prisonerprice.util.StringsRes;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = {"/files"})
public class FileController {

    private String queueName = "Ryo-is-really-awesome1d900f88-7";
    private String fileDownloadDir = "/Users/hangbozhang/IdeaProjects/music_management";
    private Logger logger;
    private FileService fileService;
    private MessageService messageService;
    private UserService userService;

    @Autowired
    public FileController(Logger logger, FileService fileService, MessageService messageService, UserService userService) {
        this.logger = logger;
        this.fileService = fileService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @RequestMapping(value = "/{bucketName}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@PathVariable String bucketName, @RequestParam("file") MultipartFile file) {
        String msg = String.format("The file name=%s, size=%d could not be uploaded.", file.getOriginalFilename(), file.getSize());
        ResponseEntity responseEntity = ResponseEntity.status(HttpServletResponse.SC_NOT_ACCEPTABLE).body(msg);

        try {
            String path = System.getProperty("user.dir") + File.separator + "temp";
            fileService.saveFile(file, path);   // save file to local dir, not related to AWS
            String url = fileService.uploadFile(bucketName, file);

            if (url != null) {
                msg = String.format("The file name=%s, size=%d was uploaded, url=%s", file.getOriginalFilename(), file.getSize(), url);
                messageService.sendMessage(queueName, url);
                responseEntity = ResponseEntity.status(HttpServletResponse.SC_OK).body(msg);
            }

            logger.info(msg);
        }
        catch (Exception e) {
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_NOT_ACCEPTABLE).body(e.getMessage());
            logger.error(e.getMessage());
        }

        return responseEntity;
    }

    @RequestMapping(value = "/{fileName}", method = RequestMethod.GET, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> downloadFile(@PathVariable String fileName, HttpServletRequest httpServletRequest) {
        // request is not used
        Resource resource = null;
        String msg = "The file doesn't exist.";
        ResponseEntity responseEntity;

        // TODO: get User email
        User u = userService.getUserById(httpServletRequest.getAttribute(StringsRes.USER_EMAIL_TAG).toString());
        String email = u.getEmail();

        logger.info(email);

        try {
            Path filePath = Paths.get(fileDownloadDir).toAbsolutePath().resolve(fileName).normalize();
            resource = new UrlResource(filePath.toUri());
            if(!resource.exists()) return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(msg);
            responseEntity = ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

            //Send message to SQS
            msg = String.format("The file %s was downloaded by user %s", resource.getFilename(), email);

            messageService.sendMessage(queueName, msg);
            logger.debug(msg);

        }
        catch (Exception ex) {
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(ex.getMessage());
            logger.debug(ex.getMessage());
        }

        return responseEntity;
    }
}
