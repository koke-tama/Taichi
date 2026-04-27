<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目情報変更完了</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
                科目情報変更
            </h2>

            <p class="mb-3 fw-normal bg-success bg-opacity-10 py-2 px-4 border border-success rounded text-center">
                変更が完了しました。
            </p>

            <%-- レイアウト維持のための余白 --%>
            <br><br><br><br>

            <div class="d-flex justify-content-between mt-4 px-3">
                <%-- 左：戻る（科目一覧へ戻るのが一般的ですが、要件に合わせて記述） --%>
                <%-- 変更画面へ戻る場合は、どの科目の変更かを指定する必要があるため、一覧へ戻るようにしています --%>
                <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action"
                   class="text-primary text-decoration-underline">
                    戻る
                </a>

                <%-- 右：科目一覧（メニューと同じ一覧処理へ） --%>
                <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action"
                   class="text-primary text-decoration-underline me-3">
                    科目一覧
                </a>
            </div>
        </section>
    </c:param>
</c:import>