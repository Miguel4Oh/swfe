<%@ page import="com.ortega.webapp.servlet.swfe.models.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    List<ClienteEntity> clientes = (List<ClienteEntity>) request.getAttribute("clientes");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Seleccionar cliente</title>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&family=Quicksand:wght@300..700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/icons/favicon.ico">
</head>
<body>
<jsp:include page="ventana-modal.jsp" flush="true"/>
<jsp:include page="header.jsp" flush="true"/>
<main>
    <jsp:include page="barra-lateral.jsp" flush="true"/>
    <section class="main-section" id="main-section">
        <div class="tabla-envoltura">
            <div class="tabla-header">
                <h1>Selecionar cliente</h1>
            </div>
            <div>
                <div class="tabla-contenedor">
                    <table class="tabla">
                        <tr>
                            <th>Nombre</th>
                            <th>RFC</th>
                            <th>Razón social</th>
                            <th>Régimen fiscal</th>
                            <th>Opciones</th>
                        </tr>
                        <%for (ClienteEntity cliente : clientes) {%>
                        <tr>
                            <td><%=cliente.getNombreCliente()%>
                            </td>
                            <td><%=cliente.getRfcCliente()%>
                            </td>
                            <td><%=cliente.getRazonSocial()%>
                            </td>
                            <td><%=cliente.getRegimenFiscal()%>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/cliente/seleccionar?cliente-id=<%=cliente.getClienteId()%>&nombre-cliente=<%=cliente.getNombreCliente()%>">
                                    <button class="boton-agregar">
                                        <div>
                                            Seleccionar
                                        </div>
                                    </button>
                                </a>
                            </td>
                        </tr>
                        <%}%>
                    </table>
                </div>
            </div>
        </div>
    </section>
</main>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/ventana-modal.js"></script>
</body>
</html>
