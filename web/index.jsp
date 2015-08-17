<%@page import="raiz.VistaRaiz"%>
<html>
    <!--CABECERA-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <!--CUERPO-->
    <body>
        <%
          out.println(new VistaRaiz().mostrar());
          if(atributo == "Registro"){
        %>
        
        <codigo>
        html
        </codigo>
        
        <%}else if(atributo == "Pensadero"){%>
        
        <codigo>
        html
        </codigo>
        
        <%}%>
    </body>
</html>
