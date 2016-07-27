<%--
  Created by IntelliJ IDEA.
  User: AlexY
  Date: 2016/7/27
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>


<form action="${pageContext.request.contextPath}/Servlet/ControllerServlet?op=addCustomer"
    method="post">

    <table border="1">
        <tr>
            <td>姓名：</td>
            <td>
                <input name="name">
            </td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                <input type="radio" name="gender" value="male"/>男性
                <input type="radio" name="gender" value="female"/>女性
            </td>
        </tr>
        <tr>
            <td>生日(yyyy-MM-dd)：</td>
            <td>
                <input type="text" name="birthday"/>
            </td>
        </tr>
        <tr>
            <td>电话：</td>
            <td>
                <input type="text" name="phone"/>
            </td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>爱好：</td>
            <td>
                <input type="checkbox" name="hobbies" value="吃饭"/>吃饭
                <input type="checkbox" name="hobbies" value="睡觉"/>睡觉
                <input type="checkbox" name="hobbies" value="学java"/>学java
            </td>
        </tr>
        <tr>
            <td>类型：</td>
            <td>
                <input type="radio" name="type" value="普通客户" checked="checked"/>普通客户
                <input type="radio" name="type" value="VIP"/>VIP
            </td>
        </tr>
        <tr>
            <td>简介：</td>
            <td>
                <textarea rows="3" cols="38" name="description"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="保存"/>
            </td>
        </tr>
    </table>


</form>


</body>
</html>
