<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#list #list_body li{
	background-color:#EEE;
	list-style:none;
	border-width:2px 0px 0px 2px;
	border-style:solid;
	border-color:white;
	padding:10px 50px 10px 50px;
	
}
#list #list_body li:hover{
	background-color:#DDD;
}
#list_body a{
	color:#000000;
	display:block;
}
#list_body a:hover{
	text-decoration:none;
	color:#000000;
}
</style>
</head>
<body>
${mem}
<a href="write.qu">질문하기</a><br>
<div id=search style="width:800px;float:center;margin:auto;">
	<fielsset style="border:4px solid;display:block;width:798px;height:46px;margin:auto;">
		<input type=text class="input text" style="border-width:0px;padding:0px"/>
		<button class="btn" style="float:right;">검색</button>
	</fielsset>
</div>
<hr>
<div id=list style="width:800px;float:center;margin:auto;">
	<div id=list_body>
	<h1>오늘 올라온 질문들</h1>
	<c:forEach var="q" items="${qlist }">
		<li><a href="read.qu?q_no=<c:out value="${q.q_no }" />">
			<h2><b><c:out value="${q.title }" /></b></h2><br>
			<c:choose>
				<c:when test="${q.content.length()>20 }" >
					<c:out value="${q.content.substring(0,20) }" />.......
				</c:when>
				<c:otherwise>
					<c:out value="${q.content }" />
				</c:otherwise>
			</c:choose>
			<br>
			<strong><c:out value="${q.writer }" /></strong><br>
			<small style="float:right"><c:out value="${q.reg_date }" /></small>
			<small style="float:right"><fmt:formatDate value="${q.reg_date }" pattern="yyyy.MM.dd hh:mm:ss"/></small>
			<c:out value="${q.answerCount }" /> 개 답변
		</a></li>
	</c:forEach>
	</div>
</div>
</body>
</html>
