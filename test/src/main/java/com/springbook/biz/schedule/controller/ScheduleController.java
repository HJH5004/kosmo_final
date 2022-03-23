package com.springbook.biz.schedule.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbook.biz.board.PageVO;
import com.springbook.biz.memberList.MemberListService;
import com.springbook.biz.memberList.MemberListVO;
import com.springbook.biz.party.PartyService;
import com.springbook.biz.party.PartyVO;
import com.springbook.biz.sch.SchVO;
import com.springbook.biz.sch.ScheduleService;
import com.springbook.biz.user.UserVO;

@Controller
public class ScheduleController {
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	MemberListService memberListService;
	
	@Autowired
	PartyService partyService;
	
	@RequestMapping("calendar2.do") 
	public String getScheduleDetail(SchVO vo, Model model, PageVO page, MemberListVO memberListVO,HttpSession session ) {	
		int party_id=vo.getParty_id();
		int count = scheduleService.getSchCnt(vo);
		String year=vo.getYear();
		String month=vo.getMonth();
		String day=vo.getDay();
		String pageNo = page.getPageNo();
		int currentPage = 1;
		int listSize = 1;
		int pageSize = 1;
		if(pageNo != null) {
			currentPage = Integer.parseInt(pageNo);
		}
		int startRow = (currentPage-1) * listSize;
		UserVO userVO = (UserVO) session.getAttribute("user");
		session.setAttribute("user", userVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("party_id", party_id);
		map.put("startRow", startRow);
		map.put("listSize", listSize);
		map.put("year", year);
		map.put("month", month);
		map.put("day",day);
		map.put("sch_id", vo.getSch_id());
		
		PageVO pages = new PageVO(count, currentPage, listSize, pageSize);
		List<SchVO> mapResult = scheduleService.getScheduleDetail(map);
		
		
		// 필요한거 -> 방장 가져오기, 
		memberListVO.setPARTY_ID(party_id);
		List<MemberListVO> list=memberListService.getJoinMemberList(memberListVO);
		MemberListVO leader =list.get(0);
		model.addAttribute("leader", leader);
		
		Map<String, Object> cntList = new HashMap<String, Object>();
		cntList.put("sch_id", mapResult.get(0).getSch_id());
		
		//현재 멤버 카운트 저장 
		int sch_member_current_count = scheduleService.getCurrentMemberCnt(mapResult.get(0).getSch_id());
		
		model.addAttribute("SchDetail",mapResult);
		model.addAttribute("pages", pages);
		model.addAttribute("cntList", scheduleService.getCntList(cntList));
		System.out.println(scheduleService.getCntList(cntList).toString());
		model.addAttribute("current_count",sch_member_current_count );
		
		return "CalendarDetail.jsp";	
	}
	@RequestMapping("deleteSch.do")
	public String deleteSch(SchVO vo,Model model){
		String year=vo.getYear();
		String month=vo.getMonth();
		String day=vo.getDay();
		int party_id=vo.getParty_id();
		scheduleService.deleteSch(vo);
		return "redirect:calendar.do?year="+year+"&month="+month+"&day="+day+"&party_id="+party_id;
	}
	
	@RequestMapping("deleteSch2.do")
	public String deleteSch2(SchVO vo,Model model){
		String year=vo.getYear();
		String month=vo.getMonth();
		String day=vo.getDay();
		int party_id=vo.getParty_id();
		scheduleService.deleteSch(vo);
		System.out.println("딜리트 왜 안돼? ");
		System.out.println(vo.toString());
		return "redirect:calendar3.do?year="+year+"&month="+month+"&day="+day+"&party_id="+party_id;
	}
	
	@RequestMapping("cntUp.do")
	public String cntUp(SchVO vo,Model model,HttpSession session, PageVO page, HttpServletRequest request){
		UserVO userVO=(UserVO)session.getAttribute("user");		
		vo.setUser_id(userVO.getUser_Id());
		scheduleService.schMemberCntUp(vo);
		return "getParty.do?PARTY_ID="+vo.getParty_id();
	}
	
	@RequestMapping("cntDown.do")
	public String cntDown(SchVO vo,Model model,HttpSession session, PageVO page){
		UserVO userVO=(UserVO)session.getAttribute("user");
		vo.setUser_id(userVO.getUser_Id());
		scheduleService.schMemberCntDown(vo);
		return "getParty.do?PARTY_ID="+vo.getParty_id();
	}
	
	@RequestMapping("insertSchedule.do")
	public String insertSchedule(SchVO vo,Model model,HttpSession session){
		UserVO userVO=(UserVO)session.getAttribute("user");
		if(userVO!=null){
			scheduleService.insertSchedule(vo);
			vo.setSch_id(scheduleService.getNewSchedule(vo).getSch_id());
			scheduleService.schMemberCntUp(vo);
			return "getParty.do?PARTY_ID="+vo.getParty_id();
		}else {
			return "login.do";
		}
	}
	
	@RequestMapping("calendar.do")
	public String getScheduleList(SchVO vo,Model model,HttpServletRequest request){
		UserVO userVO=(UserVO)request.getSession().getAttribute("user");
		vo.setUser_id(userVO.getUser_Id());
		if(request.getParameter("year")!=null){
			model.addAttribute("year", request.getParameter("year"));
			model.addAttribute("month", request.getParameter("month"));
			model.addAttribute("day", request.getParameter("day"));
			vo.setYear(request.getParameter("year"));
			switch (request.getParameter("month")) {
			case "1":
				vo.setMonth("01");
				break;
			case "2":
				vo.setMonth("02");
				break;
			case "3":
				vo.setMonth("03");
				break;
			case "4":
				vo.setMonth("04");
				break;
			case "5":
				vo.setMonth("05");
				break;
			case "6":
				vo.setMonth("06");
				break;
			case "7":
				vo.setMonth("07");
				break;
			case "8":
				vo.setMonth("08");
				break;
			case "9":
				vo.setMonth("09");
				break;
			default:
				vo.setMonth(request.getParameter("month"));
				break;
			}
			
		}else{
		LocalDate now = LocalDate.now();
		String date=now.toString();
		String[] dateList=date.split("-");
		
		model.addAttribute("year", dateList[0]);
		model.addAttribute("month", dateList[1]);
		model.addAttribute("day", 01);
		
		vo.setYear(dateList[0]);
		vo.setMonth(dateList[1]);
		}
		
		
		model.addAttribute("party_id", request.getParameter("party_id"));
		List<SchVO> getList=scheduleService.getScheduleList(vo);
		model.addAttribute("SchduleList", getList);
		List<String> checkList =new ArrayList<>();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
		
		for(SchVO s:getList){
			String tmpDate=simpleDateFormat.format(s.getSch_date());
			checkList.add(tmpDate);
		}
		model.addAttribute("checkList", checkList);
		return "Calendar.jsp";
	}
	@RequestMapping("calendar3.do")
	public String myPageGetScheduleList(SchVO vo,Model model,HttpServletRequest request){
		UserVO userVO=(UserVO)request.getSession().getAttribute("user");
		vo.setUser_id(userVO.getUser_Id());
		if(request.getParameter("year")!=null){
			model.addAttribute("year", request.getParameter("year"));
			model.addAttribute("month", request.getParameter("month"));
			model.addAttribute("day", request.getParameter("day"));
			vo.setYear(request.getParameter("year"));
			switch (request.getParameter("month")) {
			case "1":
				vo.setMonth("01");
				break;
			case "2":
				vo.setMonth("02");
				break;
			case "3":
				vo.setMonth("03");
				break;
			case "4":
				vo.setMonth("04");
				break;
			case "5":
				vo.setMonth("05");
				break;
			case "6":
				vo.setMonth("06");
				break;
			case "7":
				vo.setMonth("07");
				break;
			case "8":
				vo.setMonth("08");
				break;
			case "9":
				vo.setMonth("09");
				break;
			default:
				vo.setMonth(request.getParameter("month"));
				break;
			}
			
		}else{
		LocalDate now = LocalDate.now();
		String date=now.toString();
		String[] dateList=date.split("-");
		
		model.addAttribute("year", dateList[0]);
		model.addAttribute("month", dateList[1]);
		model.addAttribute("day", 01);
		
		vo.setYear(dateList[0]);
		vo.setMonth(dateList[1]);
		}
		
		
		model.addAttribute("party_id", request.getParameter("party_id"));
		List<SchVO> getList=scheduleService.getScheduleList2(vo);
		model.addAttribute("SchduleList", getList);
		List<String> checkList =new ArrayList<>();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
		
		for(SchVO s:getList){
			String tmpDate=simpleDateFormat.format(s.getSch_date());
			checkList.add(tmpDate);
		}
		model.addAttribute("checkList", checkList);
		return "Calendar2.jsp";
	}
	
	@RequestMapping("calendar4.do") 
	public String myPageGetScheduleDetail(SchVO vo, Model model, PageVO page, MemberListVO memberListVO,PartyVO partyVO,HttpSession session) {	
		UserVO userVO = (UserVO) session.getAttribute("user");
		session.setAttribute("user", userVO);
		System.out.println("처음값 확인용:" + vo.getDay());
		vo.setUser_id(userVO.getUser_Id());
		//System.out.println(vo.getYear());
		if(vo.getYear()==null) {
		   vo.setYear((String) session.getAttribute("year"));
		   vo.setMonth((String) session.getAttribute("month"));
		   vo.setDay((String) session.getAttribute("day"));
		}else {
			session.setAttribute("year", null);
			session.setAttribute("month", null);
			session.setAttribute("day", null);
		 
		}
		String sessionChk = (String) session.getAttribute("year");
		int count = scheduleService.getSchCnt2(vo);
		String year=vo.getYear();
		String month=vo.getMonth();
		String day=vo.getDay();
		String pageNo = page.getPageNo();
		int currentPage = 1;
		int listSize = 1;
		int pageSize = 1;
		if(pageNo != null) {
			currentPage = Integer.parseInt(pageNo);
		}
		int startRow = (currentPage-1) * listSize;
		System.out.println("startRow 확인 : "+startRow);
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userVO.getUser_Id());
		map.put("startRow", startRow);
		map.put("listSize", listSize);
		map.put("year", year);
		map.put("month", month);
		map.put("day",day);
		map.put("sch_id", vo.getSch_id());
		
		
		
		PageVO pages = new PageVO(count, currentPage, listSize, pageSize);
		List<SchVO> mapResult = scheduleService.getScheduleDetail2(map);
		partyVO.setPARTY_ID(mapResult.get(0).getParty_id());
		PartyVO getVO =partyService.getParty(partyVO);
		model.addAttribute("party_title", getVO.getPARTY_TITLE());
		vo.setParty_id(getVO.getPARTY_ID());
		
		Map<String, Object> cntList = new HashMap<String, Object>();
		cntList.put("sch_id", mapResult.get(0).getSch_id());
		
		//현재 멤버 카운트 저장 
		int sch_member_current_count = scheduleService.getCurrentMemberCnt(mapResult.get(0).getSch_id());
		
		model.addAttribute("SchDetail",mapResult);
		model.addAttribute("pages", pages);
		model.addAttribute("cntList", scheduleService.getCntList(cntList));
		System.out.println(scheduleService.getCntList(cntList).toString());
		System.out.println(pages.toString());
		model.addAttribute("current_count",sch_member_current_count );
		
		System.out.println("mapResult=" + mapResult.toString());
		System.out.println("pages=" + pages.toString());
		System.out.println("cntList="+scheduleService.getCntList(cntList));
		System.out.println("current_count="+ sch_member_current_count);
		System.out.println("getVO" + getVO.toString());
		
        session.setAttribute("year", year);
        session.setAttribute("month", month);
        session.setAttribute("day", day);
        System.out.println("체크!!!!!"+sessionChk);
        if(sessionChk!=null) {
       	return "redirect:calendar4.do?year="+vo.getYear()+"&month="+vo.getMonth()+"&day="+vo.getDay()+"&party_id"+vo.getParty_id();    			
       }else {
        	return "CalendarDetail2.jsp?party_id="+vo.getParty_id();
        }
			
	}
	
	
	@RequestMapping("scheduleReview.do")
	public String scheduleReview(SchVO vo,Model model,HttpSession session, PartyVO partyVO, PageVO page) {
		// 로그인 AOP 구현하면 좋을듯
		scheduleService.scheduleReview(vo);
		
		return "calendar4.do";
		
	}
		
		
	
		
		
	
	
}
