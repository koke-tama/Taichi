<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<%-- 共通のヘッダー・サイドバーを含むベースファイルをインポート --%>
<c:import url="/common/base.jsp">
 
    <c:param name="title">成績管理</c:param>
 
    <c:param name="content">
        <section class="me-4">
            <%-- ① 見出し部分 --%>
            <h2 class="h3 mb-3 bg-light p-2">成績管理</h2>
 
            <%-- ② 登録完了メッセージ（緑色のバー） --%>
            <div class="alert alert-success py-2" role="alert">
                登録が完了しました
            </div>
 
            <%-- ③ 戻るリンク ＋ ④ 成績参照リンク --%>
            <div class="mt-4">
                <a href="TestRegist.action" class="me-3">戻る</a>
                <a href="TestList.action">成績参照</a>
            </div>
        </section>
    </c:param>
 
</c:import>
 