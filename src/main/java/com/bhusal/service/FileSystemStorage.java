package com.bhusal.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bhusal.entity.FileData;
import com.bhusal.repo.FileDataRepository;

@Service
public class FileSystemStorage {
	
	@Autowired
	private FileDataRepository fileRepo;
	private final String FOLDER_PATH="C:\\users\\bhusa\\Desktop\\takeo\\";
	
	public String uploadImageToFileSystem(MultipartFile file) throws  IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		
		FileData fileData = fileRepo.save(FileData.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).filepath(filePath).build());
		file.transferTo(new File(filePath));
		if (fileData != null) {
			return "file uploaded successfully: " + filePath;
			
		}
		
		return null;
		
	}
	
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
		Optional<FileData> fileData = fileRepo.findByName(fileName);
		String filePath = fileData.get().getFilepath();
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		return images;
		
	}
	
	public List<FileData> viewAll(){
		List<FileData> findAll = fileRepo.findAll();
		return findAll;
	}

}
