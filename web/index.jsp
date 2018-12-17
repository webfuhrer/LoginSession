<%-- 
    Document   : index
    Created on : 17-dic-2018, 9:47:37
    Author     : Mañanas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Object o=request.getAttribute("respuesta");
    String respuesta_registro="";
    if (o!=null)
    {
         int respuesta=(int)o;
         switch(respuesta)
         {
             case 0:
                 respuesta_registro="Todo ha ido bien";
                 break;
             case 1:
                 respuesta_registro="El usuario está repetido";
                 break;
                 
             case 2: 
                 respuesta_registro="Ha habido algún problema";
                 break;
             case 3:
                 respuesta_registro="Usuario o pwd incorrectos";
                 break;
         }
    }
   
    //0-Todo bien; 1-Usuario repe; 2-Fallo conexion o algo así
 
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Login</p>
        <h1><%=respuesta_registro%></h1>
        <form action="ServletLogin" method="POST">
            Usuario: <input type="text" placeholder="Usuario" name="usuario">
            Password: <input type="password" name="password">
            <input type="hidden" name="accion" value="login">
            <input type="submit" value="Entrar">
        </form>
        <hr>
        <form action="ServletLogin" method="POST">
           
            Nombre: <input type="text" placeholder="Nombre" name="nombre">
            Usuario: <input type="text" placeholder="Usuario" name="usuario">
            Password: <input type="password" name="password">
            <input type="hidden" name="accion" value="registro">
            <input type="submit" value="Registrarse">
        </form>
    </body>
</html>
