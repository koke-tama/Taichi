<%-- 河合太一 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
    <c:param name="title">成績参照</c:param>
 
    <c:param name="content">
        <section class="me-4">
 
            <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
                成績一覧（科目）
            </h2>
 
            <!-- 検索フォームエリア -->
            <div class="card p-4 mb-4">
                <!-- 科目情報検索（画像上段） -->
                <form action="TestListSubjectExecute.action" method="get" class="mb-4">
                    <div class="row align-items-center">
                        <div class="col-md-2">科目情報</div>
                        <div class="col-md-2">
                            <label class="small">入学年度</label>
                            <select name="f1" class="form-select form-select-sm">
                                <option value="">--------</option>
                                <c:forEach var="y" items="${ent_year_set}">
                                    <option value="${y}">${y}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <label class="small">クラス</label>
                            <select name="f2" class="form-select form-select-sm">
                                <option value="">--------</option>
                                <c:forEach var="c" items="${class_num_set}">
                                    <option value="${c}">${c}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label class="small">科目</label>
                            <select name="f3" class="form-select form-select-sm">
                                <option value="">--------</option>
                                <c:forEach var="s" items="${subjects}">
                                    <option value="${s.cd}">${s.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-2 text-end">
                            <button type="submit" class="btn btn-secondary btn-sm px-4">検索</button>
                        </div>
                    </div>
                </form>

                <hr>

                <!-- 学生情報検索（画像中段） -->
                <form action="TestListStudentExecute.action" method="get">
                    <div class="row align-items-center">
                        <div class="col-md-2">学生情報</div>
                        <div class="col-md-8">
                            <label class="small">学生番号</label>
                            <input type="text" name="f4" value="${f4}" class="form-control form-control-sm w-50" placeholder="1111111">
                        </div>
                        <div class="col-md-2 text-end">
                            <button type="submit" class="btn btn-secondary btn-sm px-4">検索</button>
                        </div>
                    </div>
                </form>
            </div>
 
 
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
 
                            <c:set var="data" value="${entry.value}" />
                            <c:set var="student" value="${data[0]}" />
 
                            <c:if test="${student != null}">
                                <tr>
                                    <td>${student.entYear}</td>
                                    <td>${student.classNum}</td>
                                    <td>${student.no}</td>
                                    <td>${student.name}</td>
 
                                    <td>
                                        <c:choose>
                                            <c:when test="${data[1] != 0}">
                                                ${data[1]}点
                                            </c:when>
                                            <c:otherwise>-</c:otherwise>
                                        </c:choose>
                                    </td>
 
                                    <td>
                                        <c:choose>
                                            <c:when test="${data[2] != 0}">
                                                ${data[2]}点
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
 
                <c:otherwise>
                    <p>学生情報が存在しませんでした</p>
                </c:otherwise>
 
            </c:choose>
 
        </section>
    </c:param>
</c:import>
 