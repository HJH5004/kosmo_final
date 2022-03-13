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

<body>
<header>
</header>
<section>
<c:forEach items="${SchDetail}" var="sDetail">
  <c:if test="${pages.hasBoard()}">	
			<td>
				<c:if test="${pages.startPage > pages.pageSize}">
				<a href="calendar2.do?year=${param.year}&month=${param.month}&day=${param.day}&party_id=${param.party_id }&pageNo=${pages.startPage - pages.pageSize}">&#9664;</a>
				</c:if>
				${sDetail.sch_title }
				<c:if test="${pages.endPage < pages.totalPages }" >
				<a href="calendar2.do?year=${param.year}&month=${param.month}&day=${param.day}&party_id=${param.party_id }&pageNo=${pages.startPage + pages.pageSize}">	&#9658;</a>
				</c:if>
			</td>
	</c:if>
	<table style="width: 500px; height: 400px">
	    <tr>
	        <td rowspan="5" >
	        	맵!!!!
	        </td>
	        <td>
	            주최자: ${sDetail.sch_writer} 
	        </td>
	    </tr>
	    <tr>
	        <td>
	           위치: ${sDetail.sch_location}
	        </td>
	    </tr>
	    <tr>
	        <td>
	            참가인원:  ${current_count} / ${sDetail.sch_member_count }
	        </td>
	    </tr>
	     <tr >
	        <td style="display:flex;">
	        <c:set var="condition" value="false"/>
			        <c:forEach items="${cntList}" var="cnt" varStatus="status"> 
			     		<c:if test="${cnt.user_id == sessionScope.user.user_Id}">  
				      		<c:set var="condition" value="true"/>
			            </c:if>
			      </c:forEach> 
			      <c:if test="${condition eq false}">
			      <c:if test="${current_count < sDetail.sch_member_count }"> 
				       <button onclick="location.href='cntUp.do?user_id=${sessionScope.user.user_Id}&party_id=${sDetail.party_id}&sch_id=${sDetail.sch_id}'">	
						참가하기 
						</button>
					</c:if>
					</c:if>
				<c:if test="${condition eq true }">
				<button onclick="location.href='cntDown.do?user_id=${sessionScope.user.user_Id}&sch_id=${sDetail.sch_id}' "> 취소하기 </button>
	      		</c:if>
	        
	       
	        </td>
	    </tr>
	    
	    <tr>
	        <td >
	        	<c:if test="${sDetail.user_id==sessionScope.user.user_Id||leader.USER_ID==sessionScope.user.user_Id}">
	            <button onclick="location.href='deleteSch.do?&sch_id=${sDetail.sch_id}&year=${param.year}&month=${param.month}&day=${param.day}&party_id=${param.party_id}';">정모삭제하기    </button>
	            </c:if>
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2">
	            ${sDetail.sch_content}
	        </td>
	    </tr>	
	    <tr>
	    	
	        <td colspan="2" >
	           <hr /> 
	           ${sDetail.sch_note}
	        </td>
	    </tr> 
	    
	</table> 
	</c:forEach>
</section>
<footer>
</footer>

</body>
</html>