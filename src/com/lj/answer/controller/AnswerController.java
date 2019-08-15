package com.lj.answer.controller;

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

@WebServlet("*.ans")
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AnswerController() {
		super();

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		// response.setCharacterEncoding("text/plain;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ActionForward forward = null;
		Action action = null;
		if (command.equals("/write.ans")) {
			// request.setAttribute("qlist", new QuestionService().getList());
			forward = new ActionForward();
			String q_no = request.getParameter("q_no");
			AnswerVO a = new AnswerVO();
			a.setWriter(request.getParameter("writer"));
			a.setQ_no(Integer.parseInt(q_no));
			a.setContent(request.getParameter("content"));
			int result = new AnswerService().writeAnswer(a);
			if (result > 0) {
				PrintWriter out = response.getWriter();
				AnswerVO ans = new AnswerService().getLastest();
				// System.out.println(ans);
				System.out.println(convertToJSON(ans.toString()));
				// out.print(convertToJSON(ans));
				// response.setContentType("application/json;charset=utf-8");
				// out.print("{\"aaa\":111,\"bbb\":222}");
				// out.print("aaa:111,bbb:222");
				// out.print(convertToJSON(ans.toString()));
				response.setCharacterEncoding("UTF-8");
				out.print(convertToJSON(new AnswerService().getLastest()));
				return;
			}
			return;
			// forward.setPath("/question/qread.jsp?q_no="+q_no);
		} else if (command.equals("/delete.ans")) {
			forward = new ActionForward();
			String a_no = request.getParameter("a_no");
			System.out.println(a_no);
			int result = new AnswerService().deleteAnswer(a_no);
			return;
		} else if (command.equals("/selection.ans")) {
			forward = new ActionForward();
			String a_no = request.getParameter("a_no");
			System.out.println(a_no);
			int result = new AnswerService().getAnswerSelection(a_no);
			return;
			/*
			 * if(a==null) { int result=new AnswerService().selectionAnswer(a_no);
			 * PrintWriter out= response.getWriter(); out.print("add"); }else { int
			 * result=new AnswerService().cancelSelectionAnswer(a_no); PrintWriter out=
			 * response.getWriter(); out.print("remove"); }
			 */
			//return;
		} else if (command.equals("/update.ans")) {
			forward = new ActionForward();
			AnswerVO a = new AnswerVO();
			String a_no = request.getParameter("a_no");
			String content = request.getParameter("content");
			a.setA_no(Integer.parseInt(a_no));
			a.setContent(content);
			int result = new AnswerService().updateAnswer(a);// 수정했고
			a = new AnswerService().getAnswer(a_no);// 수정된거 찾아오고
			PrintWriter out = response.getWriter();
			out.print(convertToJSON(a));
			return;
		} else if (command.equals("/like.ans")) {
			forward = new ActionForward();
			String a_no = request.getParameter("a_no");
			String login_id = request.getParameter("login_id");
			Answer_likeVO a = new Answer_likeVO();
			a.setA_no(Integer.parseInt(a_no));
			a.setWriter(login_id);
			Answer_likeVO aa = new AnswerService().getAnswerLike(a);
			System.out.println(a);
			if (aa == null) {
				int result = new AnswerService().plusAnswerLike(a);
				PrintWriter out = response.getWriter();
				out.print("add");
			} else {
				int result = new AnswerService().minusAnswerLike(a);
				PrintWriter out = response.getWriter();
				out.print("remove");
			}
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
		String str = obj.toString();
		str = str.substring(str.indexOf("["));
		str = str.replace("AnswerVO ", "");
		str = str.replace("[", "{\"");
		str = str.replace("=", "\":\"");
		str = str.replace(", ", "\",\"");
		str = str.replace("]", "\"}");
		return str;
	}
}