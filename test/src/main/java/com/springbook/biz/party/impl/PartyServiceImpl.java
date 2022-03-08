package com.springbook.biz.party.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.party.PartyService;
import com.springbook.biz.party.PartyVO;

@Service("partyService")
public class PartyServiceImpl implements PartyService{
	@Autowired
	PartyDAO partyDAO;
	
	@Override
	public void insertParty(PartyVO vo) {
		partyDAO.insertParty(vo);
	}

	@Override
	public PartyVO getParty(PartyVO vo) {
		return partyDAO.getParty(vo);
	}
	@Override
	public void deleteParty(PartyVO vo){
		partyDAO.deleteParty(vo);
	}
	
	@Override
	public List<PartyVO> getPartyList(Map<String, Object> map) {
		
		//System.out.println("Category list 뽑아오기");
		List<PartyVO> partyList = partyDAO.getPartyList(map);
		
		//for (PartyVO party : partyList) {
			//System.out.println("---> " + party.toString());
		//}		
		return partyList;
	}

	@Override
	public int getPartyCnt(Map<String,Object> map) {
		return partyDAO.getPartyCnt(map);
	}
	
	@Override
	public PartyVO getNewParty(PartyVO vo){
		return partyDAO.getNewParty(vo);
	}
	

}
