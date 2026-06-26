const datos =
    window.metricas;

const opcionesBase = {
    responsive: true,
    maintainAspectRatio: false,
    interaction: {
        mode: "index",
        intersect: false
    },
    plugins: {
        legend: {
            display: true
        },
        tooltip: {
            enabled: true
        }
    },
    scales: {
        y: {
            beginAtZero: true
        }
    }
};

new Chart(
    document.getElementById("ingresosChart"),
    {
        type: "line",
        data: {
            labels: datos.meses,
            datasets: [
                {
                    label: "Ingresos",
                    data: datos.ingresos,
                    borderColor: "#16a34a",
                    backgroundColor: "#16a34a",
                    tension: 0.35,
                    fill: false
                }
            ]
        },
        options: opcionesBase
    }
);

new Chart(
    document.getElementById("salidasChart"),
    {
        type: "line",
        data: {
            labels: datos.meses,
            datasets: [
                {
                    label: "Salidas",
                    data: datos.salidas,
                    borderColor: "#dc2626",
                    backgroundColor: "#dc2626",
                    tension: 0.35,
                    fill: false
                }
            ]
        },
        options: opcionesBase
    }
);

new Chart(
    document.getElementById("movimientosChart"),
    {
        type: "bar",
        data: {
            labels: datos.meses,
            datasets: [
                {
                    label: "Movimientos Totales",
                    data: datos.movimientos,
                    backgroundColor: "#2563eb",
                    borderRadius: 8
                }
            ]
        },
        options: opcionesBase
    }
);

new Chart(
    document.getElementById("productosChart"),
    {
        type: "bar",
        data: {
            labels: datos.meses,
            datasets: [
                {
                    label: "Productos Distintos",
                    data: datos.productosDistintos,
                    backgroundColor: "#f59e0b",
                    borderRadius: 8
                }
            ]
        },
        options: opcionesBase
    }
);

new Chart(
    document.getElementById("proveedoresChart"),
    {
        type: "line",
        data: {
            labels: datos.meses,
            datasets: [
                {
                    label: "Proveedores Usados",
                    data: datos.proveedoresUsados,
                    borderColor: "#7c3aed",
                    backgroundColor: "#7c3aed",
                    tension: 0.35,
                    fill: false
                }
            ]
        },
        options: opcionesBase
    }
);

new Chart(
    document.getElementById("comparacionChart"),
    {
        type: "line",
        data: {
            labels: datos.meses,
            datasets: [
                {
                    label: "Ingresos",
                    data: datos.ingresos,
                    borderColor: "#16a34a",
                    backgroundColor: "#16a34a",
                    tension: 0.35,
                    fill: false
                },
                {
                    label: "Salidas",
                    data: datos.salidas,
                    borderColor: "#dc2626",
                    backgroundColor: "#dc2626",
                    tension: 0.35,
                    fill: false
                },
                {
                    label: "Movimientos",
                    data: datos.movimientos,
                    borderColor: "#2563eb",
                    backgroundColor: "#2563eb",
                    tension: 0.35,
                    fill: false
                }
            ]
        },
        options: opcionesBase
    }
);