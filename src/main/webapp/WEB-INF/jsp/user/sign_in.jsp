<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="login-box">
		<h1>로그인</h1>

		<form action="/user/sign_in" id="loginForm" method="post">
			<div class="input-gruop">
			<%--input-group-prepend--%>
				<div class="input-group-prepend">
					<span class="input-group-text">ID</span>
				<input type="text" class="form-control" id="loginId" name="loginId">
				</div>
			</div>
			
			<div class="input-gruop mt-3 mb-3">
			<%--input-group-prepend--%>
				<div class="input-group-prepend">
					<span class="input-group-text">PW</span>
				</div>
				<input type="text" class="form-control" id="password" name="password">
			</div>
			
			<input type="submit" class="mb-3 btn btn-primary btn-block" value="로그인">
			<a href="/user/sign_up_view" class="btn btn-dark btn-block">회원가입</a>
		</form>
	</div>

</div>


<script type="text/javascript">
	$(document).ready(function(){
		$('#loginForm').submit(function(e){
			e.preventDefault();
			
			let loginId = $('#loginId').val().trim();
			if(loginId == ''){
				alert('아이디를 입력해주세요');
				return;
			}
			
			let password = $('#password').val();
			if(password==''){
				alert('비밀번호를 입력해주세요.');
				return;
			}
			
			//ajax
			let url = $(this).attr('action');
			let params = $(this).serialize();
			$.post(url,params).done(function(data){
				
				if(data.result =='success'){
					alert('로그인성공');
					location.href='/post/post_list_view';
				}else{
					alert('로그인에 실패했습니다.');
				}
			})
			
		});
		
		
	});
</script>


