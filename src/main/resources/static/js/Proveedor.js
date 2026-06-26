const filtroNombreProveedor =
    document.getElementById(
        "filtroNombreProveedor"
    );

const filtroRubroProveedor =
    document.getElementById(
        "filtroRubroProveedor"
    );

const filtroEstadoProveedor =
    document.getElementById(
        "filtroEstadoProveedor"
    );

const filasProveedor =
    document.querySelectorAll(
        ".fila-proveedor"
    );

// =====================
// CARGAR RUBROS
// =====================

if (filtroRubroProveedor) {

    const rubros = new Set();

    filasProveedor.forEach(fila => {

        rubros.add(

            fila.querySelector(
                ".rubro-proveedor"
            ).textContent.trim()

        );

    });

    rubros.forEach(rubro => {

        const option =
            document.createElement(
                "option"
            );

        option.value = rubro;

        option.textContent = rubro;

        filtroRubroProveedor.appendChild(
            option
        );

    });

}

// =====================
// FILTRAR
// =====================

function aplicarFiltrosProveedor() {

    filasProveedor.forEach(fila => {

        const nombre =
            fila.querySelector(
                ".nombre-proveedor"
            )
                .textContent
                .toLowerCase();

        const rubro =
            fila.querySelector(
                ".rubro-proveedor"
            )
                .textContent
                .trim();

        const estado =
            fila.querySelector(
                ".estado-proveedor"
            )
                .textContent
                .trim();

        const coincideNombre =

            nombre.includes(

                filtroNombreProveedor.value
                    .toLowerCase()

            );

        const coincideRubro =

            filtroRubroProveedor.value === ""

            ||

            rubro ===
            filtroRubroProveedor.value;

        const coincideEstado =

            filtroEstadoProveedor.value === ""

            ||

            estado ===
            filtroEstadoProveedor.value;

        fila.style.display =

            coincideNombre
            &&
            coincideRubro
            &&
            coincideEstado

                ?

                ""

                :

                "none";

    });

}

// =====================
// EVENTOS
// =====================

if (filtroNombreProveedor) {

    filtroNombreProveedor
        .addEventListener(
            "input",
            aplicarFiltrosProveedor
        );

}

if (filtroRubroProveedor) {

    filtroRubroProveedor
        .addEventListener(
            "change",
            aplicarFiltrosProveedor
        );

}

if (filtroEstadoProveedor) {

    filtroEstadoProveedor
        .addEventListener(
            "change",
            aplicarFiltrosProveedor
        );

}