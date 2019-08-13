<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 네이버에디터 -->
<script src="/resources/js/naver_editor/js/service/HuskyEZCreator.js"></script>
<script>
$(document).ready(function() {
	//네이버에디터
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	   oAppRef: oEditors
	 , elPlaceHolder: "editerArea" // element ID
	 , sSkinURI: "/resources/js/naver_editor/SmartEditor2Skin.html"
	 , fCreator: "createSEditor2"
	});
});
</script>
</head>
<body>

	<textarea rows="5" cols="50" id="editerArea" name="editerArea"></textarea>
</body>
</html>