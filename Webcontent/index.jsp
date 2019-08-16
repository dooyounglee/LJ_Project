<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="js/util.js"></script>
</head>
<body>
<%

%>
<h1>오늘 ${todayCount } 전체 ${totalCount }</h1>
<center><h1>현재 회원 수 :<span id=counter1></span></h1></center>
<center><h1>현재 질문 수 :<span id=counter2></span></h1></center>
<center><h1>현재 답변 수 :<span id=counter3></span></h1></center>

<fieldset style="border:4px solid;display:block;width:500px;height:46px;margin:auto;">
	<input type=text class="input text" style="width:434px;height:38px;border-width:0px;padding:0px"/>
	<button class="btn" style="float:right;">검색</button>
</fieldset>


<script>
new numberCounter("counter1", 135);
new numberCounter("counter2", 358);
new numberCounter("counter3", 425);
</script>

</body>
</html>