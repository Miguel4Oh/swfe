const botonSeleccionarArchivo = document.querySelector('.boton-seleccionar-archivo');
const input = document.getElementById('fileInput');
const dropZone = document.querySelector('.drop-zone');
const dropText = document.querySelector('.drop-text');
const nombreArchivo = document.querySelector('.nombre-archivo');
const botonCargarArchivo = document.querySelector('.boton-cargar-archivo');
const fileInput = document.getElementById('file');
const submitButton = document.getElementById('submit-file-button');
const dropZoneButtons = document.querySelector('.drop-zone-buttons');
let file;

const radioButtons = document.querySelectorAll('input[name="tipo"]');
const tipoInput = document.getElementById('tipo');

radioButtons.forEach((radio) => {
    radio.addEventListener('change', function() {
        tipoInput.value = this.value;
    });
});

botonSeleccionarArchivo.addEventListener('click', () => {
    input.click();
});

input.addEventListener('change', () => {
    file = input.files[0];
    dropZone.classList.add('active');
    showFiles(file);
    dropZone.classList.remove('active');
});

let isDragging = false;

dropZone.addEventListener('dragover', (e) => {
    e.preventDefault();
    if (!isDragging) {
        dropZone.classList.add('active');
        dropText.textContent = 'Suelta para subir el archivo';
        botonSeleccionarArchivo.classList.add('display-none');
        dropZoneButtons.classList.add('display-none');
        isDragging = true;
    }
});

dropZone.addEventListener('dragleave', (e) => {
    e.preventDefault();

    if (!dropZone.contains(e.relatedTarget)) {
        dropZone.classList.remove('active');
        dropText.innerHTML = '<h2 class="drop-text">Arrastra y suelta el archivo <br>o <br></h2>';
        botonSeleccionarArchivo.classList.remove('display-none');
        dropZoneButtons.classList.remove('display-none');
        isDragging = false;
    }
});

dropZone.addEventListener('drop', (e) => {
    e.preventDefault();
    file = e.dataTransfer.files[0];
    console.log(file.name);
    showFiles(file);
    dropZone.classList.remove('active');
    dropText.innerHTML = '<h2 class="drop-text">Arrastra y suelta el archivo <br>o <br></h2>';
    botonSeleccionarArchivo.classList.remove('display-none');
    dropZoneButtons.classList.remove('display-none');
    isDragging = false;
});

function showFiles(file) {
    if (file !== undefined) {
        const docType = file.type;
        const validExtensions = ["application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"];

        if (validExtensions.includes(docType)) {
            nombreArchivo.textContent = file.name;
            //fileInput.value = file;
        } else {

            alert('El archivo seleccionado no es un archivo de Excel');
        }
    }
}

botonCargarArchivo.addEventListener('click', (e) => {
    console.log("entro");

    e.preventDefault();

    const formData = new FormData();

    if (file) {
        formData.append("file", file);

        if (tipoInput.value) {
            formData.append("tipo", tipoInput.value);
        } else {
            alert("Por favor, selecciona un tipo antes de enviar.");
            return;
        }

        fetch("/swfe/facturas/archivo", {
            method: "POST",
            body: formData
        })
            .then(response => response.text())
            .then(data => {
                console.log(data);
                alert("Archivo subido correctamente");
                location.reload();
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Hubo un error al subir el archivo");
            });
    } else {
        alert("Por favor, selecciona un archivo antes de enviar.");
    }
});


const ventanaCargarArchivo = document.querySelector('.ventana-cargar-archivo');
const cargarArchivoButton = document.getElementById('cargar-archivo-button');
const botonCancelar = document.querySelector('.boton-cancelar');

cargarArchivoButton.addEventListener('click', () => {
    ventanaCargarArchivo.classList.add('mostrar-ventana');
});

botonCancelar.addEventListener('click', () => {
    ventanaCargarArchivo.classList.remove('mostrar-ventana');
})

