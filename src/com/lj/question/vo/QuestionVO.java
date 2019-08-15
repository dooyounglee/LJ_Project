package com.lj.question.vo;

import java.sql.Date;

public class QuestionVO {

	private int q_no;
	private String writer;
	private String title;
	private String content;
	private String cate1;
	private String cate2;
	private String cate3;
	private int count;
	private Date reg_date;
	private Date update_date;
	private String selected;
	private int answerCount;
	private int replyCount;

	public QuestionVO() {
		super();
	}

	public QuestionVO(int q_no, String writer, String title, String content, String cate1, String cate2, String cate3,
			int count, Date reg_date, Date update_date, String selected) {
		this.q_no = q_no;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.cate1 = cate1;
		this.cate2 = cate2;
		this.cate3 = cate3;
		this.count = count;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.selected = selected;
	}
	
	public int getQ_no() {
		return q_no;
	}

	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCate1() {
		return cate1;
	}

	public void setCate1(String cate1) {
		this.cate1 = cate1;
	}

	public String getCate2() {
		return cate2;
	}

	public void setCate2(String cate2) {
		this.cate2 = cate2;
	}

	public String getCate3() {
		return cate3;
	}

	public void setCate3(String cate3) {
		this.cate3 = cate3;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "QuestionVO [q_no=" + q_no + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", cate1=" + cate1 + ", cate2=" + cate2 + ", cate3=" + cate3 + ", count=" + count + ", reg_date="
				+ reg_date + ", update_date=" + update_date + ", answerCount=" + answerCount + ", replyCount="
				+ replyCount + ", selected=" + selected + "]";
	}

}