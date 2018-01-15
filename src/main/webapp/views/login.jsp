<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 09.01.18
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<title>SIGN IN</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="styles/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<head>
    <link rel="icon" href="img/icon.png" type="image/png" sizes="16x16">
</head>
<body>
<%@include file="/views/nav.jspf" %>
<div class="w3-card">
    <div class="w3-container w3-deep-purple w3-animate-opacity">
        <h2>Sign In</h2>
    </div>
    <form class="w3-container" action="/action_page.php">
        <p>
        <div class="w3-row w3-section w3-text-deep-purple">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-envelope-o"></i></div>
            <div class="w3-rest">
                <input class="w3-input w3-border" name="email" type="email" placeholder="Email">
            </div>
        </div>
        <p>
        <div class="w3-row w3-section w3-text-deep-purple">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-unlock-alt"></i></div>
            <div class="w3-rest">
                <input class="w3-input w3-border" name="password" type="password" placeholder="Password">
            </div>
        </div>
        <input type="email">
        <p>
            <button class="w3-btn w3-deep-purple">Submit</button></p>
    </form>
</div>
<%@include file="/views/footer.jspf" %>
</body>
</html>

