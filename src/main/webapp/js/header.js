const agregarEjercicioIcono = document.getElementById('agregar-ejercicio-icono');
const contenedorNuevoEjercicio = document.querySelector('.contenedor-nuevo-ejercicio');

const ejercicioFiscalSelect = document.getElementById('ejercicio-fiscal-select');
var mesSelect;

document.getElementById('user-menu-dropdown').addEventListener('click', (event) => {
    const verticalMenu = document.getElementById('vertical-menu');

    if (verticalMenu.classList.contains('display-flex')) {
        verticalMenu.classList.remove('display-flex');
    } else {
        verticalMenu.classList.add('display-flex');
    }
});

if (document.getElementById('mes-select') !== null) {
    mesSelect = document.getElementById('mes-select');

    mesSelect.addEventListener('change', async () => {
        mesId = mesSelect.value;

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

if (ejercicioFiscalSelect !== null) {
    ejercicioFiscalSelect.addEventListener('change', () => {
        const ejercicioFiscal = ejercicioFiscalSelect.value;

        const url = `/swfe/ejercicio/seleccionar?ejercicio-fiscal-id=${ejercicioFiscal}`;

        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log("Respuesta del servidor:", data);
                window.location.reload();
            })
            .catch(error => {
                console.error("Error al hacer la solicitud:", error);
            });
    });
}

if (agregarEjercicioIcono !== null) {
    agregarEjercicioIcono.addEventListener('click', () => {
        if (contenedorNuevoEjercicio.classList.contains('mostrar-formulario-nuevo-ejercicio')) {
            contenedorNuevoEjercicio.classList.remove('mostrar-formulario-nuevo-ejercicio');
        } else {
            contenedorNuevoEjercicio.classList.add('mostrar-formulario-nuevo-ejercicio');
        }
    });
}

const header = document.getElementById('header');
window.addEventListener('scroll', () => {
    if (window.scrollY > 0) {
        header.classList.add('position-fixed');
    } else {
        header.classList.remove('position-fixed');
    }
});