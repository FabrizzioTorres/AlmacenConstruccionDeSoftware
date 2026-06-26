if (
    document.getElementById("filtroNombre")
    &&
    document.getElementById("filtroEstado")
    &&
    !document.getElementById("filtroCategoria")
) {

    const filtroNombreCategoria =
        document.getElementById(
            "filtroNombre"
        );

    const filtroEstadoCategoria =
        document.getElementById(
            "filtroEstado"
        );

    function filtrarCategorias() {

        const nombre =
            filtroNombreCategoria.value
                .toLowerCase();

        const estado =
            filtroEstadoCategoria.value
                .toLowerCase();

        document
            .querySelectorAll("tbody tr")
            .forEach(fila => {

                const nombreFila =
                    fila.children[1]
                        .innerText
                        .toLowerCase();

                const estadoFila =
                    fila.children[2]
                        .innerText
                        .toLowerCase();

                fila.style.display =

                    nombreFila.includes(nombre)
                    &&
                    (
                        estado === ""
                        ||
                        estadoFila === estado
                    )

                        ? ""

                        : "none";
            });
    }

    filtroNombreCategoria.addEventListener(
        "input",
        filtrarCategorias
    );

    filtroEstadoCategoria.addEventListener(
        "change",
        filtrarCategorias
    );
}