<%@page import="java.sql.Date"%>
<%@page import="com.lj.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
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

<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>
    
    
<!-- <link  href="summernote/dist/summernote.css" rel="stylesheet" type="text/css" />
<script src="summernote/dist/summernote.min.js" type="text/javascript"></script> -->

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
	console.log(large+' '+middle)
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
$(document).on('click','#small',function() {
	large = $('#large option:selected').index() || '0'
	middle = $('#middle option:selected').index() || '0'
	small = $('#small option:selected').index() || '0'
	console.log(large+''+middle+''+small)
});
</script>
</head>
<body>
${mem}
<div style="width:800px;margin:auto;">
	<form autocomplete=off action="qwrite_ok.qu" method="post">
		<h1>제목</h1>
		<input type=text name="title"/><br>
		<br>
		<h1>내용</h1>
		<textarea  rows="10" cols="128" id="editerArea" name="editerArea" style="text-align:center"></textarea>
		<!-- <textarea name="content"></textarea> -->
		<br>
		<br>
		<%-- <label>writer</label><input type=text name="writer" value="${mem.id}" readonly/><br> --%>
		<input type=hidden name="writer" value="${mem.id}" />
		<table><tr>
		<td><select name="large" id="large" size="6"></select></td>
		<td><select name="middle" id="middle" size="6"></select></td>
		<td><select name="small" id="small" size="6"></select></td>
		</tr></table>
		<br>
		<input type=submit value="질문등록" class="btn"/>
	</form>
</div>
<script>
var arr_large = [ '교육', '건강', 'IT', '생활', '경제', '문화' ];
for (i = 0; i < arr_large.length; i++) {
	$('#large').append(
			'<option value="'+arr_large[i]+'" >' + arr_large[i]
					+ '</option>')
}
</script>



<form method="post">
  <textarea id="summernote" name="editordata"></textarea>
</form>
<script>
/* $(document).ready(function(){
	$('.summernote').summernote();
}) */

$(document).ready(function() {
	  $('#summernote').summernote();
	});

</script>
</body>
</html>