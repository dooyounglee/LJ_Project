package com.lj.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lj.common.Action;
import com.lj.common.ActionForward;
import com.lj.question.service.QuestionService;
import com.lj.question.vo.QuestionVO;

public class qwrite implements Action {
	
	private QuestionService qs=new QuestionService();

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
//		System.out.println(request.getParameter("title"));
//		System.out.println(request.getParameter("content"));
//		System.out.println(request.getParameter("writer"));
//		System.out.println(request.getParameter("large"));
//		System.out.println(request.getParameter("middle"));
//		System.out.println(request.getParameter("small"));
		QuestionVO q=new QuestionVO();
		q.setWriter(request.getParameter("writer"));
		q.setTitle(request.getParameter("title"));
		q.setContent(request.getParameter("editerArea"));
		q.setCate1(request.getParameter("large"));
		q.setCate2(request.getParameter("middle"));
		q.setCate3(request.getParameter("small"));
		System.out.println(request.getParameter("editerArea"));
		qs.inputQuestion(q);//insert하고.
		
		forward.setPath("list.qu");
		return forward;
	}

}
