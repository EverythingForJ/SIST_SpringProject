package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.QnaDao;
import com.example.vo.QnaVO;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {
	@Autowired
	private QnaDao qnaDao;

	@Override
	public void create(QnaVO qnaVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public QnaVO read(int bno) {
		return this.qnaDao.selectQna(bno);
	}

	@Override
	public List<QnaVO> readAll() {
		return this.qnaDao.selectAllQna();
	}

	@Override
	public void update(QnaVO qnaVO) {
		//Service는 비즈니스 처리 해야 함.
		//1. 엔터키를 <br />로 변경 
		//2. tag를 일반 특수문자 &lt;(<)  &gt(>); 로 변경
		//3. 홑따옴표를 쌍따옴표로 변경
		String content = qnaVO.getContent();
		content = this.changeEnter(content); //일반 Textarea에서는 해야 됨.
		content = this.changeTag(content);  
		content = content.replace("'", "\"");  //일반 Textarea에서는 해야 됨.
		qnaVO.setContent(content);
		
		String title = qnaVO.getTitle();
		title = this.changeTag(title);
		title = title.replace("'", "\"");
		qnaVO.setTitle(title);
		this.qnaDao.updateQna(qnaVO);
	}

	@Override
	public void delete(int bno) {
		this.qnaDao.deleteQna(bno);
	}

	@Override
	public void updateReadnum(int bno) {
		this.qnaDao.readnumUpdate(bno);
	}
	
	private String changeEnter(String str) {
		return str.replace("\n", "<br />");
	}
	
	private String reverseChangeEnter(String str) {
		return str.replace("<br />", "\n");
	}
	
	private String changeTag(String str) {
		String newStr = str.replace("<", "&lt;");
		return newStr.replace(">", "&gt;");
	}
	
	private String reverseChangeTag(String str) {
		String newStr = str.replace("&lt;", "<");
		return newStr.replace("&gt;", ">");
	}

}
