package com.lj.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lj.common.Action;
import com.lj.common.ActionForward;
import com.lj.question.service.QuestionService;

public class qdelete implements Action {

	private QuestionService qs=new QuestionService();
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		String q_no=request.getParameter("q_no");
		int result=qs.deleteQuestion(q_no);//insert하고.
		forward.setPath("list.qu");
		return forward;
	}

}
