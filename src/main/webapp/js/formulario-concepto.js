const formulario = document.getElementById('formulario-concepto');
const inputs = document.querySelectorAll('#formulario-concepto input');
const concepto = document.getElementById('concepto');
const porcentaje = document.getElementById('porcentaje');
const conceptoError = document.getElementById('concepto-error-message');
const porcentajeError = document.getElementById('porcentaje-error-message');

const expresiones = {
    concepto: /^[a-zA-ZÀ-ÿ\s]{1,40}$/,
    porcentaje: /^\d+(\.\d{1,2})?$/
}

const validarFormulario = (event) => {
    switch (event.target.name) {
        case "concepto":
            conceptoError.classList.add('remover');
            concepto.classList.remove('input-error');
            break;
        case "porcentaje":
            porcentajeError.classList.add('remover');
            porcentaje.classList.remove('input-error');
            break;
    }
}

inputs.forEach((input) => {
    input.addEventListener('keydown', validarFormulario);
    input.addEventListener('blur', validarFormulario);
});

formulario.addEventListener('submit', (event) => {
    console.log("entra a formulario.addEventListener");
    event.preventDefault();

    const conceptoValue = document.getElementById('concepto').value;
    const porcentajeValue = document.getElementById('porcentaje').value;

    console.log("conceptoValue: " + conceptoValue);
    console.log("porcentajeValue: " + porcentajeValue);
    var error = false;

    if (conceptoValue === '') {
        conceptoError.innerText = 'Este campo es obligatorio';
        conceptoError.classList.remove('remover');
        concepto.classList.add('input-error');
        error = true;
    }else if (!expresiones.concepto.test(conceptoValue)) {
        conceptoError.innerText = 'Formato inválido';
        conceptoError.classList.remove('remover');
        concepto.classList.add('input-error');
        error = true;
    }

    if (porcentajeValue === '') {
        porcentajeError.innerText = 'Este campo es obligatorio';
        porcentajeError.classList.remove('remover');
        porcentaje.classList.add('input-error');
        error = true;
    }else if (!expresiones.porcentaje.test(porcentajeValue)) {
        porcentajeError.innerText = 'Formato inválido';
        porcentajeError.classList.remove('remover');
        porcentaje.classList.add('input-error');
        error = true;
    }

    if (!error) {
        formulario.submit();
    }
});