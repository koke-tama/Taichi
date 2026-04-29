<%-- 河合太一 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
    <c:param name="title">成績参照</c:param>
 
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
                成績参照
            </h2>
 
            <div class="bg-white p-4 rounded border mb-4 shadow-sm">
                
                <form action="TestListSubjectExecute.action" method="get" class="mb-3">
                    <input type="hidden" name="searched" value="true">
                    <div class="row align-items-center">
                        <div class="col-md-2 fw-bold text-secondary">科目情報</div>
                        
                        <div class="col-md-2">
                            <label class="form-label small mb-1">入学年度</label>
                            <select name="f1" class="form-select form-select-sm">
                                <option value="">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="col-md-2">
                            <label class="form-label small mb-1">クラス</label>
                            <select name="f2" class="form-select form-select-sm">
                                <option value="">--------</option>
                                <c:forEach var="classNum" items="${class_num_set}">
                                    <option value="${classNum}" <c:if test="${classNum == f2}">selected</c:if>>${classNum}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="col-md-4">
                            <label class="form-label small mb-1">科目</label>
                            <select name="f3" class="form-select form-select-sm">
                                <option value="">--------</option>
                                <c:forEach var="sub" items="${subjects}">
                                    <option value="${sub.cd}" <c:if test="${sub.cd == f3}">selected</c:if>>${sub.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="col-md-2 pt-4 text-end">
                            <button type="submit" class="btn btn-secondary btn-sm px-4">検索</button>
                        </div>
                    </div>
                </form>
                
                <c:if test="${not empty param.searched and (empty param.f1 or empty param.f2 or empty param.f3)}">
                    <div class="text-warning mb-3 ms-2">
                        入学年度とクラスと科目を選択してください
                    </div>
                </c:if>
 
                <hr class="my-4">
 
                <form action="TestListStudentExecute.action" method="get">
                    <div class="row align-items-center">
                        <div class="col-md-2 fw-bold text-secondary">学生情報</div>
                        
                        <div class="col-md-8">
                            <label class="form-label small mb-1">学生番号</label>
                            <input type="text" name="f4" value="${f4}" class="form-control form-control-sm w-50"
                                   placeholder="学生番号を入力してください">
                        </div>
                        
                        <div class="col-md-2 pt-4 text-end">
                            <button type="submit" class="btn btn-secondary btn-sm px-4">検索</button>
                        </div>
                    </div>
                </form>
            </div>
 
            <c:set var="hasScore" value="false" />
            <c:forEach var="entry" items="${scoreMap}">
                <c:set var="data" value="${entry.value}" />
                <c:if test="${data[1] != 0 || data[2] != 0}">
                    <c:set var="hasScore" value="true" />
                </c:if>
            </c:forEach>
 
            <c:choose>
                <c:when test="${empty scoreMap && empty f1 && empty f4}">
                    <div class="text-info fw-bold mb-3">
                        科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
                    </div>
                </c:when>
 
                <c:when test="${hasScore}">
                    <h2 class="h4 mb-3 fw-bold border-bottom pb-2">成績一覧</h2>
                    
                    <div class="mb-3 ps-1">
                        <c:choose>
                            <c:when test="${not empty student}">
                                <span class="badge bg-secondary me-2">学生</span> 氏名：${student.name} (${student.no})
                            </c:when>
                            <c:when test="${not empty subject}">
                                <span class="badge bg-secondary me-2">科目</span> 科目：${subject.name}
                            </c:when>
                        </c:choose>
                    </div>
 
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
                                <c:set var="st" value="${data[0]}" />
                                <tr>
                                    <td>${st.entYear}</td>
                                    <td>${st.classNum}</td>
                                    <td>${st.no}</td>
                                    <td>${st.name}</td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${data[1] != 0}">${data[1]}点</c:when>
                                            <c:otherwise>-</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${data[2] != 0}">${data[2]}点</c:when>
                                            <c:otherwise>-</c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
 
                <c:when test="${not empty student}">
                    <div class="alert alert-warning">
                        氏名：${student.name} (${student.no})<br>
                        成績情報が存在しませんでした
                    </div>
                </c:when>
 
                <c:otherwise>
                    <div class="alert alert-danger">該当するデータが見つかりませんでした</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>