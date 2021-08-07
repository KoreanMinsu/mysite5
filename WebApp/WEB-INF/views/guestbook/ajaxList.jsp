<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- header & nav-->
		
		<div id="container" class="clearfix">
			
			<c:import url="/WEB-INF/views/includes/aside_Guestbook.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</th>
									<td><input id="input-uname" type="text" name="name" value=""></td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</th>
									<td><input id="input-pass" type="password" name="password" value=""></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>
							<button id="btnshow" type="button">보이기</button>
							<button id="btnhide" type="button">숨기기</button>

						</table>

					</form>
					
					
					<div id = "listArea">
						<!-- jquery 리스트 영역 -->
					</div>
					

				</div>
				<!-- //guestBook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- footer -->
		
	</div>
	<!-- //wrap -->
	
	<!-- 모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달 -->
	<!-- 모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달 -->
	
	<!-- 삭제 모달창 -->
	<div id="delModal" class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">방명록 삭제</h4>
	      </div>
	      <div class="modal-body">
	      	
	      	<label for="modalPassword" >비밀번호</label >
	      	<input id="modalPassword" type="password" name="password" value="">
	        
	        <input type="text" name="no" value="">
	        
	      </div>
	      <div class="modal-footer">
       	        <button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!-- 모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달 -->
	<!-- 모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달모달 -->

</body>



<script type="text/javascript">

//화면 로딩직전
$(document).ready(function(){
	console.log("화면 로딩직전");
	//ajax 요청
	$.ajax({
			
		url : "${pageContext.request.contextPath }/api/guestbook/list",		
		type : "post",
		//contentType : "application/json",
		//data : {name: "김민수"},

		//dataType : "json",
		success : function(guestList){
			/*성공시 처리해야될 코드 작성*/
			console.log(guestList);
			
			//화면에 그리기
			for(var i=0, i<guestList.length; i++){
				render(guestList[i], "down"); //방명록 글 1개씩 추가(렌더링 함수이용)
		
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});

//로딩 이후
//등록 버튼 클릭시
$("#btnSubmit").on("click", function(){
	event.preventDefault();
	console.log("등록버튼 클릭");
	
	/*
	//name 읽어오기
	var userName = $("#input-uname").val();
	console.log(userName);
	
	//password 읽어오기
	var password = $("#input-pass").val();
	console.log(password);
	
	//content 읽어오기
	var content = $("[name=content]").val();
	console.log(content);
	
	*/
	
	var guiestbookVo = {
			name : $("#input-uname").val();
		,	password : $("#input-pass").val();
		,	content : $("[name=content]").val();
	};
	
	
	//ajax 방식으로 서버에 전송
	$.ajax({
		
		//url : "${pageContext.request.contextPath }/api/guestbook/write?name="+ userName + "&password="+ password + "&content="+ content ,		
		//url에 값 다 입력시 번거로움 -> data 부분에 넣어서 편하게
		type : "get",
		
		//contentType : "application/json",
		//data : {name: username, password = password, content = content},
		//일일이 넣어주거나. vo만들어서 넣어도 무관
		
		data : guestbookVo,

		//dataType : "json",
		success : function(guestbookVo){
			/*성공시 처리해야될 코드 작성*/
			console.log(guestbookVo);
			render(guestbookVo, "up");

			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
})

//삭제 버튼 클릭시
$("#listArea").on("click", ".btnDel", function(){
	console.log("삭제버튼 클릭");
	
	var tag = $(this);
	tag.data("no");
	
	
	//hidden no 입력하기
	var no = $(this).data("no");
	$("[name=no]").val(no);
	
	
	//모달창 보이기
	$("#delModal").modal();
	
});

//삭제모달창의 삭제버튼 클릭시
$("#modalBtnDel").on("click", funcion(){
	console.log("모달창 삭제 버튼클릭")
	
	var no = $("[name='no']").val();
	
	var guestbookVo = {
		no: $("[name='no']").val(),
		password: $("[name='password']").val()
	};
	
	//서버에 삭제요청( no, password 전달)
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/remove",		
		type : "post",
		//contentType : "application/json",
		data : guestbookVo,
		dataType : "json",
		success : function(count){
			/*성공시 처리해야될 코드 작성*/
			
			if(count === 1){
				//모달창 닫기
				$("#delModal").modal("hide");
				
				//리스트의 삭제버튼이 있던 테이블 화면에서 지우기
				$("#t-" + no).remove();
			}else {
				//모달창 닫기
				$("#delModal").modal("hide");
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});

//1개씩 렌더링
function render(guestbookVo, type){
		var str = "";
		str += '<table class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>'+ guestbookVo.no +'</td>';
		str += '		<td>'+ guestbookVo.name +'</td>';
		str += '		<td>'+ guestbookVo.redDate +'</td>';
		str += '		<td><button class="btnDel" data-no = "' + guestbookVo.no + '">[삭제]</button></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'+ guestbookVo.content +'</td>';
		str += '	</tr>';
		str += '</table>';
		
		if(type === 'down'){
			$("#listArea").append(str);	
		}else if((type === 'up')){
			$("#listArea").prepend(str);
		}else {
			console.log("방향을 지정해 주세요");
		}
}

/*보이기 숨기기 예졔 */
$("#btnhide").on("click", function(){
	console.log("숨기기버튼 클릭")
	
	$("#btnSubmit").hide();
	
}); 
}

$("#btnshow").on("click", function(){
	console.log("보이기버튼 클릭")
	
	$("#btnSubmit").show();
	
}); 
}
</script>
</html>