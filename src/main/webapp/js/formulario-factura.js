const formulario = document.getElementById('formulario-factura');
const inputs = document.querySelectorAll('.login-input');
const folioFactura = document.getElementById('folioFactura');
const fechaFactura = document.getElementById('fechaFactura');
const fechaCobranza = document.getElementById('fechaCobranza');
const rfcEmisor = document.getElementById('rfcEmisor');
const nombreEmisor = document.getElementById('nombreEmisor');
const subtotalFactura = document.getElementById('subtotalFactura');
const ivaFactura = document.getElementById('ivaFactura');
const isrRetenido = document.getElementById('isrRetenido');
const ivaRetenido = document.getElementById('ivaRetenido');
const totalFactura = document.getElementById('totalFactura');
const uuidFactura = document.getElementById('uuidFactura');
const tcFactura = document.getElementById('tcFactura');
const usoCfdiReceptor = document.getElementById('usoCfdiReceptor');
const tipoFactura = document.getElementById('tipoFactura');
const serie = document.getElementById('serie');
const moneda = document.getElementById('moneda');
const estatus = document.getElementById('estatus');
const efecto = document.getElementById('efecto');
const metodoPago = document.getElementById('metodoPago');
const formaPago = document.getElementById('formaPago');
const formaPagoDeducible = document.getElementById('formaPagoDeducible');

const folioFacturaError = document.getElementById('folioFactura-error-message');
const fechaFacturaError = document.getElementById('fechaFactura-error-message');
const fechaCobranzaError = document.getElementById('fechaCobranza-error-message');
const rfcEmisorError = document.getElementById('rfcEmisor-error-message');
const nombreEmisorError = document.getElementById('nombreEmisor-error-message');
const subtotalFacturaError = document.getElementById('subtotalFactura-error-message');
const ivaFacturaError = document.getElementById('ivaFactura-error-message');
const isrRetenidoError = document.getElementById('isrRetenido-error-message');
const ivaRetenidoError = document.getElementById('ivaRetenido-error-message');
const totalFacturaError = document.getElementById('totalFactura-error-message');
const uuidFacturaError = document.getElementById('uuidFactura-error-message');
const tcFacturaError = document.getElementById('tcFactura-error-message');
const usoCfdiReceptorError = document.getElementById('usoCfdiReceptor-error-message');
const tipoFacturaError = document.getElementById('tipoFactura-error-message');
const serieError = document.getElementById('serie-error-message');
const monedaError = document.getElementById('moneda-error-message');
const estatusError = document.getElementById('estatus-error-message');
const efectoError = document.getElementById('efecto-error-message');
const metodoPagoError = document.getElementById('metodoPago-error-message');
const formaPagoError = document.getElementById('formaPago-error-message');
const formaPagoDeducibleError = document.getElementById('formaPagoDeducible-error-message');

const expresiones = {
    folioFactura: /^[0-9]{1,10}$/,
    fechaFactura: /^\d{4}-\d{2}-\d{2}$/,
    fechaCobranza: /^\d{4}-\d{2}-\d{2}$/,
    rfcEmisor: /^[A-Z0-9]{3,4}\d{6}[A-Z0-9]{3}$/i,
    nombreEmisor: /^[a-zA-ZÀ-ÿ0-9\s]{1,40}$/i,
    subtotalFactura: /^\d+(\.\d{1,2})?$/,
    ivaFactura: /^\d+(\.\d{1,2})?$/,
    isrRetenido: /^\d+(\.\d{1,2})?$/,
    ivaRetenido: /^\d+(\.\d{1,2})?$/,
    totalFactura: /^\d+(\.\d{1,2})?$/,
    uuidFactura: /^[a-zA-ZÀ-ÿ0-9\s-]{1,40}$/i,
    tcFactura: /^\d+(\.\d{1,2})?$/,
    usoCfdiReceptor: /^[a-zA-ZÀ-ÿ\s]{1,50}$/i,
    tipoFactura: /^[0-9]{1,10}$/,
    serie: /^[a-zA-ZÀ-ÿ\s]{1,40}$/i,
    moneda: /^[a-zA-ZÀ-ÿ\s]{1,40}$/i,
    estatus: /^[a-zA-ZÀ-ÿ\s]{1,40}$/i,
    efecto: /^[a-zA-ZÀ-ÿ\s]{1,40}$/i,
    metodoPago: /^[a-zA-ZÀ-ÿ\s]{1,40}$/i,
    formaPago: /^[a-zA-ZÀ-ÿ\s]{1,40}$/i,
    formaPagoDeducible: /^[a-zA-ZÀ-ÿ\s]{1,40}$/i,
}

