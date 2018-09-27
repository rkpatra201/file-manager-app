package com.file.app.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.app.drive.DriveOperations;
import com.google.api.services.drive.model.File;

@RestController
public class FileController {


    @Autowired
    private FileService fileStorageService;

    @PostMapping("/upload/{folderId}")
    public File uploadFile(@PathVariable("folderId")String folderId,@RequestParam("file") MultipartFile file) throws IOException {
        FileModel model=new FileModel();
        model.setFileId(folderId);
        model.setMultipartFile(file);
    	return fileStorageService.storeFile(model);
    }
    
    @GetMapping("/root")
    public File getRootFile() throws IOException
    {
    	return fileStorageService.getFileDetails(DriveOperations.rootFolderId);
    }
    @GetMapping("/download/{fileId}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("fileId") String fileId) throws IOException
    {
     return new ResponseEntity<InputStreamResource>(fileStorageService.getFileById(fileId),org.springframework.http.HttpStatus.OK);
    }
    
    @GetMapping("/sub/{fileId}")
    public List<File> getSubFolders(@PathVariable("fileId") String fileId) throws IOException
    {
    	return fileStorageService.getFiles(fileId);
    }
    
    @GetMapping("/{fileId}")
    public File getFileDetails(@PathVariable("fileId") String fileId) throws IOException
    {
    	return fileStorageService.getFileDetails(fileId);
    }
    
    @DeleteMapping("/{fileId}")
    public File deleteFile(@PathVariable("fileId") String fileId) throws IOException
    {
    	return fileStorageService.deleteFile(fileId);
    }
    
    @PostMapping("/{folderName}")
    public File createFolderUnderRoot(@PathVariable("folderName") String folderName) throws IOException
    {
    	return fileStorageService.createFolderUnderRoot(folderName);
    }
    
    @PostMapping("/{folderName}/{parentFolderId}")
    public File createChildFolder(@PathVariable("folderName") String folderName,@PathVariable("parentFolderId") String parentFolderId) throws IOException
    {
    	return fileStorageService.createChildFolder(folderName, parentFolderId);
    }
}