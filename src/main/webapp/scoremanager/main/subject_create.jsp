<%-- 長家優紀 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
    <c:param name="title">科目情報登録</c:param>
    
    <c:param name="content">
        <section class="me-4">

            <%-- 見出し --%>
            <h2 class="h4 mb-4 bg-light p-2" style="font-weight: bold;">
                科目情報登録
            </h2>
     
            <%-- エラーメッセージ --%>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger p-2 mb-3" style="font-size: 0.9rem;">
                    <c:forEach var="error" items="${errors}">
                        <p class="mb-0">${error}</p>
                    </c:forEach>
                </div>
            </c:if>
     
            <%-- 登録フォーム --%>
            <form action="SubjectCreateExecute.action" method="post" class="ms-2">
                
                <%-- 科目コード --%>
                <div class="mb-3">
                    <label class="form-label d-block mb-1" style="font-weight: bold;">科目コード</label>
                    <input type="text" name="cd" value="${cd}"
                           class="form-control"
                           placeholder="科目コードを入力してください"
                           required
                           style="width: 100%; max-width: 500px; background-color: #fff !important;">
                </div>
     
                <%-- 科目名 --%>
                <div class="mb-3">
                    <label class="form-label d-block mb-1" style="font-weight: bold;">科目名</label>
                    <input type="text" name="name" value="${name}"
                           class="form-control"
                           placeholder="科目名を入力してください"
                           required
                           style="width: 100%; max-width: 500px; background-color: #fff !important;">
                </div>
     
                <%-- 登録ボタン --%>
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary px-4" style="font-size: 0.9rem;">
                        登録
                    </button>
                </div>
            </form>
     
            <%-- 戻るリンク --%>
            <div class="mt-3 ms-2">
                <a href="SubjectList.action"
                   class="text-primary"
                   style="text-decoration: underline; font-size: 0.9rem;">
                    戻る
                </a>
            </div>

        </section>
    </c:param>
</c:import>