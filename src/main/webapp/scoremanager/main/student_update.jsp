<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目情報変更</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
                科目情報変更
            </h2>

            <%-- エラーメッセージの表示（Actionから渡された場合） --%>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger">
                    <c:forEach var="error" items="${errors}">
                        <p class="mb-0">${error}</p>
                    </c:forEach>
                </div>
            </c:if>

            <%-- 変更フォーム：送信先は SubjectUpdateExecute.action --%>
            <form action="SubjectUpdateExecute.action" method="post">
                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <%-- 科目コードは主キーのため変更不可（読み取り専用）として表示することが一般的です --%>
                    <input type="text" class="form-control-plaintext" name="cd" value="${subject.cd}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">科目名</label>
                    <%-- required: 未入力時に「このフィールドを入力してください」を表示 --%>
                    <input type="text" class="form-control" name="name" value="${subject.name}" 
                           required 
                           placeholder="科目名を入力してください">
                </div>

                <div class="mt-4">
                    <%-- 変更ボタン --%>
                    <button type="submit" class="btn btn-primary">変更</button>
                </div>
            </form>

            <%-- 戻るボタン：メニューと同じ処理（SubjectList.action）を経由させる --%>
            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action"
                   class="text-primary text-decoration-underline">
                    戻る
                </a>
            </div>
        </section>
    </c:param>
</c:import>