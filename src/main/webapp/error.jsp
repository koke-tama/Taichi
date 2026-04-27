<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">エラー</c:param>

    <c:param name="content">
        <section class="me-4 text-center">
            <%-- エラーの見出し --%>
            <h2 class="h3 mb-4 fw-bold text-danger bg-danger bg-opacity-10 py-3 px-4 rounded border border-danger border-opacity-25">
                エラーが発生しました
            </h2>
            <div class="mt-5">
                <%-- 戻るボタン：一覧画面やメニューに戻るように設定 --%>
                <a href="${pageContext.request.contextPath}/scoremanager/main/Menu.action"
                   class="btn btn-secondary px-5">
                    戻る
                </a>
            </div>
        </section>
    </c:param>
</c:import>