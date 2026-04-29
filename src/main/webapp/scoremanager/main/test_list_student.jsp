<%-- 河合太一 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績一覧（学生）</c:param>

    <c:param name="content">
        <section class="me-4">

            <%-- ■ 画面タイトル --%>
            <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
                成績一覧（学生）
            </h2>

            <%-- ■ 検索フォームエリア --%>
            <div class="card p-4 mb-4">

                <%-- ▼ 科目条件検索フォーム --%>
                <form action="TestListSubjectExecute.action" method="get" class="mb-4">
                    <div class="row align-items-center">
                        <div class="col-md-2">科目情報</div>

                        <%-- 入学年度 --%>
                        <div class="col-md-2">
                            <label class="small">入学年度</label>
                            <select name="f1" class="form-select form-select-sm">
                                <option value="">--------</option>
                                <c:forEach var="y" items="${ent_year_set}">
                                    <option value="${y}">${y}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <%-- クラス --%>
                        <div class="col-md-2">
                            <label class="small">クラス</label>
                            <select name="f2" class="form-select form-select-sm">
                                <option value="">--------</option>
                                <c:forEach var="c" items="${class_num_set}">
                                    <option value="${c}">${c}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <%-- 科目 --%>
                        <div class="col-md-4">
                            <label class="small">科目</label>
                            <select name="f3" class="form-select form-select-sm">
                                <option value="">--------</option>
                                <c:forEach var="s" items="${subjects}">
                                    <option value="${s.cd}">${s.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <%-- 検索ボタン --%>
                        <div class="col-md-2 text-end">
                            <button type="submit" class="btn btn-secondary btn-sm px-4">検索</button>
                        </div>
                    </div>
                </form>

                <hr>

                <%-- ▼ 学生番号検索フォーム --%>
                <form action="TestListStudentExecute.action" method="get">
                    <div class="row align-items-center">
                        <div class="col-md-2">学生情報</div>

                        <%-- 学生番号入力 --%>
                        <div class="col-md-8">
                            <label class="small">学生番号</label>
                            <input type="text" name="f4" value="${f4}"
                                   class="form-control form-control-sm w-50"
                                   placeholder="1111111">
                        </div>

                        <%-- 検索ボタン --%>
                        <div class="col-md-2 text-end">
                            <button type="submit" class="btn btn-secondary btn-sm px-4">検索</button>
                        </div>
                    </div>
                </form>
            </div>

            <%-- ■ 成績データ有無判定 --%>
            <c:set var="hasScore" value="false" />
            <c:forEach var="entry" items="${scoreMap}">
                <c:set var="data" value="${entry.value}" />
                <c:if test="${data[1] != 0 || data[2] != 0}">
                    <c:set var="hasScore" value="true" />
                </c:if>
            </c:forEach>

            <%-- ■ 検索結果表示 --%>
            <c:choose>

                <%-- ▼ 成績データあり --%>
                <c:when test="${hasScore}">
                    <h2 class="h4 mb-3 fw-bold border-bottom pb-2">成績一覧</h2>

                    <%-- 学生 or 科目表示 --%>
                    <c:choose>
                        <c:when test="${not empty student}">
                            <div class="mb-2">氏名：${student.name} (${student.no})</div>
                        </c:when>
                        <c:when test="${not empty subject}">
                            <div class="mb-2">科目：${subject.name}</div>
                        </c:when>
                    </c:choose>

                    <%-- 成績テーブル --%>
                    <table class="table table-hover border mt-3">
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

                                    <%-- 1回目 --%>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${data[1] != 0}">${data[1]}点</c:when>
                                            <c:otherwise>-</c:otherwise>
                                        </c:choose>
                                    </td>

                                    <%-- 2回目 --%>
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

                <%-- ▼ 学生は存在するが成績なし --%>
                <c:when test="${not empty student}">
                    <div class="ms-2">
                        <div class="mb-1">氏名：${student.name} (${student.no})</div>
                        <div class="text-secondary">成績情報が存在しませんでした</div>
                    </div>
                </c:when>

                <%-- ▼ 学生が見つからない --%>
                <c:when test="${not empty f4 && empty student}">
                    <div class="ms-2 text-danger">学生情報が存在しませんでした</div>
                </c:when>

            </c:choose>

        </section>
    </c:param>
</c:import>