const validarFormulario = (event) => {
    console.log('validarFormulario', event.target.name);
    console.log('validarFormulario', event);

    switch (event.target.name) {
        case "folioFactura":
            folioFacturaError.classList.add('remover');
            folioFactura.classList.remove('input-error');
            break;
        case "fechaFactura":
            fechaFacturaError.classList.add('remover');
            fechaFactura.classList.remove('input-error');
            break;
        case "fechaCobranza":
            fechaCobranzaError.classList.add('remover');
            fechaCobranza.classList.remove('input-error');
            break;
        case "rfcEmisor":
            rfcEmisorError.classList.add('remover');
            rfcEmisor.classList.remove('input-error');
            break;
        case "nombreEmisor":
            nombreEmisorError.classList.add('remover');
            nombreEmisor.classList.remove('input-error');
            break;
        case "subtotalFactura":
            subtotalFacturaError.classList.add('remover');
            subtotalFactura.classList.remove('input-error');
            break;
        case "ivaFactura":
            ivaFacturaError.classList.add('remover');
            ivaFactura.classList.remove('input-error');
            break;
        case "isrRetenido":
            isrRetenidoError.classList.add('remover');
            isrRetenido.classList.remove('input-error');
            break;
        case "ivaRetenido":
            ivaRetenidoError.classList.add('remover');
            ivaRetenido.classList.remove('input-error');
            break;
        case "totalFactura":
            totalFacturaError.classList.add('remover');
            totalFactura.classList.remove('input-error');
            break;
        case "uuidFactura":
            uuidFacturaError.classList.add('remover');
            uuidFactura.classList.remove('input-error');
            break;
        case "tcFactura":
            tcFacturaError.classList.add('remover');
            tcFactura.classList.remove('input-error');
            break;
        case "usoCfdiReceptor":
            usoCfdiReceptorError.classList.add('remover');
            usoCfdiReceptor.classList.remove('input-error');
            break;
        case "tipoFactura":
            tipoFacturaError.classList.add('remover');
            tipoFactura.classList.remove('input-error');
            break;
        case "serie":
            serieError.classList.add('remover');
            serie.classList.remove('input-error');
            break;
        case "moneda":
            monedaError.classList.add('remover');
            moneda.classList.remove('input-error');
            break;
        case "estatus":
            estatusError.classList.add('remover');
            estatus.classList.remove('input-error');
            break;
        case "efecto":
            efectoError.classList.add('remover');
            efecto.classList.remove('input-error');
            break;
        case "metodoPago":
            metodoPagoError.classList.add('remover');
            metodoPago.classList.remove('input-error');
            break;
        case "formaPago":
            formaPagoError.classList.add('remover');
            formaPago.classList.remove('input-error');
            break;
        case "formaPagoDeducible":
            formaPagoDeducibleError.classList.add('remover');
            formaPagoDeducible.classList.remove('input-error');
            break;
    }
}


inputs.forEach((input) => {
    input.addEventListener("change", validarFormulario);
    input.addEventListener('keydown', validarFormulario);
    input.addEventListener('blur', validarFormulario);
});

