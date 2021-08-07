package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	//insert
	public String insert(MultipartFile file, String content, int userNo) {
	    System.out.println("[GalleryService.insert()]");

	//파일업로드 처리
	    
		
	//하드파일경로
		String saveDir = "c:\\javaStudy\\upload";
		System.out.println(saveDir);
		
	//파일 서버에 저장
		
		
		//원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
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

			byte[] galleryData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(galleryData);
			bout.close();
			
			//파일정보 DB저장
			GalleryVo galleryVo = new GalleryVo (userNo, content, filePath, orgName, saveName, fileSize);
			System.out.println(galleryVo);
			
			galleryDao.insert(galleryVo);
	

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    
	    return saveName;
	}
	
	//list
	public List<GalleryVo> getList() {
	    System.out.println("[GalleryService.getList()]");

	    return galleryDao.getList();
	  }
	
	//delete
	public int delete(int no) {
	    System.out.println("[GalleryService.delete()]");

	    int count = galleryDao.delete(no);

	    return count;
	  }
	
	//get one list
	  public GalleryVo getOneList(int no) {
	    System.out.println("[GalleryService.getOneList()]");

	    return galleryDao.getOneList(no);
	  }
}
