package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BbsDao;
import com.example.vo.BbsVO;

@Service("bbsService")
public class BbsServiceImpl implements BbsService {
	
	@Autowired
	private BbsDao bbsDao;
	
	@Override
	public void create(BbsVO bbsVO) {
		this.bbsDao.insertBoard(bbsVO);
	}

	@Override
	public BbsVO read(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BbsVO> readAll() {
		return this.bbsDao.selectAllBoard();
	}

	@Override
	public void update(BbsVO bbsVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int bno) {
		// TODO Auto-generated method stub

	}

}
