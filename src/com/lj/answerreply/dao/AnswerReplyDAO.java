package com.lj.answerreply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lj.answerreply.vo.AnswerReplyVO;

public class AnswerReplyDAO {

	private boolean oracle=false;
	
	public ArrayList<AnswerReplyVO> getList(Connection con, String a_no) {
		ArrayList<AnswerReplyVO> list=new ArrayList<>();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from re_answer where a_no="+a_no;
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new AnswerReplyVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDate(5)+" "+rs.getTime(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			close(rs);
//			close(stmt);
		}
		return list;
	}

	public int writeReply(Connection con, AnswerReplyVO ar) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=null;
		if(oracle) {
			sql="insert into re_answer values(seq_re_question.nextval,?,?,?,sysdate)";
		}else {
			sql="insert into re_answer(a_no,id,content,reg_date) "
					+ "values(?,?,?,sysdate())";
		}
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, ar.getA_no());
			pstmt.setString(2, ar.getId());
			pstmt.setString(3, ar.getContent());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(pstmt);
		}
		return result;
	}

	public AnswerReplyVO getLastest(Connection con) {
		AnswerReplyVO ar=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql=null;
		if(oracle) {
			sql="select * from (select * from re_answer order by 1 desc) where rownum=1";
		}else {
			sql="select * from re_answer order by 1 desc limit 1";
		}
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				ar=new AnswerReplyVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDate(5)+" "+rs.getTime(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(rs);
			//close(stmt);
		}
		return ar;
	}

	public int deleteQuestionReply(Connection con, String ra_no) {
		int result=0;
		Statement st=null;
		String sql="delete from re_answer where ra_no="+ra_no;
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


}
