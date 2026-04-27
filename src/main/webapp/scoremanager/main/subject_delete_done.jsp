<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="/common/base.jsp">
    <c:param name="title">科目情報削除</c:param>
 
    <c:param name="content">
 
        <!-- ① タイトル -->
        <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">
            科目情報削除
        </h2>
 
        <div class="px-4">
 
            <!-- ② 成功メッセージ -->
            <div class="alert alert-success text-center text-dark">
   			 	削除が完了しました
			</div>
            
            <br><br>
 
            <!-- ③ 戻るリンク -->
            <div class="mt-3">
                <a href="SubjectList.action"
                   class="text-primary text-decoration-underline">
                    科目一覧
                </a>
            </div>
 
        </div>
 
    </c:param>
</c:import>
Oracle Java Technologies | Oracle
Java can help reduce costs, drive innovation, & improve application services; the #1 programming language for IoT, enterprise architecture, and cloud computing.
 