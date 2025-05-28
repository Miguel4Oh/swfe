<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    String mensaje = "";
    if(request.getSession().getAttribute("mensaje") != null) {
        mensaje = (String) request.getSession().getAttribute("mensaje");
        System.out.println("mensaje " + mensaje);
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Cambiar Contraseña</title>
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

    <%if (mensaje.equals("errorContrasena")) {
        request.getSession().removeAttribute("mensaje");%>
        <input type="hidden" id="mensaje" value="<%= mensaje %>">
    <% } %>

    <div class="main-form" id="main-form">
        <div class="main-form-content">
            <h1>Cambiar Contraseña</h1>

            <form id="formulario-contrasena" action="${pageContext.request.contextPath}/usuarios/contrasena" method="post">
                <div>
                    <label for="password_actual">Contraseña Actual</label>
                    <div>
                        <input class="login-input" type="password" name="password_actual" id="password_actual" placeholder="Ingrese su contraseña actual" required>
                    </div>
                    <p class="error-message-null remover" id="password-actual-error-message"></p>
                </div>

                <div>
                    <label for="password_nueva">Nueva Contraseña</label>
                    <div>
                        <input class="login-input" type="password" name="password_nueva" id="password_nueva" placeholder="Ingrese su nueva contraseña" required>
                    </div>
                    <p class="error-message-null remover" id="password-nueva-error-message"></p>
                </div>

                <div>
                    <label for="password_confirmacion">Confirmar Nueva Contraseña</label>
                    <div>
                        <input class="login-input" type="password" name="password_confirmacion" id="password_confirmacion" placeholder="Confirme su nueva contraseña" required>
                    </div>
                    <p class="error-message-null remover" id="password-confirmacion-error-message"></p>
                </div>
                <div class="boton-aceptar form-group full-width" style="text-align: center;">
                    <input type="submit" value="Aceptar">
                </div>
            </form>
        </div>
    </div>
</main>

<script src="${pageContext.request.contextPath}/js/formulario-contrasena.js"></script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/main-content.js"></script>
<script src="${pageContext.request.contextPath}/js/ventana-modal.js"></script>
</body>
</html>