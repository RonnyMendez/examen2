$(document).ready(function() {
    var automovilesSeleccionados = [];

    // Función para agregar un automóvil a la lista
    $('#add-automovil').click(function() {
        var automovilId = $('#automovilSelect').val();
        var selectedOption = $('#automovilSelect option:selected');

        if (automovilId && !automovilesSeleccionados.some(a => a.automovilId === automovilId)) {
            var automovil = {
                automovilId: automovilId,
                matricula: selectedOption.data('matricula'),
                modelo: selectedOption.data('modelo'),
                color: selectedOption.data('color'),
                marca: selectedOption.data('marca'),
                galones: selectedOption.data('galones'),
                precioPorDia: parseFloat(selectedOption.data('precio'))
            };

            automovilesSeleccionados.push(automovil);
            mostrarAutomovilesSeleccionados();
            actualizarPrecioTotal();
            $('#automovilSelect').val('');
        }
    });

    // Función para mostrar los automóviles seleccionados en la tabla
    function mostrarAutomovilesSeleccionados() {
        $('#automoviles-list').empty();
        automovilesSeleccionados.forEach(function(automovil) {
            $('#automoviles-list').append(`
                <tr data-id="${automovil.automovilId}">
                    <td>${automovil.matricula}</td>
                    <td>${automovil.modelo}</td>
                    <td>${automovil.color}</td>
                    <td>${automovil.marca}</td>
                    <td>${automovil.galones}</td>
                    <td>S/. ${automovil.precioPorDia.toFixed(2)}</td>
                    <td><button type="button" class="btn btn-danger btn-sm remove-automovil">Quitar</button></td>
                </tr>
            `);
        });
    }

    // Función para actualizar el precio total
    function actualizarPrecioTotal() {
        var precioTotal = automovilesSeleccionados.reduce((total, automovil) => total + automovil.precioPorDia, 0);
        $('#precioTotal').text(`S/. ${precioTotal.toFixed(2)}`);
    }

    // Función para quitar un automóvil de la lista
    $('#automoviles-list').on('click', '.remove-automovil', function() {
        var automovilId = $(this).closest('tr').data('id').toString();
        automovilesSeleccionados = automovilesSeleccionados.filter(a => a.automovilId !== automovilId);
        mostrarAutomovilesSeleccionados();
        actualizarPrecioTotal();
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
                var option = $('<option>', {
                    value: automovil.automovilId,
                    text: 'Matricula: '+ automovil.matricula
                        + ' - Modelo: ' + automovil.modelo
                        + ' - Precio por dia: S/.' + parseFloat(automovil.precioPorDia).toFixed(2)
                        + ' - Galones: ' + automovil.galones,
                    'data-matricula': automovil.matricula,
                    'data-modelo': automovil.modelo,
                    'data-color': automovil.color,
                    'data-marca': automovil.marca,
                    'data-galones': automovil.galones,
                    'data-precio': automovil.precioPorDia
                });
                automovilSelect.append(option);
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
            precioTotal: parseFloat($('#precioTotal').text().replace('S/. ', '')),
            entregado: $('#entregado').val() === "true",
            automoviles: automovilesSeleccionados.map(a => ({ automovilId: parseInt(a.automovilId) }))
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
                actualizarPrecioTotal();
            },
            error: function(error) {
                alert('Error al crear la reserva');
            }
        });
    });
});