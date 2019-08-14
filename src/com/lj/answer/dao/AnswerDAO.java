package com.lj.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lj.answer.vo.AnswerVO;
import com.lj.answer.vo.Answer_likeVO;
import com.lj.answer.vo.Answer_selectVO;
import com.lj.member.vo.MemberVO;

public class AnswerDAO {

	private boolean oracle=false;
	
	public int getAnswerCount(Connection con,String q_no) {
		int result=0;
		Statement st=null;
		ResultSet rs=null;
		String sql="select count(*) from answer where q_no="+q_no;
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

	public int writeAnswer(Connection con, AnswerVO a) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=null;
		if(oracle) {
			sql="insert into answer(a_no,q_no,writer,content,reg_date,update_date) "
					+ "values(seq_answer.nextval,?,?,?,sysdate,sysdate)";
		}else {
			sql="insert into answer(q_no,writer,content,reg_date,update_date) "
			+ "values(?,?,?,sysdate(),now())";
		}
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, a.getQ_no());
			pstmt.setString(2, a.getWriter());
			//pstmt.setString(2, "1");
			pstmt.setString(3, a.getContent());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(pstmt);
		}
		return result;
	}

	public ArrayList<AnswerVO> getList(Connection con, int q_no, MemberVO mem) {
		ArrayList<AnswerVO> list=new ArrayList<>();
		Statement stmt=null;
		ResultSet rs=null;
		String sql=null;
		if(mem!=null) {
			sql="SELECT * FROM answer a LEFT JOIN (SELECT a_no,count(*) like_count FROM ANSWER_LIKE GROUP BY a_no) al ON a.a_no=al.a_no LEFT JOIN (SELECT a_no,writer FROM answer_like WHERE writer='"+mem.getId()+"') al2 ON a.a_no=al2.a_no LEFT JOIN (SELECT a_no,count(*) re_count FROM re_answer GROUP BY a_no) ra ON a.a_no=ra.a_no where q_no="+q_no;
		}else {
			sql="SELECT * FROM answer a LEFT JOIN (SELECT a_no,count(*) like_count FROM ANSWER_LIKE GROUP BY a_no) al ON a.a_no=al.a_no LEFT JOIN (SELECT a_no,count(*) FROM answer_like group by a_no) al2 ON a.a_no=al2.a_no LEFT JOIN (SELECT a_no,count(*) re_count FROM re_answer GROUP BY a_no) ra ON a.a_no=ra.a_no where q_no="+q_no;
		}
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new AnswerVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDate(5),
						rs.getDate(6),
						rs.getInt(8),
						rs.getString(10),
						rs.getInt(12)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(rs);
			//close(stmt);
		}
		return list;
	}

	public AnswerVO getLastest(Connection con) {
		AnswerVO a=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql=null;
		if(oracle) {
			sql="select * from (select * from answer order by 1 desc) where rownum=1";
		}else {
			sql="select * from answer order by 1 desc limit 1";
		}
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				a=new AnswerVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDate(5),
						rs.getDate(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(rs);
			//close(stmt);
		}
		return a;
	}

	public int deleteAnswer(Connection con, String a_no) {
		int result=0;
		Statement st=null;
		String sql="delete from answer where a_no="+a_no;
		try {
			st=con.createStatement();
			result=st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(st);
		}
		return result;
	}

	public int selectionAnswer(Connection con, String a_no) {
		int result=0;
		PreparedStatement pst=null;
		String sql=null;
		if(oracle) {
			sql="insert into answer_select values(seq_answer_select.nextval,?,sysdate)";
		}else {
			sql="insert into answer_select(a_no,reg_date) values(?,sysdate())";
		}
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1,Integer.parseInt(a_no));
			result=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(st);
		}
		return result;
	}

	public Answer_selectVO getAnswerSelection(Connection con, String a_no) {
		Answer_selectVO a=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from answer_select where a_no="+a_no;
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				a=new Answer_selectVO(rs.getInt(1),
						rs.getInt(2),
						rs.getDate(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(rs);
			//close(stmt);
		}
		return a;
	}

	public int cancelSelectionAnswer(Connection con, String a_no) {
		int result=0;
		Statement st=null;
		String sql="delete from answer_select where a_no="+a_no;
		try {
			st=con.createStatement();
			result=st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(st);
		}
		return result;
	}

	public int updateAnswer(Connection con, AnswerVO a) {
		int result=0;
		PreparedStatement pst=null;
		String sql=null;
		if(oracle) {
			sql="update answer set content=?,update_date=sysdate where a_no=?";
		}else {
			sql="update answer set content=?,update_date=sysdate() where a_no=?";
		}
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, a.getContent());
			pst.setInt(2, a.getA_no());
			result=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(st);
		}
		return result;
	}

	public AnswerVO getAnswer(Connection con, String a_no) {
		AnswerVO a=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from answer where a_no="+a_no;
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				a=new AnswerVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDate(5),
						rs.getDate(6),
						rs.getInt(7),
						rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(rs);
			//close(stmt);
		}
		return a;
	}

	public Answer_likeVO getAnswerLike(Connection con, Answer_likeVO a) {
		Answer_likeVO aa=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from answer_like where a_no=? and writer=?";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, a.getA_no());
			pst.setString(2, a.getWriter());
			rs=pst.executeQuery();
			while(rs.next()) {
				aa=new Answer_likeVO(rs.getInt(1),
						rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(rs);
			//close(stmt);
		}
		return aa;
	}

	public int plusAnswerLike(Connection con, Answer_likeVO a) {
		int result=0;
		PreparedStatement pst=null;
		String sql="insert into answer_like values(?,?)";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, a.getA_no());
			pst.setString(2, a.getWriter());
			result=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(st);
		}
		return result;
	}

	public int minusAnswerLike(Connection con, Answer_likeVO a) {
		int result=0;
		PreparedStatement pst=null;
		String sql="delete from answer_like where a_no=? and writer=?";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, a.getA_no());
			pst.setString(2, a.getWriter());
			result=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(st);
		}
		return result;
	}

	public AnswerVO isSelected(Connection con, int q_no) {
		AnswerVO a=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from answer where q_no=? and a_no in (select a_no from answer_select)";
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, q_no);
			rs=pst.executeQuery();
			while(rs.next()) {
				a=new AnswerVO(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDate(5),
						rs.getDate(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//close(rs);
			//close(stmt);
		}
		return a;
	}

}
