<%-- 長家優紀 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
    <c:param name="title">学生情報変更</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
<div class="row px-10 fs-9 my-11">
<h3 class="mb-4 bg-light p-2 border">学生情報変更</h3>
 
<form action="StudentUpdateExecute.action" method="post">
 
 <!-- 入学年度 -->
<div class="mb-3">
    <label class="fw-bold">入学年度</label>
    <div class="ms-3 pt-1">
        ${student.entYear}
        <input type="hidden" name="ent_year" value="${student.entYear}">
    </div>
</div>

<!-- 学生番号 -->
<div class="mb-3">
    <label class="fw-bold">学生番号</label>
    <div class="ms-3 pt-1">
        ${student.no}
        <input type="hidden" name="no" value="${student.no}">
        <input type="hidden" name="school_cd" value="${student.school.cd}">
    </div>
</div>

<!-- 氏名 -->
<div class="mb-3">
    <label class="fw-bold">氏名</label>
    <div class="ms-3 pt-1">
        <input type="text" name="name" value="${student.name}" class="form-control">
    </div>
</div>

<!-- クラス -->
<div class="mb-3">
    <label class="fw-bold">クラス</label>
    <div class="ms-3 pt-1">
        <select name="class_num" class="form-select">
            <c:forEach var="cls" items="${class_list}">
                <option value="${cls}" <c:if test="${cls == student.classNum}">selected</c:if>>
                    ${cls}
                </option>
            </c:forEach>
        </select>
    </div>
</div>

<!-- 在学中 -->
<div class="mb-3">
    <label class="fw-bold">在学中</label>
    <div class="ms-3 pt-1">
        <input type="checkbox" name="is_attend" class="form-check-input"
            <c:if test="${student.attend}">checked</c:if>>
    </div>
</div>
    <!-- ボタン -->
    <div class="mt-3">
    <button type="submit" class="btn btn-primary px-3">
        変更
    </button><br>
 
    <a href="StudentList.action">戻る</a>
    </a>
</div>
 
</form>
</div>         
        </section>
    </c:param>
</c:import>
 
 