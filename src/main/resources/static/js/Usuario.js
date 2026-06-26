const filtroNombreUsuario =
    document.getElementById(
        "filtroNombreUsuario"
    );

const filtroRolUsuario =
    document.getElementById(
        "filtroRolUsuario"
    );

const filtroEstadoUsuario =
    document.getElementById(
        "filtroEstadoUsuario"
    );

function aplicarFiltrosUsuario() {

    document
        .querySelectorAll(
            ".fila-usuario"
        )
        .forEach(fila => {

            const nombre =
                fila.querySelector(
                    ".nombre-usuario"
                )
                    .textContent
                    .toLowerCase();

            const rol =
                fila.querySelector(
                    ".rol-usuario"
                )
                    .textContent
                    .trim();

            const estado =
                fila.querySelector(
                    ".estado-usuario"
                )
                    .textContent
                    .trim();

            const coincideNombre =
                nombre.includes(
                    filtroNombreUsuario
                        .value
                        .toLowerCase()
                );

            const coincideRol =
                filtroRolUsuario.value === ""
                ||
                rol ===
                filtroRolUsuario.value;

            const coincideEstado =
                filtroEstadoUsuario.value === ""
                ||
                estado ===
                filtroEstadoUsuario.value;

            fila.style.display =

                coincideNombre
                &&
                coincideRol
                &&
                coincideEstado

                    ?

                    ""

                    :

                    "none";
        });
}

filtroNombreUsuario?.addEventListener(
    "input",
    aplicarFiltrosUsuario
);

filtroRolUsuario?.addEventListener(
    "change",
    aplicarFiltrosUsuario
);

filtroEstadoUsuario?.addEventListener(
    "change",
    aplicarFiltrosUsuario
);