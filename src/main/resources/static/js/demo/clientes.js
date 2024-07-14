$(document).ready(function() {
    $('#dataTable').DataTable({
        "ajax": {
            "url": "/api/clientes",
            "dataSrc": ""
        },
        "columns": [
            { "data": "clienteCodigo" },
            { "data": "nombre" },
            { "data": "direccion" },
            { "data": "telefono" },
            { "data": "dni" },
            {
                "data": null,
                "render": function(data, type, row)  {
                    return '<a href="#" class="btn btn-primary" onclick="editarCliente(' + data.clienteCodigo + ')">Editar</a> ' +
                            '<a href="#" class="btn btn-danger btn-eliminar" onclick="eliminarCliente(' + data.clienteCodigo + ')">Eliminar</a>';
                }
            }
        ]
    });
});

$(document).ready(function() {
    $('#formularioCliente').submit(function(e) {
        e.preventDefault(); // Evitar el envío tradicional del formulario

        var formData = {
            dni: $('#dni').val(),
            nombre: $('#nombre').val(),
            direccion: $('#direccion').val(),
            telefono: $('#telefono').val()
        };

        $.ajax({
            url: '/api/clientes',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(data) {
                // Mostrar modal de cliente creado
                $('#clienteCreadoModal').modal('show');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error al registrar cliente:', textStatus);
                alert('Error al registrar cliente');
            }
        });
    });
});
$('#clienteCreadoModal').on('click', '#crearOtroCliente, #regresarListaClientes', function(event) {
    if (event.target.id === 'crearOtroCliente') {
        // Limpiar el formulario
        $('#formularioCliente')[0].reset();
        // Cerrar el modal
        $('#clienteCreadoModal').modal('hide');
    } else if (event.target.id === 'regresarListaClientes') {
        // Redireccionar a la lista de clientes
        window.location.href = '/listarClientes';
    }
});


function editarCliente(clienteCodigo) {
    $.ajax({
        url: '/api/clientes/' + clienteCodigo,
        type: 'GET',
        success: function(cliente) {
            $('#clienteCodigo').val(cliente.clienteCodigo);
            $('#dni').val(cliente.dni);
            $('#nombre').val(cliente.nombre);
            $('#direccion').val(cliente.direccion);
            $('#telefono').val(cliente.telefono);

            $('#editarClienteModal').modal('show'); // Mostrar modal de edición
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error al obtener cliente para editar:', textStatus);
            alert('Error al obtener cliente para editar');
        }
    });
}

function actualizarCliente() {
    var formData = {
        clienteCodigo: $('#clienteCodigo').val(),
        dni: $('#dni').val(),
        nombre: $('#nombre').val(),
        direccion: $('#direccion').val(),
        telefono: $('#telefono').val()
    };

    $.ajax({
        url: '/api/clientes/' + formData.clienteCodigo,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function(data) {
            $('#editarClienteModal').modal('hide'); // Ocultar modal de edición después de guardar
            $('#dataTable').DataTable().ajax.reload(); // Recargar DataTable con los datos actualizados
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error al actualizar cliente:', textStatus);
            alert('Error al actualizar cliente');
        }
    });
}

function eliminarCliente(id) {
    $.ajax({
        url: '/api/clientes/delete/' + id,
        type: 'DELETE',
        success: function(data) {
            // Recargar la tabla después de eliminar
            $('#dataTable').DataTable().ajax.reload(); // Opcionalmente, puedes usar la variable 'table' si está definida globalmente
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log('Error al eliminar cliente:', textStatus);
            // Manejar el error según sea necesario
        }
    });
}
