const botonAgregar = document.querySelector('.boton-agregar');
const ventanaFormulario = document.querySelector('.ventana-formulario');
const cuerpoTablaFormulario = document.getElementById('cuerpo-tabla-formulario');
const botonCerrarFormulario = document.getElementById('boton-cerrar-formulario');
const noResultados = document.querySelector('.no-resultados');

const filasEliminadas = [];
const filasNuevas = [];
let filasConCambios = [];

botonAgregar.addEventListener('click', async () => {
    console.log('Botón agregar');

    const idMesSelect = document.getElementById('mes-select');

    if (idMesSelect.value === "0") {
        mensajeModal.textContent = 'Favor de seleccionar un mes';
        ventanaModal.classList.remove('display-none');
        return;
    }

    if (noResultados !== null) {
        noResultados.remove();
    }

    const conceptosDepreciacion = await consultarConceptosDepreciacion();

    var filaHTML = `
            <td><input class="login-input form-input" type="date" ></td>
            <td><input class="login-input form-input" type="number"></td>
            <td>
            <select class="login-input form-input">
                ${conceptosDepreciacion}
            </select>
            </td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
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
            <td>
                <input value="" type="hidden">
            </td>
    `;

    var nuevaFila = document.createElement('tr');
    nuevaFila.innerHTML = filaHTML;

    cuerpoTablaFormulario.appendChild(nuevaFila);
});

async function consultarConceptosDepreciacion() {
    const filtro = "";

    const url = `/swfe/conceptos/filtro?filtro=${encodeURIComponent(filtro)}`;

    try {
        const response = await fetch(url);
        const data = await response.json();

        let conceptosDepreciacion = "";

        data.forEach(depreciacion => {
            conceptosDepreciacion += `<option value="${depreciacion.conceptoDepreciacionId}">${depreciacion.nombreDepreciacion}</option>`;
        });

        return conceptosDepreciacion;
    } catch (error) {
        console.error('Error:', error);
    }
}

cuerpoTablaFormulario.addEventListener('click', (e) => {
    if (e.target && (e.target.classList.contains('boton-eliminar') || e.target.closest('.boton-eliminar'))) {
        e.preventDefault();

        const fila = e.target.closest('tr');

        if (fila) {
            fila.remove();
        }
    }
});

document.querySelectorAll('.tabla-factura .form-input').forEach(input => {
    input.addEventListener('change', (event) => {
        let row = event.target.closest('tr');

        console.log(row.querySelector('td:nth-child(3) select').value)

        let fechaAdquisicion = row.querySelector('td:nth-child(1) input').value;
        let montoInversion = row.querySelector('td:nth-child(2) input').value;
        let conceptoDepreciacion = row.querySelector('td:nth-child(3) select').value;
        let depreciacionId = row.querySelector('input[type="hidden"]').value;

        let filaData = {
            depreciacionId,
            fechaAdquisicion,
            montoInversion,
            conceptoDepreciacion
        };

        let existingIndex = filasConCambios.findIndex(item => item.depreciacionId === depreciacionId);
        if (existingIndex !== -1) {
            filasConCambios[existingIndex] = filaData;
        } else {
            filasConCambios.push(filaData);
        }

        console.log('Datos de las filas con cambios:', filasConCambios);

    });
});

document.querySelectorAll('.tabla-factura .boton-eliminar').forEach(button => {
    button.addEventListener('click', (event) => {
        let row = event.target.closest('tr');

        let depreciacionId = row.querySelector('input[type="hidden"]').value;

        row.remove();

        console.log('Fila eliminada con Depreciacion ID:', depreciacionId);

        filasEliminadas.push(depreciacionId);

    });
});

document.getElementById('boton-guardar').addEventListener('click', function (event) {
    event.preventDefault();
    let error = false;

    const filas = document.querySelectorAll('#cuerpo-tabla-formulario tr');

    filas.forEach(fila => {

        let depreciacionId = fila.querySelector('input[type="hidden"]').value;

        console.log('depreciacionId:', depreciacionId);

        if (!depreciacionId) {
            let fecha = fila.querySelector('td:nth-child(1) input').value;
            let fechaAdquisicion;
            const montoInversion = parseFloat(fila.querySelector('td:nth-child(2) input').value);
            const conceptoDepreciacion = fila.querySelector('td:nth-child(3) select').value;

            console.log("fecha", fecha);
            console.log("montoInversion", montoInversion);
            console.log("conceptoDepreciacion", conceptoDepreciacion);

            if (fecha === "" || Number.isNaN(montoInversion) || montoInversion === 0 || conceptoDepreciacion === "") {
                mensajeModal.textContent = 'Favor de llenar todos los campos';
                ventanaModal.classList.remove('display-none');
                error = true;
            } else {

                fechaAdquisicion = new Date(fecha).toISOString().split('T')[0];

                const depreciacionId = parseInt(0);

                const filaData = {
                    fechaAdquisicion,
                    montoInversion,
                    conceptoDepreciacion,
                    depreciacionId
                };

                filasNuevas.push(filaData);

            }
        }
    });

    if (!error) {
        console.log("Datos filas", filasNuevas);
        console.log("Filas eliminadas", JSON.stringify(filasEliminadas));
        console.log('Datos de las filas con cambios:', filasConCambios);

        debugger;

        guardarCambios();
    }

});

async function guardarCambios() {
    const url = `/swfe/depreciaciones/actualizar`;

    let todasLasPeticionesCompletadas = true;

    if (filasConCambios.length > 0) {
        console.log('Actualizando filas:', filasConCambios);
        try {
            const response = await fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(filasConCambios)
            });
            const data = await response.json();
            console.log('Respuesta del servidor (PUT):', data);
        } catch (error) {
            console.error('Error al actualizar:', error);
            todasLasPeticionesCompletadas = false;
        }
    }

    if (filasEliminadas.length > 0) {
        console.log('Eliminando filas:', filasEliminadas);
        try {
            const response = await fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(filasEliminadas)
            });
            const data = await response.json();
            console.log('Respuesta del servidor (DELETE):', data);
        } catch (error) {
            console.error('Error al eliminar:', error);
            todasLasPeticionesCompletadas = false;
        }
    }

    if (filasNuevas.length > 0) {
        console.log('Guardando nuevas filas:', filasNuevas);
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(filasNuevas)
            });
            const data = await response.json();
            console.log('Respuesta del servidor (POST):', data);
        } catch (error) {
            console.error('Error al guardar nuevas filas:', error);
            todasLasPeticionesCompletadas = false;
        }
    }

    if (todasLasPeticionesCompletadas) {
        console.log("Todas las peticiones se completaron con éxito. Recargando la página...");
        window.location.reload();
    } else {
        console.log("Hubo un error en alguna de las peticiones. No se recargará la página.");
    }
}