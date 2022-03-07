package com.springbook.biz.schedule.impl;

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
}