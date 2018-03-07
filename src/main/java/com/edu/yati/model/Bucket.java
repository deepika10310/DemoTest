package com.edu.yati.model;

import java.io.Serializable;

public class Bucket implements Serializable {
	private static final long serialVersionUID = -9154895202407419290L;
	private String imageName;
	private String uploadedImagesURL;

	public String getUploadedImagesURL() {
		return uploadedImagesURL;
	}

	public void setUploadedImagesURL(String uploadedImagesURL) {
		this.uploadedImagesURL = uploadedImagesURL;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "Bucket [imageName=" + imageName + ", uploadedImagesURL=" + uploadedImagesURL + "]";
	}
}
