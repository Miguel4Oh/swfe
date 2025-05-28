const formulario = document.getElementById('formulario-tabla');
const inputs = document.querySelectorAll('#formulario-tabla input');
const mes = document.getElementById('mes');
const limiteInferior = document.getElementById('limiteInferior');
const limiteSuperior = document.getElementById('limiteSuperior');
const cuotaFija = document.getElementById('cuotaFija');
const porcentajeExcendente = document.getElementById('porcentajeExcedente');
const mesError = document.getElementById('mes-error-message');
const limiteInferiorError = document.getElementById('limiteInferior-error-message');
const limiteSuperiorError = document.getElementById('limiteSuperior-error-message');
const cuotaFijaError = document.getElementById('cuotaFija-error-message');
const porcentajeExcendenteError = document.getElementById('porcentajeExcedente-error-message');

const expresiones = {
    mes: /^[a-zA-ZÀ-ÿ\s]{1,40}$/i,
    limiteInferior: /^\d+(\.\d{1,2})?$/,
    limiteSuperior: /^\d+(\.\d{1,2})?$/,
    cuotaFija: /^\d+(\.\d{1,2})?$/,
    porcentajeExcendente: /^\d{1,2}$/
}

console.log(formulario);

const validarFormulario = (event) => {
    switch (event.target.name) {
        case "mes":
            mesError.classList.add('remover');
            mes.classList.remove('input-error');
            break;
        case "limiteInferior":
            limiteInferiorError.classList.add('remover');
            limiteInferior.classList.remove('input-error');
            break;
        case "limiteSuperior":
            limiteSuperiorError.classList.add('remover');
            limiteSuperior.classList.remove('input-error');
            break;
        case "cuotaFija":
            cuotaFijaError.classList.add('remover');
            cuotaFija.classList.remove('input-error');
            break;
        case "porcentajeExcendente":
            porcentajeExcendenteError.classList.add('remover');
            porcentajeExcendente.classList.remove('input-error');
            break;
    }
}

inputs.forEach((input) => {
    input.addEventListener('keydown', validarFormulario);
    input.addEventListener('blur', validarFormulario);
});

formulario.addEventListener('submit', (event) => {
    console.log("entro");
    event.preventDefault();

    const mesValue = document.getElementById('mes').value;
    const limiteInferiorValue = document.getElementById('limiteInferior').value;
    const limiteSuperiorValue = document.getElementById('limiteSuperior').value;
    const cuotaFijaValue = document.getElementById('cuotaFija').value;
    const porcentajeExcendenteValue = document.getElementById('porcentajeExcedente').value;

    console.log("mes: " + mesValue);
    console.log("limiteInferior: " + limiteInferiorValue);
    var error = false;

    if (mesValue === '') {
        mesError.innerText = 'Este campo es obligatorio';
        mesError.classList.remove('remover');
        mes.classList.add('input-error');
        error = true;
    }

    if (limiteInferiorValue === '') {
        limiteInferiorError.innerText = 'Este campo es obligatorio';
        limiteInferiorError.classList.remove('remover');
        limiteInferior.classList.add('input-error');
        error = true;
    }else if (!expresiones.limiteInferior.test(limiteInferiorValue)) {
        limiteInferiorError.innerText = 'Formato inválido';
        limiteInferiorError.classList.remove('remover');
        limiteInferior.classList.add('input-error');
        error = true;
    }

    if (limiteSuperiorValue === '') {
        limiteSuperiorError.innerText = 'Este campo es obligatorio';
        limiteSuperiorError.classList.remove('remover');
        limiteSuperior.classList.add('input-error');
        error = true;
    }else if (!expresiones.limiteSuperior.test(limiteSuperiorValue)) {
        limiteSuperiorError.innerText = 'Formato inválido';
        limiteSuperiorError.classList.remove('remover');
        limiteSuperior.classList.add('input-error');
        error = true;
    }

    if (cuotaFijaValue === '') {
        cuotaFijaError.innerText = 'Este campo es obligatorio';
        cuotaFijaError.classList.remove('remover');
        cuotaFija.classList.add('input-error');
        error = true;
    }else if (!expresiones.cuotaFija.test(cuotaFijaValue)) {
        cuotaFijaError.innerText = 'Formato inválido';
        cuotaFijaError.classList.remove('remover');
        cuotaFija.classList.add('input-error');
        error = true;
    }

    if (porcentajeExcendenteValue === '') {
        porcentajeExcendenteError.innerText = 'Este campo es obligatorio';
        porcentajeExcendenteError.classList.remove('remover');
        porcentajeExcendente.classList.add('input-error');
        error = true;
    }else if (!expresiones.porcentajeExcendente.test(porcentajeExcendenteValue)) {
        porcentajeExcendenteError.innerText = 'Formato inválido';
        porcentajeExcendenteError.classList.remove('remover');
        porcentajeExcendente.classList.add('input-error');
        error = true;
    }

    if (error) {
        return;
    }

    document.getElementById('formulario-tabla').submit();
});
