const botonAgregarTabla = document.getElementById('botonAgregarTabla');
const botonGuardar = document.getElementById('botonGuardar');
const selectMes = document.getElementById('mesSeleccionado');
const noResultados = document.querySelector('.no-resultados');

botonAgregarTabla.addEventListener('click', function () {
    const mesSeleccionado = selectMes.value.toUpperCase();

    if (noResultados !== null) {
        noResultados.remove();
    }

    var tablaCuerpo = document.getElementById('tablaCuerpo');

    var nuevaFila = document.createElement('tr');

    nuevaFila.innerHTML = `
            <td><input class="login-input form-input" type="text" id="mesTabla" value="${mesSeleccionado}" readonly></td>
            <td><input class="login-input form-input" type="number" id="limiteInferior"></td>
            <td><input class="login-input form-input" type="number" id="limiteSuperior" "></td>
            <td><input class="login-input form-input" type="number" id="cuotaFija"></td>
            <td><input class="login-input form-input" type="number" id="porcentajeExcedente" "></td>
            <td></td>
        `;

    tablaCuerpo.appendChild(nuevaFila);
});

botonGuardar.addEventListener('click', async function () {
    const url = '/swfe/tablas/guardar';  // La URL del servlet
    const filas = document.querySelectorAll('#tablaCuerpo tr');
    const tablasData = [];

    filas.forEach(function (fila) {
        var tablaId;
        if (fila.querySelector('input[id="tablaId"]')) {
            tablaId = fila.querySelector('input[id="tablaId"]').value.trim();
        } else {
            tablaId = 0;
        }
        const mes = fila.querySelector('input[id="mesTabla"]').value.trim();
        const limiteInferior = parseFloat(fila.querySelector('input[id="limiteInferior"]').value.trim());
        const limiteSuperior = parseFloat(fila.querySelector('input[id="limiteSuperior"]').value.trim());
        const cuotaFija = parseFloat(fila.querySelector('input[id="cuotaFija"]').value.trim());
        const porcentajeExcedente = parseFloat(fila.querySelector('input[id="porcentajeExcedente"]').value.trim());

        const tabla = {
            tablaId: tablaId,
            mesTabla: mes,
            limiteInferior: limiteInferior,
            limiteSuperior: limiteSuperior,
            cuotaFija: cuotaFija,
            porcentajeExcedente: porcentajeExcedente
        };

        tablasData.push(tabla);

    });

    try {

        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(tablasData)
        });

    } catch (error) {
        console.error('Error al guardar los datos:', error);
    }

    window.location.href = `/swfe/tablas?nombreTabla=${encodeURIComponent(tablasData[0].mesTabla)}`;

});
