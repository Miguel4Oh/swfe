const formulario = document.getElementById('formulario-empleado');
const inputs = document.querySelectorAll('#formulario-empleado input');
const nombre = document.getElementById('nombre');
const sueldo = document.getElementById('sueldo');
const nombreError = document.getElementById('nombre-error-message');
const sueldoError = document.getElementById('sueldo-error-message');
console.log("entro");
const expresiones = {
    nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/,
    sueldo: /^\d{1,7}(\.\d{1,2})?$/
}

const validarFormulario = (event) => {
    switch (event.target.name) {
        case "nombre":
            nombreError.classList.add('remover');
            nombre.classList.remove('input-error');
            break;
        case "sueldo":
            sueldoError.classList.add('remover');
            sueldo.classList.remove('input-error');
            break;
    }
}

inputs.forEach((input) => {
    input.addEventListener('keydown', validarFormulario);
    input.addEventListener('blur', validarFormulario);
});

formulario.addEventListener('submit', (event) => {
    event.preventDefault();

    const nombreValue = document.getElementById('nombre').value;
    const sueldoValue = document.getElementById('sueldo').value;

    console.log("nombreValue: " + nombreValue);
    console.log("sueldoValue: " + sueldoValue);

    var error = false;

    if (nombreValue === '') {
        nombreError.innerText = 'Este campo es obligatorio';
        nombreError.classList.remove('remover');
        nombre.classList.add('input-error');
        error = true;
    }else if (!expresiones.nombre.test(nombreValue)) {
        nombreError.innerText = 'Formato inválido';
        nombreError.classList.remove('remover');
        nombre.classList.add('input-error');
        error = true;
    }

    if (sueldoValue === '') {
        sueldoError.innerText = 'Este campo es obligatorio';
        sueldoError.classList.remove('remover');
        sueldo.classList.add('input-error');
        error = true;
    }else if (!expresiones.sueldo.test(sueldoValue)) {
        sueldoError.innerText = 'Formato inválido';
        sueldoError.classList.remove('remover');
        sueldo.classList.add('input-error');
        error = true;
    }

    if (!error) {
        formulario.submit();
    }
});