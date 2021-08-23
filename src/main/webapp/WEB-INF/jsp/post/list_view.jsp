<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


	<div class="d-flex justify-content-center">
<div>
		<h1 class="text-center">글 목록</h1>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>No.</th>
					<th>제목</th>
					<th>작성날짜</th>
					<th>수정날짜</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="post" items="${postlist}">
				<tr>
					<td>${post.id}</td>
					<td><a href="/post/post_detail_view?id=${post.id}">${post.subject}</a></td>
					<td>
					<%--${post.createdAt}--%>
					<fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
					<fmt:formatDate value="${post.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
					<%-- ${post.updatedAt} --%>
					</td>
				</tr>
			</c:forEach>
			</tbody>

		</table> 
		
		<div class="d-flex justify-content-end mb-2">
		<a href="/post/post_create_view" class="btn btn-primary">글쓰기</a>
		</div>
		

	</div>

</div>