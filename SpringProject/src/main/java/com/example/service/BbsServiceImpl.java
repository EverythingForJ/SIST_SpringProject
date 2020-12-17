package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BbsDao;
import com.example.vo.BbsVO;

import lombok.extern.java.Log;

@Service("bbsService")
@Log
public class BbsServiceImpl implements BbsService {
	@Autowired
	private BbsDao bbsDao;

	@Override
	public void create(BbsVO bbsVO) {
		//Service는 비즈니스 처리 해야 함.
		//1. 엔터키를 <br />로 변경 
		//2. tag를 일반 특수문자 &lt;(<)  &gt(>); 로 변경
		//3. 홑따옴표를 쌍따옴표로 변경
		String content = bbsVO.getContent();
		content = this.changeEnter(content);
		content = this.changeTag(content);  
		//content = content.replace("'", "\"");
		bbsVO.setContent(content);
		
		String title = bbsVO.getTitle();
		title = this.changeTag(title);
		title = title.replace("'", "\"");
		bbsVO.setTitle(title);
		
		//log.info(bbsVO.toString());
		this.bbsDao.insertBoard(bbsVO);
	}

	@Override
	public BbsVO read(int bno) {
		return this.bbsDao.selectBoard(bno);
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

	private String changeEnter(String str) {
		return str.replace("\n", "<br />");
	}
	
	private String changeTag(String str) {
		String newStr = str.replace("<", "&lt;");
		return newStr.replace(">", "&gt;");
	}
	
}
