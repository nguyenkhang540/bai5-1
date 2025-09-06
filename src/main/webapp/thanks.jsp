<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thanks</title>
    <link rel="stylesheet" href="styles/main.css">
</head>
<body class="brg">
<div class="form-card">
    <h1>Thanks for joining our email list!</h1>
    <p>Here is the information that you entered:</p>
    <ul class="user-info">
        <li><strong>First Name:</strong> ${firstName}</li>
        <li><strong>Last Name:</strong> ${lastName}</li>
        <li><strong>Email:</strong> ${email}</li>
    </ul>
    <form action="emailList" method="post" style="margin-top:10px">
        <button id="submit" type="submit">Return</button>
    </form>
</div>
</body>
</html>