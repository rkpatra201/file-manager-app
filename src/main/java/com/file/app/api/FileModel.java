package com.file.app.api;

import org.springframework.web.multipart.MultipartFile;

public class FileModel {

	private String fileId;
	private MultipartFile multipartFile;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

}
