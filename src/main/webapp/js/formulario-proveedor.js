const formulario = document.getElementById('formulario-proveedor');
const inputs = document.querySelectorAll('#formulario-proveedor input');
const rfc = document.getElementById('rfc');
const nombre = document.getElementById('nombre');
const rfcError = document.getElementById('rfc-error-message');
const nombreError = document.getElementById('nombre-error-message');

const expresiones = {
    rfc: /^[a-z]{3,4}\d{6}[a-z]{3}$/i,
    nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/
}

const validarFormulario = (event) => {
    switch (event.target.name) {
        case "rfc":
            rfcError.classList.add('remover');
            rfc.classList.remove('input-error');
            break;
        case "nombre":
            nombreError.classList.add('remover');
            nombre.classList.remove('input-error');
            break;
    }
}

inputs.forEach((input) => {
    input.addEventListener('keydown', validarFormulario);
    input.addEventListener('blur', validarFormulario);
});

formulario.addEventListener('submit', (event) => {
    event.preventDefault();

    const rfcValue = document.getElementById('rfc').value;
    const nombreValue = document.getElementById('nombre').value;

    var error = false;

    if (rfcValue === '') {
        rfcError.innerText = 'Este campo es obligatorio';
        rfcError.classList.remove('remover');
        rfc.classList.add('input-error');
        error = true;
    } else if (!expresiones.rfc.test(rfcValue)) {
        rfcError.innerText = 'Formato inválido';
        rfcError.classList.remove('remover');
        rfc.classList.add('input-error');
        error = true;
    }

    if (nombreValue === '') {
        nombreError.innerText = 'Este campo es obligatorio';
        nombreError.classList.remove('remover');
        nombre.classList.add('input-error');
        error = true;
    } else if (!expresiones.nombre.test(nombreValue)) {
        nombreError.innerText = 'Formato inválido';
        nombreError.classList.remove('remover');
        nombre.classList.add('input-error');
        error = true;
    }

    if (!error) {
        formulario.submit();
    }
});