<%@ page import="com.ortega.webapp.servlet.swfe.models.EmpleadoEntity" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    EmpleadoEntity concepto = (EmpleadoEntity) request.getAttribute("empleado");
    HttpSession httpSession = request.getSession();
    String clienteId = (String) httpSession.getAttribute("clienteId");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Formulario de login</title>
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
            <h1>Empleado</h1>
            <form id="formulario-empleado" action="${pageContext.request.contextPath}/empleados/empleado-form" method="post">
                <div>
                    <label>Nombre</label>
                    <div>
                        <input class="login-input" type="text" name="nombre" id="nombre"
                               placeholder="Ingrese el nombre del empleado"
                               value="<%=concepto.getNombre() != null ? concepto.getNombre() : ""%>">
                    </div>
                    <p class="error-message-null remover" id="nombre-error-message"></p>
                </div>
                <div>
                    <label>Sueldo</label>
                    <div>
                        <input class="login-input" type="text" name="sueldo" id="sueldo"
                               placeholder="Ingrese el sueldo del empleado"
                               value="<%=concepto.getSueldo() != 0 ? concepto.getSueldo() : ""%>">
                    </div>
                    <p class="error-message-null remover" id="sueldo-error-message"></p>
                </div>

                <input type="hidden" name="empleadoId" id="empleadoId" value="<%=concepto.getEmpleadoId() != 0 ? concepto.getEmpleadoId(): 0%>">
                <input type="hidden" name="clienteId" id="clienteId" value="<%=concepto.getClienteId() != 0 ? concepto.getClienteId(): clienteId%>">
                <div class="boton-aceptar form-group full-width" style="text-align: center;">
                    <input type="submit" value="Aceptar">
                </div>
            </form>
        </div>
    </div>
</main>

<script src="${pageContext.request.contextPath}/js/formulario-empleado.js"></script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/main-content.js"></script>
</body>
</html>
