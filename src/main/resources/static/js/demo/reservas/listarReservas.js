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
            {
                "data": "precioTotal",
                "render": function(data, type, row) {
                    return 'S/. ' + parseFloat(data).toFixed(2);
                }},
            {
                "data": "entregado",
                "render": function(data, type, row) {
                    return data ? "Entregado" : "Sin entregar";
                }
            },
            {
                "data": null,
                "render": function(data, type, row) {
                    return '<a href="#" class="btn btn-primary generate-pdf">Generar PDF</a>' +
                        '<a href="#" class="btn btn-danger btn-eliminar"><i class="fas fa-trash"></i> Anular</a>';
                }
            }
        ]
    });
});


/// Manejar la generación del PDF
$('#dataTable tbody').on('click', '.generate-pdf', function(event) {
    event.preventDefault();

    var reservaId = 1;

    $.ajax({
        url: `/api/reservas/${reservaId}`,
        method: 'GET',
        success: function(data) {
            const { jsPDF } = window.jspdf;
            const doc = new jsPDF();

            // Agregar el contenido al PDF
            doc.text("Comprobante de Reserva", 20, 20);
            doc.text(`Cliente: ${data.cliente.nombre}`, 20, 30);
            doc.text(`Fecha de Inicio: ${new Date(data.fechaInicio).toLocaleDateString()}`, 20, 40);
            doc.text(`Fecha Final: ${new Date(data.fechaFinal).toLocaleDateString()}`, 20, 50);

            let startY = 60;
            doc.text("Automóviles Seleccionados:", 20, startY);
            startY += 10;

            if (data.automoviles && data.automoviles.length > 0) {
                data.automoviles.forEach(function(automovil, index) {
                    doc.text(`${index + 1}. Matrícula: ${automovil.matricula}`, 20, startY);
                    doc.text(`   Modelo: ${automovil.modelo}`, 20, startY + 10);
                    doc.text(`   Color: ${automovil.color}`, 20, startY + 20);
                    doc.text(`   Marca: ${automovil.marca}`, 20, startY + 30);
                    doc.text(`   Galones: ${automovil.galones}`, 20, startY + 40);
                    doc.text(`   Precio por Día: S/. ${automovil.precioPorDia.toFixed(2)}`, 20, startY + 50);
                    startY += 60;
                });
            } else {
                doc.text("No hay automóviles seleccionados.", 20, startY);
            }

            doc.text(`Precio Total: S/. ${parseFloat(data.precioTotal).toFixed(2)}`, 20, startY);

            // Descargar el PDF
            doc.save("comprobante_reserva.pdf");
        },
        error: function(error) {
            alert('Error al obtener los datos de la reserva');
        }
    });
});

