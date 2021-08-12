<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모 게시판</title>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>      
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <!-- datepicker 라이브러리 -->
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<link rel="stylesheet" type="text/css" href="/static/css/style.css">
</head>
<body>

	<div id="wrap" class="bg-dark">

		<jsp:include page="../include/gmb.jsp" />

		<section class="content d-flex justify-content-center">
			<%-- <jsp:include page="../${viewName}.jsp" /> --%>
			<div class="loginput">
			<table class="table">
				<tr>
					<td>아이디 (4자이상)</td>
					<td><input type="text" class="from-control"><button class="btn btn-success">중복확인</button> </td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="text" class="from-control"></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="text" class="from-control"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" class="from-control"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" class="from-control"></td>
				</tr>
			</table>
			<button class="btn btn-info mt-3">회원가입</button>
			</div>

		</section>

		<jsp:include page="../include/footer.jsp"/>


	</div>


</body>
</html>