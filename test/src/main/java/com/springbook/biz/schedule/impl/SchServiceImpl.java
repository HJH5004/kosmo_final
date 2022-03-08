package com.springbook.biz.schedule.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.sch.SchVO;
import com.springbook.biz.sch.ScheduleService;

@Service("scheduleService")
public class SchServiceImpl implements ScheduleService {
	@Autowired
	SchDAO schDAO;

	@Override
	public void insertSchedule(SchVO vo) {
		System.out.println(vo.getSch_title());
		schDAO.insertSchedule(vo);
		
	}
	
	@Override
	public List<SchVO> getScheduleList(SchVO vo) {
		return schDAO.getScheduleList(vo);
		
	}

	@Override
	public int getSchCnt(SchVO vo) {
		return schDAO.getSchCnt(vo);
	}

	@Override
	public List<SchVO> getScheduleDetail(Map<String, Object> map) {
		return schDAO.getScheduleDetail(map);
	}

	@Override
	public void schMemberCntUp(SchVO vo) {
		schDAO.schMemberCntUp(vo);
	}

	@Override
	public void schMemberCntDown(SchVO vo) {
		schDAO.schMemberCntDown(vo);	
	}

	@Override
	public void deleteSch(SchVO vo) {
		schDAO.deleteSch(vo);
		
	}
}