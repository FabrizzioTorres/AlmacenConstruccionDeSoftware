const filtroTipo =
    document.getElementById(
        "filtroTipo"
    );

const filtroResponsable =
    document.getElementById(
        "filtroResponsable"
    );

const filtroAsignado =
    document.getElementById(
        "filtroAsignado"
    );

const fechaDesde =
    document.getElementById(
        "fechaDesde"
    );

const fechaHasta =
    document.getElementById(
        "fechaHasta"
    );

const horaDesde =
    document.getElementById(
        "horaDesde"
    );

const horaHasta =
    document.getElementById(
        "horaHasta"
    );

const filas =
    document.querySelectorAll(
        ".fila-transaccion"
    );

const responsables =
    new Set();

const asignados =
    new Set();
filas.forEach(fila => {

    responsables.add(

        fila.querySelector(
            ".responsable-transaccion"
        ).textContent.trim()

    );

    asignados.add(

        fila.querySelector(
            ".asignado-transaccion"
        ).textContent.trim()

    );

});

responsables.forEach(nombre => {

    const option =
        document.createElement(
            "option"
        );

    option.value = nombre;

    option.textContent = nombre;

    filtroResponsable.appendChild(
        option
    );

});

asignados.forEach(nombre => {

    const option =
        document.createElement(
            "option"
        );

    option.value = nombre;

    option.textContent = nombre;

    filtroAsignado.appendChild(
        option
    );

});

function aplicarFiltrosTransaccion() {

    filas.forEach(fila => {

        const tipo =
            fila.querySelector(
                ".tipo-transaccion"
            ).textContent.trim();

        const responsable =
            fila.querySelector(
                ".responsable-transaccion"
            ).textContent.trim();

        const asignado =
            fila.querySelector(
                ".asignado-transaccion"
            ).textContent.trim();

        const fecha =
            fila.querySelector(
                ".fecha-transaccion"
            ).textContent.trim();

        const hora =
            fila.querySelector(
                ".hora-transaccion"
            ).textContent.trim();


        const coincideTipo =

            filtroTipo.value === ""

            ||

            tipo === filtroTipo.value;


        const coincideResponsable =

            filtroResponsable.value === ""

            ||

            responsable ===
            filtroResponsable.value;


        const coincideAsignado =

            filtroAsignado.value === ""

            ||

            asignado ===
            filtroAsignado.value;


        const coincideFechaDesde =

            fechaDesde.value === ""

            ||

            fecha >= fechaDesde.value;


        const coincideFechaHasta =

            fechaHasta.value === ""

            ||

            fecha <= fechaHasta.value;


        const coincideHoraDesde =

            horaDesde.value === ""

            ||

            hora >= horaDesde.value;


        const coincideHoraHasta =

            horaHasta.value === ""

            ||

            hora <= horaHasta.value;


        fila.style.display =

            coincideTipo
            &&
            coincideResponsable
            &&
            coincideAsignado
            &&
            coincideFechaDesde
            &&
            coincideFechaHasta
            &&
            coincideHoraDesde
            &&
            coincideHoraHasta

                ?

                ""

                :

                "none";

    });

}


// =====================
// EVENTOS
// =====================

filtroTipo.addEventListener(
    "change",
    aplicarFiltrosTransaccion
);

filtroResponsable.addEventListener(
    "change",
    aplicarFiltrosTransaccion
);

filtroAsignado.addEventListener(
    "change",
    aplicarFiltrosTransaccion
);

fechaDesde.addEventListener(
    "change",
    aplicarFiltrosTransaccion
);

fechaHasta.addEventListener(
    "change",
    aplicarFiltrosTransaccion
);

horaDesde.addEventListener(
    "change",
    aplicarFiltrosTransaccion
);

horaHasta.addEventListener(
    "change",
    aplicarFiltrosTransaccion
);










































































