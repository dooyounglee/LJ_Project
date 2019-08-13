package com.lj.questionreply.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lj.answer.service.AnswerService;
import com.lj.answer.vo.AnswerVO;
import com.lj.answer.vo.Answer_likeVO;
import com.lj.answer.vo.Answer_selectVO;
import com.lj.common.Action;
import com.lj.common.ActionForward;
import com.lj.questionreply.service.QuestionReplyService;
import com.lj.questionreply.vo.QuestionReplyVO;


@WebServlet("*.qur")
public class QuestionReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuestionReplyController() {
        super();
  
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		//response.setCharacterEncoding("text/plain;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ActionForward forward = null;
		Action action = null;
		if (command.equals("/write.qur")) {
			forward = new ActionForward();
			String q_no=request.getParameter("q_no");
			String writer=request.getParameter("writer");
			String content=request.getParameter("content");
			QuestionReplyVO qr=new QuestionReplyVO();
			qr.setQ_no(Integer.parseInt(q_no));
			qr.setId(writer);
			qr.setContent(content);
			int result=new QuestionReplyService().writeReply(qr);
			if(result>0) {
				PrintWriter out= response.getWriter();
				QuestionReplyVO ans = new QuestionReplyService().getLastest();
				//System.out.println(ans);
				System.out.println(convertToJSON(ans));
				out.print(convertToJSON(ans));
				return;
			}
//			return;
			//forward.setPath("/question/qread.jsp?q_no="+q_no);
		}else if (command.equals("/delete.qur")) {
			forward = new ActionForward();
			String rq_no=request.getParameter("rq_no");
			System.out.println(rq_no);
			int result=new QuestionReplyService().deleteQuestionReply(rq_no);
			return;
		}
		
		
		
		// �럹�씠吏� �씠�룞 泥섎━
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	public String convertToJSON(Object obj) {
		String str=obj.toString();
		str=str.substring(str.indexOf("["));
		str=str.replace("AnswerVO ","");
		str=str.replace("[", "{\"");
		str=str.replace("=", "\":\"");
		str=str.replace(", ", "\",\"");
		str=str.replace("]", "\"}");
		return str;
	}
}
