<%-- 長家優紀 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
    <c:param name="title">成績登録</c:param>
 
    <c:param name="content">
        <h2 class="mb-4">成績管理</h2>
 
        <div class="card p-3 mb-4" style="background-color:#ffffff;">
            <%-- 検索フォーム（年度・クラス・科目・回数） --%>
            <form action="TestRegist.action" method="post" class="row g-3">
 
                <div class="col-md-2">
                    <label class="form-label">入学年度</label>
                    <select name="year" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="y" items="${yearList}">
                            <option value="${y}" <c:if test="${y == selectedYear}">selected</c:if>>
                                ${y}
                            </option>
                        </c:forEach>
                    </select>
                </div>
 
                <div class="col-md-2">
                    <label class="form-label">クラス</label>
                    <select name="classNum" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="c" items="${classList}">
                            <option value="${c}" <c:if test="${c == selectedClassNum}">selected</c:if>>
                                ${c}
                            </option>
                        </c:forEach>
                    </select>
                </div>
 
                <div class="col-md-3">
                    <label class="form-label">科目</label>
                    <select name="subjectCd" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="s" items="${subjectList}">
                            <option value="${s.cd}" <c:if test="${s.cd == selectedSubjectId}">selected</c:if>>
                                ${s.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
 
                <div class="col-md-2">
                    <label class="form-label">回数</label>
                    <select name="count" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="c" items="${countList}">
                            <option value="${c}" <c:if test="${c == selectedCount}">selected</c:if>>
                                ${c}
                            </option>
                        </c:forEach>
                    </select>
                </div>
 
                <div class="col-md-2 d-flex align-items-end">
                    <button type="submit" class="btn btn-secondary">検索</button>
                </div>
            </form>
        </div>
 
        <%-- 成績入力・登録フォーム --%>
        <form action="TestRegistExecute.action" method="post">
 
            <c:if test="${not empty testList}">
 
                <div class="mb-3 fw-bold">
                    科目：
                    <c:forEach var="s" items="${subjectList}">
                        <c:if test="${s.cd == selectedSubjectId}">${s.name}</c:if>
                    </c:forEach>
                    （${selectedCount}回）
                </div>
 
                <table class="table align-middle">
                    <thead>
                        <tr style="border-bottom: 2px solid #dee2e6;">
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>
                    </thead>
 
                    <tbody>
                        <c:forEach var="t" items="${testList}">
                            <tr style="border-bottom: 1px solid #dee2e6;">
                                <td>${t.student.entYear}</td>
                                <td>${t.student.classNum}</td>
                                <td>${t.student.no}</td>
                                <td>${t.student.name}</td>
                                <td>
                                    <%-- 点数入力欄（エラー表示付き） --%>
                                    <div style="width:220px;">
                                        <input type="text"
                                               name="point_${t.student.no}"
                                               value="${t.point}"
                                               class="form-control"
                                               style="width:100%;">
 
                                        <c:if test="${not empty errorMap[t.student.no]}">
                                            <div style="color:#ffc107; font-size:14px; margin-top:4px; white-space:nowrap;">
                                                ${errorMap[t.student.no]}
                                            </div>
                                        </c:if>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
 
                <%-- hidden（再送信用） --%>
                <input type="hidden" name="year" value="${selectedYear}">
                <input type="hidden" name="classNum" value="${selectedClassNum}">
                <input type="hidden" name="subjectCd" value="${selectedSubjectId}">
                <input type="hidden" name="count" value="${selectedCount}">
 
                <div class="mt-4">
                    <button type="submit" class="btn btn-secondary">登録して終了</button>
                </div>
 
            </c:if>
        </form>
 
    </c:param>
</c:import>