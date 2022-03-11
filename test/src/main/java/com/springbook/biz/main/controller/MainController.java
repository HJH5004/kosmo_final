package com.springbook.biz.main.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.main.CategoryVO;
import com.springbook.biz.main.MainService;
import com.springbook.biz.main.MbtiVO;
import com.springbook.biz.user.UserVO;

@Controller
public class MainController {
	@Autowired
	private MainService mainService;

	@RequestMapping("/writeParty.do")
	public String writeParty(MbtiVO vo,CategoryVO vo2,Model model,HttpServletRequest request){
		HttpSession session=request.getSession();
		//admin 10 삭제. 로그인 기능 구현 삽입.
//		UserVO userVO=new UserVO();
//		userVO.setUser_Id("ADMIN10");
//		session.setAttribute("user", userVO);
		model.addAttribute("MbtiList",mainService.getMbtiList(vo)); //MbtiList model 추가	
		model.addAttribute("CategoryList",mainService.getCategoryList(vo2)); //카테고리리스트  model 추가
		return "writeParty.jsp";
	}
	@RequestMapping(value="/registry_form.do", method=RequestMethod.GET)
	public String registryForm(MbtiVO vo,Model model){
//		System.out.println("로그인 페이지 진입 완료");
		model.addAttribute("MbtiList",mainService.getMbtiList(vo)); //MbtiList model 추가
		return "registryPage.jsp";
	}
	
}
