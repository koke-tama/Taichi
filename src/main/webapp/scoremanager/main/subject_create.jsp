<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目管理</c:param>
    
    <c:param name="content">
        <div class="header" style="background-color: #f0f0f0; padding: 10px; font-weight: bold; width: 600px;">
            科目登録
        </div>

        <%-- エラーメッセージの表示エリア（重複エラーや3文字エラーなど） --%>
        <c:if test="${not empty errors}">
            <div style="color: red; margin: 10px 0;">
                <c:forEach var="error" items="${errors}">
                    <p style="margin: 0;">${error}</p>
                </c:forEach>
            </div>
        </c:if>

        <%-- 登録フォーム：送信先は SubjectCreateExecute.action --%>
        <form action="SubjectCreateExecute.action" method="post" style="margin-top: 20px;">
            <div style="margin-bottom: 15px;">
                <label style="display: inline-block; width: 100px;">科目コード</label>
                <%-- required: 未入力時に「このフィールドを入力してください」を表示 --%>
                <%-- value: エラーで戻ってきたときに入力内容を保持 --%>
                <input type="text" name="cd" value="${cd}" 
                       required 
                       placeholder="例：A01"
                       style="padding: 3px;">
            </div>

            <div style="margin-bottom: 15px;">
                <label style="display: inline-block; width: 100px;">科目名</label>
                <input type="text" name="name" value="${name}" 
                       required 
                       placeholder="例：数学"
                       style="padding: 3px;">
            </div>

            <div style="margin-top: 20px;">
                <button type="submit" style="padding: 5px 15px;">登録</button>
            </div>
        </form>

        <%-- 戻るボタン：メニューと同じ処理（ListAction）を経由させる --%>
        <div style="margin-top: 20px;">
            <a href="SubjectList.action" style="color: blue; text-decoration: underline;">戻る</a>
        </div>
    </c:param>
</c:import>