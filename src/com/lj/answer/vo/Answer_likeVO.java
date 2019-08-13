package com.lj.answer.vo;

public class Answer_likeVO {
   
   private int a_no;
   private String writer;
   
   public Answer_likeVO() {
      super();
   }

   public Answer_likeVO(int a_no, String writer) {
      super();
      this.a_no = a_no;
      this.writer = writer;
   }

   public int getA_no() {
      return a_no;
   }

   public void setA_no(int a_no) {
      this.a_no = a_no;
   }

   public String getWriter() {
      return writer;
   }

   public void setWriter(String writer) {
      this.writer = writer;
   }

   @Override
   public String toString() {
      return "Answer_likeVO [a_no=" + a_no + ", writer=" + writer + "]";
   }
   

   
   
   
   
}