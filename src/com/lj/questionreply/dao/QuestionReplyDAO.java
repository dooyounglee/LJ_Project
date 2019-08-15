package com.lj.questionreply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lj.questionreply.vo.QuestionReplyVO;

public class QuestionReplyDAO {

	private boolean oracle=true;
	
	public ArrayList<QuestionReplyVO> getList(Connection con, int q_no) {
		ArrayList<QuestionReplyVO> list=new ArrayList<>();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from re_question where q_no="+q_no;
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new QuestionReplyVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getTimestamp(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			close(rs);
//			close(stmt);
		}
		return list;
	}

	public int writeReply(Connection con, QuestionReplyVO qr) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=null;
		if(oracle) {
			sql="insert into re_question(rq_no,q_no,id,content,reg_date) "
				+ "values(seq_re_question.nextval,?,?,?,sysdate)";
		}else {
			sql="insert into re_question(q_no,id,content,reg_date) "
				+ "values(?,?,?,sysdate())";
		}
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qr.getQ_no());
			pstmt.setString(2, qr.getId());
			pstmt.setString(3, qr.getContent());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(pstmt);
		}
		return result;
	}

	public QuestionReplyVO getLastest(Connection con) {
		QuestionReplyVO qr=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql=null;
		if(oracle) {
			sql="select * from (select * from re_question order by 1 desc) where rownum=1";
		}else {
			sql="select * from re_question order by 1 desc limit 1";
		}
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				qr=new QuestionReplyVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getTimestamp(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(rs);
			//close(stmt);
		}
		return qr;
	}

	public int deleteQuestionReply(Connection con, String rq_no) {
		int result=0;
		Statement st=null;
		String sql="delete from re_question where rq_no="+rq_no;
		try {
			st=con.createStatement();
			result=st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(pstmt);
		}
		return result;
	}

	public int getAnswerCount(Connection con, String q_no) {
		int result=0;
		Statement st=null;
		ResultSet rs=null;
		String sql="select count(*) from re_question where q_no="+q_no;
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(st);
		}
		return result;
	}

}
