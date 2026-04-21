<%-- 削除済み学生一覧（ゴミ箱） --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">
        ゴミ箱 | 得点管理システム
    </c:param>
 
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-dark bg-opacity-10 py-2 px-4">削除済み学生一覧</h2>
            
            <div class="my-3 text-end px-4">
                <a href="StudentList.action" class="btn btn-outline-secondary">学生一覧へ戻る</a>
            </div>
 
            <c:choose>
                <c:when test="${not empty deletedStudents}">
                    <div class="alert alert-info mx-3">
                        「元に戻す」をクリックすると、その学生は通常の一覧に再表示されます。
                    </div>
                    
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>クラス</th>
                                <th class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${deletedStudents}">
                                <tr>
                                    <td><c:out value="${student.entYear}" /></td>
                                    <td><c:out value="${student.no}" /></td>
                                    <td><c:out value="${student.name}" /></td>
                                    <td><c:out value="${student.classNum}" /></td>
                                    <td class="text-center">
                                        <%-- 復活ボタン: StudentRestoreActionへ飛ばす --%>
                                        <a href="StudentRestore.action?no=<c:out value="${student.no}" />"
                                           class="btn btn-success btn-sm">元に戻す</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-light border mx-3 mt-4 text-center">
                        削除済みの学生はいません。
                    </div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
 