const consultaInput = document.getElementById('consulta-input');

consultaInput.addEventListener('keyup', function () {
    console.log(consultaInput.value);

    const filtro = consultaInput.value;

    const url = `/swfe/clientes/filtro?filtro=${encodeURIComponent(filtro)}`;

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            return response.json();
        })
        .then(clientes => {
            llenarCampos(clientes);
        })
        .catch(error => {
            console.error('Hubo un error:', error);
        });
});

function llenarCampos(clientes) {
    console.log(clientes);

    if (clientes.length === 0) {
        console.log('No se encontraron resultados');
        const tbody = document.querySelector('.cuerpo-tabla');
        tbody.innerHTML = '';
    } else {
        llenarTabla(clientes);
    }
}

function llenarTabla(clientes) {
    const tbody = document.querySelector('.cuerpo-tabla');

    tbody.innerHTML = '';

    clientes.forEach(cliente => {
        console.log(cliente.clienteId);
        const tr = document.createElement('tr');

        tr.innerHTML = `
                <td>${cliente.nombreCliente}</td>
                <td>${cliente.rfcCliente}</td>
                <td>${cliente.razonSocial}</td>
                <td>${cliente.regimenFiscal}</td>
                <td>
                     <a class="enlace-contents"
                        href="/swfe/clientes/cliente-form?cliente-id=${cliente.clienteId}">
                         <button class="boton-editar">
                             <svg class="svg-editar" width="20px" height="20px" viewBox="0 0 24 24" fill="none"
                                  xmlns="http://www.w3.org/2000/svg">
                                 <path d="M20.1497 7.93997L8.27971 19.81C7.21971 20.88 4.04971 21.3699 3.27971 20.6599C2.50971 19.9499 3.06969 16.78 4.12969 15.71L15.9997 3.84C16.5478 3.31801 17.2783 3.03097 18.0351 3.04019C18.7919 3.04942 19.5151 3.35418 20.0503 3.88938C20.5855 4.42457 20.8903 5.14781 20.8995 5.90463C20.9088 6.66146 20.6217 7.39189 20.0997 7.93997H20.1497Z"
                                       stroke="white" stroke-width="1.5" stroke-linecap="round"
                                       stroke-linejoin="round"/>
                                 <path d="M21 21H12" stroke="white" stroke-width="1.5" stroke-linecap="round"
                                       stroke-linejoin="round"/>
                             </svg>
                         </button>
                     </a>
                     <a class="enlace-contents" href="/swfe/clientes/eliminar?cliente-id=${cliente.clienteId}">
                         <button class="boton-eliminar">
                             <svg class="svg-eliminar" width="20px" height="20px" viewBox="0 0 24 24" fill="none"
                                  xmlns="http://www.w3.org/2000/svg">
                                 <path d="M10 12V17" stroke="white" stroke-width="2" stroke-linecap="round"
                                       stroke-linejoin="round"/>
                                 <path d="M14 12V17" stroke="white" stroke-width="2" stroke-linecap="round"
                                       stroke-linejoin="round"/>
                                 <path d="M4 7H20" stroke="white" stroke-width="2" stroke-linecap="round"
                                       stroke-linejoin="round"/>
                                 <path d="M6 10V18C6 19.6569 7.34315 21 9 21H15C16.6569 21 18 19.6569 18 18V10"
                                       stroke="white" stroke-width="2" stroke-linecap="round"
                                       stroke-linejoin="round"/>
                                 <path d="M9 5C9 3.89543 9.89543 3 11 3H13C14.1046 3 15 3.89543 15 5V7H9V5Z"
                                       stroke="white" stroke-width="2" stroke-linecap="round"
                                       stroke-linejoin="round"/>
                             </svg>
                         </button>
                     </a>
                </td>
            `;

        tbody.appendChild(tr);
    });
}

const buscador = document.querySelector('.buscador-contenedor');
const input = buscador.querySelector('input');
const botones = document.querySelector('.botones-facturacion');

function actualizarVisibilidadBotones() {
    if (window.innerWidth < 990) {
        if (document.activeElement === input || buscador.matches(':hover')) {
            botones.style.display = 'none';
        } else {
            botones.style.display = 'flex';
        }
    } else {
        botones.style.display = 'flex';
    }
}

input.addEventListener('focus', actualizarVisibilidadBotones);
input.addEventListener('blur', actualizarVisibilidadBotones);

buscador.addEventListener('mouseenter', actualizarVisibilidadBotones);
buscador.addEventListener('mouseleave', actualizarVisibilidadBotones);

window.addEventListener('resize', actualizarVisibilidadBotones);

actualizarVisibilidadBotones();