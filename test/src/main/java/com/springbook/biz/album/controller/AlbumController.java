package com.springbook.biz.album.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbook.biz.album.AlbumService;
import com.springbook.biz.album.AlbumVO;

@Controller
public class AlbumController {
	@Autowired
	AlbumService albumservice;
	
	@RequestMapping("insertAlbum.do")
	public String insertAlbum(AlbumVO vo,Model model,HttpServletRequest request){
			if(vo.getAlb_img().getSize()!=0){
			
			
			// 저장할 경로 가져오기
			String path = request.getSession().getServletContext().getRealPath("resources"); // 프로젝트내 resource 폴더의 실제경로
			String root = path + "\\uploadFiles" ; // 저장할 위치
			
			File file = new File(root); //경로생성용 파일 생성
			
			// 만약 uploadFiles 폴더가 없으면 생성해라 라는뜻
			if(!file.exists()) file.mkdirs();
			
			// 업로드할 폴더 설정
			String originFileName = vo.getAlb_img().getOriginalFilename(); // 원래 파일이름
			String ext = originFileName.substring(originFileName.lastIndexOf(".")); 
			String ranFileName = UUID.randomUUID().toString() + ext; //랜덤변수가 붙은 파일이름
			
			File changeFile = new File(root + "\\" + ranFileName); //파일생성 
			
			
			// 파일업로드
			try {
				vo.getAlb_img().transferTo(changeFile); //파일 업로드
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			
			
			return "/insertAlbum.do";
			}
		
		//VO 필드변경
		String oldName =root + "\\" + ranFileName;	 //변경전 이름
		String changeName=oldName.replaceAll("\\\\", "\\\\\\\\"); //DB와 string에서 \를 인식 못하기 때문에 \\로 바꿔줘야함, \\를 인식하기 위해선 \\\\를 적어야함
		vo.setAlb_img_path(changeName); //VO갱신
		}
		vo.setAlb_writer("admin02");
		albumservice.insertAlbum(vo);
		return "index.jsp";
	}
}