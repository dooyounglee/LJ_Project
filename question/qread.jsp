<%@page import="java.util.Date"%>
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
.answer li{
	list-style:none;
}
.question_reply li{
	list-style:none;
}
</style>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
function reg_ans(){
	$.ajax({
		url:'write.ans',
		async:false,
		type:'POST',
		data:{
			writer:'${mem.id}',
			q_no:'${q.q_no}',
			content:$('#answer_content').val(),
		},
		//contentType : "application/json; charset=utf-8",
		//contentType : "text/html;charset=utf-8",
		//dataType:'json',
		//dataType:'charset=utf8;',
		success: function(data) {
			var a=JSON.parse(data)
			console.log(a);
			var str="";
			str+='<li data-a_no="'+a.a_no+'" ><div>'
			if($('#login_ig').val()==a.writer){
				str+='<button class="selection" >채택</button><br>'
			}
			str+='<b>'+a.writer+'</b><br>'
			str+='<span class="content">'+a.content+'</span><br>'
			str+='<small>'+a.update_date+'</small>'
			str+='<img src="img/pencil.png" class="update_ans" style="width:25px">'
			str+='<img src="img/x-delete.png" class="del_ans" data-a_no="${a.a_no }" style="width:25px">'
			str+='<img src="img/reply.jpg" class="open-answerreply" data-a_no="'+a.a_no+'" style="width:25px">'
			str+='<span class="replyCount">0</span>'
			str+='<button class="btn like_ans" style="float:right">좋아요 0</button>'
			str+='</div><div class="ans_reply" data-a_no="$'+a.a_no+'"></div><hr></li>'
			$('.answer_list_body').append(str);
			$('#answer_content').val("");
		},
	});
}

function reg_question_reply(){
	$.ajax({
		url:'write.qur',
		async:false,
		type:'POST',
		data:{
			writer:'${mem.id}',
			q_no:'${q.q_no}',
			content:$('#question_reply_content').val(),
		},
		success: function(data) {
			var a=JSON.parse(data)
			console.log(a);
			var str="";
			str+='<li data-rq_no="'+a.rq_no+'"><hr>'
			//str+='('+a.rq_no+' '+a.q_no+') '+a.id+'<br>'
			str+='<b>'+a.id+'</b><br>'
			str+=a.content+'<br>'
			str+='<small>'+a.reg_date+'</small>'
			str+='<button class="btn del_question_reply">삭제</button></li>'
			$('.question_reply_body').append(str)
			$('#question_reply_content').val("")
		}
	});
}
</script>
</head>
<body>
${mem}
<input type=hidden id="login_id" value="${mem.id}" />
<a href="list.qu">리스트로 가기</a><br>
<div id=question style="background-color:white">
	<div id=question_body style="width:800px;margin:auto;padding-bottom:50px;">
		<%-- <label>q_no:</label>${q.q_no}<br> --%>
		<h1>${q.title}</h1>
		<b>작성자 : ${q.writer}</b><span style="float:right">조회수 : ${q.count}</span><br>
		<hr>
		<h4>${q.content}</h4><br><br>
		${q.cate1}>${q.cate2}>${q.cate3}<br>
		<small>${q.reg_date}</small><br>
		<hr>
		<%-- <label>update:</label>${q.update_date}<br> --%>
		<c:if test="${mem.id eq q.writer }" >
			<c:if test="${answerCount==0}">
			<a id=btn_edit href="update.qu?q_no=${q.q_no }">수정</a><!-- 질문이 달렸다면 수정불가 -->
			</c:if>
		<a href="delete.qu?q_no=${q.q_no }">삭제</a>
		</c:if>
		
		<c:if test="${replyCount>0 }">
			<button class="btn open-questionreply" >댓글<c:out value="${replyCount }"/></button>
		</c:if>
		<c:if test="${replyCount==0 }">
			<button class="btn open-questionreply" >댓글쓰기</button>
		</c:if>
		<div class="question_reply">
			<c:if test="${mem != null }">
				<div class="question_reply_writeform" style='padding:15px 0px 15px 52px'><hr>
	<!-- 				<input type=text id=question_reply_content />
					<input type=button value="댓글등록" onclick="reg_question_reply()"/> -->
					<fieldset style="border:4px solid;display:block;width:748px;height:46px;margin:auto;">
					<input type=text id=question_reply_content style="width:680px;height:38px;border-width:0px;padding:0px"/>
					<button class="btn" style="float:right;" onclick="reg_question_reply()">등록</button>
					</fieldset>
				
				</div>
			</c:if>
			<div class="question_reply_body" style='padding:15px 0px 15px 52px'>
				<c:forEach var="re" items="${qrlist }">
					<li data-rq_no="${re.rq_no }"><hr>
						<%-- (${re.rq_no } ${re.q_no }) ${re.id }<br> --%>
						<b>${re.id }</b><br>
						${re.content }<br>
						<small><fmt:formatDate type="both" value="${re.reg_date }" pattern="yyyy.MM.dd HH:mm:ss" /></small> 
						<c:if test="${mem.id eq re.id }">
							<button class="btn del_question_reply">삭제</button>
						</c:if>
					</li>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<script>
