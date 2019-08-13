package com.lj.answer.vo;

import java.sql.Date;

public class Answer_selectVO {

	private int selection_no;
	private int a_no;
	private Date reg_date;

	public Answer_selectVO() {
		super();
	}
	
	public Answer_selectVO(int selection_no, int a_no, Date reg_date) {
		super();
		this.selection_no = selection_no;
		this.a_no = a_no;
		this.reg_date = reg_date;
	}

	public int getSelection_no() {
		return selection_no;
	}

	public void setSelection_no(int selection_no) {
		this.selection_no = selection_no;
	}

	public int getA_no() {
		return a_no;
	}

	public void setA_no(int a_no) {
		this.a_no = a_no;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "Answer_selectVO [selection_no=" + selection_no + ", a_no=" + a_no + ", reg_date=" + reg_date + "]";
	}
	
	
	
}
