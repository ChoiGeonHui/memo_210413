<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<div class="d-flex justify-content-center my-4">
	<div class="post-box">

		<h1>글 상세/수정</h1>
		<input type="text" name="subject" class="form-control" value="${post.subject}"><br>
		<textarea name="content" class="form-control" rows="15" cols="100">${post.content }</textarea>

		<!--이미지가 있을때만 이미지 영역 추가  -->
		<c:if test="${not empty post.imagePath }"></c:if>
		<div>
			<img alt="업로드된 이미지" src="${post.imagePath}" width="300">
		</div>
	<div>
			<!-- 여러파일을 업로드 할 경우에는  multiple프로퍼티를 추가한다. -->
			<input name="image" accept=".jpg,.jpeg,.png,.gif" type="file" class="form-control">

	</div>

	<div class="mt-5 clearfix">
		<button id="postDelBtn" class="btn btn-secondary float-left">삭제</button>
		<div class="float-right">
			<a href="/post/post_list_view" class="btn btn-dark">목록으로</a>
			<button id="saveBtn" class="btn btn-primary" data-post-id="${post.id }">수정</button>
		</div>
	</div>

	</div>

</div>

<script>

$(document).ready(function(){
	$('#saveBtn').on('click', function(){
		
		let subject =$('input[name=subject]').val().trim();
		if(subject==''){
			alert('제목을 입력해주세요');
			return;
		}
		let content =$('textarea[name=content]').val();
		if(content==''){
			alert('내용을 입력해주세요');
			return;
		}
		
		let image =$('input[name=image]').val();
		//console.log(image);
		if(image != ''){
			let ext = image.split('.').pop().toLowerCase();
			// .을 기준으로 나누고 확장자가 있는 마지막 배열 칸을가져오고 소문자로 모두 변경
			
			if($.inArray(ext, ['gif','jpg','jpeg','png'])==-1){
				alert('gif,jpg,jpeg,png 파일만 업로드 할 수 있습니다.');
				$('input[name=image]').val('');
				return;
			}
			
			
		}
		//-----------
		
		
		
		//서버에 보내기
		
		//form태그를 javascript에서 만든다
		
		let formData = new FormData();
		formData.append("postId",$(this).data('post-id'));
		formData.append("subject",subject);
		formData.append("content",content);
		//$('input[name=image]')[0]  =>첫번째 input이미지 태그를 의미
		//files[0] => 업로드된 파일중 첫번째를 의미
		formData.append("image",$('input[name=image]')[0].files[0]);
		
		
		$.ajax({
			method:'POST',
			url:'/post/update',
			data:formData,
			
			processData:false,//기본 true, json또는 queryString =>String
			contentType:false,
			enctype:'multipart/form-data',
			success:function(data){
				if(data.result=='success'){
					alert("저장되었습니다.");
					location.href="/post/post_list_view"
				}
			},
			error:function(e){
					alert("메모저장에 실패했습니다.");
			}
			
		});
		
	})
})





</script>

