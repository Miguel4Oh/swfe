const ventanaModal = document.getElementById('ventana-modal');
const mensajeModal = document.getElementById('mensaje-modal');
const botonModalAceptar = document.getElementById('boton-modal-aceptar');
const enlaceImpuestos = document.getElementById('enlace-impuestos');
const enlaceDepreciacion = document.getElementById('enlace-depreciacion');
const enlaceNomina = document.getElementById('enlace-nomina');
const enlaceProveedores = document.getElementById('enlace-proveedores');
const enlaceEmpleados = document.getElementById('enlace-empleados')
const botonGuardar = document.getElementById('boton-guardar');
const mensaje = document.getElementById('mensaje');
const enlaceResumen = document.getElementById('enlace-resumen');
const mesSelector = document.getElementById('mes-select');

const headerEnlaceFacturacion = document.getElementById('header-enlace-facturacion');
const enlaceIngresos = document.getElementById('enlace-ingresos');
const enlaceEgresos = document.getElementById('enlace-egresos');

enlaceEgresos.addEventListener('click', e => {
    const nombreCliente = document.querySelector('.nombre-cliente span').textContent;
    e.preventDefault();

    if (nombreCliente !== "Seleccionar Cliente") {
        window.location.href = enlaceEgresos.href;
    } else {
        mensajeModal.textContent = 'Favor de seleccionar un cliente';
        ventanaModal.classList.remove('display-none');
    }
});

enlaceIngresos.addEventListener('click', e => {
    const nombreCliente = document.querySelector('.nombre-cliente span').textContent;
    e.preventDefault();

    if (nombreCliente !== "Seleccionar Cliente") {
        window.location.href = enlaceIngresos.href;
    } else {
        mensajeModal.textContent = 'Favor de seleccionar un cliente';
        ventanaModal.classList.remove('display-none');
    }
});

headerEnlaceFacturacion.addEventListener('click', e => {
    const nombreCliente = document.querySelector('.nombre-cliente span').textContent;
    e.preventDefault();

    if (nombreCliente !== "Seleccionar Cliente") {
        window.location.href = headerEnlaceFacturacion.href;
    } else {
        mensajeModal.textContent = 'Favor de seleccionar un cliente';
        ventanaModal.classList.remove('display-none');
    }
});

enlaceImpuestos.addEventListener('click', e => {
    const nombreCliente = document.querySelector('.nombre-cliente span').textContent;
    e.preventDefault();

    if (nombreCliente !== "Seleccionar Cliente") {
        if (mesSelector.value !== "" && mesSelector.value !== "0") {
            window.location.href = enlaceImpuestos.href;
        } else {
            mensajeModal.textContent = 'Favor de seleccionar un mes';
            ventanaModal.classList.remove('display-none');
        }

    } else {
        mensajeModal.textContent = 'Favor de seleccionar un cliente';
        ventanaModal.classList.remove('display-none');
    }
});


enlaceDepreciacion.addEventListener('click', e => {
    const nombreCliente = document.querySelector('.nombre-cliente span').textContent;

    e.preventDefault();

    if (nombreCliente !== "Seleccionar Cliente") {
        window.location.href = enlaceDepreciacion.href;
    } else {
        mensajeModal.textContent = 'Favor de seleccionar un cliente';
        ventanaModal.classList.remove('display-none');
    }

});

enlaceNomina.addEventListener('click', e => {
    const nombreCliente = document.querySelector('.nombre-cliente span').textContent;
    e.preventDefault();

    if (nombreCliente !== "Seleccionar Cliente") {
        window.location.href = enlaceNomina.href;
    } else {
        mensajeModal.textContent = 'Favor de seleccionar un cliente';
        ventanaModal.classList.remove('display-none');
    }
});

enlaceProveedores.addEventListener('click', e => {
    const nombreCliente = document.querySelector('.nombre-cliente span').textContent;
    e.preventDefault();

    if (nombreCliente !== "Seleccionar Cliente") {
        window.location.href = enlaceProveedores.href;
    } else {
        mensajeModal.textContent = 'Favor de seleccionar un cliente';
        ventanaModal.classList.remove('display-none');
    }
});

enlaceEmpleados.addEventListener('click', e => {
    const nombreCliente = document.querySelector('.nombre-cliente span').textContent;
    e.preventDefault();

    if (nombreCliente !== "Seleccionar Cliente") {
        window.location.href = enlaceEmpleados.href;
    } else {
        mensajeModal.textContent = 'Favor de seleccionar un cliente';
        ventanaModal.classList.remove('display-none');
    }
});

botonModalAceptar.addEventListener('click', e => {
    ventanaModal.classList.add('display-none');
});

enlaceResumen.addEventListener('click', e => {
    const nombreCliente = document.querySelector('.nombre-cliente span').textContent;
    e.preventDefault();

    if (nombreCliente !== "Seleccionar Cliente") {
        window.location.href = enlaceResumen.href;
    } else {
        mensajeModal.textContent = 'Favor de seleccionar un cliente';
        ventanaModal.classList.remove('display-none');
    }
});

window.addEventListener('load', e => {
    if(mensaje !== null){
        if (mensaje.value === "error") {
            mensaje.value = "";
            mensajeModal.textContent = 'El usuario ya existe';
            ventanaModal.classList.remove('display-none');
        }

        if (mensaje.value === "errorContrasena") {
            mensaje.value = "";
            mensajeModal.textContent = 'La contraseña es incorrecta';
            ventanaModal.classList.remove('display-none');
        }
    }
});