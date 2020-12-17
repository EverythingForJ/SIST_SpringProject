package com.example.dao;

import java.util.List;

import com.example.vo.BbsVO;
import com.example.vo.UserVO;

public interface BbsDao {
	void insertBoard(BbsVO bbsVO);
	BbsVO selectBoard(int bno);
	List<BbsVO> selectAllBoard();
	void updateBoard(BbsVO bbsVO);
	void deleteBoard(int bno);
}
