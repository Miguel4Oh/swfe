<%@ page import="com.ortega.webapp.servlet.swfe.models.UserEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%
    List<UserEntity> usuarios = (List<UserEntity>) request.getAttribute("usuarios");

    String mensaje = "";
    if (request.getSession().getAttribute("mensaje") != null) {
        mensaje = (String) request.getSession().getAttribute("mensaje");
        System.out.println("Mensaje: " + mensaje);
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Usuarios</title>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&family=Quicksand:wght@300..700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/icons/favicon.ico">
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<main>

    <jsp:include page="barra-lateral.jsp" flush="true"/>
    <jsp:include page="ventana-modal.jsp" flush="true"/>

    <%if (mensaje.equals("error")) {
        request.getSession().removeAttribute("mensaje");%>
        <input type="hidden" id="mensaje" value="<%= mensaje %>">
    <% } %>

    <section class="main-section" id="main-section">
        <div class="tabla-envoltura">
            <div class="tabla-header">
                <div class="titulo-buscador">
                    <h1>Usuarios</h1>
                </div>
                <div class="botones-facturacion">
                    <a class="enlace" href="${pageContext.request.contextPath}/usuarios/usuario-form">
                        <button class="boton-agregar">
                            <svg class="svg-agregar" width="800px" height="800px" viewBox="0 0 24 24" fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                                <circle opacity="0.5" cx="12" cy="12" r="10" stroke="#1C274C" stroke-width="1.5"/>
                                <path d="M15 12L12 12M12 12L9 12M12 12L12 9M12 12L12 15" stroke="#1C274C"
                                      stroke-width="1.5"
                                      stroke-linecap="round"/>
                            </svg>
                            <span>Agregar Usuario</span>
                        </button>
                    </a>
                </div>
            </div>

            <div class="tabla-contenedor">
                <table class="tabla tabla-factura">
                    <thead class="tabla tabla-factura encabezado-tabla">
                    <tr>
                        <th>Nombre de Usuario</th>
                        <th>Rol</th>
                        <th>Opciones</th>
                    </tr>
                    </thead>
                    <tbody class="cuerpo-tabla">
                    <% for (UserEntity usuario : usuarios) {
                        String rol = "Desconocido";
                        if ("1".equals(usuario.getRoleId())) {
                            rol = "Administrador";
                        } else if ("2".equals(usuario.getRoleId())) {
                            rol = "Contador";
                        }
                    %>
                    <tr>
                        <td><%= usuario.getUsername() %>
                        </td>
                        <td><%= rol %>
                        </td>
                        <td>
                            <a class="enlace-contents"
                               href="${pageContext.request.contextPath}/usuarios/usuario-form?usuario-id=<%=usuario.getUsuarioId()%>">
                                <button class="boton-editar">
                                    <svg class="svg-editar" width="20px" height="20px" viewBox="0 0 24 24" fill="none"
                                         xmlns="http://www.w3.org/2000/svg">
                                        <path d="M20.1497 7.93997L8.27971 19.81C7.21971 20.88 4.04971 21.3699 3.27971 20.6599C2.50971 19.9499 3.06969 16.78 4.12969 15.71L15.9997 3.84C16.5478 3.31801 17.2783 3.03097 18.0351 3.04019C18.7919 3.04942 19.5151 3.35418 20.0503 3.88938C20.5855 4.42457 20.8903 5.14781 20.8995 5.90463C20.9088 6.66146 20.6217 7.39189 20.0997 7.93997H20.1497Z"
                                              stroke="white" stroke-width="1.5" stroke-linecap="round"
                                              stroke-linejoin="round"/>
                                        <path d="M21 21H12" stroke="white" stroke-width="1.5" stroke-linecap="round"
                                              stroke-linejoin="round"/>
                                    </svg>
                                </button>
                            </a>
                            <a class="enlace-contents"
                               href="${pageContext.request.contextPath}/usuarios/eliminar?usuario-id=<%=usuario.getUsuarioId()%>">
                                <button class="boton-eliminar">
                                    <svg class="svg-eliminar" width="20px" height="20px" viewBox="0 0 24 24" fill="none"
                                         xmlns="http://www.w3.org/2000/svg">
                                        <path d="M10 12V17" stroke="white" stroke-width="2" stroke-linecap="round"
                                              stroke-linejoin="round"/>
                                        <path d="M14 12V17" stroke="white" stroke-width="2" stroke-linecap="round"
                                              stroke-linejoin="round"/>
                                        <path d="M4 7H20" stroke="white" stroke-width="2" stroke-linecap="round"
                                              stroke-linejoin="round"/>
                                        <path d="M6 10V18C6 19.6569 7.34315 21 9 21H15C16.6569 21 18 19.6569 18 18V10"
                                              stroke="white" stroke-width="2" stroke-linecap="round"
                                              stroke-linejoin="round"/>
                                        <path d="M9 5C9 3.89543 9.89543 3 11 3H13C14.1046 3 15 3.89543 15 5V7H9V5Z"
                                              stroke="white" stroke-width="2" stroke-linecap="round"
                                              stroke-linejoin="round"/>
                                    </svg>
                                </button>
                            </a>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</main>

<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/ventana-modal.js"></script>
</body>
</html>
