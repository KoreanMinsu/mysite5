package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//insert
	public int insert(GalleryVo galleryVo) {
	    System.out.println("[GalleryDao.insert()]");
	    
	    int count = sqlSession.insert("gallery.insert", galleryVo);
	    
	    return count;
	}
	
	//list
	public List<GalleryVo> getList(){
		System.out.println("[GalleryDao.getList()]");
	
		List<GalleryVo> galleryVo = sqlSession.selectList("gallery.getList");
		
		return galleryVo;
	}	
	
	//delete
	public int delete(int no) {
		System.out.println("[GalleryDao.delete()]");

	    int count = sqlSession.delete("gallery.delete", no);

	    return count;
	  }
	
	
	//get one
	 public GalleryVo getOneList(int no) {
		System.out.println("[GalleryDao.delete()]");
		
		GalleryVo galleryVo = sqlSession.selectOne("gallery.selectOnt", no);
	    
		return galleryVo; 
	    
	  }
}
