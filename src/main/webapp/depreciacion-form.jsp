<%@ page import="com.ortega.webapp.servlet.swfe.models.ConceptoDepreciacionEntity" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
  ConceptoDepreciacionEntity concepto = (ConceptoDepreciacionEntity) request.getAttribute("concepto");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <title>Depreciación</title>
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
      <h1>Depreciaciones</h1>
      <form id="formulario-concepto" action="${pageContext.request.contextPath}/conceptos/concepto-form"
            method="post">
        <div>
          <label>Concepto</label>
          <div>
            <input class="login-input" type="text" name="concepto" id="concepto"
                   placeholder="Ingrese el concepto"
                   value="<%=concepto.getNombreDepreciacion() != null ? concepto.getNombreDepreciacion() : ""%>">
          </div>
          <p class="error-message-null remover" id="concepto-error-message"></p>
        </div>
        <div>
          <label>Porcentaje depreciación</label>
          <div>
            <input class="login-input" type="text" name="porcentaje" id="porcentaje"
                   placeholder="Ingrese el porcentaje de depreciación"
                   value="<%=concepto.getPorcentajeDepreciacion() != 0 ? concepto.getPorcentajeDepreciacion() : ""%>">
          </div>
          <p class="error-message-null remover" id="porcentaje-error-message"></p>
        </div>

        <input type="hidden" name="conceptoId" id="conceptoId"
               value="<%=concepto.getConceptoDepreciacionId() != 0 ? concepto.getConceptoDepreciacionId(): 0%>">
        <div class="boton-aceptar form-group full-width" style="text-align: center;">
          <input type="submit" value="Aceptar">
        </div>
      </form>
    </div>
  </div>
</main>
<script src="${pageContext.request.contextPath}/js/formulario-concepto.js"></script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/main-content.js"></script>
</body>
</html>