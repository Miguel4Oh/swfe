document.addEventListener('DOMContentLoaded', () => {
    const formulario = document.getElementById('formulario-contrasena');

    const passwordActual = document.getElementById('password_actual');
    const passwordNueva = document.getElementById('password_nueva');
    const passwordConfirmacion = document.getElementById('password_confirmacion');

    const errorPasswordActual = document.getElementById('password-actual-error-message');
    const errorPasswordNueva = document.getElementById('password-nueva-error-message');
    const errorPasswordConfirmacion = document.getElementById('password-confirmacion-error-message');

    const expresiones = {
        password: /^(?=.*[A-Z]).{8,30}$/
    };

    const limpiarErrores = (campo, errorContainer) => {
        errorContainer.classList.add('remover');
        campo.classList.remove('input-error');
    };

    const validarCampo = (campo, errorContainer, mensaje) => {
        errorContainer.innerText = mensaje;
        errorContainer.classList.remove('remover');
        campo.classList.add('input-error');
    };

    passwordActual.addEventListener('input', () => limpiarErrores(passwordActual, errorPasswordActual));
    passwordNueva.addEventListener('input', () => limpiarErrores(passwordNueva, errorPasswordNueva));
    passwordConfirmacion.addEventListener('input', () => limpiarErrores(passwordConfirmacion, errorPasswordConfirmacion));

    formulario.addEventListener('submit', (event) => {
        event.preventDefault();
        let error = false;

        if (passwordActual.value.trim() === '') {
            validarCampo(passwordActual, errorPasswordActual, 'Este campo es obligatorio');
            error = true;
        }

        if (passwordNueva.value.trim() === '') {
            validarCampo(passwordNueva, errorPasswordNueva, 'Este campo es obligatorio');
            error = true;
        } else if (!expresiones.password.test(passwordNueva.value)) {
            validarCampo(passwordNueva, errorPasswordNueva, 'Debe tener entre 8 y 30 caracteres y al menos una mayúscula');
            error = true;
        }

        if (passwordConfirmacion.value.trim() === '') {
            validarCampo(passwordConfirmacion, errorPasswordConfirmacion, 'Este campo es obligatorio');
            error = true;
        } else if (passwordNueva.value !== passwordConfirmacion.value) {
            validarCampo(passwordConfirmacion, errorPasswordConfirmacion, 'Las contraseñas no coinciden');
            error = true;
        }

        if (!error) {
            formulario.submit();
        }
    });
});
