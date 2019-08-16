<%@page import="com.lj.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <!-- JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>
</head>
<body>
${todayCount } / ${totalCount }
<%-- <%
	request.setCharacterEncoding("UTF-8");
	
	//세션 확인
	MemberVO mem = (MemberVO)session.getAttribute("mem");
	
%> --%>
    <!-- header -->
 <!--    <div class="jumbotron text-center">
        <h1>LJ HEADER</h1>
        <p>LJ 지식in</p>
    </div> -->
    
     <nav class="navbar navbar-expand-sm navbar-dark bg-dark" style="position:fixed;width:100%">
        <a href="/01_lj" class="navbar-brand">궁금해yo</a>
        <!-- Toggle Button -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item"><a href="list.qu" class="nav-link">Question</a></li>
                <li class="nav-item"><a href="chat/chat.jsp" class="nav-link">Chat</a></li>
                <li class="nav-item"><a href="myPage.mem" class="nav-link">Mypage</a></li>
                <li class="nav-item"><a href="#" class="nav-link">SiteMap</a></li>
                <li class="nav-item"><a href="join.mem" class="nav-link">Join</a></li>
            </ul>    
        </div>
        <div align="right" style="color: white">
        	<c:choose>
        		<c:when test="${empty sessionScope.mem}">
        			<button class="btn" onclick="location.href='login.mem'">Login</button>
        		</c:when>
        		<c:otherwise>
        			<a href="myPage.mem">${mem.id }</a> 님  환영합니다!    
        			<button class="btn" onclick="location.href='logout.mem'">Logout</button> 
    			</c:otherwise>
        	</c:choose>
        </div>  
    </nav>
    <div  style="height:56px"></div>
     
 




</body>
</html>