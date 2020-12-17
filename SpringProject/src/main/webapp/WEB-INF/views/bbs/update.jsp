<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:import url="../common/header.jsp" />
<c:set var="bbs" value="${bbsInfo}" />

<script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>
<h1 class="text-center">게시판 글 수정하기</h1>
  <div class="container">
    <div class="row">
      <form class="form-horizontal" action="/bbs/write" method="POST">
        <div class="form-group">
          <label for="txtTitle" class="col-sm-2 control-label">글 제목</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" name="title" id="txtTitle" 
            placeholder="글 제목을 입력합니다." required value="${bbs.title}">
          </div>
        </div>
        <div class="form-group">
          <label for="txtEmail" class="col-sm-2 control-label">Email</label>
          <c:if test="${not empty bbs.email}">
          	 <c:set var="emailArray" value="${fn:split(bbs.email, '@')}" />
          </c:if>
          <div class="col-sm-3">
            <input type="text" name="email" class="form-control" 
            	id="txtEmail" placeholder="글쓴이의 Email을 입력합니다." value="${emailArray[0]}">
          </div>
          <div class="input-group  col-sm-3">
        	<span class="input-group-addon">@</span>
        	<select class="form-control" name="company" id="company">
  				<option value="naver.com" <c:if test="${emailArray[1] eq 'naver.com'}">selected</c:if>>네이버</option>
  				<option value="hanmail.net">한메일</option>
			  	<option value="google.com">구글</option>
			  	<option value="nate.com">네이트</option>
			  	<option>직접입력</option>
				</select>
      	  </div>
        </div>
        <div class="form-group">
          <label for="txtContent" class="col-sm-2 control-label">글 내용</label>
          <div class="col-sm-8">
            <textarea class="form-control" rows="10" required="required" name="content"></textarea>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success" id='btnSubmit'>등록하기</button>
            <button type="reset" class="btn btn-warning">취소하기</button>
          </div>
        </div>
      </form>
    </div>
  </div>
<script>
	CKEDITOR.replace( 'content' );
</script>
<script>
	$('#company').on('change', function(){
		let company = document.getElementById("company")
		if(company.selectedIndex == 4){  //직접입력을 선택했다면
			$(this).replaceWith("<input type='text' name='company' class='form-control'>");
		}
	});
</script>
<c:import url="../common/footer.jsp" />