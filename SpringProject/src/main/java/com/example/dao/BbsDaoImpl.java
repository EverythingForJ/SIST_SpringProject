package com.example.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.vo.BbsVO;

@Repository("bbsDao")
public class BbsDaoImpl implements BbsDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertBoard(BbsVO bbsVO) {
		this.sqlSession.insert("Bbs.insertSP", bbsVO);
	}

	@Override
	public BbsVO selectBoard(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BbsVO> selectAllBoard() {
		Map<String, Object> map = new HashMap<String, Object>();
		this.sqlSession.selectList("Bbs.selectAllSP",map);
		return (List<BbsVO>)map.get("result");
	}

	@Override
	public void updateBoard(BbsVO bbsVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBoard(int bno) {
		// TODO Auto-generated method stub

	}

}
