const submenu = document.getElementById('submenu-lateral');
const catalogoLateral = document.getElementById('catalogo-lateral');
const barralateral = document.getElementById('barra-lateral');
const mainSection = document.getElementById('main-section');
const burgerIconId = document.getElementById('burger-icon-id');

const impuestosLateral = document.getElementById('impuestos-lateral');
const impuestosSubmenuLateral = document.getElementById('impuestos-submenu-lateral');

catalogoLateral.addEventListener('click', () => {
    if (submenu.classList.contains('remover')) {
        submenu.classList.remove('remover');
    } else {
        submenu.classList.add('remover');
    }
});

burgerIconId.addEventListener('click', () => {

    if (window.innerWidth > 1199) {
        if (barralateral.classList.contains('ocultar-barra')) {
            barralateral.classList.remove('ocultar-barra');
            mainSection.classList.remove('mostrar-completa');
        } else {
            barralateral.classList.add('ocultar-barra');
            mainSection.classList.add('mostrar-completa');
        }
    } else {
        if (barralateral.classList.contains('ocultar-barra')) {
            barralateral.classList.remove('ocultar-barra');
        } else {
            barralateral.classList.add('ocultar-barra');
        }
    }
});

window.addEventListener('resize', () => {
    if (window.innerWidth > 1199) {
        barralateral.classList.remove('ocultar-barra');
        mainSection.classList.remove('mostrar-completa');
    } else {
        barralateral.classList.add('ocultar-barra');
        mainSection.classList.add('mostrar-completa');
    }
});

window.dispatchEvent(new Event('resize'));

window.addEventListener('scroll', () => {
    if (window.scrollY > 0) {
        barralateral.classList.add('top-50');
    } else {
        barralateral.classList.remove('top-50');
    }
});

impuestosLateral.addEventListener('click', () => {
    if (impuestosSubmenuLateral.classList.contains('remover')) {
        impuestosSubmenuLateral.classList.remove('remover');
    } else {
        impuestosSubmenuLateral.classList.add('remover');
    }
});

const agregarEjercicioIconoLateral = document.getElementById('agregar-ejercicio-icono-lateral');
if (agregarEjercicioIconoLateral !== null) {
    agregarEjercicioIconoLateral.addEventListener('click', () => {
        if (contenedorNuevoEjercicio.classList.contains('mostrar-formulario-nuevo-ejercicio')) {
            contenedorNuevoEjercicio.classList.remove('mostrar-formulario-nuevo-ejercicio');
        } else {
            contenedorNuevoEjercicio.classList.add('mostrar-formulario-nuevo-ejercicio');
        }
    });
}

if (document.getElementById('mes-select-lateral') !== null) {
    mesSelectLateral = document.getElementById('mes-select-lateral');

    mesSelectLateral.addEventListener('change', async () => {
        mesId = mesSelectLateral.value;

        const url = `/swfe/mes/seleccionar?mesId=${mesId}`;

        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log("Respuesta del servidor:", data);
                window.location.reload();
            })
            .catch(error => {
                console.error("Error al hacer la solicitud:", error);
                window.location.reload();
            });
    });
}

