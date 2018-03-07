package com.edu.yati.service.s3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AmazonClient {

    private AmazonS3 s3client;

   // @Autowired
   // private S3Configuration s3Configuration;
    
    @PostConstruct
    private void initializeAmazon() {
       //AWSCredentials credentials = new BasicAWSCredentials(s3Configuration.getAccessKey(), s3Configuration.getSecretKey());
    	AWSCredentials credentials = new BasicAWSCredentials("AKIAIWKWQCTYV5FQEXAA", "Xc5pUafsromrg2OY3QYkJawMMNsmVl7E0VwX6KMV");
       this.s3client = new AmazonS3Client(credentials);
    }
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
    private void uploadFileTos3bucket(String fileName, File file) {
        /*s3client.putObject(new PutObjectRequest(s3Configuration.getBucketName(), fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));*/
        s3client.putObject(new PutObjectRequest("testproblemsupload", fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        
    }
    public String uploadFile(MultipartFile multipartFile) {

        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            //fileUrl = s3Configuration.getEndpointUrl() + "/" + s3Configuration.getBucketName() + "/" + fileName;
            fileUrl = "http://s3.ap-south-1.amazonaws.com" + "/" + "testproblemsupload" + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fileUrl;
    }
    
    public String deleteFileFromS3Bucket(String fileUrl) {
        /*String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        //s3client.deleteObject(new DeleteObjectRequest(s3Configuration.getBucketName() + "/", fileName));
        s3client.deleteObject(new DeleteObjectRequest("testproblemsupload" + "/", fileName));
        return "Successfully deleted";*/
    	return "NOT IMPLEMENTED";
    }
    
}