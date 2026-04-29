<%-- 長家優紀 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<%-- 共通のヘッダー・サイドバーを含むベースファイルをインポート --%>
<c:import url="/common/base.jsp">
 
    <c:param name="title">成績管理</c:param>
 
    <c:param name="content">
        <section class="me-4">
            <%-- 見出し --%>
            <h2 class="h4 mb-3 bg-light p-2" style="font-weight: bold;">成績管理</h2>
 
            <%-- 登録完了メッセージ --%>
            <div class="alert text-center py-2 mb-4" role="alert"
                 style="background-color: #d1e7dd; border-color: #badbcc; color: #0f5132; font-size: 0.95rem;">
                登録が完了しました
            </div>
 
            <%-- リンク --%>
            <div class="d-flex gap-4 mt-4">
                <a href="TestRegist.action" class="text-primary" style="text-decoration: underline;">戻る</a>
                <a href="TestList.action" class="text-primary" style="text-decoration: underline;">成績参照</a>
            </div>
        </section>
    </c:param>
 
</c:import>