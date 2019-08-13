package com.lj.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lj.common.Action;
import com.lj.common.ActionForward;
import com.lj.question.service.QuestionService;
import com.lj.question.vo.QuestionVO;

public class qupdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		QuestionVO q=new QuestionVO();
		String q_no=request.getParameter("q_no");
		q.setQ_no(Integer.parseInt(q_no));
		q.setWriter(request.getParameter("writer"));
		q.setTitle(request.getParameter("title"));
		q.setContent(request.getParameter("content"));
		q.setCate1(request.getParameter("large"));
		q.setCate2(request.getParameter("middle"));
		q.setCate3(request.getParameter("small"));
		new QuestionService().updateQuestion(q);
		
		forward.setPath("read.qu?q_no="+q_no);
		return forward;
	}

}
