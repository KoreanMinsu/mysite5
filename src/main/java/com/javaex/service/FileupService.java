package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileupService {

	//파일업로드 처리
	public String restore(MultipartFile file) {
		
	//하드파일경로
		String saveDir = "c:\\javaStudy\\upload";
		
	//파일 서버에 저장
		
		
		//원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(exName);
		
		//저장파일이름(관리때문에 중복 없는 새이름 부여)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+exName;
		System.out.println(saveName);
		
		//파일패스
		String filePath = saveDir + "\\" + saveName;
		System.out.println(filePath);
		
		//파일 사이즈
		long fileSize = file.getSize();
	    System.out.println("fileSize: " + fileSize + " byte");

		
		//파일을 서버의 하드에 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream("c:\\Javastudy\\");
			BufferedOutputStream bos = new BufferedOutputStream(out);
		
			 bos.write(fileData);
			 bos.close();
			 
	    // 2. 파일 정보를 DB에 저장 -- 과제

		}	catch (IOException e) {

	      e.printStackTrace();
	    }
		
		return saveName;

	}
}
