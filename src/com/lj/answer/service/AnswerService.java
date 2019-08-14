package com.lj.answer.service;

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
import com.lj.member.vo.MemberVO;

public class AnswerService {

	private AnswerDAO ad=new AnswerDAO();

	public int getAnswerCount(String q_no) {
		Connection con=getconnection();
		int result=0;
		result=ad.getAnswerCount(con,q_no);
		close(con);
		return result;
	}

	public int writeAnswer(AnswerVO a) {
		Connection con=getconnection();
		int result=0;
		result=ad.writeAnswer(con,a);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public ArrayList<AnswerVO> getList(int q_no,MemberVO mem) {
		Connection con=getconnection();
		ArrayList<AnswerVO> list=ad.getList(con,q_no,mem);
		close(con);
		return list;
	}

	public AnswerVO getLastest() {
		Connection con=getconnection();
		AnswerVO result=ad.getLastest(con);
		close(con);
		return result;
	}

	public int deleteAnswer(String a_no) {
		Connection con=getconnection();
		int result=ad.deleteAnswer(con,a_no);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int selectionAnswer(String a_no) {
		Connection con=getconnection();
		int result=ad.selectionAnswer(con,a_no);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int getAnswerSelection(String a_no) {
		Connection con=getconnection();
		int result=0;
		int result1=ad.setAnswerSelected(con,a_no);
		AnswerVO ans=ad.getAnswer(con,a_no);
		int result2=qd.setAnswerSelected(con,ans.getQ_no());
		if(result1>0 && result2>0) {
			commit(con);
			result=1;
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int cancelSelectionAnswer(String a_no) {
		Connection con=getconnection();
		int result=ad.cancelSelectionAnswer(con,a_no);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int updateAnswer(AnswerVO a) {
		Connection con=getconnection();
		int result=ad.updateAnswer(con,a);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public AnswerVO getAnswer(String a_no) {
		Connection con=getconnection();
		AnswerVO result=ad.getAnswer(con,a_no);
		close(con);
		return result;
	}

	public Answer_likeVO getAnswerLike(Answer_likeVO a) {
		Connection con=getconnection();
		Answer_likeVO result=ad.getAnswerLike(con,a);
		close(con);
		return result;
	}

	public int plusAnswerLike(Answer_likeVO a) {
		Connection con=getconnection();
		int result=ad.plusAnswerLike(con,a);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int minusAnswerLike(Answer_likeVO a) {
		Connection con=getconnection();
		int result=ad.minusAnswerLike(con,a);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public AnswerVO isSelected(int q_no) {
		Connection con=getconnection();
		AnswerVO result=ad.isSelected(con,q_no);
		close(con);
		return result;
	}

	



}
