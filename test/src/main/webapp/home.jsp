<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
	.article{
		border: 1px black solid;
		display: inline-block;
		width: 280px;
		height: 250px;
	
</style>
<style type="text/css">
	 a { text-decoration: none; color: black; }
    a:visited { text-decoration: none; }
    a:hover { text-decoration: none; }
    a:focus { text-decoration: none; }
    a:hover, a:active { text-decoration: none; }
</style>
<body>
	<h1>대충 Nav가 올자리</h1><hr />
	<c:choose>
	
	<c:when test="${sessionScope.user.user_Id==null}">
	<h3>이런! 로그인을 안하셨군요</h3>
	</c:when>
	
	<c:when test="${not empty bestList}">
	<h1>당신과 같은 MBTI가 많은 모임!</h1>
	<div>
	
	<c:forEach items="${bestList}" var="best">
		<div class="article">
		<a href="getParty.do?PARTY_ID=${best.PARTY_ID}">
			<img src="${best.PARTY_TUMB_PATH }" alt="이미지가 없네요..." width=270px  height=200px/>
			<p>${best.PARTY_TITLE }</p>
		</a>
		</div>
	</c:forEach>
	</div>
	</c:when>
	<c:when test="${empty bestList}">
	<h3>추천 모임이 없습니다</h3>
	</c:when>
	</c:choose>
</body>
</html>