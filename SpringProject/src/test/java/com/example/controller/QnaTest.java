package com.example.controller;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.vo.QnaVO;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log
class QnaTest {
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	void test() {
		//assertNotNull(this.sqlSession);
		QnaVO qnaVO = null;
		String [] nameArray= {"박지민", "홍지민", "이지민", "안지민", "유지민", "곽지민",
								"고지민", "정지민", "최지민", "양지민", "한지민", "홍지민"};
		
		for(int i=0; i< nameArray.length; i++) {
			qnaVO = new QnaVO();
			qnaVO.setWriter(nameArray[i]);
			qnaVO.setTitle("제목 "+(i+1));
			qnaVO.setContent("내용 "+(i+1));			
			qnaVO.setEmail("jimin@google.com");			
			this.sqlSession.insert("Qna.insertSP", qnaVO);
		}
	}

}
