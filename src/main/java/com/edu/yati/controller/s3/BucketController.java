package com.edu.yati.controller.s3;

import javax.servlet.annotation.MultipartConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.edu.yati.model.Bucket;
import com.edu.yati.service.s3.AmazonClient;

@RestController
@RequestMapping("/api/admin/account/storage")
@MultipartConfig
public class BucketController {
	
	Logger logger = LoggerFactory.getLogger(BucketController.class);
	
	private AmazonClient amazonClient;
	
	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
	    commonsMultipartResolver.setDefaultEncoding("utf-8");
	    commonsMultipartResolver.setMaxUploadSize(50000000);
	    return commonsMultipartResolver;
	}
	
	@Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @RequestMapping(value="/uploadFile/{imageName}", method=RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Bucket> uploadFile(@RequestPart MultipartFile file, @PathVariable("imageName") String imageName) {
    	Bucket bucket = new Bucket();
    	String uploadedFile = this.amazonClient.uploadFile(file);
    	bucket.setUploadedImagesURL(uploadedFile);
    	bucket.setImageName(imageName);
    	logger.debug("uploadedFile " + uploadedFile);
    	return new ResponseEntity<Bucket>(bucket, HttpStatus.OK);
    }

    @RequestMapping(value="/deleteFile", method=RequestMethod.DELETE)
    public ResponseEntity<Bucket> deleteFile(@RequestPart(value = "url") String fileUrl) {
    	Bucket bucket = new Bucket();
    	String deletedFile = this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    	return new ResponseEntity<Bucket>(bucket, HttpStatus.OK);
    }
}
