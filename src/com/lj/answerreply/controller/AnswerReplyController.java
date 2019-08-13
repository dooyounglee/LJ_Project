package com.lj.answerreply.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
import com.lj.answerreply.service.AnswerReplyService;
import com.lj.answerreply.vo.AnswerReplyVO;
import com.lj.common.Action;
import com.lj.common.ActionForward;
import com.lj.questionreply.service.QuestionReplyService;
import com.lj.questionreply.vo.QuestionReplyVO;


@WebServlet("*.ansr")
public class AnswerReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnswerReplyController() {
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
		if (command.equals("/list.ansr")) {
			//request.setAttribute("qlist", new QuestionService().getList());
			forward = new ActionForward();
			String a_no=request.getParameter("a_no");
			System.out.println(a_no);
			PrintWriter out= response.getWriter();

			ArrayList<AnswerReplyVO> list=new AnswerReplyService().getList(a_no);
			out.print(convertToJSON(list));
			return;
		}else if (command.equals("/write.ansr")) {
			forward = new ActionForward();
			String a_no=request.getParameter("a_no");
			String id=request.getParameter("writer");
			String content=request.getParameter("content");
			AnswerReplyVO ar=new AnswerReplyVO();
			ar.setA_no(Integer.parseInt(a_no));
			ar.setId(id);
			ar.setContent(content);
			int result=new AnswerReplyService().writeReply(ar);
			System.out.println(result+"이거냐?");
			if(result>0) {
				PrintWriter out= response.getWriter();
				AnswerReplyVO ans = new AnswerReplyService().getLastest();
				System.out.println("이게 가지고 온거임"+ans);
				//System.out.println(convertToJSON(ans));
				out.print(convertToJSON(ans));
				return;
			}
			return;
		}else if (command.equals("/delete.ansr")) {
			forward = new ActionForward();
			String ra_no=request.getParameter("ra_no");
			System.out.println(ra_no);
//			AnswerReplyVO ar=new AnswerReplyVO();
//			ar.setA_no(Integer.parseInt(a_no));
//			ar.setId(id);
//			ar.setContent(content);
			int result=new AnswerReplyService().deleteReply(ra_no);
//			if(result>0) {
//				PrintWriter out= response.getWriter();
//				AnswerReplyVO ans = new AnswerReplyService().getLastest();
//				//System.out.println(ans);
//				System.out.println(convertToJSON(ans));
//				out.print(convertToJSON(ans));
//				return;
//			}
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
		str=str.replace("[", "{\"");
		str=str.replace("=", "\":\"");
		str=str.replace(", ", "\",\"");
		str=str.replace("]", "\"}");
		return str;
	}
	
	public String convertToJSON(ArrayList list) {
		String str="";
		if(list.size()>0) {
			str="["+convertToJSON(list.get(0));
			for(int i=1;i<list.size();i++) {
				System.out.println(list.get(i));
				str+=","+convertToJSON(list.get(i));
			}
			str+="]";
		}
		return str;
	}
}
