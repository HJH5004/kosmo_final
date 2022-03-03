package com.springbook.biz.board.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAOMybatis BoardDAO;
	
	@Override
	public int getBoardCnt(BoardVO vo) {
		return BoardDAO.getBoardCnt(vo);
	}

	@Override
	public List<BoardVO> getBoardList(Map<String, Object> map) {
		return  BoardDAO.getBoardList(map);
	}



	
}
