$(document).ready(function() {
    $('#dataTable').DataTable({
        "ajax": {
            "url": "/api/automoviles",
            "dataSrc": ""
        },
        "columns": [
            { "data": "automovilId" },
            { "data": "matricula" },
            { "data": "modelo" },
            { "data": "color" },
            { "data": "marca" },
            { "data": "garaje.nombre" }, // Asumiendo que quieres mostrar el nombre del garaje
            {
                "data": null,
                "render": function(data, type, row)  {
                    return '<a href="#" class="btn btn-primary">Editar</a> ' +
                        '<a href="#" class="btn btn-danger btn-eliminar">Eliminar</a>';
                }
            }
        ]
    });
});