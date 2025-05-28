<%@ page import="com.ortega.webapp.servlet.swfe.models.TablaIsrEntity" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    TablaIsrEntity tablaIsr = (TablaIsrEntity) request.getAttribute("tabla");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Tablas Isr</title>
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
            <h1>Tablas Isr</h1>
            <form id="formulario-tabla" action="${pageContext.request.contextPath}/tablas/tabla-form" method="post">

                <div>
                    <label>Mes</label>
                    <div>
                        <input class="login-input" type="month" name="mes" id="mes"
                               placeholder="Ingrese el mes de la tabla"
                               value="<%=tablaIsr.getMesTabla() != null ? tablaIsr.getMesTabla() : ""%>">
                    </div>
                    <p class="error-message-null remover" id="mes-error-message"></p>
                </div>
                <div>
                    <label>Limite inferior</label>
                    <div>
                        <input class="login-input" type="number" step="any" name="limiteInferior" id="limiteInferior"
                               placeholder="Ingrese el limite inferior de la tabla"
                               value="<%=tablaIsr.getLimiteInferior() != 0 ? tablaIsr.getLimiteInferior() : null%>">
                    </div>
                    <p class="error-message-null remover" id="limiteInferior-error-message"></p>
                </div>
                <div>
                    <label>Limite superior</label>
                    <div>
                        <input class="login-input" type="number" step="any" name="limiteSuperior" id="limiteSuperior"
                               placeholder="Ingrese el limite superior de la tabla"
                               value="<%=tablaIsr.getLimiteSuperior() != 0 ? tablaIsr.getLimiteSuperior() : null%>">
                    </div>
                    <p class="error-message-null remover" id="limiteSuperior-error-message"></p>
                </div>
                <div>
                    <label>Cuota fija</label>
                    <div>
                        <input class="login-input" type="number" step="any" name="cuotaFija" id="cuotaFija"
                               placeholder="Ingrese la cuota fija de la tabla"
                               value="<%=tablaIsr.getCuotaFija() != 0 ? tablaIsr.getCuotaFija() : null%>">
                    </div>
                    <p class="error-message-null remover" id="cuotaFija-error-message"></p>
                </div>
                <div>
                    <label>Porcentaje excedente</label>
                    <div>
                        <input class="login-input" type="number" step="any" name="porcentajeExcedente" id="porcentajeExcedente"
                               placeholder="Ingrese el porcentaje excedente de la tabla"
                               value="<%=tablaIsr.getPorcentajeExcedente() != 0 ? tablaIsr.getPorcentajeExcedente() : null%>">
                    </div>
                    <p class="error-message-null remover" id="porcentajeExcedente-error-message"></p>
                </div>
                <input type="hidden" name="tablaId" id="tablaId" value="<%=tablaIsr.getTablaId()%>">
                <div class="boton-aceptar">
                    <input type="submit" value="Aceptar">
                </div>
            </form>
        </div>
    </div>
</main>
<script src="${pageContext.request.contextPath}/js/formulario-tabla.js"></script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/main-content.js"></script>
</body>
</html>