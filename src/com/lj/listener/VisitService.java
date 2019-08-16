package com.lj.listener;

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

public class VisitService {

	public void visit() {
		Connection con=getconnection();
		int result=new VisitDAO().visit(con);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
	}

	public int getTotal() {
		Connection con=getconnection();
		int result=new VisitDAO().getTotal(con);
		close(con);
		return result;
	}
	
	public int getToday() {
		Connection con=getconnection();
		int result=new VisitDAO().getToday(con);
		close(con);
		return result;
	}
}
