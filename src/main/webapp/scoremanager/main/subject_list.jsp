<%-- 河合太一 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目管理</c:param>
    
    <c:param name="scripts"></c:param>

    <c:param name="content">

        <%-- 画面タイトル --%>
        <div class="header" style="background-color: #f0f0f0; padding: 10px; font-weight: bold; width: 600px;">
            科目管理
        </div>

        <%-- 新規登録リンク --%>
        <div class="my-2 text-end px-4">
            <a href="SubjectCreate.action">新規登録</a>
        </div>

        <%-- 一覧テーブル --%>
        <table style="width: 600px; border-collapse: collapse;">
            <thead>
                <tr style="border-bottom: 1px solid #ddd;">
                    <th style="text-align: left; padding: 5px;">科目コード</th>
                    <th style="text-align: left; padding: 5px;">科目名</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="subject" items="${subjects}">
                    <tr>
                        <td style="border-bottom: 1px solid #eee; padding: 8px 5px;">
                            ${subject.cd}
                        </td>
                        <td style="border-bottom: 1px solid #eee; padding: 8px 5px;">
                            ${subject.name}
                        </td>
                        <td class="link-group" style="border-bottom: 1px solid #eee; padding: 8px 5px;">
                            <a href="SubjectUpdate.action?cd=${subject.cd}"
                               style="color: blue; text-decoration: underline; margin-left: 10px;">
                                変更
                            </a>
                            <a href="SubjectDelete.action?cd=${subject.cd}"
                               style="color: blue; text-decoration: underline; margin-left: 10px;">
                                削除
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </c:param>

</c:import>