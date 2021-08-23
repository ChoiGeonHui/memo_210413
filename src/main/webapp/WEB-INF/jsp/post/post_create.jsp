<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center my-3">
	<div>
	<h1>글쓰기</h1>
	<input type="text" class="mb-2 form-control" name="subject" placeholder="제목을 입력하세요">
	<textarea class="mb-2 from-control" rows="15" cols="100" name="content" placeholder="내용을 입력해주세요"></textarea>

		<div>
			<!-- 여러파일을 업로드 할 경우에는  multiple프로퍼티를 추가한다. -->
			<input name="image" accept=".jpg,.jpeg,.png,.gif" type="file" class="form-control">

		</div>

		<div class="mt-3 clearfix">
		<!-- float 정렬을 하게 되면 block태그도 옆에 계속 붙게 된다. -->
			<button type="button" id="postlistBtn" class="btn btn-dark">목록</button>
			
			<div class="float-right">			
			<button type="button" id="clearBtn" class="btn btn-secondary">모두 지우기</button>
			<button type="button" id="saveBtn" class="btn btn-info">저장</button>
			</div>

		</div>


	</div>
</div>

<script>
$(document).ready(function(){
	$('#postlistBtn').on('click',function(e){
		location.href="/post/post_list_view";
	});
	
	//모두지우기 버튼 클릭
	$('#clearBtn').on('click',function(){
		//제목과 textarea를 빈 칸으로 채운다.
		$('input[name=subject]').val('');
		$('textarea[name=content]').val('');
		$('input[name=image]').val('');
		
	});
	
	
	$('#saveBtn').on('click',function(){
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
		formData.append("subject",subject);
		formData.append("content",content);
		//$('input[name=image]')[0]  =>첫번째 input이미지 태그를 의미
		//files[0] => 업로드된 파일중 첫번째를 의미
		formData.append("image",$('input[name=image]')[0].files[0]);
		
		
		$.ajax({
			method:'post',
			url:'/post/create',
			data:formData,
			
			processData:false,//기본 true, json또는 queryString =>String
			contentType:false,
			enctype:'multipart/form-data',
			success:function(data){
				if(data.result=='success'){
					alert("저장되었습니다.");
					location.href="post/post_list_view"
				}
			},
			error:function(e){
					alert("메모저장에 실패했습니다.");
			}
			
		});
		
		
		
	});
	
	
});


</script>