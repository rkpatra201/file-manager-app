package com.file.app.drive;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

@Service
public class DriveOperations {

	public static String rootFolderId = null;
	private String fields = "nextPageToken, files(id, parents, name, createdTime)";

	@Autowired
	private Drive drive;

	@PostConstruct
	public void createRootFolder() throws IOException, InterruptedException {
		setRootFolderIdFromFile();
		File root = getFileDetails(rootFolderId);
		if (root == null || root.getTrashed() == true) {
			File file = createFolder("root", null);
			setRootFolderId(file.getId());
			writeToFile();
		}
	}

	public File createChildFolder(String folderName) throws IOException {
		return createFolder(folderName, rootFolderId);
	}

	public File createFile(String parentFolderId, MultipartFile multipartFile) throws IOException {

		File fileMetadata = new File();
		fileMetadata.setName(multipartFile.getOriginalFilename());
		if (parentFolderId == null)
			parentFolderId = rootFolderId;
		fileMetadata.setParents(Collections.singletonList(parentFolderId));
		File file = drive.files().create(fileMetadata, new ByteArrayContent("", multipartFile.getBytes()))
				.setFields("id, parents, name, createdTime").execute();
		return file;
	}

	public File createFolder(String folderName, String parentFolderId) throws IOException {
		File fileMetadata = new File();
		fileMetadata.setName(folderName);
		fileMetadata.setMimeType("application/vnd.google-apps.folder");

		if (parentFolderId != null)
			fileMetadata.setParents(Collections.singletonList(parentFolderId));
		File file = drive.files().create(fileMetadata).setFields("id, parents, name, createdTime").execute();
		return file;
	}

	public File getFileDetails(String fileId) throws IOException {
		if (fileId == null)
			return null;
		return drive.files().get(fileId).setFields("id, trashed, parents, name, createdTime").execute();
	}

	private void setRootFolderId(String folderId) throws IOException {
		rootFolderId = folderId;
	}

	public void deleteFile(String fileId) throws IOException {
		drive.files().delete(fileId).execute();
	}

	public void downloadFile(String fileId, OutputStream os) throws IOException {
		drive.files().get(fileId).executeMediaAndDownloadTo(os);
	}

	public final List<File> getGoogleSubFolders(String googleFolderIdParent) throws IOException {

		String pageToken = null;
		List<File> list = new ArrayList<File>();

		String query = null;
		if (googleFolderIdParent == null) {
			query = " mimeType = 'application/vnd.google-apps.folder' " //
					+ " and 'root' in parents";
		} else {
			query = "'" + googleFolderIdParent + "' in parents";
		}

		do {
			FileList result = drive.files().list().setQ(query).setSpaces("drive") //
					// Fields will be assigned values: id, name, createdTime
					.setFields(fields)//
					.setPageToken(pageToken).execute();
			for (File file : result.getFiles()) {
				list.add(file);
			}
			pageToken = result.getNextPageToken();
		} while (pageToken != null);
		return list;
	}

	public void writeToFile() throws IOException {
		FileWriter writer = new FileWriter(new java.io.File("root.txt"));
		writer.write(rootFolderId);
		writer.close();
	}

	public void setRootFolderIdFromFile() throws IOException {
		java.io.File f = new java.io.File("root.txt");
		if (f.exists() == false)
			return;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(new java.io.File("root.txt")));

		BufferedReader br = new BufferedReader(isr);

		try {
			String line = null;

			while ((line = br.readLine()) != null) {
				rootFolderId = line;
				break;
			}
		} finally {
			if (isr != null)
				isr.close();
			if (br != null)
				br.close();
		}

	}

}
