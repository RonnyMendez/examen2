$(document).ready(function() {
    $('#dataTable').DataTable({
        "ajax": {
            "url": "/api/usuarios",
            "dataSrc": ""
        },
        "columns": [
            { "data": "usuarioId" },
            { "data": "nombre" },
            { "data": "usuario" },
            { "data": "email" },
            { "data": "rol" },
            {
                "data": null,
                "render": function(data, type, row)  {
                    return '<a href="#" class="btn btn-primary" onclick="editarUsuario(' + data.usuarioId + ')">Editar</a> ' +
                        '<a href="#" class="btn btn-danger btn-eliminar" onclick="eliminarUsuario(' + data.usuarioId + ')">Eliminar</a>';
                }
            }
        ]
    });
});

// Función para cargar datos del usuario en el formulario de edición
function editarUsuario(usuarioId) {
    $.ajax({
        url: '/api/usuarios/' + usuarioId,
        type: 'GET',
        success: function(usuario) {
            $('#usuarioId').val(usuario.usuarioId);
            $('#nombre').val(usuario.nombre);
            $('#usuario').val(usuario.usuario);
            $('#email').val(usuario.email);
            $('#rol').val(usuario.rol);

            $('#editarUsuarioModal').modal('show'); // Mostrar modal de edición
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error al obtener usuario para editar:', textStatus);
            alert('Error al obtener usuario para editar');
        }
    });
}

$('#usuarioCreadoModal').on('click', '#crearOtroUsuario, #regresarListaUsuarios', function(event) {
    if (event.target.id === 'crearOtroUsuario') {
        // Limpiar el formulario
        $('#formularioUsuario')[0].reset();
        // Cerrar el modal
        $('#usuarioCreadoModal').modal('hide');
    } else if (event.target.id === 'regresarListaUsuarios') {
        // Redireccionar a la lista de usuarios
        window.location.href = '/listarUsuarios';
    }
});


// Función para enviar la actualización del usuario
function actualizarUsuario() {
    var formData = {
        usuarioId: $('#usuarioId').val(),
        nombre: $('#nombre').val(),
        usuario: $('#usuario').val(),
        email: $('#email').val(),
        rol: $('#rol').val()
    };

    // Agregar el campo de contraseña solo si se ha ingresado una nueva contraseña
    var password = $('#password').val();
    if (password !== '') {
        formData.password = password;
    }

    $.ajax({
        url: '/api/usuarios/' + formData.usuarioId,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function(data) {
            $('#editarUsuarioModal').modal('hide'); // Ocultar modal de edición después de guardar
            $('#dataTable').DataTable().ajax.reload(); // Recargar DataTable con los datos actualizados
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error al actualizar usuario:', textStatus);
            alert('Error al actualizar usuario');
        }
    });
}

function eliminarUsuario(id) {
    $.ajax({
        url: '/api/usuarios/delete/' + id,
        type: 'DELETE',
        success: function(data) {
            // Recargar la tabla después de eliminar
            $('#dataTable').DataTable().ajax.reload(); // Opcionalmente, puedes usar la variable 'table' si está definida globalmente
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log('Error al eliminar usuario:', textStatus);
            // Manejar el error según sea necesario
        }
    });
}

$(document).ready(function() {
    $('#formularioUsuario').submit(function(e) {
        e.preventDefault(); // Evitar el envío tradicional del formulario

        var formData = {
            usuario: $('#usuario').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            nombre: $('#nombre').val(),
            rol: $('#rol').val()
        };

        $.ajax({
            url: '/api/usuarios',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(data) {
                // Mostrar modal de usuario creado
                $('#usuarioCreadoModal').modal('show');

                // Limpiar el formulario
                $('#formularioUsuario')[0].reset();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error al registrar usuario:', textStatus);
                alert('Error al registrar usuario');
            }
        });
    });
});