<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세페이지 -모달창 활용할 jsp </title>
</head>
<script language="javascript">
	function check() {
		var checked_rate1 = document.querySelector('input[name = "rate1"]:checked');
		var checked_rate2 = document.querySelector('input[name = "rate2"]:checked');
		var checked_rate3 = document.querySelector('input[name = "rate3"]:checked');

		if(checked_rate1 == null){
		alert('평가1 미체크');
		return false;
		}
		else if(checked_rate2 == null){
		alert('평가2 미체크');
		return false;
		}
		else if(checked_rate3 == null){
		alert('평가3 미체크');
		return false;
		}
		
	}
</script>
<body>
<header>
<jsp:include page="Nav.jsp"/>
</header>
<section>
<div class="container mt-3">
		
		<div class="col-sm" >
		<ul class="nav nav-pills nav-justified">
		<li class="nav-item"><a class="nav-link active"  href="mypage/getUserInfo.do">1.개인정보 관리</a></li>
     	<li class="nav-item"><a class="nav-link active"  href="mypage/getPartyList.do">2.내가 가입한 소모임</a></li>
     	<li class="nav-item"><a class="nav-link active"  href="mypage/getPartyFavList.do">3.내가 찜한 소모임</a></li>
     	<li class="nav-item"><a class="nav-link active"  href="mypage/getPartyCreatorList.do">4.생성한 소모임 관리</a></li>
     	<li class="nav-item"><a class="nav-link active"  href="mypage/../calendar3.do?user_id=${sessionScope.user.user_Id }">5.스케쥴러</a></li>
     	</ul>
		</div>
			</div>

<c:forEach items="${SchDetail}" var="sDetail">
<c:if test="${pages.hasBoard()}">
<ul class="pagination">
<c:if test="${pages.startPage > pages.pageSize}">
  <li class="page-item"><a class="page-link" href="calendar4.do?year=${param.year}&month=${param.month}&day=${param.day}&pageNo=${pages.startPage - pages.pageSize}">&#9664;</a></li>
  </c:if>
  <c:if test="${pages.endPage < pages.totalPages }" >
  <li class="page-item"><a class="page-link" href="calendar4.do?year=${param.year}&month=${param.month}&day=${param.day}&pageNo=${pages.startPage + pages.pageSize}">&#9658;</a></li>
  </c:if>
</ul>
	</c:if>
	
	
	<div class="container pt-5">
		<div class="row">
			<div class="col sm-3">
			<img src="" alt="맵이 들어올 자리"  width="100px" height="100px"/>
				</div>
				
				<div class="col sm-3">
					<ul class="list-group">
					<li class="list-group-item list-group-item-secondary"> 정모제목 : ${sDetail.sch_title }</li>
						<li class="list-group-item list-group-item-secondary"> 파티이름 : ${party_title}</li>
						 <li class="list-group-item list-group-item-secondary"> 주최자: ${sDetail.sch_writer} </li>
						  <li class="list-group-item list-group-item-secondary">위치: ${sDetail.sch_location}</li>
						   <li class="list-group-item list-group-item-secondary">참가인원: ${current_count} / ${sDetail.sch_member_count }</li>			  
					</ul>
					
					  <c:set var="condition" value="false"/>
			        <c:forEach items="${cntList}" var="cnt" varStatus="status"> 
			     		<c:if test="${cnt.user_id == sessionScope.user.user_Id}">  
				      		<c:set var="condition" value="true"/>
			            </c:if>
			      </c:forEach> 
			      
			      
			      
			        <c:forEach items="${cntList}" var="cnt"> 
	    <c:if test="${cnt.user_id == sessionScope.user.user_Id}">  
			      <form action="scheduleReview.do" method="post" name="Reg_form" onsubmit="return check()">
			      <div class="form-check">
			      		
			      		<br>
			      		<br />
  						<input class="form-check-input" type="radio"   value="1" name="rate1" checked> 
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="2" name="rate1" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="3" name="rate1" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="4" name="rate1" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="5" name="rate1" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary">친절도</button></label>
  						
  						<br />
  						<br />
  						<input class="form-check-input" type="radio"   value="1" name="rate2" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="2" name="rate2" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="3" name="rate2" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="4" name="rate2" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="5" name="rate2" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary">만족도</button></label>
  						<br>
  						<br />
  						<input class="form-check-input" type="radio"   value="1" name="rate3" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="2" name="rate3" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="3" name="rate3" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="4" name="rate3" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  						<input class="form-check-input" type="radio"   value="5" name="rate3" checked>
  						<label class="form-check-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary">가격</button></label>
  						<input type="hidden" name="party_id" value="${sDetail.party_id }"/>
  						<input type="hidden" name="sch_id" value="${sDetail.sch_id }"/>
						<input type="hidden" name="user_id" value="${sessionScope.user.user_Id }"/>
						<br />
						<input class="btn btn-primary" type="submit" value="작성">
				</div>
			      
			      
			      </form>
			         </c:if>
	    </c:forEach>
			     <div class="container pt-5">
			       <div class="btn-group" >
			      <c:if test="${condition eq false}">
			        <c:if test="${current_count < sDetail.sch_member_count }"> 
			      	<button type="button" class="btn btn-primary " onclick="location.href='cntUp.do?user_id=${sessionScope.user.user_Id}&party_id=${sDetail.party_id}&sch_id=${sDetail.sch_id}'">참가하기</button>
			      	</c:if>
					</c:if>
					<c:if test="${condition eq true }">
  					<button type="button" class="btn btn-primary" onclick="location.href='cntDown.do?user_id=${sessionScope.user.user_Id}&sch_id=${sDetail.sch_id}' ">취소하기</button>
  					</c:if>
  					<c:if test="${sDetail.user_id==sessionScope.user.user_Id||leader.USER_ID==sessionScope.user.user_Id}">
  					<button type="button" class="btn btn-primary" onclick="location.href='deleteSch.do?&sch_id=${sDetail.sch_id}&year=${param.year}&month=${param.month}&day=${param.day}&party_id=${param.party_id}'">정모삭제하기</button>
  					</c:if>
			      </div>
			      
			</div>
		</div>
	</div>
	</div>
			<div class="container pt-5">
				<div class="row">
					<div class="mt-4 p-5 bg-primary text-white rounded">
					  <p>${sDetail.sch_content}</p>
					</div>
				</div>
			</div>
	
		<div class="container pt-5">
				<div class="row">
					<ul	class="list-group">
						<li class="list-group-item list-group-item-secondary">${sDetail.sch_note}</li>
					</ul>
				</div>
			</div>
	
	
	
	
	</c:forEach>
</section>
<footer>
<jsp:include page="footer.jsp"/>
</footer>

</body>
</html>