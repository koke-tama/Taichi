<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目登録完了</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
                科目登録
            </h2>

            <p class="mb-3 fw-normal bg-success bg-opacity-10 py-2 px-4 border border-success rounded text-center">
                登録が完了しました。
            </p>

            <%-- 余白調整 --%>
            <br><br><br><br>

            <div class="d-flex justify-content-between mt-4 px-3">
                <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectCreate.action"
                   class="text-primary text-decoration-underline">
                    戻る
                </a>

                <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action"
                   class="text-primary text-decoration-underline me-3">
                    科目一覧
                </a>
            </div>
        </section>
    </c:param>
</c:import>