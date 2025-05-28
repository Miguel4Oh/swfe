<%@ page import="com.ortega.webapp.servlet.swfe.models.TablaIsrEntity" %>
<%@ page import="com.ortega.webapp.servlet.swfe.models.nomina.NominaEntity" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
  NominaEntity nomina = (NominaEntity) request.getAttribute("nomina");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <title>Nómina Empleado</title>
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
      <form id="formulario-nomina" action="${pageContext.request.contextPath}/nomina/empleado" method="post">
        <div>
            <label>Empleado</label>
            <div>
                <input class="login-input" type="text" name="empleado" id="empleado"
                     placeholder="Ingrese el nombre del empleado"
                     value="<%=nomina.getNombreEmpleado() != null ? nomina.getNombreEmpleado() : ""%>" readonly>
            </div>
            <p class="error-message-null remover" id="empleado-error-message"></p>
        </div>
        <div>
            <label>Salario</label>
            <div>
                <input class="login-input" type="number" step="any" name="salario" id="salario"
                     placeholder="Ingrese el salario del empleado"
                     value="<%=nomina.getSalario() != 0 ? nomina.getSalario() : null %>" readonly>
            </div>
            <p class="error-message-null remover" id="salario-error-message"></p>
        </div>
        <div>
            <label>Días trabajados</label>
            <div>
                <input class="login-input" type="number" step="any" name="diasTrabajados" id="diasTrabajados"
                     placeholder="Ingrese los días trabajados"
                     value="<%=nomina.getDiasLaborados() != 0 ? nomina.getDiasLaborados() : null%>">
            </div>
            <p class="error-message-null remover" id="diasTrabajados-error-message"></p>
        <input type="hidden" name="nominaId" id="nominaId" value="<%=nomina.getNominaid()%>">
        <div class="boton-aceptar">
          <input type="submit" value="Aceptar">
        </div>
      </form>
    </div>
  </div>
</main>
<script src="${pageContext.request.contextPath}/js/formulario-nomina.js"></script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/main-content.js"></script>
</body>
</html>