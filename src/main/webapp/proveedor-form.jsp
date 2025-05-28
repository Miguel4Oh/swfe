<%@ page import="com.ortega.webapp.servlet.swfe.models.ProveedorEntity" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    ProveedorEntity proveedor = (ProveedorEntity) request.getAttribute("proveedor");
    HttpSession httpSession = request.getSession();
    int clienteId = Integer.parseInt(httpSession.getAttribute("clienteId").toString());
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Proveedor</title>
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
            <h1>Proveedores</h1>
            <form id="formulario-proveedor" action="${pageContext.request.contextPath}/proveedores/proveedor-form" method="post">
                <div>
                    <label>RFC</label>
                    <div>
                        <input class="login-input" type="text" name="rfc" id="rfc"
                               placeholder="Ingrese el rfc del proveedor"
                               value="<%=proveedor.getRfcProveedor() != null ? proveedor.getRfcProveedor() : ""%>">
                    </div>
                    <p class="error-message-null remover" id="rfc-error-message"></p>
                </div>
                <div>
                    <label>Nombre</label>
                    <div>
                        <input class="login-input" type="text" name="nombre" id="nombre"
                               placeholder="Ingrese el nombre del proveedor"
                               value="<%=proveedor.getNombreProveedor() != null ? proveedor.getNombreProveedor() : ""%>">
                    </div>
                    <p class="error-message-null remover" id="nombre-error-message"></p>
                </div>
                <input type="hidden" name="proveedorId" id="proveedorId" value="<%=proveedor.getProveedorId() != 0 ? proveedor.getProveedorId() : 0%>">
                <input type="hidden" name="clienteId" id="clienteId" value="<%=proveedor.getClienteId() != 0 ? proveedor.getClienteId() : clienteId%>">
                <div class="boton-aceptar form-group full-width" style="text-align: center;">
                    <input type="submit" value="Aceptar">
                </div>
            </form>
        </div>
    </div>
</main>
<script src="${pageContext.request.contextPath}/js/formulario-proveedor.js"></script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/main-content.js"></script>
</body>
</html>
