if (
    document.getElementById("filtroCategoria")
) {
    const filtroNombreProducto =
        document.getElementById(
            "filtroNombre"
        );

    const filtroCategoriaProducto =
        document.getElementById(
            "filtroCategoria"
        );

    const filtroEstadoProducto =
        document.getElementById(
            "filtroEstado"
        );

    const filas =
        document.querySelectorAll(
            ".fila-producto"
        );

    const categorias =
        new Set();

    filas.forEach(fila => {

        categorias.add(
            fila.querySelector(
                ".categoria-producto"
            ).textContent.trim()
        );

    });

    categorias.forEach(cat => {

        const option =
            document.createElement(
                "option"
            );

        option.value = cat;

        option.textContent = cat;

        filtroCategoriaProducto.appendChild(
            option
        );

    });

    function aplicarFiltros() {

        filas.forEach(fila => {

            const nombre =
                fila.querySelector(
                    ".nombre-producto"
                ).textContent.toLowerCase();

            const categoria =
                fila.querySelector(
                    ".categoria-producto"
                ).textContent;

            const estado =
                fila.querySelector(
                    ".estado-producto"
                ).textContent;

            const coincideNombre =
                nombre.includes(
                    filtroNombreProducto.value.toLowerCase()
                );

            const coincideCategoria =
                filtroCategoriaProducto.value === ""
                ||
                categoria === filtroCategoriaProducto.value;

            const coincideEstado =
                filtroEstadoProducto.value === ""
                ||
                estado === filtroEstadoProducto.value;

            fila.style.display =

                coincideNombre
                &&
                coincideCategoria
                &&
                coincideEstado

                    ?

                    ""

                    :

                    "none";

        });

    }

    filtroNombreProducto.addEventListener(
        "input",
        aplicarFiltros
    );

    filtroCategoriaProducto.addEventListener(
        "change",
        aplicarFiltros
    );

    filtroEstadoProducto.addEventListener(
        "change",
        aplicarFiltros
    );
}