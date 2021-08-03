<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- header & nav-->
		
		<div id="container" class="clearfix">
			
			<c:import url="/WEB-INF/views/include/aside.jsp"></c:import>
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
					<form action="${pageContext.request.contextPath }/guest/add" method="get">
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
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</th>
									<td><input id="input-pass" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>

						</table>

					</form>
					
					
					<div id = "listArea">
						jquery 리스트 영역
					</div>
					
					<c:forEach items="${requestScope.guestbookList }" var="guestbookList" varStatus = "status">

						<table class="guestRead">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 40%;">
								<col style="width: 10%;">
							</colgroup>
							<tr>
								<td>${guestbookList.no}</td>
								<td>${guestbookList.name}</td>
								<td>${guestbookList.regDate}</td>
								<td><a href="${pageContext.request.contextPath }/guest/deleteForm?no=${guestbookList.no}">[삭제]</a></td>
							</tr>
							<tr>
								<td colspan=4 class="text-left">$guestbookList.content}</td>
							</tr>
						</table>
						
					</c:forEach>

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

</body>

<script type="text/javascript">

//화면 로딩직전
$(document).ready(function(){
	console.log("화면 로딩직전");
	//ajax 요청
	$.ajax({
			
		url : "${pageContext.request.contextPath }/api/guestbook/list",		
		type : "post",
		contentType : "application/json",
		data : {name: "김민수"},

		dataType : "json",
		success : function(guestList){
			/*성공시 처리해야될 코드 작성*/
			console.log(guestList);
			
			//화면에 그리기
			for(var i=0, i<guestList.letngh; i++){
				render(guestList[i]); //방명록 글 1개씩 추가(렌더링 함수이용)
		
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
	
	//name 읽어오기
	var userName = $("#input-uname").val();
	console.log(userName);
	
	//password 읽어오기
	var password = $("#input-pass").val();
	console.log(password);
	
	//content 읽어오기
	var content = $("[name=content]").val();
	console.log(content);
	
	var guiestbookVo = {
			name : $("#input-uname").val();
		,	password : $("#input-pass").val();
		,	content : $("[name=content]").val();
	};
	
	
	//ajax 방식으로 서버에 전송
	$.ajax({
		
		//url : "${pageContext.request.contextPath }/api/guestbook/write?name="+ userName + "&password="+ password + "&content="+ content ,		
		//url에 값 다 입력시 번거로움 -> data 부분에 넣어서 편하게
		type : "post",
		contentType : "application/json",

		//data : {name: username, password = password, content = content},
		//일일이 넣어주거나. vo만들어서 넣어도 무관
		
		data : guestbookVo,

		dataType : "json",
		success : function(){
			/*성공시 처리해야될 코드 작성*/
			console.log();
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
})

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
		str += '		<td><a href="${pageContext.request.contextPath }/guest/deleteForm?no='+ guestbookVo.no +">[삭제]</a></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'+ guestbookVo.content +'</td>';
		str += '	</tr>';
		str += '</table>';
		
		$("#listArea").appened(Str);
		}
}
</script>
</html>