formulario.addEventListener('submit', (event) => {
    console.log('submit');
    event.preventDefault();

    const folioFacturaValue = document.getElementById('folioFactura').value;
    const fechaFacturaValue = document.getElementById('fechaFactura').value;
    const fechaCobranzaValue = document.getElementById('fechaCobranza').value;
    const rfcEmisorValue = document.getElementById('rfcEmisor').value;
    const nombreEmisorValue = document.getElementById('nombreEmisor').value;
    const subtotalFacturaValue = document.getElementById('subtotalFactura').value;
    const ivaFacturaValue = document.getElementById('ivaFactura').value;
    const isrRetenidoValue = document.getElementById('isrRetenido').value;
    const ivaRetenidoValue = document.getElementById('ivaRetenido').value;
    const totalFacturaValue = document.getElementById('totalFactura').value;
    const uuidFacturaValue = document.getElementById('uuidFactura').value;
    const tcFacturaValue = document.getElementById('tcFactura').value;
    const usoCfdiReceptorValue = document.getElementById('usoCfdiReceptor').value;
    const tipoFacturaValue = document.getElementById('tipoFactura').value;
    const serieValue = document.getElementById('serie').value;
    const monedaValue = document.getElementById('moneda').value;
    const estatusValue = document.getElementById('estatus').value;
    const efectoValue = document.getElementById('efecto').value;
    const metodoPagoValue = document.getElementById('metodoPago').value;
    const formaPagoValue = document.getElementById('formaPago').value;
    const formaPagoDeducibleValue = document.getElementById('formaPagoDeducible').value;

    var error = false;

    if (folioFacturaValue === '') {
        folioFacturaError.innerText = 'Este campo es obligatorio';
        folioFacturaError.classList.remove('remover');
        folioFactura.classList.add('input-error');
        error = true;
    } else if (!expresiones.folioFactura.test(folioFacturaValue)) {
        folioFacturaError.innerText = 'Formato inválido';
        folioFacturaError.classList.remove('remover');
        folioFactura.classList.add('input-error');
        error = true;
    }

    if (fechaFacturaValue === '') {
        fechaFacturaError.innerText = 'Este campo es obligatorio';
        fechaFacturaError.classList.remove('remover');
        fechaFactura.classList.add('input-error');
        error = true;
    } else if (!expresiones.fechaFactura.test(fechaFacturaValue)) {
        fechaFacturaError.innerText = 'Formato inválido';
        fechaFacturaError.classList.remove('remover');
        fechaFactura.classList.add('input-error');
        error = true;
    }

    if (fechaCobranzaValue !== '' && !expresiones.fechaCobranza.test(fechaCobranzaValue)) {
        fechaCobranzaError.innerText = 'Formato inválido';
        fechaCobranzaError.classList.remove('remover');
        fechaCobranza.classList.add('input-error');
        error = true;
    }

    if (rfcEmisorValue === '') {
        rfcEmisorError.innerText = 'Este campo es obligatorio';
        rfcEmisorError.classList.remove('remover');
        rfcEmisor.classList.add('input-error');
        error = true;
    } else if (!expresiones.rfcEmisor.test(rfcEmisorValue)) {
        rfcEmisorError.innerText = 'Formato inválido';
        rfcEmisorError.classList.remove('remover');
        rfcEmisor.classList.add('input-error');
        error = true;
    }

    if (nombreEmisorValue === '') {
        nombreEmisorError.innerText = 'Este campo es obligatorio';
        nombreEmisorError.classList.remove('remover');
        nombreEmisor.classList.add('input-error');
        error = true;
    } else if (!expresiones.nombreEmisor.test(nombreEmisorValue)) {
        nombreEmisorError.innerText = 'Formato inválido';
        nombreEmisorError.classList.remove('remover');
        nombreEmisor.classList.add('input-error');
        error = true;
    }

    if (subtotalFacturaValue === '') {
        subtotalFacturaError.innerText = 'Este campo es obligatorio';
        subtotalFacturaError.classList.remove('remover');
        subtotalFactura.classList.add('input-error');
        error = true;
    } else if (!expresiones.subtotalFactura.test(subtotalFacturaValue)) {
        subtotalFacturaError.innerText = 'Formato inválido';
        subtotalFacturaError.classList.remove('remover');
        subtotalFactura.classList.add('input-error');
        error = true;
    }

    if (ivaFacturaValue === '') {
        ivaFacturaError.innerText = 'Este campo es obligatorio';
        ivaFacturaError.classList.remove('remover');
        ivaFactura.classList.add('input-error');
        error = true;
    } else if (!expresiones.ivaFactura.test(ivaFacturaValue)) {
        ivaFacturaError.innerText = 'Formato inválido';
        ivaFacturaError.classList.remove('remover');
        ivaFactura.classList.add('input-error');
        error = true;
    }

    if (isrRetenidoValue !== '' && !expresiones.isrRetenido.test(isrRetenidoValue)) {
        isrRetenidoError.innerText = 'Formato inválido';
        isrRetenidoError.classList.remove('remover');
        isrRetenido.classList.add('input-error');
        error = true;
    }

    if (ivaRetenidoValue !== '' && !expresiones.ivaRetenido.test(ivaRetenidoValue)) {
        ivaRetenidoError.innerText = 'Formato inválido';
        ivaRetenidoError.classList.remove('remover');
        ivaRetenido.classList.add('input-error');
        error = true;
    }

    if (totalFacturaValue === '') {
        totalFacturaError.innerText = 'Este campo es obligatorio';
        totalFacturaError.classList.remove('remover');
        totalFactura.classList.add('input-error');
        error = true;
    } else if (!expresiones.totalFactura.test(totalFacturaValue)) {
        totalFacturaError.innerText = 'Formato inválido';
        totalFacturaError.classList.remove('remover');
        totalFactura.classList.add('input-error');
        error = true;
    }

    if (uuidFacturaValue === '') {
        uuidFacturaError.innerText = 'Este campo es obligatorio';
        uuidFacturaError.classList.remove('remover');
        uuidFactura.classList.add('input-error');
        error = true;
    } else if (!expresiones.uuidFactura.test(uuidFacturaValue)) {
        uuidFacturaError.innerText = 'Formato inválido';
        uuidFacturaError.classList.remove('remover');
        uuidFactura.classList.add('input-error');
        error = true;
    }

    if (tcFacturaValue !== '' && !expresiones.tcFactura.test(tcFacturaValue)) {
        tcFacturaError.innerText = 'Formato inválido';
        tcFacturaError.classList.remove('remover');
        tcFactura.classList.add('input-error');
        error = true;
    }

    if (usoCfdiReceptorValue === '') {
        usoCfdiReceptorError.innerText = 'Este campo es obligatorio';
        usoCfdiReceptorError.classList.remove('remover');
        usoCfdiReceptor.classList.add('input-error');
        error = true;
    } else if (!expresiones.usoCfdiReceptor.test(usoCfdiReceptorValue)) {
        usoCfdiReceptorError.innerText = 'Formato inválido';
        usoCfdiReceptorError.classList.remove('remover');
        usoCfdiReceptor.classList.add('input-error');
        error = true;
    }

    if (serieValue === '') {
        serieError.innerText = 'Este campo es obligatorio';
        serieError.classList.remove('remover');
        serie.classList.add('input-error');
        error = true;
    }

    if (monedaValue === '') {
        monedaError.innerText = 'Este campo es obligatorio';
        monedaError.classList.remove('remover');
        moneda.classList.add('input-error');
        error = true;
    }

    if (estatusValue === '') {
        estatusError.innerText = 'Este campo es obligatorio';
        estatusError.classList.remove('remover');
        estatus.classList.add('input-error');
        error = true;
    }

    if (efectoValue === '') {
        efectoError.innerText = 'Este campo es obligatorio';
        efectoError.classList.remove('remover');
        efecto.classList.add('input-error');
        error = true;
    }

    if (metodoPagoValue === '') {
        metodoPagoError.innerText = 'Este campo es obligatorio';
        metodoPagoError.classList.remove('remover');
        metodoPago.classList.add('input-error');
        error = true;
    }

    if (formaPagoValue === '') {
        formaPagoError.innerText = 'Este campo es obligatorio';
        formaPagoError.classList.remove('remover');
        formaPago.classList.add('input-error');
        error = true;
    }

    if (formaPagoDeducibleValue === '') {
        formaPagoDeducibleError.innerText = 'Este campo es obligatorio';
        formaPagoDeducibleError.classList.remove('remover');
        formaPagoDeducible.classList.add('input-error');
        error = true;
    }

    if (!error) {
        formulario.submit();
    }

});