<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
    <c:param name="title">成績参照</c:param>
 
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
                成績参照
            </h2>
 
            <div class="mb-4">
                <form action="TestListStudentExecute.action" method="get" class="bg-light p-3 rounded border">
                    <div class="row align-items-end">
                        <div class="col-md-9 mb-3">
                            <label class="form-label">学籍番号</label>
                            <input type="text" name="f4" value="${f4}" class="form-control" required>
                        </div>
                        <div class="col-md-3 mb-3 text-center">
                            <button type="submit" class="btn btn-primary w-100">検索</button>
                        </div>
                    </div>
                </form>
            </div>
 
            <hr class="my-4">
 
            <c:choose>
 
                <c:when test="${not empty scoreMap}">
                    <table class="table table-hover border">
                        <thead class="table-light">
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>1回</th>
                                <th>2回</th>
                            </tr>
                        </thead>
                        <tbody>
 
                            <c:forEach var="entry" items="${scoreMap}">
 
                                <!-- null対策 -->
                                <c:if test="${entry.value[0] != null}">
                                    <tr>
                                        <td>${entry.value[0].entYear}</td>
                                        <td>${entry.value[0].classNum}</td>
                                        <td>${entry.value[0].no}</td>
                                        <td>${entry.value[0].name}</td>
 
                                        <td>
                                            <c:choose>
                                                <c:when test="${entry.value[1] != 0}">
                                                    ${entry.value[1]}点
                                                </c:when>
                                                <c:otherwise>-</c:otherwise>
                                            </c:choose>
                                        </td>
 
                                        <td>
                                            <c:choose>
                                                <c:when test="${entry.value[2] != 0}">
                                                    ${entry.value[2]}点
                                                </c:when>
                                                <c:otherwise>-</c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:if>
 
                            </c:forEach>                               
 
                        </tbody>
                    </table>
                </c:when>
 
                <c:when test="${not empty student && empty scoreMap}">
                    <div class="mb-3">
                        <h3 class="h5 fw-bold">
                            氏名: ${student.name} (${student.no})
                        </h3>
                    </div>
                    <div class="alert alert-warning">
                        成績データが見つかりませんでした。
                    </div>
                </c:when>
 
                <c:otherwise>
                    <c:if test="${not empty errors}">
                        <div class="alert alert-danger">${errors}</div>
                    </c:if>
                </c:otherwise>
 
            </c:choose>
 
        </section>
    </c:param>
</c:import>
 
 