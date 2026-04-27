<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
<c:param name="title">ログイン</c:param>
<c:param name="scripts">
<script>
function togglePassword() {
    const pw = document.getElementById("password");
    pw.type = pw.type === "password" ? "text" : "password";
}
</script>
</c:param>
 
<c:param name="content">
 
<div class="mx-auto mt-5" style="max-width: 400px;">
 
    <h2 class="h5 text-center p-2 mb-0 bg-light border border-bottom-0" style="color: #333;">ログイン</h2>
 
    <div class="border p-4 bg-white">

        <c:if test="${not empty error}">
            <p class="mb-3" style="font-size: 0.85rem; color: #000;">
                ${error}
            </p>
        </c:if>
 
        <form action="LoginExecute.action" method="post">
 
            <div class="mb-2 p-2 border rounded" style="background-color: #fff;">
                <label class="d-block small text-secondary mb-0" style="font-size: 0.7rem;">ID</label>
                <input type="text" name="id" value="${id}" required
                       class="w-100 border-0"
                       style="background: transparent; outline: none; padding: 2px 0;">
            </div>
 
            <div class="mb-3 p-2 border rounded" style="background-color: #fff;">
                <label class="d-block small text-secondary mb-0" style="font-size: 0.7rem;">パスワード</label>
                <input type="password" id="password" name="password" required
                       class="w-100 border-0"
                       style="background: transparent; outline: none; padding: 2px 0;">
            </div>
 
            <div class="d-flex justify-content-center mb-4" style="font-size: 0.85rem; color: #666;">
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="showPw" onclick="togglePassword()">
                    <label class="form-check-label" for="showPw">パスワードを表示</label>
                </div>
            </div>
 
            <div class="text-center">
                <button type="submit" class="btn btn-primary px-4" style="min-width: 120px;">ログイン</button>
            </div>
 
        </form>
 
    </div>
</div>
 
</c:param>
</c:import>
 