<%@ page import="java.util.List" %>
<%@ page import="com.ortega.webapp.servlet.swfe.models.resumen.IngresosEgresosEntity" %>
<%@ page import="com.ortega.webapp.servlet.swfe.models.resumen.ResumenImpuestosEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<IngresosEgresosEntity> resumen = (List<IngresosEgresosEntity>) request.getAttribute("ingresosEgresos");
    List<ResumenImpuestosEntity> impuestos = (List<ResumenImpuestosEntity>) request.getAttribute("impuestos");

    List<com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity> ivaResumen =
            (List<com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity>) request.getAttribute("iva");

    double acumuladoIngresos = 0;
    double acumuladoEgresos = 0;
    double totalIvaIngresos = 0;
    double totalIvaEgresos = 0;

    double acumuladoImpuesto = 0;
    double acumuladoIva = 0;
    double acumuladoSalarios = 0;

    System.out.println("Resumen de Ingresos y Egresos: " + resumen);
    System.out.println("Resumen de Impuestos: " + impuestos);

<%
    List<String> meses = new ArrayList<>();
    List<Double> ingresosMensuales = new ArrayList<>();
    List<Double> egresosMensuales = new ArrayList<>();
    List<Double> utilidades = new ArrayList<>();
    List<Double> ivaIngresosList = new ArrayList<>();
    List<Double> ivaEgresosList = new ArrayList<>();
    List<Double> ivaResultadoList = new ArrayList<>();

    if (resumen != null) {
        for (IngresosEgresosEntity item : resumen) {
            meses.add(item.getMes());
            ingresosMensuales.add(item.getIngreso());
            egresosMensuales.add(item.getEgreso());
            double utilidad = item.getIngreso() - item.getEgreso();
            double ivaResultado = item.getIvaIngreso() - item.getIvaEgreso();

            utilidades.add(utilidad);
            ivaIngresosList.add(item.getIvaIngreso());
            ivaEgresosList.add(item.getIvaEgreso());
            ivaResultadoList.add(ivaResultado);
        }
    }
