<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="loginput">
	<form id="signUpForm" method="post" action="/user/signup">
		<table class="m-auto">
			<tr>
				<td>아이디 (4자이상)</td>
				<td><input type="text" id="loginId" name="loginId"
					class="from-control">
					<button id="idchk" type="button" class="btn btn-success">중복확인</button></td>
			</tr>
			<tr>
				<td id="idchk1" class="text-danger d-none">아이디를 4자 이상 입력하세요</td>
				<td id="idchk2" class="text-danger d-none">중복된 아이디 입니다</td>
				<td id="idchk3" class="text-success d-none">사용가능한 아이디입니다</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" id="password" name="password"
					class="from-control"></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="text" id="passwordChk" class="from-control"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" id="name" name="name"
					class="from-control"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" id="email" name="email"
					class="from-control"></td>
			</tr>
		</table>
		<button id="signupBtn" type="submit"
			class="btn btn-info mt-3 btn-right">회원가입</button>
	</form>
</div>
<script>
$(document).ready(function(){
	//아이디 중복확인
	$("#idchk").on('click',function(){
		
		let loginId =$('#loginId').val().trim();
		
		//아이디가 4자 이상이 아니면
		if(loginId.length<4){
			$('#idchk1').removeClass('d-none');//노출
			$('#idchk2').addClass('d-none');
			$('#idchk3').addClass('d-none');
			return;
		}
		
		// 중복환인 여부는 DB를 조회해야 하므로 서버에 묻는다.
		//화면을 이동시키지 않고 ajax통신으로 중복여부 확인하고 동적으로 문구 노출
		$.ajax({
			url:'/user/is_dulicated_id',
			type:'get',
			data:{'loginId':loginId},
			success:function(data){
				if(data.result){//중복인 경우
					$('#idchk1').addClass('d-none');
					$('#idchk2').removeClass('d-none');//노출
					$('#idchk3').addClass('d-none');
					
				}else{//사용 가능한 경우
					$('#idchk1').addClass('d-none');
					$('#idchk2').addClass('d-none');
					$('#idchk3').removeClass('d-none');//노출
				}
			}, error:function(){
				alert('아이디 중복확인에 실패했습니다. 관리자에게 문의해 주세요.');
			}			
			
		});
	
	});
	
		//회원가입 버튼 동작
		$('#signUpForm').submit(function(e){
			e.preventDefault();
			let loginId =$('#loginId').val().trim();
			if(loginId==''){
				alert('아이디를 입력하세요');
				return;
			}
			
			let password=$('#password').val().trim();
			let passwordChk =$('#passwordChk').val().trim();
			if(password==''||passwordChk==''){
				alert('비밀번호를 입력하세요');
				return;
			}
			if(password!=passwordChk){
				alert('비밀번호같게 하세요');
				return;
			}
			
			let name=$('#name').val().trim();
			if(name==''){
				alert('이름를 입력하세요');
				return;
			}
			
			let email=$('#email').val().trim();
			if(email==''){
				alert('이메일 입력하세요');
				return;
			}
			
			//아이디 중복확인이 완료됐는지 확인
			// idchk3 ->d-none 이 없으면 사용 가능한 아이디라고 가정한다. 
			if($('#idchk3').hasClass('d-none')){
				alert('아이디 중복확인을 해주세요');
				return;
			}
			
			//1) submit : name속성에 있는 값들이 서버로 넘어간다.
			//$(this)[0].submit();
			//submit은 submit후 전체 화면을 이동시키는 경우사용한다 
			
			
			
			//2)
			let url='/user/signup_forajax';
			let data =$(this).serialize();//테그속 들어있는 nameinput이 구성된다.
			//만약 이것을 사용하지 않으면 datajson을 사용해야한다.
			
			
			$.post(url,data).done(function(data){
				if(data.result=="success"){
					alert('회원가입을 환영합니다.');
					location.href="/user/sign_in_view";
				}else{
					alert('가입에 실패했습니다');
				}
			});
			
		});
	
});


</script>

