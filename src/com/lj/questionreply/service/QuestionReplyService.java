package com.lj.questionreply.service;

import static com.lj.common.JdbcUtil.close;
import static com.lj.common.JdbcUtil.commit;
import static com.lj.common.JdbcUtil.getconnection;
import static com.lj.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.lj.answer.vo.AnswerVO;
import com.lj.question.dao.QuestionDAO;
import com.lj.questionreply.dao.QuestionReplyDAO;
import com.lj.questionreply.vo.QuestionReplyVO;

public class QuestionReplyService {

	private QuestionReplyDAO qrd=new QuestionReplyDAO();

	public ArrayList<QuestionReplyVO> getList(int q_no) {
		Connection con=getconnection();
		ArrayList<QuestionReplyVO> list=qrd.getList(con,q_no);
		close(con);
		return list;
	}

	public int writeReply(QuestionReplyVO qr) {
		Connection con=getconnection();
		int result=qrd.writeReply(con, qr);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public QuestionReplyVO getLastest() {
		Connection con=getconnection();
		QuestionReplyVO result=qrd.getLastest(con);
		close(con);
		return result;
	}

	public int deleteQuestionReply(String rq_no) {
		Connection con=getconnection();
		int result=qrd.deleteQuestionReply(con, rq_no);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int getReplyCount(String q_no) {
		Connection con=getconnection();
		int result=0;
		result=qrd.getAnswerCount(con,q_no);
		close(con);
		return result;
	}

	
}
