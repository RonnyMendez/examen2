$(document).ready(function() {
    $('#dataTable').DataTable({
        "ajax": {
            "url": "/api/reservas",
            "dataSrc": ""
        },
        "columns": [
            { "data": "reservaId" },
            { "data": "cliente.nombre" },
            {
                "data": "fechaInicio",
                "render": function(data, type, row) {
                    var date = new Date(data);
                    return date.toLocaleDateString();
                }
            },
            {
                "data": "fechaFinal",
                "render": function(data, type, row) {
                    var date = new Date(data);
                    return date.toLocaleDateString();
                }
            },
            { "data": "precioTotal" },
            {
                "data": "entregado",
                "render": function(data, type, row) {
                    return data ? "Entregado" : "Sin entregar";
                }
            },
            {
                "data": null,
                "render": function(data, type, row) {
                    return '<a href="#" class="btn btn-primary">Editar</a> ' +
                        '<a href="#" class="btn btn-danger btn-eliminar">Eliminar</a>';
                }
            }
        ]
    });
});

$(document).ready(function() {
    var automovilesSeleccionados = [];

    // Función para agregar un automóvil a la lista
    $('#add-automovil').click(function() {
        var automovilId = $('#automovilSelect').val();
        var automovilTexto = $('#automovilSelect option:selected').text();
        if (automovilId && !automovilesSeleccionados.includes(automovilId)) {
            automovilesSeleccionados.push(automovilId);
            $('#automoviles-list').append('<li data-id="' + automovilId + '">' + automovilTexto +
                ' <button type="button" class="btn btn-danger btn-sm remove-automovil">Quitar</button></li>');
        }
    });

    // Función para quitar un automóvil de la lista
    $('#automoviles-list').on('click', '.remove-automovil', function() {
        var automovilId = $(this).parent().data('id');
        automovilesSeleccionados = automovilesSeleccionados.filter(id => id !== automovilId);
        $(this).parent().remove();
    });

    // Cargar dinámicamente los valores de los clientes
    $.ajax({
        url: '/api/clientes', // La URL de tu API para obtener los clientes
        method: 'GET',
        success: function(data) {
            var clienteSelect = $('#cliente');
            data.forEach(function(cliente) {
                clienteSelect.append(new Option(cliente.nombre, cliente.clienteCodigo));
            });
        },
        error: function() {
            alert('Error al cargar clientes');
        }
    });

    // Cargar dinámicamente los valores de los automóviles
    $.ajax({
        url: '/api/automoviles', // La URL de tu API para obtener los automóviles
        method: 'GET',
        success: function(data) {
            var automovilSelect = $('#automovilSelect');
            data.forEach(function(automovil) {
                automovilSelect.append(new Option(automovil.matricula + ' - ' + automovil.modelo, automovil.automovilId));
            });
        },
        error: function() {
            alert('Error al cargar automóviles');
        }
    });

    // Manejar el envío del formulario
    $('#formularioReserva').submit(function(event) {
        event.preventDefault();

        var reservaData = {
            cliente: {
                clienteCodigo: $('#cliente').val()
            },
            fechaInicio: $('#fechaInicio').val(),
            fechaFinal: $('#fechaFinal').val(),
            precioTotal: parseFloat($('#precioTotal').val()),
            entregado: $('#entregado').val() === "true",
            automoviles: automovilesSeleccionados.map(id => {
                return {
                    automovilId: parseInt(id)
                };
            })
        };

        $.ajax({
            url: '/api/reservas', // La URL de tu API para guardar la reserva
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(reservaData),
            success: function(response) {
                alert('Reserva creada exitosamente');
                // Limpiar formulario y lista de automóviles seleccionados si es necesario
                $('#formularioReserva')[0].reset();
                $('#automoviles-list').empty();
                automovilesSeleccionados = [];
            },
            error: function(error) {
                alert('Error al crear la reserva');
            }
        });
    });
});