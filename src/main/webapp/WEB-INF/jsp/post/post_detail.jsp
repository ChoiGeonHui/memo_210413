<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center my-4">
	<div class="post-box">

		<h1>글 상세/수정</h1>
		<input type="text" name="subject" class="form-control"><br>
		<textarea name="content" class="form-control" rows="15" cols="100"></textarea>

		<!--이미지가 있을때만 이미지 영역 추가  -->
		<div>
			<img alt="업로드된 이미지" src="" width="300">
		</div>
	</div>

	<div class="mt-5 clearfix">
		<button id="postDelBtn" class="btn btn-secondary float-left">삭제</button>
		<div class="float-right">
			<a href="/post/post_list_view" class="btn btn-dark">목록으로</a>
			<button id="saveBtn" class="btn btn-primary">수정</button>
		</div>
	</div>


</div>