package com.lj.answerreply.service;

import static com.lj.common.JdbcUtil.close;
import static com.lj.common.JdbcUtil.commit;
import static com.lj.common.JdbcUtil.getconnection;
import static com.lj.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.lj.answer.dao.AnswerDAO;
import com.lj.answer.vo.AnswerVO;
import com.lj.answer.vo.Answer_likeVO;
import com.lj.answer.vo.Answer_selectVO;
import com.lj.answerreply.dao.AnswerReplyDAO;
import com.lj.answerreply.vo.AnswerReplyVO;
import com.lj.questionreply.vo.QuestionReplyVO;

public class AnswerReplyService {

	private AnswerReplyDAO ard=new AnswerReplyDAO();

	public ArrayList<AnswerReplyVO> getList(String a_no) {
		Connection con=getconnection();
		ArrayList<AnswerReplyVO> list=ard.getList(con,a_no);
		close(con);
		return list;
	}

	public int writeReply(AnswerReplyVO ar) {
		Connection con=getconnection();
		int result=ard.writeReply(con, ar);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public AnswerReplyVO getLastest() {
		Connection con=getconnection();
		AnswerReplyVO result=ard.getLastest(con);
		close(con);
		return result;
	}

	public int deleteReply(String ra_no) {
		Connection con=getconnection();
		int result=ard.deleteQuestionReply(con, ra_no);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}



}
