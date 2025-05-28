<%@ page import="java.util.List" %>
<%@ page import="com.ortega.webapp.servlet.swfe.models.FacturaEntity" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    List<FacturaEntity> facturas = (List<FacturaEntity>) request.getAttribute("facturas");
    System.out.println("Facturassss: " + facturas);
    Integer paginaActual = (Integer) request.getAttribute("paginaActual");
    Integer totalPaginas = (Integer) request.getAttribute("totalPaginas");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Facturas</title>
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
    <jsp:include page="ventana-modal.jsp" flush="true"/>

    <section class="ventana-cargar-archivo">
        <div class="drop-zone">
            <svg class="svg-cloud-icon" fill="#000000" height="800px" width="800px" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                 viewBox="0 0 486.3 486.3" xml:space="preserve">
                    <g>
                        <g>
                            <path d="M395.5,135.8c-5.2-30.9-20.5-59.1-43.9-80.5c-26-23.8-59.8-36.9-95-36.9c-27.2,0-53.7,7.8-76.4,22.5
                                c-18.9,12.2-34.6,28.7-45.7,48.1c-4.8-0.9-9.8-1.4-14.8-1.4c-42.5,0-77.1,34.6-77.1,77.1c0,5.5,0.6,10.8,1.6,16
                                C16.7,200.7,0,232.9,0,267.2c0,27.7,10.3,54.6,29.1,75.9c19.3,21.8,44.8,34.7,72,36.2c0.3,0,0.5,0,0.8,0h86
                                c7.5,0,13.5-6,13.5-13.5s-6-13.5-13.5-13.5h-85.6C61.4,349.8,27,310.9,27,267.1c0-28.3,15.2-54.7,39.7-69
                                c5.7-3.3,8.1-10.2,5.9-16.4c-2-5.4-3-11.1-3-17.2c0-27.6,22.5-50.1,50.1-50.1c5.9,0,11.7,1,17.1,3c6.6,2.4,13.9-0.6,16.9-6.9
                                c18.7-39.7,59.1-65.3,103-65.3c59,0,107.7,44.2,113.3,102.8c0.6,6.1,5.2,11,11.2,12c44.5,7.6,78.1,48.7,78.1,95.6
                                c0,49.7-39.1,92.9-87.3,96.6h-73.7c-7.5,0-13.5,6-13.5,13.5s6,13.5,13.5,13.5h74.2c0.3,0,0.6,0,1,0c30.5-2.2,59-16.2,80.2-39.6
                                c21.1-23.2,32.6-53,32.6-84C486.2,199.5,447.9,149.6,395.5,135.8z"/>
                            <path d="M324.2,280c5.3-5.3,5.3-13.8,0-19.1l-71.5-71.5c-2.5-2.5-6-4-9.5-4s-7,1.4-9.5,4l-71.5,71.5c-5.3,5.3-5.3,13.8,0,19.1
                                c2.6,2.6,6.1,4,9.5,4s6.9-1.3,9.5-4l48.5-48.5v222.9c0,7.5,6,13.5,13.5,13.5s13.5-6,13.5-13.5V231.5l48.5,48.5
                                C310.4,285.3,318.9,285.3,324.2,280z"/>
                        </g>
                    </g>
            </svg>

            <h2 class="drop-text">Arrastra y suelta el archivo <br>
                o <br>
            </h2>
            <button class="boton-seleccionar-archivo">
                Haz click para seleccionar
            </button>
            <span class="nombre-archivo"></span>

            <div class="tipo-archivo">
                <label>
                    <input type="radio" name="tipo" value="1" required> Ingreso
                </label>
                <label>
                    <input type="radio" name="tipo" value="2" required> Egreso
                </label>
            </div>

            <div class="drop-zone-buttons">
                <button class="boton-cargar-archivo drop-zone-button">
                    Cargar Archivo
                </button>
                <button class="boton-cancelar drop-zone-button">
                    Cancelar
                </button>
            </div>
            <input type="file" id="fileInput" accept=".xlsx" hidden>
        </div>
    </section>
    <section class="main-section" id="main-section">
        <div class="tabla-envoltura">
            <div class="tabla-header">
                <div class="titulo-buscador">
                    <h1>Facturas</h1>
                    <div class="buscador-contenedor">
                        <input placeholder="Buscar" id="consulta-input">
                        <svg width="30px" height="30px" viewBox="0 -0.5 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M5.5 10.7655C5.50003 8.01511 7.44296 5.64777 10.1405 5.1113C12.8381 4.57483 15.539 6.01866 16.5913 8.55977C17.6437 11.1009 16.7544 14.0315 14.4674 15.5593C12.1804 17.0871 9.13257 16.7866 7.188 14.8415C6.10716 13.7604 5.49998 12.2942 5.5 10.7655Z" stroke="#000000c4" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
                            <path d="M17.029 16.5295L19.5 19.0005" stroke="#000000c4" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
                        </svg>
                    </div>
                </div>
                <div class="botones-facturacion">
                    <button class="boton-archivo" id="cargar-archivo-button">
                        <svg class="svg-archivo" width="800px" height="800px" viewBox="0 0 24 24" fill="none"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" clip-rule="evenodd"
                                  d="M12 1.25C12.2189 1.25 12.427 1.34567 12.5694 1.51191L15.5694 5.01191C15.839 5.3264 15.8026 5.79988 15.4881 6.06944C15.1736 6.33901 14.7001 6.30259 14.4306 5.98809L12.75 4.02744L12.75 15C12.75 15.4142 12.4142 15.75 12 15.75C11.5858 15.75 11.25 15.4142 11.25 15L11.25 4.02744L9.56944 5.98809C9.29988 6.30259 8.8264 6.33901 8.51191 6.06944C8.19741 5.79988 8.16099 5.3264 8.43056 5.01191L11.4306 1.51191C11.573 1.34567 11.7811 1.25 12 1.25ZM6.99583 8.25196C7.41003 8.24966 7.74768 8.58357 7.74999 8.99778C7.7523 9.41198 7.41838 9.74963 7.00418 9.75194C5.91068 9.75803 5.1356 9.78642 4.54735 9.89448C3.98054 9.99859 3.65246 10.1658 3.40901 10.4092C3.13225 10.686 2.9518 11.0746 2.85315 11.8083C2.75159 12.5637 2.75 13.5648 2.75 15.0002V16.0002C2.75 17.4356 2.75159 18.4367 2.85315 19.1921C2.9518 19.9259 3.13225 20.3144 3.40901 20.5912C3.68577 20.868 4.07434 21.0484 4.80812 21.1471C5.56347 21.2486 6.56458 21.2502 8 21.2502H16C17.4354 21.2502 18.4365 21.2486 19.1919 21.1471C19.9257 21.0484 20.3142 20.868 20.591 20.5912C20.8678 20.3144 21.0482 19.9259 21.1469 19.1921C21.2484 18.4367 21.25 17.4356 21.25 16.0002V15.0002C21.25 13.5648 21.2484 12.5637 21.1469 11.8083C21.0482 11.0746 20.8678 10.686 20.591 10.4092C20.3475 10.1658 20.0195 9.99859 19.4527 9.89448C18.8644 9.78642 18.0893 9.75803 16.9958 9.75194C16.5816 9.74963 16.2477 9.41198 16.25 8.99778C16.2523 8.58357 16.59 8.24966 17.0042 8.25196C18.0857 8.25798 18.9871 8.28387 19.7236 8.41916C20.4816 8.55839 21.1267 8.82363 21.6517 9.34856C22.2536 9.95048 22.5125 10.7084 22.6335 11.6085C22.75 12.4754 22.75 13.5778 22.75 14.9453V16.0551C22.75 17.4227 22.75 18.525 22.6335 19.392C22.5125 20.2921 22.2536 21.0499 21.6517 21.6519C21.0497 22.2538 20.2919 22.5127 19.3918 22.6337C18.5248 22.7503 17.4225 22.7502 16.0549 22.7502H7.94513C6.57754 22.7502 5.47522 22.7503 4.60825 22.6337C3.70814 22.5127 2.95027 22.2538 2.34835 21.6519C1.74643 21.0499 1.48754 20.2921 1.36652 19.392C1.24996 18.525 1.24998 17.4227 1.25 16.0551V14.9453C1.24998 13.5777 1.24996 12.4754 1.36652 11.6085C1.48754 10.7084 1.74643 9.95048 2.34835 9.34856C2.87328 8.82363 3.51835 8.55839 4.27635 8.41916C5.01291 8.28386 5.9143 8.25798 6.99583 8.25196Z"
                                  fill="#1C274C"/>
                        </svg>
                        <span>
                            Cargar Archivo
                        </span>
                        <form action="${pageContext.request.contextPath}/facturas/archivo" method="post" enctype="multipart/form-data">
                            <input type="file" name="file" id="file" hidden>
                            <input type="hidden" name="tipo" id="tipo">
                            <input type="submit" id="submit-file-button" hidden>
                        </form>
                    </button>
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
                                <th>Cobranza</th>
                                <th>RFC Emisor</th>
                                <th>Nombre Emisor</th>
                                <th>Subtotal</th>
                                <th>IVA</th>
                                <th>ISR Retenido</th>
                                <th>IVA Retenido</th>
                                <th>Total</th>
                                <th>UUID</th>
                                <th>TC</th>
                                <th>Descripción uso CFDI</th>
                                <th>Tipo Factura</th>
                                <th>Serie</th>
                                <th>Moneda</th>
                                <th>Estatus</th>
                                <th>Efecto</th>
                                <th>Metodo de Pago</th>
                                <th>Forma de Pago</th>
                                <th>Forma Pago Deducible</th>
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody class="tabla tabla-factura cuerpo-tabla">
                            <%if (facturas != null && !facturas.isEmpty()) {%>
                                <%for (FacturaEntity factura : facturas) {%>
                                <tr class="<%=factura.getFechaCobranza() != null ? "verde" : ""%>">
                                    <td><%=factura.getFolioFactura()%>
                                    </td>
                                    <td><%=factura.getFechaFactura()%>
                                    </td>
                                    <td >
                                        <%=factura.getFechaCobranza() != null ? factura.getFechaCobranza() : "Por definir"%>
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
                                    <td><%=factura.getUuidFactura()%>
                                    </td>
                                    <td><%=factura.getTcFactura()%>
                                    </td>
                                    <td><%=factura.getUsoCfdiReceptor()%>
                                    </td>
                                    <td><%=factura.getTipoFactura()%>
                                    </td>
                                    <td><%=factura.getSerie()%>
                                    </td>
                                    <td><%=factura.getMoneda()%>
                                    </td>
                                    <td><%=factura.getEstatus()%>
                                    </td>
                                    <td><%=factura.getEfecto()%>
                                    </td>
                                    <td><%=factura.getMetodoPago()%>
                                    </td>
                                    <td><%=factura.getFormaPago()%>
                                    </td>
                                    <td><%=factura.getFormaPagoDeducible()%>
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
                                    <td id="noresultadotd" colspan="22">Sin información para mostrar</td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <div class="paginacion" style="margin: 20px 0; text-align: center;">
                        <% if (totalPaginas != null && totalPaginas > 1) { %>
                        <% if (paginaActual > 1) { %>
                        <a href="${pageContext.request.contextPath}/facturas?pagina=<%=paginaActual - 1%>" class="pagina-link">&laquo; Anterior</a>
                        <% } %>
                        <% for (int i = 1; i <= totalPaginas; i++) { %>
                        <a href="${pageContext.request.contextPath}/facturas?pagina=<%=i%>" class="pagina-link<%= (i == paginaActual) ? " pagina-actual" : "" %>">
                            <%=i%>
                        </a>
                        <% } %>
                        <% if (paginaActual < totalPaginas) { %>
                        <a href="${pageContext.request.contextPath}/facturas?pagina=<%=paginaActual + 1%>" class="pagina-link">Siguiente &raquo;</a>
                        <% } %>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<script>
    const contexPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/js/drop-zone.js"></script>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/filtro-consultas/consulta-facturas.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/ventana-modal.js"></script>
</body>
</html>