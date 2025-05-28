<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (request.getSession() != null && request.getSession().getAttribute("username")!= null) {
        response.sendRedirect(request.getContextPath() + "/clientes/seleccionar");
        return;
    }

%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Formulario de login</title>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@200..800&family=Quicksand:wght@300..700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/icons/favicon.ico">

</head>
<body class="login-body">
<div class="login-form">
    <img class="jesav-img" src="assets/images/Jesav.jpg">
    <form id="login-form" class="form-inputs" action="${pageContext.request.contextPath}/login" method="post">
        <div>
            <div class="login-input login-input-form">
                <svg width="25px" height="25px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M5 21C5 17.134 8.13401 14 12 14C15.866 14 19 17.134 19 21M16 7C16 9.20914 14.2091 11 12 11C9.79086 11 8 9.20914 8 7C8 4.79086 9.79086 3 12 3C14.2091 3 16 4.79086 16 7Z" stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
                <input class="login-text-input" type="text" name="username" id="username" placeholder="Nombre de usuario">
            </div>
        </div>
        <div>
            <div class="login-input login-input-form">
                <svg width="25px" height="25px" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
                    <g id="Layer_2" data-name="Layer 2">
                        <g id="invisible_box" data-name="invisible box">
                            <rect width="48" height="48" fill="none"/>
                        </g>
                        <g id="Layer_7" data-name="Layer 7">
                            <g>
                                <path d="M39,18H35V13A11,11,0,0,0,24,2H22A11,11,0,0,0,11,13v5H7a2,2,0,0,0-2,2V44a2,2,0,0,0,2,2H39a2,2,0,0,0,2-2V20A2,2,0,0,0,39,18ZM15,13a7,7,0,0,1,7-7h2a7,7,0,0,1,7,7v5H15ZM37,42H9V22H37Z"/>
                                <circle cx="15" cy="32" r="3"/>
                                <circle cx="23" cy="32" r="3"/>
                                <circle cx="31" cy="32" r="3"/>
                            </g>
                        </g>
                    </g>
                </svg>
                <input class="login-text-input" type="password" name="password" id="password" placeholder="Contraseña">
            </div>
        </div>

        <!-- <div class="g-recaptcha" data-sitekey="6Lf74yQrAAAAAJAu7O2kFo-SjliSyWIh_MtKNXlC"></div> -->

        <div>
            <button type="submit" class="login-button">
                Iniciar sesión
            </button>
        </div>
    </form>
</div>

<script src="https://www.google.com/recaptcha/api.js" async defer></script>

</body>
</html>
