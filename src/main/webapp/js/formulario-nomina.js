const formulario = document.getElementById('formulario-nomina');
const inputs = document.querySelectorAll('#formulario-nomina input');
const salario = document.getElementById('salario');
const diasTrabajados = document.getElementById('diasTrabajados');
const salarioError = document.getElementById('salario-error-message');
const diasTrabajadosError = document.getElementById('diasTrabajados-error-message');

const expresiones = {
    salario: /^\d{1,7}(\.\d{1,2})?$/,
    diasTrabajados: /^\d{1,2}$/
}

const validarFormulario = (event) => {
    switch (event.target.name) {
        case "salario":
            salarioError.classList.add('remover');
            salario.classList.remove('input-error');
            break;
        case "diasTrabajados":
            diasTrabajadosError.classList.add('remover');
            diasTrabajados.classList.remove('input-error');
            break;
    }
}

inputs.forEach((input) => {
    input.addEventListener('keydown', validarFormulario);
    input.addEventListener('blur', validarFormulario);
});

formulario.addEventListener('submit', (event) => {
    event.preventDefault();

    const salarioValue = document.getElementById('salario').value;
    const diasTrabajadosValue = document.getElementById('diasTrabajados').value;

    var error = false;

    if (salarioValue === '') {
        salarioError.innerText = 'Este campo es obligatorio';
        salarioError.classList.remove('remover');
        salario.classList.add('input-error');
        error = true;
    }else if (!expresiones.salario.test(salarioValue)) {
        salarioError.innerText = 'Formato inválido';
        salarioError.classList.remove('remover');
        salario.classList.add('input-error');
        error = true;
    }

    if (diasTrabajadosValue === '') {
        diasTrabajadosError.innerText = 'Este campo es obligatorio';
        diasTrabajadosError.classList.remove('remover');
        diasTrabajados.classList.add('input-error');
        error = true;
    }else if (!expresiones.diasTrabajados.test(diasTrabajadosValue)) {
        diasTrabajadosError.innerText = 'Formato inválido';
        diasTrabajadosError.classList.remove('remover');
        diasTrabajados.classList.add('input-error');
        error = true;
    }

    if (!error) {
        formulario.submit();
    }
});