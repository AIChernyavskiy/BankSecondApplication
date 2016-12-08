<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <spring:url value="/resources/front/css/admin.css" var="adminCss" />
    <link href="${adminCss}" rel="stylesheet" />
    <spring:url value="/resources/front/js/admin.js" var="adminJs"/>
    <script src="${adminJs}"></script>
    <spring:url value="/resources/front/js/jquery.js" var="jqueryJs"/>
    <script src="${jqueryJs}"></script>
    <meta charset="UTF-8">
    <title>Bank Application</title>
</head>
<body>
<!--<div class = "menu1">
    <ul class="main-menu">
        <li class="opened">
            <a href="/Client">Client</a>
                <ul class="sub-menu sub-menu-1">
                <li><a href="/Client">Create client</a></li>
                <li><a href="/Client">Update client</a></li>
                <li><a href="/Client">Delete client</a></li>
                <li><a href="/Client">Find client</a></li>
                <li><a href="/Client">Print all client</a></li>
            </ul>
        </li>
        <li class="opened">
            <a href="/Account">Account</a>
                <ul class="sub-menu sub-menu-2">
                <li><a href="/Account">Create account</a></li>
                <li><a href="/Account">Create account and client</a></li>
                <li><a href="/Account">Update account</a></li>
                <li><a href="/Account">Delete account</a></li>
                <li><a href="/Account">Find account</a></li>
                <li><a href="/Account">Find all account</a></li>
            </ul>
        </li>
        <li class="opened">
            <a href="/Document">Document</a>
                <ul class="sub-menu sub-menu-3">
                <li><a href="/Document">Create document</a></li>
                <li><a href="/Document">Create document, account and client</a></li>
                <li><a href="/Document">Delete document</a></li>
                <li><a href="/Document">Find document</a></li>
                <li><a href="/Document">Find all document</a></li>
            </ul>
        </li>

    </ul>
</div> -->
<div class="SendMessage">
    <div>
        <form action="/AdminSendMessage" method="get">
            <p><strong>ID DOCUMENT</strong></p>
            <p><input name="idDocument" required maxlength="10" size="50" pattern="^[ 0-9]+$"></p>
            <p><input type="submit" value="Cancel document"></p>
        </form>
        <input name="messageFromServerCreate" maxlength="30" size="50" value="${messageCancel}">
    </div>
</div>
</body>
</html>