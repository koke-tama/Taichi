<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">学生削除確認 | 得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-danger bg-opacity-10 py-2 px-4 text-danger">学生情報の削除確認</h2>
            <div class="mx-4 mb-4">
                <table class="table table-bordered w-50">
                    <tr><th class="bg-light w-25">学生番号</th><td><c:out value="${student.no}" /></td></tr>
                    <tr><th class="bg-light">氏名</th><td><c:out value="${student.name}" /></td></tr>
                    <tr><th class="bg-light">クラス</th><td><c:out value="${student.classNum}" /></td></tr>
                </table>
 
                <%-- 実際に削除を実行するためのフォーム --%>
                <form action="StudentDelete.action" method="post">
                    <input type="hidden" name="no" value="${student.no}">
                    <input type="hidden" name="execute" value="true"> <%-- これがActionへの実行合図 --%>
                    
                    <button type="submit" class="btn btn-danger">本当に削除する</button>
                    <a href="StudentList.action" class="btn btn-secondary ms-2">キャンセル</a>
                </form>
            </div>
        </section>
    </c:param>
</c:import>
 