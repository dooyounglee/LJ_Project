package com.lj.question.service;

import static com.lj.common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.lj.question.dao.QuestionDAO;
import com.lj.question.vo.QuestionVO;

public class QuestionService {

	private QuestionDAO qd=new QuestionDAO();
	
	public int inputQuestion(QuestionVO q) {
		int result=0;
		Connection con=getconnection();
		result=new QuestionDAO().inputQuestion(con, q);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public ArrayList<QuestionVO> getList() {
		Connection con=getconnection();
		ArrayList<QuestionVO> list=qd.getList(con);
		close(con);
		return list;
	}

	public QuestionVO getQuestion(String q_no) {
		Connection con=getconnection();
		QuestionVO q=qd.getQuestion(con,q_no);
		close(con);
		return q;
	}

	public int deleteQuestion(String q_no) {
		int result=0;
		Connection con=getconnection();
		result=qd.deleteQuestion(con,q_no);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int updateQuestion(QuestionVO q) {
		int result=0;
		Connection con=getconnection();
		result=qd.updateQuestion(con,q);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public void plus1Count(int q_no) {
		Connection con=getconnection();
		int result=qd.plus1Count(con,q_no);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
	}

}
