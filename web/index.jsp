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
        %>
        
        <!-- Aquí comienza el menu de navegación -->
        <ul class="menu-navigation">
            <%
            //Obtienes del reques o del session el arraylist<String, Boolean
            ArrayList lista_secciones
            foreach(Seccion sec : lista_secciones){
            %>
                <!-- Sección del menú -->
                <li> 
                    <% sec.getNombre(); %> 
                </li>
                
            <%
            }
            %>
        </ul>
        <!-- Fin del menu desplegable -->
        
        <%>
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
