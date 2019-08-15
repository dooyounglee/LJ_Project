package com.lj.answer.vo;

import java.sql.Date;

public class AnswerVO {

   private int a_no;
   private int q_no;
   private String writer;
   private String content;
   private Date reg_date;
   private Date update_date;
   private int likeCount;
   private String whoLiked;
   private int replyCount;
   private String selected;
   
   
   public String getSelected() {
	return selected;
}

public void setSelected(String selected) {
	this.selected = selected;
}

public AnswerVO() {
      super();
   }

   public AnswerVO(int a_no, int q_no, String writer, String content, Date reg_date, Date update_date) {
      super();
      this.a_no = a_no;
      this.q_no = q_no;
      this.writer = writer;
      this.content = content;
      this.reg_date = reg_date;
      this.update_date = update_date;
   }
   
   public AnswerVO(int a_no, int q_no, String writer, String content, Date reg_date, Date update_date, int likeCount,
		String whoLiked) {
	this.a_no = a_no;
	this.q_no = q_no;
	this.writer = writer;
	this.content = content;
	this.reg_date = reg_date;
	this.update_date = update_date;
	this.likeCount = likeCount;
	this.whoLiked = whoLiked;
}

public AnswerVO(int a_no, int q_no, String writer, String content, Date reg_date, Date update_date, int likeCount,
		String whoLiked, int replyCount) {
	super();
	this.a_no = a_no;
	this.q_no = q_no;
	this.writer = writer;
	this.content = content;
	this.reg_date = reg_date;
	this.update_date = update_date;
	this.likeCount = likeCount;
	this.whoLiked = whoLiked;
	this.replyCount = replyCount;
}


public int getA_no() {
      return a_no;
   }


   public void setA_no(int a_no) {
      this.a_no = a_no;
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


   public String getContent() {
      return content;
   }


   public void setContent(String content) {
      this.content = content;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	public String getWhoLiked() {
		return whoLiked;
	}


	public void setWhoLiked(String whoLiked) {
		this.whoLiked = whoLiked;
	}
	

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	@Override
	public String toString() {
		return "AnswerVO [a_no=" + a_no + ", q_no=" + q_no + ", writer=" + writer + ", content=" + content
				+ ", reg_date=" + reg_date + ", update_date=" + update_date + ", likeCount=" + likeCount + ", whoLiked="
				+ whoLiked + ", replyCount=" + replyCount + ", selected=" + selected + "]";
	}


}