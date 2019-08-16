package com.lj.listener;

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

@WebServlet("*.visit")
public class VisitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VisitController() {
        super();
  
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		response.setContentType("text/html;charset=UTF-8");
		ActionForward forward = null;
		Action action = null;
		if (command.equals("/visit.visit")) {
			forward = new ActionForward();
			
			//forward.setPath("/question/qread.jsp?q_no="+q_no);
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
