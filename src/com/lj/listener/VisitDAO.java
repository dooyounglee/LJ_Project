package com.lj.listener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lj.answerreply.vo.AnswerReplyVO;

public class VisitDAO {

	private boolean oracle=true;
	
	public int visit(Connection con) {
		int result=0;
		Statement st=null;
		String sql=null;
		if(oracle) {
			sql="insert into visit values(sysdate)";
		}else {
			sql="insert into visit values(sysdate())";			
		}
		try {
			st=con.createStatement();
			result=st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			close(rs);
//			close(stmt);
		}
		return result;
	}

	public int getTotal(Connection con) {
		int result=0;
		Statement st=null;
		ResultSet rs=null;
		String sql="select count(*) from visit";
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			close(rs);
		return result;
		}
	}

	public int getToday(Connection con) {
		int result=0;
		Statement st=null;
		ResultSet rs=null;
		String sql=null;
		if(oracle) {
			sql="select count(*) from visit";
		}else {
			sql="select count(*) from visit where date(visit_date)=date(sysdate())";			
		}
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			close(rs);
		return result;
		}
	}
}
