<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
    <c:param name="title">成績参照</c:param>
 
    <c:param name="content">
        <section class="me-4">
 
            <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
                成績一覧（科目）
            </h2>
 
            <!-- ========================= -->
            <!-- 入力エリア -->
            <!-- ========================= -->
            <div class="bg-white p-3 rounded border mb-3">
 
 
                <!-- 上：科目検索 -->
                <form action="TestListSubjectExecute.action" method="get">
 
                    <div class="row align-items-end">
 
                        <!-- 科目情報ラベル -->
                        <div class="col-md-2 mb-3 fw-bold text-secondary">
                            科目情報
                        </div>
 
                        <!-- 入学年度 -->
                        <div class="col-md-2 mb-3">
                            <label class="form-label">入学年度</label>
                            <select name="f1" class="form-select" required>
                                <option value="">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>
                                        ${year}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
 
                        <!-- クラス -->
                        <div class="col-md-2 mb-3">
                            <label class="form-label">クラス</label>
                            <select name="f2" class="form-select" required>
                                <option value="">--------</option>
                                <c:forEach var="classNum" items="${class_num_set}">
                                    <option value="${classNum}" <c:if test="${classNum == f2}">selected</c:if>>
                                        ${classNum}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
 
                        <!-- 科目 -->
                        <div class="col-md-3 mb-3">
                            <label class="form-label">科目</label>
                            <select name="f3" class="form-select" required>
                                <option value="">--------</option>
                                <c:forEach var="sub" items="${subjects}">
                                    <option value="${sub.cd}" <c:if test="${sub.cd == f3}">selected</c:if>>
                                        ${sub.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
 
                        <!-- ボタン -->
                        <div class="col-md-3 mb-3 text-center">
                            <button type="submit" class="btn btn-secondary">
                                検索
                            </button>
                        </div>
 
                    </div>
 
                </form>
 
                <hr>
 
                <!-- 下：学生検索 -->
                <form action="TestListSubjectExecute.action" method="get">
 
                    <div class="row align-items-center">
 
                        <!-- 学生情報ラベル -->
                        <div class="col-md-2 mb-2 fw-bold text-secondary">
                            学生情報
                        </div>
 
                        <!-- 学生番号 -->
                        <div class="col-md-7 mb-2">
                            <label class="form-label">学生番号</label>
                            <input type="text" name="studentNo" class="form-control"
                                   placeholder="学生番号を入力してください">
                        </div>
 
                        <!-- ボタン -->
                        <div class="col-md-3 mb-2 text-center">
                            <button type="submit" class="btn btn-secondary">
                                検索
                            </button>
                        </div>
 
                    </div>
 
                </form>
 
            </div>
 
 
            <!-- ========================= -->
            <!-- 結果 -->
            <!-- ========================= -->
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
 