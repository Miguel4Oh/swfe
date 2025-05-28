const formulario = document.getElementById('formulario-usuario');
const inputs = document.querySelectorAll('#formulario-usuario input');
const usuarioId = document.getElementById('usuarioId');
const username = document.getElementById('username');
const roleId = document.getElementById('roleId');
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirmPassword');
const action = document.getElementById('action');

const usuarioIdError = document.getElementById('id-error-message');
const usernameError = document.getElementById('username-error-message');
const roleError = document.getElementById('rol-error-message');
const passwordError = document.getElementById('password-error-message');
const confirmPasswordError = document.getElementById('confirm-password-error-message');

const expresiones = {
    usuarioId: /^\d+$/,
    username: /^[A-Za-zÁÉÍÓÚáéíóúñÑ ]{2,100}$/,
    password: /^.{6,30}$/
};

const validarFormulario = (event) => {
    switch (event.target.name) {
        case "usuarioId":
            usuarioIdError.classList.add('remover');
            usuarioId.classList.remove('input-error');
            break;
        case "username":
            usernameError.classList.add('remover');
            username.classList.remove('input-error');
            break;
        case "password":
            passwordError.classList.add('remover');
            password.classList.remove('input-error');
            break;
        case "confirmPassword":
            confirmPasswordError.classList.add('remover');
            confirmPassword.classList.remove('input-error');
            break;
    }
};

inputs.forEach((input) => {
    input.addEventListener('keydown', validarFormulario);
    input.addEventListener('blur', validarFormulario);
});

formulario.addEventListener('submit', (event) => {
    event.preventDefault();

    let error = false;

    const isCreate = action && action.value === 'create';

    if (isCreate) {
        if (usuarioId.value === '') {
            usuarioIdError.innerText = 'Este campo es obligatorio';
            usuarioIdError.classList.remove('remover');
            usuarioId.classList.add('input-error');
            error = true;
        } else if (!expresiones.usuarioId.test(usuarioId.value)) {
            usuarioIdError.innerText = 'Solo se permiten números';
            usuarioIdError.classList.remove('remover');
            usuarioId.classList.add('input-error');
            error = true;
        }
    }

    if (username.value.trim() === '') {
        usernameError.innerText = 'Este campo es obligatorio';
        usernameError.classList.remove('remover');
        username.classList.add('input-error');
        error = true;
    } else if (!expresiones.username.test(username.value)) {
        usernameError.innerText = 'Debe tener entre 4 y 20 caracteres alfanuméricos';
        usernameError.classList.remove('remover');
        username.classList.add('input-error');
        error = true;
    }

    if (roleId.value !== '1' && roleId.value !== '2') {
        roleError.innerText = 'Seleccione un rol válido';
        roleError.classList.remove('remover');
        roleId.classList.add('input-error');
        error = true;
    }

    if (isCreate) {
        if (password.value === '') {
            passwordError.innerText = 'Este campo es obligatorio';
            passwordError.classList.remove('remover');
            password.classList.add('input-error');
            error = true;
        } else if (!expresiones.password.test(password.value)) {
            passwordError.innerText = 'La contraseña debe tener entre 6 y 30 caracteres';
            passwordError.classList.remove('remover');
            password.classList.add('input-error');
            error = true;
        }

        if (confirmPassword.value !== password.value) {
            confirmPasswordError.innerText = 'Las contraseñas no coinciden';
            confirmPasswordError.classList.remove('remover');
            confirmPassword.classList.add('input-error');
            error = true;
        }
    }

    if (!error) {
        formulario.submit();
    }
});
