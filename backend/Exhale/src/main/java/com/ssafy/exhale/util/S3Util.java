package com.ssafy.exhale.util;

import com.amazonaws.services.s3.AmazonS3;
import com.ssafy.exhale.exception.handler.S3Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Util {

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    private final AmazonS3 s3Client;

    private String createPath(String fullFileName) {
        return "https://" + bucketName + ".s3.amazonaws.com/" + fullFileName;
    }

    public String saveImage(MultipartFile multipartFile) {
        System.out.println(multipartFile.getOriginalFilename());
        String extension = (multipartFile.getOriginalFilename().split("\\."))[1];
        String newFileName = UUID.randomUUID().toString();
        String fullFileName = newFileName + '.' + extension;

        try {
            File file = new File(multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            s3Client.putObject(bucketName, fullFileName, file);
        } catch (Exception exception) {
            throw new S3Exception(exception);
        }
        return createPath(fullFileName);
    }

    public void deleteImage(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
    }
}
