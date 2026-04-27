<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績登録</c:param>

    <c:param name="content">
        <h2 class="mb-4">成績管理</h2>

        <!-- エラーメッセージ -->
        <c:if test="${not empty filter_errors}">
            <div class="text-danger mb-3 small">${filter_errors}</div>
        </c:if>

        <!-- 検索フォーム -->
        <div class="card p-3 mb-4 bg-light">
            <form action="TestRegist.action" method="post" class="row g-3">

                <!-- 入学年度 -->
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

                <!-- ★ クラス（ここが修正ポイント） -->
                <div class="col-md-2">
                    <label class="form-label">クラス</label>
                    <select name="classNum" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="c" items="${classList}">
                            <option value="${c}"
                                <c:if test="${c == selectedClassNum}">selected</c:if>>
                                ${c}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- 科目 -->
                <div class="col-md-3">
                    <label class="form-label">科目</label>
                    <select name="subjectCd" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="s" items="${subjectList}">
                            <option value="${s.cd}"
                                <c:if test="${s.cd == selectedSubjectId}">selected</c:if>>
                                ${s.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- 回数 -->
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

        <!-- 成績一覧 -->
        <form action="TestRegistExecute.action" method="post">

            <c:if test="${not empty testList}">
                <table class="table table-bordered align-middle">
                    <thead class="table-light">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="t" items="${testList}">
                            <tr>
                                <td>${t.student.entYear}</td>
                                <td>${t.student.classNum}</td>
                                <td>${t.student.no}</td>
                                <td>${t.student.name}</td>
                                <td>
                                    <input type="text"
                                           name="point_${t.student.no}"
                                           value="${t.point}"
                                           class="form-control"
                                           style="width:100px;">
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- ★ hiddenで値を引き継ぐ -->
                <input type="hidden" name="year" value="${selectedYear}">
                <input type="hidden" name="classNum" value="${selectedClassNum}">
                <input type="hidden" name="subjectCd" value="${selectedSubjectId}">
                <input type="hidden" name="count" value="${selectedCount}">

                <div class="mt-3">
                    <button type="submit" class="btn btn-secondary">登録して終了</button>
                </div>
            </c:if>

        </form>

    </c:param>
</c:import>