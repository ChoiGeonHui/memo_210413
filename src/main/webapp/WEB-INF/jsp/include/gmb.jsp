<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<header class="bg-secondary">
	<div class="d-flex">
		<div class="logo">
			<h1 class="text-white p-4 font-weight-bold">메모 게시판</h1>
		</div>
		
		<div class="login-info d-flex justify-content-end">

			<c:if test="${not empty userName}">
				<div class="mt-5">
					<span class="text-white"><b>${userName}</b>님 안녕하세요</span> 
					<a
						class="font-weight-bold text-white ml-2" href="/user/sign_out">로그
						아웃</a>
				</div>
			</c:if>


			<%--로그인이 안 된 경우 --%>
			<c:if test="${empty userName}">
			<div class="mt-5">
				<a class="font-weight-bold text-white mr-3" href="/user/sign_in_view">로그인</a>
			</div>
			</c:if>
		</div>
	</div>

</header>