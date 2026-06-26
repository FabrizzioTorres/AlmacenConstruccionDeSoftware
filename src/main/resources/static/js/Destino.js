const filtroNombreDestino =
    document.getElementById(
        "filtroNombreDestino"
    );

const filtroResponsableDestino =
    document.getElementById(
        "filtroResponsableDestino"
    );

const filtroEstadoDestino =
    document.getElementById(
        "filtroEstadoDestino"
    );

const filasDestino =
    document.querySelectorAll(
        ".fila-destino"
    );

if(
    filtroNombreDestino &&
    filtroResponsableDestino &&
    filtroEstadoDestino
){

    const responsables =
        new Set();

    filasDestino.forEach(fila => {

        const responsable =
            fila.querySelector(
                ".responsable-destino"
            );

        if(responsable){

            responsables.add(
                responsable.textContent.trim()
            );

        }
    });

    responsables.forEach(resp => {

        const option =
            document.createElement(
                "option"
            );

        option.value = resp;

        option.textContent = resp;

        filtroResponsableDestino.appendChild(
            option
        );
    });

    function aplicarFiltrosDestino(){

        filasDestino.forEach(fila => {

            const nombre =
                fila.querySelector(
                    ".nombre-destino"
                ).textContent.toLowerCase();

            const responsable =
                fila.querySelector(
                    ".contacto-destino"
                ).textContent;

            const estado =
                fila.querySelector(
                    ".estado-destino"
                ).textContent;

            const coincideNombre =
                nombre.includes(
                    filtroNombreDestino.value.toLowerCase()
                );

            const coincideResponsable =
                filtroResponsableDestino.value === ""
                ||
                responsable ===
                filtroResponsableDestino.value;

            const coincideEstado =
                filtroEstadoDestino.value === ""
                ||
                estado ===
                filtroEstadoDestino.value;

            fila.style.display =

                coincideNombre
                &&
                coincideResponsable
                &&
                coincideEstado

                    ? ""

                    : "none";
        });
    }

    filtroNombreDestino.addEventListener(
        "input",
        aplicarFiltrosDestino
    );

    filtroResponsableDestino.addEventListener(
        "change",
        aplicarFiltrosDestino
    );

    filtroEstadoDestino.addEventListener(
        "change",
        aplicarFiltrosDestino
    );
}