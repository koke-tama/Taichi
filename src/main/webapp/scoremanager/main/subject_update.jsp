<%-- 河合太一 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目情報変更</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
                科目情報変更
            </h2>

            <c:if test="${not empty errors}">
                <div class="alert alert-danger">
                    <c:forEach var="error" items="${errors}">
                        <p class="mb-0">${error}</p>
                    </c:forEach>
                </div>
            </c:if>

            <form action="SubjectUpdateExecute.action" method="post">
                <input type="hidden" name="oldCd" value="${subject.cd}">

                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <input type="text" class="form-control-plaintext" name="cd" value="${subject.cd}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">科目名</label>
                    <input type="text" class="form-control" name="name" value="${subject.name}" 
                           required placeholder="科目名を入力してください">
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">変更</button>
                </div>
            </form>

            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action"
                   class="text-primary text-decoration-underline">
                    戻る
                </a>
            </div>
        </section>
    </c:param>
</c:import>