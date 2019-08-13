package com.lj.question.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lj.answer.service.AnswerService;
import com.lj.common.Action;
import com.lj.common.ActionForward;
import com.lj.member.vo.MemberVO;
import com.lj.question.action.qdelete;
import com.lj.question.action.qupdate;
import com.lj.question.action.qwrite;
import com.lj.question.service.QuestionService;
import com.lj.question.vo.QuestionVO;
import com.lj.questionreply.service.QuestionReplyService;

@WebServlet("*.qu")// http://localhost:8080/01_lj/list.qu
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String[] large={"교육","건강","IT","생활","경제","문화"};
	private String[][] middle;
	private String[][][] small;
	
	{
		  middle= new String[large.length][];
		  middle[0]= new String[]{"유치원","초중고","평생교육"};
		  middle[1]= new String[]{"질병","식습관","운동"};
		  middle[2]= new String[]{"자바","백엔드","프론트엔드"};
		  middle[3]= new String[]{"날씨","요리","패션"};
		  middle[4]= new String[]{"주식","부동산","가계부"};
		  middle[5]= new String[]{"영화","애니","음악","웹툰"};
		  
		  small=new String[large.length][][];
		  for(int i=0;i<large.length;i++) {
		   small[i]=new String[middle[i].length][];
		  }
		  small[0][0]= new String[]{"유치원선생님","유치원생활"};
		  small[0][1]= new String[]{"초등임용","중등임용","초등학교생활"};
		  small[0][2]= new String[]{"평생1","평생2"};
		  small[1][0]= new String[]{"당뇨","디스크","암"};
		  small[1][1]= new String[]{"다이어드","단백질","단수화물"};
		  small[1][2]= new String[]{"웨이트","유산소"};
		  small[2][0]= new String[]{"객체지향","자바스크립트","이클립스"};
		  small[2][1]= new String[]{"데이터베이스","스프링","JSP/Servlet"};
		  small[2][2]= new String[]{"vuejs","javascript","angular","react"};
		  small[3][0]= new String[]{"오늘날씨","주간날씨","내일날씨"};
		  small[3][1]= new String[]{"중국요리","한국요리","스페인요리"};
		  small[3][2]= new String[]{"패션쇼","모델"};
		  small[4][0]= new String[]{"떡락","환율","GDP"};
		  small[4][1]= new String[]{"투기","아파트"};
		  small[4][2]= new String[]{"재무관리","회계사"};
		  small[5][0]= new String[]{"공포","멜로","미스테리","코미디"};
		  small[5][1]= new String[]{"스포츠","19금","추리","병맛"};
		  small[5][2]= new String[]{"발라드","랩","댄스"};
		  small[5][3]= new String[]{"무한동력","덴마","좀비딸","신과함께"};
	}
	
	public QuestionController() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=utf-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		ActionForward forward = null;
		Action action = null;
		HttpSession session=null;

		session = request.getSession();
		System.out.println("새로운거니?: "+session.isNew()+new Date());
		System.out.println("세션 시작: "+new Date());
		System.out.println("세션 시간조정 5초"+new Date());
		System.out.println(session.getId()+new Date());
		System.out.println(""+session.getMaxInactiveInterval()+new Date());
		
		if (command.equals("/list.qu")) {
			request.setAttribute("qlist", new QuestionService().getList());
			forward = new ActionForward();
			forward.setPath("/question/qlist.jsp");
			
		} else if (command.equals("/write.qu")) {
			forward = new ActionForward();
			session=request.getSession();
			if(session.getAttribute("mem")==null) {
				forward.setPath("/login.mem");
			}else {
				forward.setPath("/question/qwrite.jsp");
			}
			
		} else if (command.equals("/qwrite_ok.qu")) {
			action=new qwrite();
			try {
				forward = action.execute(request, response);
				forward.setRedirect(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/read.qu")) {
			String q_no=request.getParameter("q_no");
			session = request.getSession();
			MemberVO mem=(MemberVO)session.getAttribute("mem");
			new QuestionService().plus1Count(Integer.parseInt(q_no));
			request.setAttribute("q", new QuestionService().getQuestion(q_no));
			request.setAttribute("replyCount",new QuestionReplyService().getReplyCount(q_no));
			request.setAttribute("answerCount", new AnswerService().getAnswerCount(q_no));
			request.setAttribute("alist", new AnswerService().getList(Integer.parseInt(q_no),mem));
			request.setAttribute("isSelected", new AnswerService().isSelected(Integer.parseInt(q_no)));
			request.setAttribute("qrlist", new QuestionReplyService().getList(Integer.parseInt(q_no)));
			forward = new ActionForward();
			forward.setPath("/question/qread.jsp?q_no="+q_no);
			
		} else if (command.equals("/delete.qu")) {
			//String q_no=request.getParameter("q_no");
			action=new qdelete();
			try {
				forward = action.execute(request, response);
				forward.setRedirect(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/update.qu")) {

			String q_no=request.getParameter("q_no");
			QuestionVO q=new QuestionService().getQuestion(q_no);
			request.setAttribute("q", q);
			int large=0,middle=0,small=0;
			request.setAttribute("arr_large", this.large);
			for(int i=0;i<this.large.length;i++) {
				if(this.large[i].equals(q.getCate1())) {
					large=i;break;
				}
			}
			request.setAttribute("arr_middle", this.middle[large]);
			for(int i=0;i<this.middle[large].length;i++) {
				if(this.middle[large][i].equals(q.getCate2())) {
					middle=i;break;
				}
			}
			request.setAttribute("arr_small", this.small[large][middle]);
			for(int i=0;i<this.small[large][middle].length;i++) {
				if(this.small[large][middle][i].equals(q.getCate3())) {
					small=i;break;
				}
			}
			
			forward = new ActionForward();
			forward.setPath("/question/qupdate.jsp?q_no="+q_no);
			
		} else if (command.equals("/qupdate_ok.qu")) {
			action=new qupdate();
			try {
				forward = action.execute(request, response);
				forward.setRedirect(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/getmiddle.qu")) {
			int large=Integer.parseInt(request.getParameter("large"));
			PrintWriter out = response.getWriter();
			String[] tmp=middle[large];
			String str=tmp[0];
			for(int i=1;i<tmp.length;i++) {
				str+=","+tmp[i];
			}
			out.print(str);
			return;
		} else if (command.equals("/getsmall.qu")) {
			int large=Integer.parseInt(request.getParameter("large"));
			int middle=Integer.parseInt(request.getParameter("middle"));
			PrintWriter out = response.getWriter();
			String[] tmp=small[large][middle];
			String str=tmp[0];
			for(int i=1;i<tmp.length;i++) {
				str+=","+tmp[i];
			}
			out.print(str);
			return;
		}
		

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}

	}


}
