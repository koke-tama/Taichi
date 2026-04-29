<%-- 河合太一 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
    <c:param name="title">学生情報変更</c:param>
 
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
                学生情報変更
            </h2>
 
            <p class="mb-3 fw-normal bg-success bg-opacity-10 py-2 px-4 border border-success rounded text-center">
                変更が完了しました。
            </p>
 
            <!-- 学生一覧に戻るリンク -->
            <br><br><br><br>

            <div class="d-flex justify-content-between mt-4 px-3">
 
                <!-- 左：戻る -->
                <a href="${pageContext.request.contextPath}/scoremanager/main/studentadd.jsp"
                   class="text-primary text-decoration-underline">
                    戻る
                </a>
 
                <!-- 右：学生一覧 -->
                <a href="${pageContext.request.contextPath}/scoremanager/main/StudentList.action"
                   class="text-primary text-decoration-underline me-3">
                    学生一覧
                </a>
 
            </div>
        </section>
    </c:param>
</c:import>