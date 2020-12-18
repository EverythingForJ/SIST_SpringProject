package com.example.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.vo.QnaVO;

@Repository("qnaDao")
public class QnaDaoImpl implements QnaDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertQna(QnaVO qnaVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public QnaVO selectQna(int bno) {
		return this.sqlSession.selectOne("Qna.selectOne", bno);
	}

	@Override
	public List<QnaVO> selectAllQna() {
		return this.sqlSession.selectList("Qna.selectAll");
	}

	@Override
	public void updateQna(QnaVO qnaVO) {
		this.sqlSession.update("Qna.update", qnaVO);
	}

	@Override
	public void deleteQna(int bno) {
		this.sqlSession.delete("Qna.delete", bno);
	}

	@Override
	public void readnumUpdate(int bno) {
		this.sqlSession.update("Qna.readnumUpdate", bno);
	}

}
