const botonGuardar = document.getElementById('boton-guardar');

botonGuardar.addEventListener('click', async function() {
    event.preventDefault();

    const filas = document.querySelectorAll('#cuerpo-tabla-formulario tr');

    const datosFilas = [];

    const mes = document.getElementById('mes').value;
    const quincena = document.getElementById('quincena').value;

    filas.forEach(fila => {
        let nombre = fila.querySelector('td:nth-child(1) input').value;  // Obtener nombre
        let sueldo = fila.querySelector('td:nth-child(2) input').value;  // Obtener sueldo
        let diasTrabajados = fila.querySelector('td:nth-child(3) input').value;  // Obtener días trabajados
        let empleadoId = fila.querySelector('input[type="hidden"]').value;  // Obtener ID del empleado

        let filaData = {
            sueldo: parseFloat(sueldo),
            diasLaborados: parseInt(diasTrabajados),
            mes: mes,
            quincena: quincena,
            empleadoId: empleadoId
        };

        datosFilas.push(filaData);
    });

    await guardarDatos(datosFilas);

    window.location.href= '/swfe/nomina';
});

async function guardarDatos(datos) {
    const url = '/swfe/nomina/nomina-form';

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        const data = await response.json();
        console.log('Respuesta del servidor:', data);

    } catch (error) {
        console.error('Error al guardar los datos:', error);
    }
}