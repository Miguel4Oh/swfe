<%@ page import="com.ortega.webapp.servlet.swfe.models.UserEntity" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%
  UserEntity usuario = (UserEntity) request.getAttribute("usuario");
  System.out.println("usuario: " + usuario);
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <title>Usuario</title>
  <meta charset="UTF-8">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&family=Quicksand:wght@300..700&display=swap"
        rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/icons/favicon.ico">
</head>
<body class="login-body">
<jsp:include page="header.jsp" flush="true"/>
<main>
  <jsp:include page="barra-lateral.jsp" flush="true"/>
  <div class="main-form" id="main-form">
    <div class="main-form-content">
      <h1>Formulario de Usuario</h1>
      <form id="formulario-usuario" action="${pageContext.request.contextPath}/usuarios/usuario-form" method="post">

        <%if (usuario == null || usuario.getUsuarioId() == 0){%>
          <div>
            <label>ID de Usuario</label>
            <div>
              <input class="login-input" type="number" name="usuarioId" id="usuarioId"
                     placeholder="ID del usuario">
            </div>
            <p class="error-message-null remover" id="id-error-message"></p>
          </div>
          <input type="hidden" name="action" id="action" value="create">
        <% } else {%>
            <input type="hidden" name="usuarioId" id="usuarioId" value="<%= usuario.getUsuarioId() %>">
        <% } %>
        <div>
          <label>Nombre de Usuario</label>
          <div>
            <input class="login-input" type="text" name="username" id="username"
                   placeholder="Ingrese el nombre del usuario"
                   value="<%= usuario != null && usuario.getUsername() != null ? usuario.getUsername() : "" %>">
          </div>
          <p class="error-message-null remover" id="username-error-message"></p>
        </div>

        <div>
          <label>Rol</label>
          <div>
            <select class="login-input" name="roleId" id="roleId">
              <option value="1" <%= usuario != null && "1".equals(usuario.getRoleId()) ? "selected" : "" %>>Administrador</option>
              <option value="2" <%= usuario != null && "2".equals(usuario.getRoleId()) ? "selected" : "" %>>Contador</option>
            </select>
          </div>
          <p class="error-message-null remover" id="rol-error-message"></p>
        </div>

        <% if (usuario == null || usuario.getUsuarioId() == 0) { %>
        <div>
          <label>Contraseña</label>
          <div>
            <input class="login-input" type="password" name="password" id="password"
                   placeholder="Ingrese una contraseña">
          </div>
          <p class="error-message-null remover" id="password-error-message"></p>
        </div>

        <div>
          <label>Confirmar Contraseña</label>
          <div>
            <input class="login-input" type="password" name="confirmPassword" id="confirmPassword"
                   placeholder="Confirme la contraseña">
          </div>
          <p class="error-message-null remover" id="confirm-password-error-message"></p>
        <% } %>

          <div class="boton-aceptar form-group full-width" style="text-align: center;">
            <input type="submit" value="Aceptar">
          </div>
      </form>
    </div>
  </div>
</main>

<script src="${pageContext.request.contextPath}/js/formulario-usuario.js"></script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/main-content.js"></script>
</body>
</html>
