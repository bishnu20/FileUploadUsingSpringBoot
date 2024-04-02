package com.bhusal.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bhusal.entity.FileData;
import com.bhusal.service.FileSystemStorage;

@RestController
public class ImageController {
	
	@Autowired
	private FileSystemStorage fss;
	
	@PostMapping("/fileSystem")
	public ResponseEntity<?> downloadImageFromFileSystem(@RequestParam("image") MultipartFile file) throws IOException{
		String uploadImage = fss.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
		
	}
	
	// http://localhost:8080/image/fileSystem/1.jpg
	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException{
		byte[] imageData = fss.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/jpeg")).body(imageData);
	}
	
	@GetMapping("/fileSystem/viewall")
	public ResponseEntity<?> viewAll() {
		List<FileData> viewAll = fss.viewAll();
		return ResponseEntity.ok().body(viewAll);
	}

}
