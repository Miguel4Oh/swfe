<%@ page import="com.ortega.webapp.servlet.swfe.models.EmpleadoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ortega.webapp.servlet.swfe.models.MesEntity" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    HttpSession httpSession = request.getSession();
    List<EmpleadoEntity> empleados = (List<EmpleadoEntity>) request.getAttribute("empleados");
    List<MesEntity> periodos = (List<MesEntity>) request.getAttribute("periodos");
    int clienteId = Integer.parseInt(httpSession.getAttribute("clienteId").toString());
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Nómina</title>
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
    <section class="main-section" id="main-section">
        <div class="tabla-envoltura">
            <div class="tabla-header">
                <div class="titulo-buscador">
                    <h1>Nueva nómina</h1>
                </div>
                <div class="botones-
                facturacion">
                    <button class="boton-archivo" id="boton-guardar">
                        <svg class="svg-guardar" width="25px" height="25px" viewBox="0 0 32 32" version="1.1"
                             xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                             xmlns:sketch="http://www.bohemiancoding.com/sketch/ns">
                            <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd"
                               sketch:type="MSPage">
                                <g id="Icon-Set" sketch:type="MSLayerGroup"
                                   transform="translate(-152.000000, -515.000000)" fill="white">
                                    <path d="M171,525 C171.552,525 172,524.553 172,524 L172,520 C172,519.447 171.552,519 171,519 C170.448,519 170,519.447 170,520 L170,524 C170,524.553 170.448,525 171,525 L171,525 Z M182,543 C182,544.104 181.104,545 180,545 L156,545 C154.896,545 154,544.104 154,543 L154,519 C154,517.896 154.896,517 156,517 L158,517 L158,527 C158,528.104 158.896,529 160,529 L176,529 C177.104,529 178,528.104 178,527 L178,517 L180,517 C181.104,517 182,517.896 182,519 L182,543 L182,543 Z M160,517 L176,517 L176,526 C176,526.553 175.552,527 175,527 L161,527 C160.448,527 160,526.553 160,526 L160,517 L160,517 Z M180,515 L156,515 C153.791,515 152,516.791 152,519 L152,543 C152,545.209 153.791,547 156,547 L180,547 C182.209,547 184,545.209 184,543 L184,519 C184,516.791 182.209,515 180,515 L180,515 Z"
                                          id="save-floppy" sketch:type="MSShapeGroup">
                                    </path>
                                </g>
                            </g>
                        </svg>
                        <span>Guardar</span>
                    </button>
                </div>
            </div>
            <div>
                <div class="tabla-contenedor">
                    <div class="nomina-form">
                        <select id="mes" class="login-input form-input">
                            <option>Mes</option>
                            <% for (MesEntity periodo : periodos) { %>
                                <option value="<%=periodo.getMesId()%>"><%=periodo.getNombreMes()%></option>
                            <% } %>
                        </select>
                        <select id="quincena" class="login-input form-input">
                            <option value="1">Quincena 1</option>
                            <option value="2">Quincena 2</option>
                        </select>
                    </div>
                    <div>
                        <h2>Empleados</h2>
                        <table class="tabla tabla-factura">
                            <thead class="tabla tabla-factura encabezado-tabla tabla-formulario">
                            <tr>
                                <th>Nombre</th>
                                <th>Sueldo</th>
                                <th>Días trabajados</th>
                                <th>Eliminar</th>
                            </tr>
                            </thead>
                            <tbody class="tabla tabla-factura cuerpo-tabla" id="cuerpo-tabla-formulario">
                            <% for (EmpleadoEntity empleadoEntity : empleados) { %>
                            <tr>
                                <td>
                                    <input class="login-input form-input input-nomina" type="text" value="<%=empleadoEntity.getNombre()%>" readonly>
                                </td>
                                <td>
                                    <input class="login-input form-input input-nomina" type="number" value="<%=empleadoEntity.getSueldo()%>">
                                </td>
                                <td>
                                    <input class="login-input form-input input-nomina" type="number" value="15">
                                </td>

                                <td>
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
                                </td>
                                <input type="hidden" value="<%=empleadoEntity.getEmpleadoId()%>">
                            </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<script src="${pageContext.request.contextPath}/js/menu-lateral.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/main-content.js"></script>
<script src="${pageContext.request.contextPath}/js/nomina-form.js"></script>
</body>
</html>
