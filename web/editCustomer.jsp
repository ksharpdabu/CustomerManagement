<%--
  Created by IntelliJ IDEA.
  User: AlexY
  Date: 2016/7/27
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>修改客户</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<form action="${pageContext.request.contextPath}/Servlet/ControllerServlet?op=editCustomer"
    method="post"
>

    <input type="hidden" name="id" value="${c.id}">

    <table border="1">
        <tr>
            <td>姓名：</td>
            <td>
                <input name="name" value="${c.name}">
            </td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                <input type="radio" name="gender" value="male" ${c.gender=='male'?'checked="checked"':'' }/>男性
                <input type="radio" name="gender" value="female" ${c.gender=='female'?'checked="checked"':'' }/>女性
            </td>
        </tr>
        <tr>
            <td>生日(yyyy-MM-dd)：</td>
            <td>
                <input type="text" name="birthday" value="${c.birthday }"/>
            </td>
        </tr>
        <tr>
            <td>电话：</td>
            <td>
                <input type="text" name="phone" value="${c.phone }"/>
            </td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td><input type="text" name="email" value="${c.email }"/></td>
        </tr>
        <tr>
            <td>爱好：</td>
            <td>
                <input type="checkbox" name="hobbies" value="吃饭" ${fn:contains(c.hobby,'吃饭')?'checked="checked"':'' } />吃饭
                <input type="checkbox" name="hobbies" value="睡觉" ${fn:contains(c.hobby,'睡觉')?'checked="checked"':'' }/>睡觉
                <input type="checkbox" name="hobbies" value="学java" ${fn:contains(c.hobby,'学java')?'checked="checked"':'' }/>学java
            </td>
        </tr>
        <tr>
            <td>类型：</td>
            <td>
                <input type="radio" name="type" value="普通客户" ${c.type=='普通客户'?'checked="checked"':'' } />普通客户
                <input type="radio" name="type" value="VIP" ${c.type=='VIP'?'checked="checked"':'' }/>VIP
            </td>
        </tr>
        <tr>
            <td>简介：</td>
            <td>
                <textarea rows="3" cols="38" name="description">${c.description }</textarea>
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
