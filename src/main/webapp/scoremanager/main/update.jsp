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
    <div class="mb-3 row">
        <label class="col-sm-3 col-form-label">入学年度</label><br>
        <div class="col-sm-7">
            <input type="number" name="ent_year"
                   value="${student.entYear}"
                   class="form-control">
        </div>
    </div>
 
    <!-- 学生番号 -->
    <div class="mb-3 row">
        <label class="col-sm-3 col-form-label">学生番号</label><br>
        <div class="col-sm-9 pt-2">
            ${student.no}
            <input type="hidden" name="no" value="${student.no}">
            <input type="hidden" name="school_cd" value="${student.school.cd}">
        </div>
    </div>
 
    <!-- 氏名 -->
    <div class="mb-3 row">
        <label class="col-sm-3 col-form-label">氏名</label><br>
        <div class="col-sm-9">
            <input type="text" name="name"
                   value="${student.name}"
                   class="form-control">
        </div>
    </div>
 
    <!-- クラス -->
    <div class="mb-3 row">
        <label class="col-sm-3 col-form-label">クラス</label><br>
        <div class="col-sm-9">
            <select name="class_num" class="form-select">
                <c:forEach var="cls" items="${class_list}">
                    <option value="${cls}"
                        <c:if test="${cls == student.classNum}">selected</c:if>>
                        ${cls}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
 
    <!-- 在学中 -->
    <div class="mb-3 row">
    <label class="col-sm-3 col-form-label">在学中</label>
 
    <div class="col-sm-9 d-flex align-items-center">
        <input type="checkbox" name="is_attend" class="me-2"
            <c:if test="${student.attend}">checked</c:if>>
    </div>
</div>
    <!-- ボタン -->
    <div class="text-center mt-3">
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
 
 