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
            {
                "data": "precioPorDia",
                "render": function(data, type, row) {
                    return 'S/. ' + parseFloat(data).toFixed(2);
                }
            },
            { "data": "estado" },
            { "data": "galones" },
            { "data": "garaje.nombre" },
            {
                "data": null,
                "render": function(data, type, row) {
                    return '<a href="#" class="btn btn-primary" onclick="editarAutomovil(' + data.automovilId + ')">Editar</a> ' +
                        '<a href="#" class="btn btn-danger btn-eliminar" onclick="eliminarAutomovil(' + data.automovilId + ')">Eliminar</a>';
                }
            }
        ]
    });
});

$(document).ready(function() {
    // Cargar la lista de garajes en el select
    $.ajax({
        url: '/api/garajes',
        type: 'GET',
        success: function(garajes) {
            var garajeSelect = $('#garaje');
            garajes.forEach(function(garaje) {
                garajeSelect.append('<option value="' + garaje.garajeId + '">' + garaje.nombre + '</option>');
            });
        },
        error: function(xhr, status, error) {
            alert("Error al cargar los garajes: " + error);
        }
    });
  });

$('#formularioAutomovil').submit(function(e) {
        e.preventDefault(); // Evitar el envío tradicional del formulario

        var formData = {
            matricula: $('#matricula').val(),
            modelo: $('#modelo').val(),
            color: $('#color').val(),
            marca: $('#marca').val(),
            precioPorDia: $('#precioPorDia').val(),
            galones: $('#galones').val(),
            estado: $('#estado').val(),
            garaje: {
                garajeId: $('#garaje').val()
            }
        };

        $.ajax({
            url: '/api/automoviles',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(data) {
                // Mostrar modal de automóvil creado
                $('#automovilCreadoModal').modal('show');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error al registrar automóvil:', textStatus);
                alert('Error al registrar automóvil');
            }
        });
    });

    $('#automovilCreadoModal').on('click', '#crearOtroAutomovil, #regresarListaAutomoviles', function(event) {
        if (event.target.id === 'crearOtroAutomovil') {
            // Limpiar el formulario
            $('#formularioAutomovil')[0].reset();
            // Cerrar el modal
            $('#automovilCreadoModal').modal('hide');
        } else if (event.target.id === 'regresarListaAutomoviles') {
            // Redireccionar a la lista de automóviles
            window.location.href = '/listarAutomoviles'; // Cambia esta URL según tu necesidad
        }
    });

    function editarAutomovil(automovilId) {
    $.ajax({
        url: '/api/automoviles/' + automovilId,
        type: 'GET',
        success: function(automovil) {
            $('#automovilId').val(automovil.automovilId);
            $('#matricula').val(automovil.matricula);
            $('#modelo').val(automovil.modelo);
            $('#color').val(automovil.color);
            $('#marca').val(automovil.marca);
            $('#precioPorDia').val(automovil.precioPorDia);
            $('#galones').val(automovil.galones);
            $('#garaje').val(automovil.garaje.garajeId);

            $('#editarAutomovilModal').modal('show'); // Mostrar modal de edición
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error al obtener automóvil para editar:', textStatus);
            alert('Error al obtener automóvil para editar');
        }
    });
}

function actualizarAutomovil() {
    var formData = {
        automovilId: $('#automovilId').val(),
        matricula: $('#matricula').val(),
        modelo: $('#modelo').val(),
        color: $('#color').val(),
        marca: $('#marca').val(),
        precioPorDia: $('#precioPorDia').val(),
        galones: $('#galones').val(),
        garaje: {
            garajeId: $('#garaje').val()
        }
    };

    $.ajax({
        url: '/api/automoviles/' + formData.automovilId,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function(data) {
            $('#editarAutomovilModal').modal('hide'); // Ocultar modal de edición después de guardar
            $('#dataTable').DataTable().ajax.reload(); // Recargar DataTable con los datos actualizados
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error al actualizar automóvil:', textStatus);
            alert('Error al actualizar automóvil');
        }
    });
}

function eliminarAutomovil(id) {
    $.ajax({
        url: '/api/automoviles/delete/' + id,
        type: 'DELETE',
        success: function(data) {
            // Recargar la tabla después de eliminar
            $('#dataTable').DataTable().ajax.reload(); // Opcionalmente, puedes usar la variable 'table' si está definida globalmente
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log('Error al eliminar automóvil:', textStatus);
            // Manejar el error según sea necesario
        }
    });
}