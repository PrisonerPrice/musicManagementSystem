/*
 *  Copyright 2019, Liwei Wang <daveywang@live.com>.
 *  All rights reserved.
 *  Author: Liwei Wang
 *  Date: 06/2019
 */

package com.prisonerprice.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface FileService {
    String uploadFile(String bucketName, MultipartFile file) throws IOException;    // return uploadUrl
    String getFileUrl(String bucketName, String fileName);
    void createBucket(String bucketName);
    boolean saveFile(MultipartFile multipartFile, String filePath);
    String deleteFile(String bucketName, String key);
}