$(document).on('click','.del_question_reply',function(){
	var this_li=$(this).closest('li')
	var rq_no=this_li.data('rq_no')
  	$.ajax({
		url:'delete.qur',
		async:false,
		type:'POST',
		data:{
			rq_no:rq_no,
		},
		success: function(data) {
			this_li.remove();
		},
	});
})
</script>
<div id=answer style="background-color:#EEE;padding-top:20px;">
	<c:if test="${isSelected eq null }">
		<c:if test="${mem!=null }">
			<div id="answer_write_form" style="width:800px;margin:auto;">
<%-- 				<label>지금 로그인한 id:</label><input type=text name=writer value="${mem.id}" readonly /><br>
				<label>q_no:</label>${q.q_no}<br>
				<input type=text id="answer_content" style="width:90%;" placeholder="내용을 입력하세요." />
				<input type=button value="답변등록" onclick="reg_ans()" /> --%>
				<fieldset style="border:4px solid;display:block;width:800px;height:46px;margin:auto;">
					<textarea id="answer_content" style="width:730px;height:38px;border-width:0px;padding:0px" placeholder="답변을 입력하세요."></textarea>
					<button class="btn" style="float:right;" onclick="reg_ans()">답변</button>
				</fieldset>
			<hr>
			</div>
		</c:if>
	</c:if>
	
	<div class="answer">
	<div class="answer_list_body" style="width:800px;margin:auto;">
		<c:forEach var="a" items="${alist}">
			<li data-a_no="${a.a_no }"><div>
				<%-- (<c:out value="${a.a_no }" />
				<c:out value="${a.q_no }" />) --%>
					<c:choose>
						<c:when test="${isSelected eq null }">
							<c:if test="${q.writer eq mem.id }">
								<button class="selection" style="">채택</button><br>
							</c:if>
						</c:when>
						<c:when test="${isSelected != null }">
							<c:if test="${isSelected.a_no eq a.a_no }">
								<img src="img/medal-icon.png" style="width:50px;"><br>
							</c:if>
						</c:when>
					</c:choose>
				<b><c:out value="${a.writer }" /></b><br>
				<span class="content"><c:out value="${a.content }" /></span><br>
				<%-- (<c:out value="${a.reg_date }" />) --%>
				<small><c:out value="${a.update_date }" /></small>
 				<%-- <fmt:parseDate value="2016-12-24 09:03:23" pattern="yyyy.MM.dd hh:mm:ss" var="date" />${data }<br> --%>
				<c:if test="${mem.id eq a.writer }" >
					<c:choose>
						<c:when test="${isSelected eq null }">
							<!-- <button class="update_ans" >수정</button> -->
							<img src="img/pencil.png" class="update_ans" style="width:25px">
						</c:when>
					</c:choose>
					<%-- <button class="btn del_ans" data-a_no="${a.a_no }" >삭제</button> --%>
					<img src="img/x-delete.png" class="del_ans" data-a_no="${a.a_no }" style="width:25px">
				</c:if>
				<%-- <c:if test="${mem != null }" > --%>
					<%-- <button class="open-answerreply" data-a_no="${a.a_no }" >댓글</button> --%>
					<img src="img/reply.jpg" class="open-answerreply" data-a_no="${a.a_no }" style="width:25px">
					<span class="replyCount">${a.replyCount }</span>
				<%-- </c:if> --%>
				
				<c:choose>
				<c:when test="${mem != null }" >
					<c:choose>
						<c:when test="${a.whoLiked eq mem.id }" >
							<button class="btn like_ans" style="float:right">좋아요★ ${a.likeCount }</button>
						</c:when>
						<c:when test="${a.whoLiked != mem.id }" >
							<button class="btn like_ans" style="float:right">좋아요 ${a.likeCount }</button>
						</c:when>
					</c:choose>
				</c:when>
				<c:otherwise>
					<button class="btn" style="float:right" disabled>좋아요 ${a.likeCount }</button>
				</c:otherwise>
				</c:choose>
			</div>
			<div class="ans_reply" data-a_no="${a.a_no }">
				
			</div><hr></li>
			
		</c:forEach>
	</div>
	
	</div>
