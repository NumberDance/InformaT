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
          Usuario usuario = getSession("User");
          ArrayList lista = usuario.cargarListaDeContactos(); // El usuario ya tiene su ID guardado
                                                              // internamente se hacen consultas sql
                                                              
        foreach(contacto : lista){%>
            
            <div>nombre Tal y Cual</div>
            
        <%}
        
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
