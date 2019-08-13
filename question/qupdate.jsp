<%@page import="com.lj.question.vo.QuestionVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input,textarea,select,table{
	width:100%
}
td{
	width:33.33%
}
</style>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script>
$(document).on('click','#large',function() {
	large = $('#large option:selected').index() || '0'
	$.ajax({
		url:'getmiddle.qu',
		async:false,
		type:'POST',
		data:{
			large:large,
		},
		success: function(data) {
			var arr=data.split(',');
			$('#middle option').remove();
			$('#middle').show();
			$('#small').hide();
			for (i = 0; i < arr.length; i++) {
				$('#middle').append(
						'<option value="'+arr[i]+'" >'
								+ arr[i] + '</option>')
			}
		},
	});
})
$(document).on('click','#middle',function() {
	large = $('#large option:selected').index() || '0'
	middle = $('#middle option:selected').index() || '0'
	$.ajax({
		url:'getsmall.qu',
		async:false,
		type:'POST',
		data:{
			large:large,
			middle:middle,
		},
		success: function(data) {
			var arr=data.split(',');
			$('#small option').remove();
			$('#small').show();
			for (i = 0; i < arr.length; i++) {
				$('#small').append(
						'<option value="'+arr[i]+'" >'
								+ arr[i] + '</option>')
			}
		},
	});
})
</script>
</head>
<body>
${mem}
<div style="width:800px;margin:auto;">
	<form autocomplete=off action="qupdate_ok.qu" method="post">
		<%-- <label>q_no</label><input type=text name="q_no" value="${q.q_no }" /><br> --%>
		<h1>제목</h1>
		<input type=text name="title" value="${q.title }"/><br>
		<br>
		<h1>내용</h1>
		<textarea name="content">${q.content }</textarea>
		<br>
		<br>
		<%-- <label>writer</label><input type=text name="writer" value="${q.writer }" readonly/><br> --%>
		<table><tr>
		<td><select name="large" id="large" size="6">
			<c:forEach var="i" items="${arr_large }" >
				<c:choose>
					<c:when test="${i eq q.cate1 }" >
						<option value="${i}" selected > ${i}</option>
					</c:when>
					<c:when test="${i != q.cate1 }" >
						<option value="${i}" > ${i}</option>
					</c:when>
				</c:choose>
			</c:forEach>
		</select></td>
		<td><select name="middle" id="middle" size="6">
			<c:forEach var="i" items="${arr_middle }" >
				<c:choose>
					<c:when test="${i eq q.cate2 }" >
						<option value="${i}" selected > ${i}</option>
					</c:when>
					<c:when test="${i != q.cate2 }" >
						<option value="${i}" > ${i}</option>
					</c:when>
				</c:choose>
			</c:forEach>
		</select></td>
		<td><select name="small" id="small" size="6">
			<c:forEach var="i" items="${arr_small }" >
				<c:choose>
					<c:when test="${i eq q.cate3 }" >
						<option value="${i}" selected > ${i}</option>
					</c:when>
					<c:when test="${i != q.cate3 }" >
						<option value="${i}" > ${i}</option>
					</c:when>
				</c:choose>
			</c:forEach>
		</select></td>
		</tr></table>
		<br>
		<input type=submit value="질문수정" class="btn" style="width:50%;float:left"/>
		<input type=button value="취소" class="btn" onclick="history.back();" style="width:50%"/>
	</form>
</div>
</body>
</html>