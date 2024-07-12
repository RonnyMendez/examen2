// Call the dataTables jQuery plugin and load data
$(document).ready(function() {
    $('#dataTable').DataTable({
        "ajax": {
            "url": "/usuarios", // La URL a tu endpoint en el controlador de Spring
            "dataSrc": ""
        },
        "columns": [
            { "data": "nombre" },
            { "data": "usuario" },
            { "data": "email" },
            { "data": "rol" },
            {
                "data": null,
                "render": function(data, type, row) {
                    // Aquí puedes definir el contenido de la columna de acciones
                    return '<button class="btn btn-primary">Editar</button> ' +
                           '<button class="btn btn-danger">Eliminar</button>';
                }
            }
        ]
    });
});


