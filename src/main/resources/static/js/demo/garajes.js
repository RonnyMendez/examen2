$(document).ready(function() {
    $('#dataTable').DataTable({
        "ajax": {
            "url": "/api/garajes",
            "dataSrc": ""
        },
        "columns": [
            { "data": "garajeId" },
            { "data": "nombre" },
            { "data": "direccion" },
            {
                "data": null,
                "render": function(data, type, row)  {
                    return '<a href="#" class="btn btn-primary" onclick="editarGaraje(' + data.garajeId + ')">Editar</a> ' +
                        '<a href="#" class="btn btn-danger btn-eliminar" onclick="eliminarGaraje(' + data.garajeId + ')">Eliminar</a>';
                }
            }
        ]
    });
});

$(document).ready(function() {
    $('#formularioGaraje').submit(function(e) {
        e.preventDefault(); // Evitar el envío tradicional del formulario

        var formData = {
            nombre: $('#nombre').val(),
            direccion: $('#direccion').val(),
        };

        console.log(formData.direccion);
        $.ajax({
            url: '/api/garajes',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(data) {
                // Mostrar modal de garaje creado
                $('#garajeCreadoModal').modal('show');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error al registrar garaje:', textStatus);
                alert('Error al registrar garaje');
            }
        });
    });
});

$('#garajeCreadoModal').on('click', '#crearOtroGaraje, #regresarListaGarajes', function(event) {
    if (event.target.id === 'crearOtroGaraje') {
        // Limpiar el formulario
        $('#formularioGaraje')[0].reset();
        // Cerrar el modal
        $('#garajeCreadoModal').modal('hide');
    } else if (event.target.id === 'regresarListaGarajes') {
        // Redireccionar a la lista de garajes
        window.location.href = '/listarGarajes';
    }
});

function editarGaraje(garajeId) {
    $.ajax({
        url: '/api/garajes/' + garajeId,
        type: 'GET',
        success: function(garaje) {
            $('#garajeId').val(garaje.garajeId);
            $('#nombre').val(garaje.nombre);
            $('#direccion').val(garaje.direccion);

            $('#editarGarajeModal').modal('show'); // Mostrar modal de edición
        },

        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error al obtener garaje para editar:', textStatus);
            alert('Error al obtener garaje para editar');
        }
    });
}

function actualizarGaraje() {
    var formData = {
        garajeId: $('#garajeId').val(),
        nombre: $('#nombre').val(),
        direccion: $('#direccion').val(),
    };

    $.ajax({
        url: '/api/garajes/' + formData.garajeId,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function(data) {
            $('#editarGarajeModal').modal('hide'); // Ocultar modal de edición después de guardar
            $('#dataTable').DataTable().ajax.reload(); // Recargar DataTable con los datos actualizados
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error al actualizar garaje:', textStatus);
            alert('Error al actualizar garaje');
        }
    });
}

function eliminarGaraje(id) {
    $.ajax({
        url: '/api/garajes/delete/' + id,
        type: 'DELETE',
        success: function(data) {
            // Recargar la tabla después de eliminar
            $('#dataTable').DataTable().ajax.reload(); // Opcionalmente, puedes usar la variable 'table' si está definida globalmente
        },
        error: function(jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 500) { // Supongamos que el error 409 Conflict es el código de respuesta para garajes con automóviles asociados
                $('#errorModal').modal('show'); // Mostrar modal de error
            } else {
                console.log('Error al eliminar garaje:', textStatus);
                // Manejar otros errores según sea necesario
            }
        }
    });
}