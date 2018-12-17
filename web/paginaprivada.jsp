<%-- 
    Document   : paginaprivada
    Created on : 17-dic-2018, 10:12:47
    Author     : Mañanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String usuario=(String)session.getAttribute("usuario");
    if (usuario==null)
    {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello <%=usuario%></h1>
INFORMACIÓN SUPERPRIVADA. 
    </body>
</html>
