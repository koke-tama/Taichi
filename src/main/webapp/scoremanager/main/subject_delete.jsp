<%-- 小野田匠希 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="/common/base.jsp">
    <c:param name="title">科目情報削除</c:param>
 
    <c:param name="content">
 
        <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
            科目情報削除
        </h2>
 
        <div class="px-4">
 
            <p class="mb-4">
                「${subject.name}（${subject.cd}）」を削除してもよろしいですか？
            </p>
 
            <form action="SubjectDeleteExecute.action" method="post">
                <input type="hidden" name="cd" value="${subject.cd}">
                <button type="submit" class="btn btn-danger">
                    削除
                </button>
            </form>
            
            <br><br>
 
            <div class="mt-3">
                <a href="SubjectList.action"
                   class="text-primary text-decoration-underline">
                    戻る
                </a>
            </div>
 
        </div>
 
    </c:param>
</c:import>