<%@ page import="java.util.List" %>
<%@ page import="com.ortega.webapp.servlet.swfe.models.FacturaEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ortega.webapp.servlet.swfe.models.MesEntity" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    List<FacturaEntity> facturas = new ArrayList<>();
    if(request.getAttribute("facturas") != null) {
         facturas = (List<FacturaEntity>) request.getAttribute("facturas");
    }
    List<MesEntity> meses = (List<MesEntity>) request.getAttribute("meses");

    int mesId = request.getAttribute("mesId") != null ? Integer.parseInt(request.getAttribute("mesId").toString()) : 0;
    System.out.println("mesId: " + mesId);

%>

<!DOCTYPE html>
<html lang="es">
<head>
    <title>Ingresos</title>
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

    <section class="main-section" id="main-section">
        <div class="tabla-envoltura">
            <div class="tabla-header">
                <div class="titulo-buscador">
                    <h1>Ingresos</h1>
                    <div class="buscador-contenedor">
                        <input placeholder="Buscar" id="consulta-input">
                        <svg width="30px" height="30px" viewBox="0 -0.5 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M5.5 10.7655C5.50003 8.01511 7.44296 5.64777 10.1405 5.1113C12.8381 4.57483 15.539 6.01866 16.5913 8.55977C17.6437 11.1009 16.7544 14.0315 14.4674 15.5593C12.1804 17.0871 9.13257 16.7866 7.188 14.8415C6.10716 13.7604 5.49998 12.2942 5.5 10.7655Z" stroke="#000000c4" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M17.029 16.5295L19.5 19.0005" stroke="#000000c4" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
                        </svg>
                    </div>
                </div>
                <div class="botones-facturacion">
                    <a href="${pageContext.request.contextPath}/facturas/factura-form">
                        <button class="boton-agregar">
                            <svg class="svg-agregar" width="800px" height="800px" viewBox="0 0 24 24" fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                                <circle opacity="0.5" cx="12" cy="12" r="10" stroke="#1C274C" stroke-width="1.5"/>
                                <path d="M15 12L12 12M12 12L9 12M12 12L12 9M12 12L12 15" stroke="#1C274C"
                                      stroke-width="1.5"
                                      stroke-linecap="round"/>
                            </svg>
                            <span>Agregar Factura</span>
                        </button>
                    </a>
                </div>
            </div>
            <div>
                <div class="tabla-contenedor">
                    <table class="tabla tabla-factura">
                        <thead class="tabla tabla-factura encabezado-tabla">
                        <tr>
                            <th>Folio</th>
                            <th>Fecha</th>
                            <th>RFC Emisor</th>
                            <th>Nombre Emisor</th>
                            <th>Subtotal</th>
                            <th>IVA</th>
                            <th>ISR Retenido</th>
                            <th>IVA Retenido</th>
                            <th>Total</th>
                            <th>Moneda</th>
                            <th>Estatus</th>
                            <th>Forma de Pago</th>
                            <th>Opciones</th>
                        </tr>
                        </thead>
                        <tbody class="tabla tabla-factura cuerpo-tabla">
                            <% if (facturas != null && !facturas.isEmpty()) {%>
                                <%for (FacturaEntity factura : facturas) {%>
                                <tr>
                                    <td><%=factura.getFolioFactura()%>
                                    </td>
                                    <td><%=factura.getFechaFactura()%>
                                    </td>
                                    <td><%=factura.getRfcEmisor()%>
                                    </td>
                                    <td><%=factura.getNombreEmisor()%>
                                    </td>
                                    <td><%=factura.getSubtotalFactura()%>
                                    </td>
                                    <td><%=factura.getIvaFactura()%>
                                    </td>
                                    <td><%=factura.getIsrRetenido()%>
                                    </td>
                                    <td><%=factura.getIvaRetenido()%>
                                    </td>
                                    <td><%=factura.getTotalFactura()%>
                                    </td>
                                    <td><%=factura.getMoneda()%>
                                    </td>
                                    <td><%=factura.getEstatus()%>
                                    </td>
                                    <td><%=factura.getFormaPago()%>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/facturas/factura-form?factura-id=<%=factura.getFacturaId()%>">
                                            <button class="boton-editar">
                                                <svg class="svg-editar" width="20px" height="20px" viewBox="0 0 24 24"
                                                     fill="none"
                                                     xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M20.1497 7.93997L8.27971 19.81C7.21971 20.88 4.04971 21.3699 3.27971 20.6599C2.50971 19.9499 3.06969 16.78 4.12969 15.71L15.9997 3.84C16.5478 3.31801 17.2783 3.03097 18.0351 3.04019C18.7919 3.04942 19.5151 3.35418 20.0503 3.88938C20.5855 4.42457 20.8903 5.14781 20.8995 5.90463C20.9088 6.66146 20.6217 7.39189 20.0997 7.93997H20.1497Z"
                                                          stroke="white" stroke-width="1.5" stroke-linecap="round"
                                                          stroke-linejoin="round"/>
                                                    <path d="M21 21H12" stroke="white" stroke-width="1.5" stroke-linecap="round"
                                                          stroke-linejoin="round"/>
                                                </svg>
                                            </button>
                                        </a>
                                        <a href="${pageContext.request.contextPath}/facturas/eliminar?factura-id=<%=factura.getFacturaId()%>">
                                            <button class="boton-eliminar">
                                                <svg class="svg-eliminar" width="20px" height="20px" viewBox="0 0 24 24"
                                                     fill="none"
                                                     xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M10 12V17" stroke="white" stroke-width="2" stroke-linecap="round"
                                                          stroke-linejoin="round"/>
                                                    <path d="M14 12V17" stroke="white" stroke-width="2" stroke-linecap="round"
                                                          stroke-linejoin="round"/>
                                                    <path d="M4 7H20" stroke="white" stroke-width="2" stroke-linecap="round"
                                                          stroke-linejoin="round"/>
                                                    <path d="M6 10V18C6 19.6569 7.34315 21 9 21H15C16.6569 21 18 19.6569 18 18V10"
                                                          stroke="white" stroke-width="2" stroke-linecap="round"
                                                          stroke-linejoin="round"/>
                                                    <path d="M9 5C9 3.89543 9.89543 3 11 3H13C14.1046 3 15 3.89543 15 5V7H9V5Z"
                                                          stroke="white" stroke-width="2" stroke-linecap="round"
                                                          stroke-linejoin="round"/>
                                                </svg>
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                                <%}%>
                            <%} else {%>
                                <tr class="no-resultados">
                                    <td colspan="13" id="noresultadotd">Sin información para mostrar</td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</main>
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/filtro-consultas/consulta-ingresos.js"></script>
</body>
</html>