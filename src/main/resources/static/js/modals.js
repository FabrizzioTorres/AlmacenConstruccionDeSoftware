function mostrarGuardar() {
    document.getElementById(
        "modalGuardar"
    ).style.display = "flex";
}

function cerrarGuardar() {
    document.getElementById(
        "modalGuardar"
    ).style.display = "none";
}


function agregarFila() {

    const tbody =
        document.getElementById(
            "detalleProductos"
        );

    const filas =
        tbody.getElementsByTagName(
            "tr"
        );

    const index =
        filas.length;

    const nuevaFila =
        document.createElement(
            "tr"
        );

    const opciones =
        filas[0]
            .querySelector("select")
            .innerHTML;

    nuevaFila.innerHTML = `
        <td class="indice">
            ${index + 1}
        </td>

        <td>
        <select
            name="detalles[${index}].idProducto"
            required
            onchange="validarDuplicados(this); mostrarStock(this)">

            ${opciones}

        </select>

        <small class="stock-actual">
            Stock actual: -
        </small>
        </td>

        <td>
            <input
                type="number"
                min="1"
                required
                name="detalles[${index}].cantidad">
        </td>

        <td>
            <button
                type="button"
                class="btn-eliminar"
                onclick="eliminarFila(this)">
                X
            </button>
        </td>
    `;

    tbody.appendChild(
        nuevaFila
    );
}

function eliminarFila(btn) {

    const tbody =
        document.getElementById(
            "detalleProductos"
        );

    const filas =
        tbody.getElementsByTagName(
            "tr"
        );

    if (filas.length <= 1) {
        return;
    }

    const fila =
        btn.closest("tr");

    fila.remove();

    reindexarFilas();
}

function reindexarFilas() {

    const tbody =
        document.getElementById(
            "detalleProductos"
        );

    const filas =
        tbody.getElementsByTagName(
            "tr"
        );

    for (let i = 0;
         i < filas.length;
         i++) {

        filas[i]
            .querySelector(".indice")
            .textContent = i + 1;

        const select =
            filas[i].querySelector(
                "select"
            );

        const input =
            filas[i].querySelector(
                "input"
            );

        select.name =
            `detalles[${i}].idProducto`;

        input.name =
            `detalles[${i}].cantidad`;
    }
}

function validarDuplicados(
    selectActual
) {

    const selects =
        document.querySelectorAll(
            "#detalleProductos select"
        );

    let valor =
        selectActual.value;

    let contador = 0;

    selects.forEach(select => {

        if (
            select.value === valor &&
            valor !== ""
        ) {
            contador++;
        }
    });

    if (contador > 1) {

        alert(
            "Ese producto ya fue agregado."
        );

        selectActual.value = "";
    }
}

function validarFormulario() {

    const filas =
        document.querySelectorAll(
            "#detalleProductos tr"
        );

    const productos =
        new Set();

    for (const fila of filas) {

        const producto =
            fila.querySelector(
                "select"
            );

        const cantidad =
            fila.querySelector(
                "input[type='number']"
            );

        if (!producto ||
            !cantidad) {

            continue;
        }

        if (
            producto.value === ""
        ) {

            alert(
                "Seleccione un producto."
            );

            return false;
        }

        if (
            productos.has(
                producto.value
            )
        ) {

            alert(
                "No puede repetir productos."
            );

            return false;
        }

        productos.add(
            producto.value
        );

        if (
            Number(
                cantidad.value
            ) <= 0
        ) {

            alert(
                "La cantidad debe ser mayor a cero."
            );

            return false;
        }
    }

    return true;
}
function mostrarStock(select){

    const stock =
        select.options[
            select.selectedIndex
            ].dataset.stock;

    const textoStock =
        select.parentElement.querySelector(
            ".stock-actual"
        );

    textoStock.textContent =
        stock
            ? "Stock actual: " + stock
            : "Stock actual: -";
}
function cambiarTipo() {

    const tipo =
        document.querySelector(
            "[name='tipo']"
        ).value;

    const proveedorContainer =
        document.getElementById(
            "proveedorContainer"
        );

    const destinoContainer =
        document.getElementById(
            "destinoContainer"
        );

    const proveedorSelect =
        document.querySelector("[name='proveedor']");

    const destinoSelect =
        document.querySelector("[name='destino']");

    if (tipo === "Ingreso") {

        proveedorContainer.style.display =
            "block";

        destinoContainer.style.display =
            "none";
        destinoSelect.value = "";
        destinoSelect.disabled = true;

        proveedorSelect.disabled = false;

        proveedorSelect.required = true;
        destinoSelect.required = false;
    }

    else if (tipo === "Salida") {

        proveedorContainer.style.display =
            "none";

        destinoContainer.style.display =
            "block";

        proveedorSelect.value = "";
        proveedorSelect.disabled = true;

        destinoSelect.disabled = false;

        proveedorSelect.required = false;
        destinoSelect.required = true;
    }
}
window.onload = function() {

    cambiarTipo();

    const fechaInput =
        document.getElementById("fecha");

    const hoy =
        new Date()
            .toISOString()
            .split("T")[0];

    fechaInput.min = hoy;
    fechaInput.max = hoy;
};