<%@ page import="com.ortega.webapp.servlet.swfe.models.FacturaEntity" %>
<%@ page import="com.ortega.webapp.servlet.swfe.models.catalogos.CatalogoEntity" %>
<%@ page import="java.util.Map" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    FacturaEntity factura = (FacturaEntity) request.getAttribute("factura");
    CatalogoEntity catalogos = (CatalogoEntity) request.getAttribute("catalogos");
    HttpSession httpSession = request.getSession();
    int clienteId = Integer.parseInt(httpSession.getAttribute("clienteId").toString());
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Formulario de factura</title>
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
            <h1>Factura</h1>
            <form id="formulario-factura" class="form-flex" action="${pageContext.request.contextPath}/facturas/factura-form" method="post">

                <!-- Datos generales -->
                <div class="form-group">
                    <label>Folio</label>
                    <input class="login-input" type="text" name="folioFactura" id="folioFactura"
                           value="<%=factura.getFolioFactura() != null ? factura.getFolioFactura() : ""%>"
                           placeholder="Ingrese el folio de la factura">
                    <p class="error-message-null remover" id="folioFactura-error-message"></p>
                </div>

                <div class="form-group">
                    <label>Fecha</label>
                    <input class="login-input" type="date" name="fechaFactura" id="fechaFactura"
                           value="<%=factura.getFechaFactura() != null ? factura.getFechaFactura() : ""%>">
                    <p class="error-message-null remover" id="fechaFactura-error-message"></p>
                </div>

                <div class="form-group">
                    <label>Fecha Cobranza</label>
                    <input class="login-input" type="date" name="fechaCobranza" id="fechaCobranza"
                           value="<%=factura.getFechaCobranza() != null ? factura.getFechaCobranza() : ""%>">
                    <p class="error-message-null remover" id="fechaCobranza-error-message"></p>
                </div>

                <div class="form-group">
                    <label>RFC Emisor</label>
                    <input class="login-input" type="text" name="rfcEmisor" id="rfcEmisor"
                           value="<%=factura.getRfcEmisor() != null ? factura.getRfcEmisor() : ""%>">
                    <p class="error-message-null remover" id="rfcEmisor-error-message"></p>
                </div>

                <div class="form-group full-width">
                    <label>Nombre Emisor</label>
                    <input class="login-input" type="text" name="nombreEmisor" id="nombreEmisor"
                           value="<%=factura.getNombreEmisor() != null ? factura.getNombreEmisor() : ""%>">
                    <p class="error-message-null remover" id="nombreEmisor-error-message"></p>
                </div>

                <!-- Importes -->
                <div class="form-group">
                    <label>Subtotal</label>
                    <input class="login-input" type="number" step="any" name="subtotalFactura" id="subtotalFactura"
                           value="<%=factura.getSubtotalFactura() != 0 ? factura.getSubtotalFactura() : null%>">
                    <p class="error-message-null remover" id="subtotalFactura-error-message"></p>
                </div>

                <div class="form-group">
                    <label>IVA</label>
                    <input class="login-input" type="number" step="any" name="ivaFactura" id="ivaFactura"
                           value="<%=factura.getIvaFactura() != 0 ? factura.getIvaFactura() : null%>">
                    <p class="error-message-null remover" id="ivaFactura-error-message"></p>
                </div>

                <div class="form-group">
                    <label>ISR Retenido</label>
                    <input class="login-input" type="number" step="any" name="isrRetenido" id="isrRetenido"
                           value="<%=factura.getIsrRetenido() != 0 ? factura.getIsrRetenido() : null%>">
                    <p class="error-message-null remover" id="isrRetenido-error-message"></p>
                </div>

                <div class="form-group">
                    <label>IVA Retenido</label>
                    <input class="login-input" type="number" step="any" name="ivaRetenido" id="ivaRetenido"
                           value="<%=factura.getIvaRetenido() != 0 ? factura.getIvaRetenido() : null%>">
                    <p class="error-message-null remover" id="ivaRetenido-error-message"></p>
                </div>

                <div class="form-group">
                    <label>Total</label>
                    <input class="login-input" type="number" step="any" name="totalFactura" id="totalFactura"
                           value="<%=factura.getTotalFactura() != 0 ? factura.getTotalFactura() : null%>">
                    <p class="error-message-null remover" id="totalFactura-error-message"></p>
                </div>

                <div class="form-group">
                    <label>Tipo Cambio (TC)</label>
                    <input class="login-input" type="number" step="any" name="tcFactura" id="tcFactura"
                           value="<%=factura.getTcFactura() != 0 ? factura.getTcFactura() : null%>">
                    <p class="error-message-null remover" id="tcFactura-error-message"></p>
                </div>

                <div class="form-group full-width">
                    <label>UUID</label>
                    <input class="login-input" type="text" name="uuidFactura" id="uuidFactura"
                           value="<%=factura.getUuidFactura() != null ? factura.getUuidFactura() : ""%>">
                    <p class="error-message-null remover" id="uuidFactura-error-message"></p>
                </div>

                <!-- Catálogos -->
                <div class="form-group">
                    <label>CFDI Receptor</label>
                    <input class="login-input" type="text" name="usoCfdiReceptor" id="usoCfdiReceptor"
                           value="<%=factura.getUsoCfdiReceptor() != null ? factura.getUsoCfdiReceptor() : ""%>">
                    <p class="error-message-null remover" id="usoCfdiReceptor-error-message"></p>
                </div>

                <div class="form-group">
                    <label>Tipo de Factura</label>
                    <select class="login-input" name="tipoFactura" id="tipoFactura">
                        <option value="">Seleccione un tipo de factura</option>
                        <% for (Map.Entry<String, Object> item : catalogos.getTiposFactura().entrySet()) { %>
                        <option value="<%=item.getKey()%>" <%= item.getKey().equals(factura.getTipoFactura()) ? "selected" : "" %>>
                            <%=item.getValue()%>
                        </option>
                        <% } %>
                    </select>
                    <p class="error-message-null remover" id="tipoFactura-error-message"></p>
                </div>

                <div class="form-group">
                    <label>Serie</label>
                    <select class="login-input" name="serie" id="serie">
                        <option value="">Seleccione la serie</option>
                        <% for (Map.Entry<String, Object> item : catalogos.getSeries().entrySet()) { %>
                        <option value="<%=item.getKey()%>" <%= item.getKey().equals(factura.getSerie()) ? "selected" : "" %>>
                            <%=item.getValue()%>
                        </option>
                        <% } %>
                    </select>
                    <p class="error-message-null remover" id="serie-error-message"></p>
                </div>

                <div class="form-group">
                    <label>Moneda</label>
                    <select class="login-input" name="moneda" id="moneda">
                        <option value="">Seleccione la moneda</option>
                        <% for (Map.Entry<String, Object> item : catalogos.getMonedas().entrySet()) { %>
                        <option value="<%=item.getKey()%>" <%= item.getKey().equals(factura.getMoneda()) ? "selected" : "" %>>
                            <%=item.getValue()%>
                        </option>
                        <% } %>
                    </select>
                    <p class="error-message-null remover" id="moneda-error-message"></p>
                </div>

                <div class="form-group">
                    <label>Estatus</label>
                    <select class="login-input" name="estatus" id="estatus">
                        <option value="">Seleccione el estatus</option>
                        <% for (Map.Entry<String, Object> item : catalogos.getEstatus().entrySet()) { %>
                        <option value="<%=item.getKey()%>" <%= item.getKey().equals(factura.getEstatus()) ? "selected" : "" %>>
                            <%=item.getValue()%>
                        </option>
                        <% } %>
                    </select>
                    <p class="error-message-null remover" id="estatus-error-message"></p>
                </div>

                <div class="form-group">
                    <label>Efecto</label>
                    <select class="login-input" name="efecto" id="efecto">
                        <option value="">Seleccione el efecto</option>
                        <% for (Map.Entry<String, Object> item : catalogos.getEfectos().entrySet()) { %>
                        <option value="<%=item.getKey()%>" <%= item.getKey().equals(factura.getEfecto()) ? "selected" : "" %>>
                            <%=item.getValue()%>
                        </option>
                        <% } %>
                    </select>
                    <p class="error-message-null remover" id="efecto-error-message"></p>
                </div>

                <div class="form-group">
                    <label>Método de Pago</label>
                    <select class="login-input" name="metodoPago" id="metodoPago">
                        <option value="">Seleccione el método de pago</option>
                        <% for (Map.Entry<String, Object> item : catalogos.getMetodosPago().entrySet()) { %>
                        <option value="<%=item.getKey()%>" <%= item.getKey().equals(factura.getMetodoPago()) ? "selected" : "" %>>
                            <%=item.getValue()%>
                        </option>
                        <% } %>
                    </select>
                    <p class="error-message-null remover" id="metodoPago-error-message"></p>
                </div>

                <div class="form-group">
                    <label>Forma de Pago</label>
                    <select class="login-input" name="formaPago" id="formaPago">
                        <option value="">Seleccione la forma de pago</option>
                        <% for (Map.Entry<String, Object> item : catalogos.getFormasPago().entrySet()) { %>
                        <option value="<%=item.getKey()%>" <%= item.getKey().equals(factura.getFormaPago()) ? "selected" : "" %>>
                            <%=item.getValue()%>
                        </option>
                        <% } %>
                    </select>
                    <p class="error-message-null remover" id="formaPago-error-message"></p>
                </div>

                <div class="form-group full-width">
                    <label>Forma de Pago Deducible</label>
                    <select class="login-input" name="formaPagoDeducible" id="formaPagoDeducible">
                        <option value="">Seleccione la forma de pago deducible</option>
                        <% for (Map.Entry<String, Object> item : catalogos.getFormasPagoDeducible().entrySet()) { %>
                        <option value="<%=item.getKey()%>" <%= item.getKey().equals(factura.getFormaPagoDeducible()) ? "selected" : "" %>>
                            <%=item.getValue()%>
                        </option>
                        <% } %>
                    </select>
                    <p class="error-message-null remover" id="formaPagoDeducible-error-message"></p>
                </div>

                <!-- Campos ocultos -->
                <input type="hidden" name="facturaId" value="<%=factura.getFacturaId() != 0 ? factura.getFacturaId(): 0%>">
                <input type="hidden" name="clienteId" value="<%=factura.getFacturaId() != 0 ? factura.getClienteId(): clienteId%>">

                <!-- Botón -->
                <div class="boton-aceptar form-group full-width" style="text-align: center;">
                    <input type="submit" value="Aceptar">
                </div>
            </form>

        </div>
    </div>
</main>

<script src="${pageContext.request.contextPath}/js/formulario-factura.js"></script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/main-content.js"></script>
</body>
</html>