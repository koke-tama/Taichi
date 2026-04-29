<%-- 長家優紀 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">ログアウト</c:param>

<c:param name="content">

    <%-- 見出し --%>
    <h2 class="h4 bg-light p-2 mb-3">ログアウト</h2>

    <%-- ログアウト完了メッセージ --%>
    <div class="p-2 mb-4 text-center" style="background-color: #89c997; color: #333;">
        ログアウトしました
    </div>

    <%-- ログイン画面へのリンク --%>
    <div>
        <a href="Login.action">ログイン</a>
    </div>

</c:param>
</c:import>