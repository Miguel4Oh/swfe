<%@ page import="com.ortega.webapp.servlet.swfe.models.ClienteEntity" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    String usuarioId = (String) request.getAttribute("usuarioId");
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
    <jsp:include page="ventana-modal.jsp" flush="true"/>

    <div class="main-form" id="main-form">
        <div class="main-form-content" >
            <h1>Cliente</h1>
            <form id="formulario-cliente" action="${pageContext.request.contextPath}/clientes/cliente-form"
                  method="post">
                <div>
                    <label for="rfc">RFC</label>
                    <div>
                        <input class="login-input" type="text" name="rfc" id="rfc"
                               placeholder="Ingrese RFC del cliente"
                               value="<%=cliente.getRfcCliente() != null ? cliente.getRfcCliente() : ""%>">
                    </div>
                    <p class="error-message-null remover" id="rfc-error-message"></p>
                </div>
                <div>
                    <label for="nombre">Nombre</label>
                    <div>
                        <input class="login-input" type="text" name="nombre" id="nombre"
                               placeholder="Ingrese el nombre del cliente"
                               value="<%=cliente.getNombreCliente() != null ? cliente.getNombreCliente() : ""%>">
                    </div>
                    <p class="error-message-null remover" id="nombre-error-message"></p>
                </div>
                <div>
                    <label for="razonSocial">Razón social</label>
                    <div>
                        <input class="login-input" type="text" name="razonSocial" id="razonSocial"
                               placeholder="Ingrese la razón social del cliente"
                               value="<%=cliente.getRazonSocial() != null ? cliente.getRazonSocial() : ""%>">
                    </div>
                    <p class="error-message-null remover" id="razonSocial-error-message"></p>
                </div>
                <div>
                    <label>Régimen fiscal</label>
                    <div>
                        <select class="login-input" name="regimenFiscal" id="regimenFiscal">
                            <%if (cliente.getRegimenFiscal() != null) {%>
                            <%if (cliente.getRegimenFiscal().equals("Simplificado")) {%>
                            <option value="Simplificado">Simplificado</option>
                            <option value="Actividad Empresarial">Actividad Empresarial</option>
                            <%} else {%>
                            <option value="Actividad Empresarial">Actividad Empresarial</option>
                            <option value="Simplificado">Simplificado</option>
                            <%};%>
                            <%} else {%>
                                <option value="Simplificado">Simplificado</option>
                                <option value="Actividad Empresarial">Actividad Empresarial</option>
                            <%};%>
                        </select>
                    </div>
                </div>
                <input type="hidden" name="clienteId" id="clienteId"
                       value="<%=cliente.getClienteId() != 0 ? cliente.getClienteId(): 0%>">
                <input type="hidden" name="usuarioId" id="usuarioId" value="<%=usuarioId != null ? usuarioId: ""%>">
                <div class="boton-aceptar form-group full-width" style="text-align: center;">
                    <input type="submit" value="Aceptar">
                </div>
            </form>
        </div>
    </div>
</main>
<script src="${pageContext.request.contextPath}/js/formulario-cliente.js"></script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/main-content.js"></script>
<script src="${pageContext.request.contextPath}/js/ventana-modal.js"></script>
</body>
</html>