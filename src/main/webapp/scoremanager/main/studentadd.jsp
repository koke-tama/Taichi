<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
  <c:param name="title">学生新規登録</c:param>
 
  <c:param name="content">
      <section class="me-4">
          <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
              学生情報登録
          </h2>
 
          <form action="StudentAdd" method="post" class="px-4">
 
              <!-- 入学年度 -->
              <div class="mb-3">
                  <label class="form-label">入学年度</label>
 
                  <select name="entYear"
                          class="form-control ${not empty errors.entYear ? 'border-warning' : ''}">
 
                      <option value="">--------</option>
 
                      <c:forEach var="y" items="${ent_year_set}">
                          <option value="${y}"
                              <c:if test="${student.entYear == y}">selected</c:if>>
                              ${y}
                          </option>
                      </c:forEach>
                  </select>
 
                  <div class="text-warning">
                      ${errors.get("entYear")}
                  </div>
              </div>
 
              <!-- 学生番号 -->
              <div class="mb-3">
                  <label class="form-label">学生番号</label>
 
                  <input type="text"
                         name="no"
                         class="form-control ${not empty errors.no ? 'border-warning' : ''}"
                         placeholder="学生番号を入力してください"
                         value="${student.no}">
 
                  <div class="text-danger">
                      ${errors.get("no")}
                  </div>
              </div>
 
              <!-- 氏名 -->
              <div class="mb-3">
                  <label class="form-label">氏名</label>
 
                  <input type="text"
                         name="name"
                         class="form-control ${not empty errors.name ? 'border-warning' : ''}"
                         placeholder="氏名を入力してください"
                         value="${student.name}">
 
                  <div class="text-danger">
                      ${errors.get("name")}
                  </div>
              </div>
 
              <!-- クラス -->
              <div class="mb-3">
                  <label class="form-label">クラス</label>
 
                  <select name="classNum"
                          class="form-control ${not empty errors.classNum ? 'border-warning' : ''}">
 
                      <c:forEach var="c" items="${class_num_set}">
                          <option value="${c}"
                              <c:if test="${student.classNum == c}">selected</c:if>>
                              ${c}
                          </option>
                      </c:forEach>
                  </select>
 
                  <div class="text-warning">
                      ${errors.get("classNum")}
                  </div>
              </div>
 
              <!-- ボタン -->
              <button type="submit" class="btn btn-primary">
                  登録して終了
              </button>
 
              <!-- 戻る -->
              <div class="mt-3 text-start">
                  <a href="${pageContext.request.contextPath}/scoremanager/main/StudentList.action"
                     class="text-primary text-decoration-underline">
                      戻る
                  </a>
              </div>
 
          </form>
      </section>
  </c:param>
</c:import>
 