package com.file.app.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.file.app.drive.DriveOperations;
import com.google.api.services.drive.model.File;

@Service
public class FileService {

	@Autowired
	private DriveOperations driveOperations;

	public File storeFile(FileModel model) throws IOException {
		return driveOperations.createFile(model.getFileId(), model.getMultipartFile());
	}

	public InputStreamResource getFileById(String fileId) throws IOException {
		OutputStream os = new ByteArrayOutputStream();
		driveOperations.downloadFile(fileId, os);
		ByteArrayOutputStream buffer = (ByteArrayOutputStream) os;
		byte[] bytes = buffer.toByteArray();
		InputStream inputStream = new ByteArrayInputStream(bytes);
		return new InputStreamResource(inputStream);
	}

	public List<File> getFiles(String fileId) throws IOException {
		return driveOperations.getGoogleSubFolders(fileId);
	}

	public File getFileDetails(String fileId) throws IOException {
		return driveOperations.getFileDetails(fileId);
	}

	public File deleteFile(String fileId) throws IOException {
		driveOperations.deleteFile(fileId);
		return getFileDetails(fileId);
	}
	
	public File createFolderUnderRoot(String folderName) throws IOException
	{
		return driveOperations.createChildFolder(folderName);
	}
	
	public File createChildFolder(String folderName,String folderId) throws IOException
	{
		if(folderId==null)
			return createFolderUnderRoot(folderName);
		return driveOperations.createFolder(folderName, folderId);
	}
}