package com.example.dao;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.vo.BbsVO;

import lombok.extern.java.Log;

@Repository("bbsDao")
@Log
public class BbsDaoImpl implements BbsDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertBoard(BbsVO bbsVO) {
		this.sqlSession.insert("Bbs.insertSP", bbsVO);
	}

	@Override
	public BbsVO selectBoard(int bno) {
		// log.info(" bno = "+bno);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		this.sqlSession.selectOne("Bbs.selectOneSP", map);
		
		List<BbsVO> list = (List<BbsVO>)map.get("result");
		return list.get(0);
	}

	@Override
	public List<BbsVO> selectAllBoard() {
		Map<String, Object> map = new HashMap<String, Object>();
		this.sqlSession.selectList("Bbs.selectAllSP", map);
		List<BbsVO> list = (List<BbsVO>)map.get("result");
		return list;
	}

	@Override
	public void updateBoard(BbsVO bbsVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBoard(int bno) {
		// TODO Auto-generated method stub

	}

	@Override
	public void readnumUpdate(int bno) {
		
	}

}