%>
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Resumen Ingresos y Egresos</title>
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
    <section class="main-section" id="main-section">
        <!-- Resumen Ingresos y Egresos -->
        <div class="tabla-resumen-financiero">
            <div class="tabla-header">
                <h1>Resumen de Ingresos y Egresos</h1>
            </div>
             <div class="tabla-contenedor">
                <table class="tabla">
                    <thead class="encabezado-tabla">
                    <tr>
                        <th>Mes</th>
                        <th>Ingresos<br>(Mensual)</th>
                        <th>Ingresos<br>(Acumulado)</th>
                        <th>Egresos<br>(Mensual)</th>
                        <th>Egresos<br>(Acumulado)</th>
                        <th>Utilidad</th>
                        <th>IVA Ingresos</th>
                        <th>IVA Egresos</th>
                        <th>IVA a Cargo / a Favor</th>
                    </tr>
                    </thead>
                    <tbody class="cuerpo-tabla">
                    <% if (resumen != null && !resumen.isEmpty()) {
                        for (IngresosEgresosEntity item : resumen) {

                            double ingresosMensual = item.getIngreso();
                            double egresosMensual = item.getEgreso();
                            double utilidad = ingresosMensual - egresosMensual;
                            double ivaIngresos = item.getIvaIngreso();
                            double ivaEgresos = item.getIvaEgreso();
                            double ivaResultado = ivaIngresos - ivaEgresos;

                            acumuladoIngresos += ingresosMensual;
                            acumuladoEgresos += egresosMensual;
                            totalIvaIngresos += ivaIngresos;
                            totalIvaEgresos += ivaEgresos;
                    %>
                    <tr>
                        <td><%= item.getMes() %></td>
                        <td>$<%= String.format("%,.2f", ingresosMensual) %></td>
                        <td>$<%= String.format("%,.2f", acumuladoIngresos) %></td>
                        <td>$<%= String.format("%,.2f", egresosMensual) %></td>
                        <td>$<%= String.format("%,.2f", acumuladoEgresos) %></td>
                        <td style="color: <%= utilidad >= 0 ? "#2db16d" : "#f74747" %>;">
                            $<%= String.format("%,.2f", utilidad) %>
                        </td>
                        <td>$<%= String.format("%,.2f", ivaIngresos) %></td>
                        <td>$<%= String.format("%,.2f", ivaEgresos) %></td>
                        <td style="color: <%= ivaResultado >= 0 ? "#e39a00" : "#2963dd" %>;">
                            $<%= String.format("%,.2f", Math.abs(ivaResultado)) %>
                            (<%= ivaResultado >= 0 ? "a Cargo" : "a Favor" %>)
                        </td>
                    </tr>
                    <% } %>
                    <!-- Fila de totales -->
                    <tr class="fila-total">
                        <td><strong>Total</strong></td>
                        <td></td>
                        <td>$<%= String.format("%,.2f", acumuladoIngresos) %></td>
                        <td></td>
                        <td>$<%= String.format("%,.2f", acumuladoEgresos) %></td>
                        <td>$<%= String.format("%,.2f", acumuladoIngresos - acumuladoEgresos) %></td>
                        <td>$<%= String.format("%,.2f", totalIvaIngresos) %></td>
                        <td>$<%= String.format("%,.2f", totalIvaEgresos) %></td>
                        <td style="color: <%= (totalIvaIngresos - totalIvaEgresos) >= 0 ? "#e39a00" : "#2963dd" %>;">
                            $<%= String.format("%,.2f", Math.abs(totalIvaIngresos - totalIvaEgresos)) %>
                            (<%= (totalIvaIngresos - totalIvaEgresos) >= 0 ? "a Cargo" : "a Favor" %>)
                        </td>
                    </tr>
                    <% } else { %>
                    <tr>
                        <td colspan="9" id="noresultadotd">No hay datos disponibles para mostrar.</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="tabla-resumen-financiero">
            <div class="tabla-header">
                <h1>Resumen de Impuestos Mensuales</h1>
            </div>
            <div class="tabla-contenedor">
                <table class="tabla">
                    <thead class="encabezado-tabla">
                    <tr>
                        <th>Mes</th>
                        <th>Impuesto</th>
                        <th>Impuesto Acumulado</th>
                        <th>Salarios</th>
                        <th>IVA</th>
                    </tr>
                    </thead>
                    <tbody class="cuerpo-tabla">
                    <% if (impuestos != null && !impuestos.isEmpty()) {
                        for (ResumenImpuestosEntity item : impuestos) {

                            double impuesto = item.getImpuesto();
                            double iva = item.getIva();
                            double salarios = item.getSalarios();

                            acumuladoImpuesto += impuesto;
                            acumuladoIva += iva;
                            acumuladoSalarios += salarios;
                    %>
                    <tr>
                        <td><%= item.getMes() %></td>
                        <td>$<%= String.format("%,.2f", impuesto) %></td>
                        <td>$<%= String.format("%,.2f", acumuladoImpuesto) %></td>
                        <td>$<%= String.format("%,.2f", salarios) %></td>
                        <td>$<%= String.format("%,.2f", iva) %></td>
                    </tr>
                    <% } %>
                    <!-- Fila de totales -->
                    <tr class="fila-total">
                        <td><strong>Total</strong></td>
                        <td>$<%= String.format("%,.2f", acumuladoImpuesto) %></td>
                        <td></td>
                        <td>$<%= String.format("%,.2f", acumuladoSalarios) %></td>
                        <td>$<%= String.format("%,.2f", acumuladoIva) %></td>
                    </tr>
                    <% } else { %>
                    <tr>
                        <td colspan="5" id="noresultadotd">No hay datos de impuestos para mostrar.</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>


        <div class="tabla-resumen-financiero">
            <div class="tabla-header">
                <h1>Resumen IVA Mensual</h1>
            </div>
            <div class="tabla-contenedor">
                <table class="tabla">
                    <thead class="encabezado-tabla">
                    <tr>
                        <th>Concepto</th>
                        <% for (com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity item : ivaResumen) { %>
                        <th><%= item.getMes() %></th>
                        <% } %>
                    </tr>
                    </thead>
                    <tbody class="cuerpo-tabla">
                    <tr>
                        <td>Total de ingresos</td>
                        <% for (com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity item : ivaResumen) { %>
                        <td>$<%= String.format("%,.2f", item.getIngresos()) %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>IVA causado</td>
                        <% for (com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity item : ivaResumen) { %>
                        <td>$<%= String.format("%,.2f", item.getIvaCausado()) %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>Total gastos</td>
                        <% for (com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity item : ivaResumen) { %>
                        <td>$<%= String.format("%,.2f", item.getEgresos()) %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>IVA acreditable</td>
                        <% for (com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity item : ivaResumen) { %>
                        <td>$<%= String.format("%,.2f", item.getIvaAcreditado()) %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>Impuesto a cargo</td>
                        <% for (com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity item : ivaResumen) { %>
                        <td style="color: #e39a00;">$<%= String.format("%,.2f", item.getImpuestoCargo()) %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>Impuesto a favor</td>
                        <% for (com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity item : ivaResumen) { %>
                        <td style="color: #2963dd;">$<%= String.format("%,.2f", item.getImpuestoFavor()) %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>Saldos a favor</td>
                        <% for (com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity item : ivaResumen) { %>
                        <td>$<%= String.format("%,.2f", item.getSaldosFavor()) %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>Impuesto por auditoría a cargo</td>
                        <% for (com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity item : ivaResumen) { %>
                        <td style="color: #e39a00;">$<%= String.format("%,.2f", item.getImpuestoAuditoriaCargo()) %></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>Impuesto por auditoría a favor</td>
                        <% for (com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity item : ivaResumen) { %>
                        <td style="color: #2963dd;">$<%= String.format("%,.2f", item.getImpuestoAuditoriaFavor()) %></td>
                        <% } %>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </section>
</main>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/resumen.js"></script>
</body>
</html>
