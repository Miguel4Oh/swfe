<%@ page import="com.ortega.webapp.servlet.swfe.models.ImpuestoEntity" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    ImpuestoEntity impuestoGenerado = (ImpuestoEntity) request.getAttribute("impuesto");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Impuestos</title>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&family=Quicksand:wght@300..700&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@200..1000&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/icons/favicon.ico">
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<main>
    <jsp:include page="barra-lateral.jsp" flush="true"/>

    <section class="main-section nav-bar impuestos-container" id="main-section">
        <div class="impuestos-container">
            <div class="impuesto-header">
                <h1 class="impuesto-titulo">Calculo de impuestos mensual</h1>
            </div>
            <article class="impuesto-article">
                <div class="container">
                    <h2>I.S.R.</h2>
                    <div class="table-wrapper">
                        <table class="impuesto-table">
                            <tr>
                                <th>Concepto</th>
                                <th>Importe</th>
                            </tr>
                            <tr>
                                <td>Ingresos del mes anterior</td>
                                <td><%=impuestoGenerado.getIngresoMesAnterior()%></td>
                            </tr>
                            <tr>
                                <td>Ingresos cobrados</td>
                                <td><%=impuestoGenerado.getIngresosCobrados()%></td>
                            </tr>
                            <tr class="total">
                                <td>Total de periodo</td>
                                <td><%=impuestoGenerado.getTotalDePeriodo()%></td>
                            </tr>
                            <tr>
                                <td>Deducciones del mes anterior</td>
                                <td><%=impuestoGenerado.getDeduccionesMesAnterior()%></td>
                            </tr>
                            <tr>
                                <td>Deducciones autorizadas pagadas</td>
                                <td><%=impuestoGenerado.getDeduccionesAutorizadasPagadas()%></td>
                            </tr>
                            <tr>
                                <td>Gastos Nómina</td>
                                <td><%=impuestoGenerado.getGastosNomina()%></td>
                            </tr>
                            <tr>
                                <td>Depreciación</td>
                                <td><%=impuestoGenerado.getDepreciacion()%></td>
                            </tr>
                            <tr class="total">
                                <td>Total de periodo</td>
                                <td><%=impuestoGenerado.getDeduccionesDelMes()%></td>
                            </tr>
                            <tr class="total">
                                <td>Base para el pago provisional</td>
                                <td><%=String.format("%.2f", impuestoGenerado.getBasePagoProvisional())%></td>
                            </tr>
                        </table>
                    </div>
                    <br>
                    <div class="table-wrapper">
                        <table class="impuesto-table">
                            <tr>
                                <td>(-) Límite inferior</td>
                                <td><%=impuestoGenerado.getLimiteInferior()%></td>
                            </tr>
                            <tr>
                                <td>(=) Excedente del limite inferior</td>
                                <td><%=String.format("%.2f", impuestoGenerado.getExcedenteLimiteInferior())%></td>
                            </tr>
                            <tr>
                                <td>(x) % sobre exedente del límite inferior</td>
                                <td><%=(impuestoGenerado.getPorcentajeExcedenteLimiteInferior() * 100) + "%"%></td>
                            </tr>
                            <tr>
                                <td>(=) Impuesto marginal</td>
                                <td><%=String.format("%.2f", impuestoGenerado.getImpuestoMarginal())%></td>
                            </tr>
                            <tr>
                                <td>(+) Cuota fija</td>
                                <td><%=impuestoGenerado.getCuotaFija()%></td>
                            </tr>
                            <tr>
                                <td>(=) impuesto</td>
                                <td><%=String.format("%.2f", impuestoGenerado.getImpuesto())%></td>
                            </tr>
                            <tr>
                                <td>(-) Reducccion de 30% (Art. 83 RLISR)</td>
                                <td><%=impuestoGenerado.getReduccionDeTreinta()%></td>
                            </tr>
                            <tr>
                                <td>(=) Impuesto reducido</td>
                                <td><%=String.format("%.2f", impuestoGenerado.getImpuestoReducido())%></td>
                            </tr>
                            <tr>
                                <td>(-) Pagos provisionales efectuados con anterioridad</td>
                                <td><%=impuestoGenerado.getPagosProvisionalesConAnterioridad()%></td>
                            </tr>
                            <tr>
                                <td>(-) 10% de retención meses anteriores</td>
                                <td><%=impuestoGenerado.getDiezRetencionMesesAnteriores()%></td>
                            </tr>
                            <tr>
                                <td>(-) 10 % I.S.R. Retenido del Mes</td>
                                <td><%=impuestoGenerado.getDiezRetenidoMes()%></td>
                            </tr>
                            <tr class="calculo-total">
                                <td>(=) Impuesto a cargo del mes</td>
                                <td><%=String.format("%.2f", impuestoGenerado.getImpuestoACargoMes())%></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </article>
            <article class="impuesto-article">
                <div class="container">
                    <h2>I.V.A.</h2>
                    <div class="table-wrapper">
                        <table class="impuesto-table">
                            <tr>
                                <th>Concepto</th>
                                <th>Importe</th>
                            </tr>
                            <tr>
                                <td>Base del impuesto</td>
                                <td><%=impuestoGenerado.getBaseDelImpuesto()%></td>
                            </tr>
                            <tr>
                                <td>I.V.A. Cobrado</td>
                                <td><%=impuestoGenerado.getIvaCobrado()%></td>
                            </tr>
                            <tr>
                                <td>(-) Retenciones</td>
                                <td><%=impuestoGenerado.getRetenciones()%></td>
                            </tr>
                            <tr>
                                <td>(-) I.V.A. Acreditable</td>
                                <td><%=impuestoGenerado.getIvaAcreditable()%></td>
                            </tr>
                            <tr>
                                <td>(=) I.V.A. a cargo o a favor del periodo</td>
                                <td><%=String.format("%.2f", impuestoGenerado.getIvaAFavorPeriodo())%></td>
                            </tr>
                            <tr>
                                <td>(-) Saldo a favor pendiente de acreditar</td>
                                <td><%=impuestoGenerado.getSaldoAFavor()%></td>
                            </tr>
                            <tr class="calculo-total">
                                <td>(=) Impuesto a cargo (+) ó a favor (-)</td>
                                <td><%=String.format("%.2f", impuestoGenerado.getImpuestoCargoFavor())%></td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="container impuesto-salarios">
                    <h2>I.S.R. Salarios</h2>
                    <div class="table-wrapper">
                        <table class="impuesto-table">
                            <tr>
                                <th>Concepto</th>
                                <th>Importe</th>
                            </tr>
                            <tr>
                                <td>I.S.R. Salarios</td>
                                <td><%=impuestoGenerado.getIsrSalarios()%></td>
                            </tr>
                            <tr>
                                <td>(-) Subsidio</td>
                                <td><%=impuestoGenerado.getSubsidio()%></td>
                            </tr>
                            <tr>
                                <td>(-) Subsidio periodos anteriores</td>
                                <td><%=impuestoGenerado.getSubsidioPeriodosAnteriores()%></td>
                            </tr>
                            <tr class="calculo-total">
                                <td>(=) ISR Salario. mensual por pagar</td>
                                <td><%=String.format("%.2f", impuestoGenerado.getIsrSalarioPorPagar())%></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </article>

        </div>
        <article>
            <div class="download-container">
                <button onclick="downloadPDF()">Descargar PDF</button>
            </div>
        </article>
    </section>

</main>

<script>
    function downloadPDF() {
        window.location.href = '/swfe/generar-pdf';
    }
</script>

<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/filtro-consultas/consulta-ingresos.js"></script>
</body>
</html>
