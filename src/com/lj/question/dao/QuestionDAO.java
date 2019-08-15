package com.lj.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lj.question.vo.QuestionVO;

public class QuestionDAO {

	private boolean oracle=true;
	
	public int inputQuestion(Connection con, QuestionVO q) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=null;
		if(oracle) {
			sql="insert into question(q_no,writer,title,content,cate_1,cate_2,cate_3,reg_date,update_date) "
					+ "values(seq_question.nextval,?,?,?,?,?,?,sysdate,sysdate)";
		}else {
			sql="insert into question(writer,title,content,cate_1,cate_2,cate_3,reg_date,update_date) "
					+ "values(?,?,?,?,?,?,sysdate(),sysdate())";
		}
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, q.getWriter());
			pstmt.setString(2, q.getTitle());
			pstmt.setString(3, q.getContent());
			pstmt.setString(4, q.getCate1());
			pstmt.setString(5, q.getCate2());
			pstmt.setString(6, q.getCate3());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(pstmt);
		}
		return result;
	}

	public ArrayList<QuestionVO> getList(Connection con) {
		ArrayList<QuestionVO> list=new ArrayList<>();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM question q " + 
				"LEFT JOIN (SELECT q_no,count(*) FROM answer GROUP BY q_no) a ON q.q_no=a.q_no " + 
				"ORDER BY q.q_no desc";
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				QuestionVO q=new QuestionVO(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getDate(9),
						rs.getDate(10),
						rs.getString(11));
				q.setAnswerCount(rs.getInt(13));
				list.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			close(rs);
//			close(stmt);
		}
		return list;
	}

	public QuestionVO getQuestion(Connection con, String q_no) {
		QuestionVO q=new QuestionVO();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from question where q_no="+q_no;
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				q=new QuestionVO(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getDate(9),
						rs.getDate(10),
						rs.getString(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(rs);
			//close(stmt);
		}
		return q;
	}

	public int deleteQuestion(Connection con, String q_no) {
		int result=0;
		Statement stmt=null;
		String sql="delete from question where q_no="+q_no;
		try {
			stmt=con.createStatement();
			result=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(stmt);
		}
		return result;
	}

	public int updateQuestion(Connection con, QuestionVO q) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=null;
		if(oracle) {
			sql="update question set writer=?,title=?,content=?,cate_1=?,cate_2=?,cate_3=?,update_date=sysdate"
					+ " where q_no=?";
		}else {
			sql="update question set writer=?,title=?,content=?,cate_1=?,cate_2=?,cate_3=?,update_date=sysdate()"
					+ " where q_no=?";
		}
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, q.getWriter());
			pstmt.setString(2, q.getTitle());
			pstmt.setString(3, q.getContent());
			pstmt.setString(4, q.getCate1());
			pstmt.setString(5, q.getCate2());
			pstmt.setString(6, q.getCate3());
			pstmt.setInt(7, q.getQ_no());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(pstmt);
		}
		return result;
	}

	public int plus1Count(Connection con, int q_no) {
		int result=0;
		Statement st=null;
		String sql="update question set count=count+1 where q_no="+q_no;
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

	public int setAnswerSelected(Connection con, int q_no) {
		int result=0;
		PreparedStatement pst=null;
		String sql="update question set selected='Y' where q_no=?";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, q_no);
			result=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(rs);
			//close(stmt);
		}
		return result;
	}
}
