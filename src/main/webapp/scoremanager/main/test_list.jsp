<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
    <c:param name="title">成績参照</c:param>
 
    <c:param name="content">
        <section class="me-4">
            <%-- ページのメイン見出し --%>
            <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
                成績参照
            </h2>
 
            <%-- --- 検索入力エリア --- --%>
            <div class="bg-white p-3 rounded border mb-4">
                
                <%-- 上段：科目別検索フォーム --%>
                <form action="TestListSubjectExecute.action" method="get" class="mb-3">
                    <div class="row align-items-center">
                        <div class="col-md-2 fw-bold">科目情報</div>
                        
                        <div class="col-md-2">
                            <label class="form-label small text-muted mb-1">入学年度</label>
                            <select name="f1" class="form-select form-select-sm" required>
                                <option value="">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="col-md-2">
                            <label class="form-label small text-muted mb-1">クラス</label>
                            <select name="f2" class="form-select form-select-sm" required>
                                <option value="">--------</option>
                                <c:forEach var="classNum" items="${class_num_set}">
                                    <option value="${classNum}" <c:if test="${classNum == f2}">selected</c:if>>${classNum}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="col-md-3">
                            <label class="form-label small text-muted mb-1">科目</label>
                            <select name="f3" class="form-select form-select-sm" required>
                                <option value="">--------</option>
                                <c:forEach var="sub" items="${subjects}">
                                    <option value="${sub.cd}" <c:if test="${sub.cd == f3}">selected</c:if>>${sub.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="col-md-2 pt-4">
                            <button type="submit" class="btn btn-secondary btn-sm px-4">検索</button>
                        </div>
                    </div>
                </form>
 
                <hr class="my-3">
 
                <%-- 下段：学生別検索フォーム --%>
                <form action="TestListStudentExecute.action" method="get">
                    <div class="row align-items-center">
                        <div class="col-md-2 fw-bold">学生情報</div>
                        
                        <div class="col-md-7">
                            <label class="form-label small text-muted mb-1">学生番号</label>
                            <input type="text" name="f4" class="form-control form-control-sm"
                                   placeholder="学生番号を入力してください" required>
                        </div>
                        
                        <div class="col-md-3 pt-4">
                            <button type="submit" class="btn btn-secondary btn-sm px-4">検索</button>
                        </div>
                    </div>
                </form>
            </div>
 
            <%-- ガイドメッセージ（検索前のみ表示） --%>
            <c:if test="${empty scoreMap && empty f1 && empty f4}">
                <div class="text-info fw-bold mb-3">
                    科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
                </div>
            </c:if>
 
            <%-- --- 検索結果表示エリア --- --%>
            <c:choose>
                <c:when test="${not empty scoreMap}">
                    <%-- 2枚目の写真の見出し --%>
                    <h2 class="h4 mb-3 fw-bold border-bottom pb-2">成績一覧（科目）</h2>
                    
                    <c:if test="${not empty subject}">
                        <div class="mb-2">科目：${subject.name}</div>
                    </c:if>
 
                    <table class="table table-hover border">
                        <thead class="table-light">
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th class="text-center">1回</th>
                                <th class="text-center">2回</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="entry" items="${scoreMap}">
                                <c:set var="data" value="${entry.value}" />
                                <c:set var="student" value="${data[0]}" />
                                <tr>
                                    <td>${student.entYear}</td>
                                    <td>${student.classNum}</td>
                                    <td>${student.no}</td>
                                    <td>${student.name}</td>
                                    <td class="text-center">
                                        <%-- 0点以上なら表示、それ以外ならハイフン --%>
                                        <c:choose>
                                            <c:when test="${data[1] != 0}">${data[1]}</c:when>
                                            <c:otherwise>-</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${data[2] != 0}">${data[2]}</c:when>
                                            <c:otherwise>-</c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                
                <%-- 検索結果が空の場合 --%>
                <c:when test="${(not empty f1 || not empty f4) && empty scoreMap}">
                    <div class="alert alert-warning">学生情報が存在しませんでした</div>
                </c:when>
            </c:choose>
        </section>
    </c:param>
</c:import>
 