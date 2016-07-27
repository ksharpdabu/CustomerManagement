<%--
  Created by IntelliJ IDEA.
  User: AlexY
  Date: 2016/7/26
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>客户信息列表</title>
    <meta http-equiv="pragma" content="no-cache" >
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link  rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/style.css" />

  </head>
  <body>

  <%--利用form表单来提交批量选中的记录的id，这样就不需要使用js来手动获取了--%>
  <form id="f1" action="${pageContext.request.contextPath}/Servlet/ControllerServlet?op=delMultiCustomer" method="post">
    <table width="88%">

      <tr>

        <td>
          <a href="${pageContext.request.contextPath}/addCustomer.jsp">添加</a>
          <a href="javascript:delMuti();">删除</a>

        </td>
      </tr>


      <tr>


        
         <table border="1" width="100%">

           <tr>

             <th>选择</th>
             <th>姓名</th>
             <th>性别</th>
             <th>生日</th>
             <th>电话</th>
             <th>邮箱</th>
             <th>爱好</th>
             <th>类型</th>
             <th>描述</th>
             <th>操作</th>



           </tr>

           <%--varStatus等价于for循环中的计数器，不过功能更多--%>
           <c:forEach items="${cs}" var="c" varStatus="vs">
             
             <tr class="${ vs.index%2 == 0 ?  "odd ": "even"}">

               <td>
                 <input type="checkbox" name="id" value="${c.id}">
               </td>
               <td>${c.name}</td>
               <td>${c.gender=='male'?'男性':'女性'}</td>
               <td>${c.birthday}</td>
               <td>${c.phone}</td>
               <td>${c.email}</td>
               <td>${c.hobby}</td>
               <td>${c.type}</td>
               <td>${c.description}</td>
               <td>
                 <a href="${pageContext.request.contextPath}/Servlet/ControllerServlet?op=editCustomerUI&customerId=${c.id}">修改</a>

                   <%--${c.id}需要用引号包裹，表名delOne函数的参数是一个字符串--%>
                 <a href="javascript:delOne('${c.id}')">删除</a>
               </td>
               
               
               
             </tr>
             
             
              

           </c:forEach>


         </table>

      </tr>


    </table>

    </form>

    <script>


      function delOne(customerId) {

        var sure = window.confirm("确认删除所选记录吗？");

        if(sure){
          window.location.href="${pageContext.request.contextPath}/Servlet/ControllerServlet?op=delOneCustomer&customerId="+customerId;
        }


      }



      function delMuti(){
        var selected = false;
        //检查用户是否做了选择
        var idsObjs = document.getElementsByName("id");
        for(var i=0;i<idsObjs.length;i++){
          if(idsObjs[i].checked){
            selected=true;
            break;
          }
        }

        if(!selected){
          alert("请先选择要删除的记录");
          return;
        }
        //用户至少选了一个
        var sure = window.confirm("确定要删除所选记录吗？");
        if(sure){
//          提交表单
          document.getElementById("f1").submit();
        }
      }



    </script>



  </body>
</html>