</div>
<script>
$('.question_reply').hide()
$(document).on('click','.open-questionreply',function(){
	$(this).removeClass('open-questionreply')
	$(this).addClass('close-questionreply')
	$('.question_reply').show()
});
$(document).on('click','.close-questionreply',function(){
	$(this).removeClass('close-questionreply')
	$(this).addClass('open-questionreply')
	$('.question_reply').hide()
});
</script>
<script>
$('.ans_reply').hide()
$(document).on('click','.open-answerreply',function(){
	$(this).removeClass('open-answerreply')
	$(this).addClass('close-answerreply')
	var a_no=$(this).closest('li').data('a_no')
	var this_ans_reply=$(this).closest('li').children('.ans_reply')
	
	var str=''
	if($('#login_id').val()==null || $('#login_id').val()==""){
	}else{
		str+='<hr><div class="ans_reply_writeform" style="padding:15px 0px 15px 52px">'
		str+='<fieldset style="border:4px solid;display:block;width:748px;height:46px;margin:auto;">'
		str+='<input type=text id="ans_reply_content" style="width:680px;height:38px;border-width:0px;padding:0px"/>'
		str+='<button class="btn reg_answer_reply" style="float:right;">등록</button></fieldset>'
		//str+='<input type=text id=ans_reply_content />'
		//str+='<input type=button value="댓글등록" class="reg_answer_reply"/>'
		str+='</div>'
	}
	
	str+='<div class="ans_reply_body" style="padding:15px 0px 15px 52px">'
	str+='</div>'
	this_ans_reply.append(str);
	
	$.ajax({
		url:'list.ansr',
		async:false,
		type:'POST',
		data:{
			a_no:a_no,
		},
		success: function(data) {
			if(data.indexOf('\}')>-1){
				var a=JSON.parse(data)
				console.log(a);
				for(i=0;i<a.length;i++){
					var strr='<li data-ra_no='+a[i].ra_no+'><hr>'
					//strr+='('+a[i].ra_no+' '+a[i].a_no+') '+a[i].id+'<br>'
					strr+='<b>'+a[i].id+'</b><br>'
					strr+=a[i].content+'<br>'
					strr+='<small>'+a[i].reg_date+'</small>'
					if($('#login_id').val()==a[i].id){
						//strr+='<button class="del_ans_reply">삭제</button>'
						strr+='<img src="img/x-delete.png" class="del_ans_reply" style="width:25px">'
					}
					strr+='</li>'
					this_ans_reply.children('.ans_reply_body').append(strr)
				}	
			}
		},
	});
	this_ans_reply.show()
});
$(document).on('click','.del_ans_reply',function(){
	var this_li=$(this).closest('li')
	var ra_no=this_li.data('ra_no')
	var this_li_display=this_li.parent().closest('li').children('div').eq(0)
	var replyCount=parseInt(this_li_display.children('span.replyCount').text());
	
	$.ajax({
		url:'delete.ansr',
		async:false,
		type:'POST',
		data:{
			ra_no:ra_no,
		},
		success: function(data) {
			this_li.remove();
			this_li_display.children('span.replyCount').text(replyCount-1)
		},
	});
})
$(document).on('click','.close-answerreply',function(){
	$(this).removeClass('close-answerreply')
	$(this).addClass('open-answerreply')
	var this_ans_reply=$(this).closest('li').children('.ans_reply')
	this_ans_reply.hide()
	this_ans_reply.children().remove();
});
</script>
<script>
$(document).on('click','.reg_answer_reply',function(){
	var a_no=$(this).closest('li').data('a_no')
	var txtbox_content=$(this).prev()
	var content=txtbox_content.val()
	var this_ans_reply=$(this).closest('li').children('.ans_reply')
	var this_li_display=$(this).closest('li').children('div').eq(0)
	var replyCount=parseInt(this_li_display.children('span.replyCount').text())
	
  	$.ajax({
		url:'write.ansr',
		async:false,
		type:'POST',
		data:{
			writer:$('#login_id').val(),
			a_no:a_no,
			content:content,
		},
		success: function(data) {
 			var a=JSON.parse(data)
			console.log(a);
 			var str='<li data-ra_no='+a.ra_no+'><hr>'
	  		str+='<b>'+a.id+'</b><br>'
			str+=a.content+'<br>'
			str+='<small>'+a.reg_date+'</small>'
/*  		str+='('+a.ra_no+' '+a.a_no+') '+a.id+'<br>'
			str+=a.content+'<br>'
			str+=a.reg_date  */
			//str+='<button class="del_ans_reply">삭제</button>'
			str+='<img src="img/x-delete.png" class="del_ans_reply" style="width:25px">'
			str+='</li>'
			this_ans_reply.children('.ans_reply_body').append(str)
			txtbox_content.val("")
			this_li_display.children('span.replyCount').text(replyCount+1);
		}
	});
})
</script>
<script>
$(document).on('click','.del_ans',function(){
	var a_no=$(this).data('a_no')
	var this_btn=$(this)
 	$.ajax({
		url:'delete.ans',
		async:false,
		type:'POST',
		data:{
			a_no:a_no,
		},
		//contentType : "application/json; charset=utf-8",
		//contentType : "text/html;charset=utf-8",
		//dataType:'json',
		//dataType:'charset=utf8;',
		success: function(data) {
			this_btn.closest('li').remove();
		},
	});
})
</script>
<script>
$(document).on('click','.selection',function(){
	var this_btn=$(this)
	var this_div=$(this).closest('div')
	var this_li=$(this).closest('li')
	var a_no=this_li.data('a_no')
	$.ajax({
		url:'selection.ans',
		async:false,
		type:'POST',
		data:{
			a_no:a_no,
		},
		//contentType : "application/json; charset=utf-8",
		//contentType : "text/html;charset=utf-8",
		//dataType:'json',
		//dataType:'charset=utf8;',
		success: function(data) {
			if(data=="add"){
				$('.selection').hide();
				$('.update_ans').remove();
				$('#answer_write_form').hide();
				this_btn.remove();
				this_div.prepend('<img src="img/medal-icon.png" style="width:50px;"><br>')
				//this_btn.show();
				//this_btn.text("채택★");
			}/* else if(data=="remove"){
				this_btn.text("채택");
				$('.selection').show();
			} */
		},
	});
})
</script>
<script>
$(document).on('click','.update_ans',function(){
	var this_btn=$(this)
	var this_li=$(this).closest('li')
	var a_no=this_li.data('a_no')
	$(this).closest('div').hide()
	var content=$(this).parent().children('span').text()
	var str=''
	str+='<div class="update_ans_writeform">'
	str+='<input type=text value="'+content+'" />'
	str+='<button class="btn update_ans_ok" >확인</button>'
	str+='<button class="btn update_ans_cancel" >취소</button></div>'
	this_li.prepend(str);

})
$(document).on('click','.update_ans_ok',function(){
	//쓴사람만 수정할 수 있도록. //쓴사람만 수정버튼이 뜰꺼라 무시해도 될꺼 같은데
	var this_btn=$(this)
	var this_txtbox=$(this).prev()
	var this_li=$(this).closest('li')
	var this_display=this_li.children(2);
	var this_div=$(this).closest('div')
	var a_no=this_li.data('a_no')
  	$.ajax({
		url:'update.ans',
		async:false,
		type:'POST',
		data:{
			a_no:a_no,
			content:this_txtbox.val(),
		},
		success: function(data) {
 			var a=JSON.parse(data)
			console.log(a);
 			this_display.children('b').text(a.writer);
 			this_display.children('small').text(a.update_date);
 			this_display.children('span .content').text(a.content);
 			this_display.children('span .replyCount').text(a.content);
 			this_div.remove();
 			this_display.show();
 			
 			//this_li.children('div').remove();
/* 			var str="";
			str+="<div>"
			str+='('+a.a_no+' '
			str+=''+a.q_no+') <button class="selection" >채택</button><br>'
			str+=''+a.writer+'님 작성한 답변입니다.<br>'
			str+=''+a.content+'<br>'
			str+='('+a.reg_date+')'
			str+=''+a.update_date+'<br>'
			if($('#login_id').val()==a.writer){
				str+='<button class="update_ans" >수정</button>'
				str+='<button class="del_ans" data-a_no="${a.a_no }" >삭제</button>'	
			}
			if($('#login_id').val()==null||$('#login_id').val()==''){
				
			}else{
				if($('#login_id').val()==a.whoLiked){
					str+='<button class="like_ans" style="float:right">좋아요★ '+a.likeCount+'</button>'
				}else{
					str+='<button class="like_ans" style="float:right">좋아요 '+a.likeCount+'</button>'
				}
			}
			str+='</div>'
			$('.answer_body').append(str);
 			this_li.append(str) */
 			///////////////////////////////////////////////////////
/*  			var str="";
			str+='<li data-a_no="'+a.a_no+'" ><div>'
			str+='<button class="selection" >채택</button><br>'/////아무나 뜨면 안돼잖아?? reg_ans()도 마찬가지고
			str+='<b>'+a.writer+'</b><br>'
			str+='<span>'+a.content+'</span><br>'
			str+='<small>'+a.update_date+'</small>'
			str+='<button class="btn update_ans" >수정</button>'
			str+='<button class="btn del_ans" data-a_no="'+a.a_no+'" >삭제</button>'	
			str+='<img src="img/reply.jpg" class="open-answerreply" data-a_no="'+a.a_no+'" style="width:25px">'
			str+='<button class="btn like_ans" style="float:right">좋아요 0</button>'
			str+='</div><div class="ans_reply" data-a_no="$'+a.a_no+'"></div></li>'
			$('.answer_list_body').append(str);
			$('#answer_content').val("");*/
		},
	}); 
	//var this_div=$(this).closest('div').remove()
	
})
$(document).on('click','.update_ans_cancel',function(){
	var this_div=$(this).closest('div')
	this_div.next().show()
	this_div.remove()
})
</script>
<script>
$(document).on('click','.like_ans',function(){
	var this_btn=$(this)
	var this_li=$(this).closest('li')
	var a_no=this_li.data('a_no')
	var likeCount=parseInt(this_btn.text().substring(this_btn.text().indexOf(" ")+1));
 	$.ajax({
		url:'like.ans',
		async:false,
		type:'POST',
		data:{
			a_no:a_no,
			login_id:$('#login_id').val(),
		},
		success: function(data) {
			
 			if(data=="add"){
				this_btn.text("좋아요★ "+(likeCount+1));//count는 나중에..
			}else if(data=="remove"){
				this_btn.text("좋아요 "+(likeCount-1));
			} 
		},
	});
})
</script>
</body>
</html>