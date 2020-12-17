<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../common/header.jsp" />
<h1 style="text-align:center">게시글 읽기</h1>
    <table width="600" style="margin:auto" cellspacing="0" cellpadding="2">
        <tr>
            <td height="22">&nbsp;</td>
        </tr>
        <tr>
            <td height="1" bgcolor="#1F4F8F"></td>
        </tr>
        <tr bgcolor="#DFEDFF">
            <td bgcolor="#DFEDFF"> 
                <div><strong id="title"></strong></div> 
            </td>
        </tr>
        <tr bgcolor="#FFFFFF">
            <td bgcolor="#F4F4F4">
                <table width="100%" border="0" cellpadding="0" cellspacing="4" height="1">
                    <tr bgcolor="#F4F4F4">
                        <td width="13%" height="7"></td>
                        <td width="51%" height="7">글쓴이 : <span id="username"></span>
                        (<span id="email"></span>)</td>
                        
                        <td width="25%" height="7"></td>
                        <td width="11%" height="7"></td>
                    </tr>
                    <tr bgcolor="#F4F4F4">
                        <td width="13%"></td>
                        <td width="51%">작성일 : <span id='writeday'></span></td>
                        <td width="25%">조회수 : <span id='readnum'></span></td>
                        <td width="11%"></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr align="center">
            <td bgcolor="#1F4F8F" height="1"></td>
        </tr>
        <tr>
            <td style="padding:20 0 20 0">
            	<br />
                <span style="color:#333333" id='contents'></span>
               <br />
            </td>
        </tr>
        <tr align="center">
            <td class="button" height="1"></td>
        </tr>
        <tr align="center">
            <td bgcolor="#1F4F8F" height="1"></td>
        </tr>
    </table>
    <table width="600" style="margin:auto" border="0" cellpadding="0" cellspacing="5">
        <tr> 
            <td align="right" width="450"><a href="bbslist.html"><img src="images/list.jpg"></a></td>
            <td width="70" align="right">
            	<a href="#"><img src="images/reply.jpg"></a>
            </td>
            <td width="70" align="right"><img src="images/edit.jpg" id="updateBtn"></td>
            <td width="70" align="right"><img src="images/del.jpg" id="delBtn"></td>
        </tr>
    </table>
    <div style="text-align:center; width:600px;margin:auto;">
    	<p><label for="replyer">Nickname : </label>
    		<input type="text" id="replyer" />
    		<input type="button" value="댓글등록" id="replyBtn"/>
    	</p>
    	<p>
    		<textarea rows="3" cols="60" placeholder="댓글을 달아주세요" id="replytext"></textarea>
    	</p>
    </div>
    <div id="replylist"></div>
<c:import url="../common/footer.jsp" />