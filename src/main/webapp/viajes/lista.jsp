<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de Viajes</title>
    <jsp:include page="/includes/imports.jsp" />
</head>

<body class="bg-light">

<jsp:include page="/includes/header.jsp" />

<div class="container mt-5 mb-5">

    <div class="card shadow-sm border-0 rounded-4">

        <div class="card-header bg-white border-bottom-0 pt-4 pb-0 px-4 d-flex justify-content-between align-items-center">
            <h2 class="h4 mb-0 text-dark fw-bold">Lista de Viajes</h2>
            <a href="${pageContext.request.contextPath}/viajes?accion=nuevo" class="btn btn-primary fw-semibold shadow-sm">
                Nuevo [+]
            </a>
        </div>

        <div class="card-body p-4">
            <div class="table-responsive">
                <table class="table table-striped table-hover align-middle mb-0">

                    <thead class="table-light text-secondary">
                    <tr>
                        <th scope="col">Ruta (Origen &rarr; Destino)</th>
                        <th scope="col">Camión</th>
                        <th scope="col">Chofer</th>
                        <th scope="col">Fecha Salida</th>
                        <th scope="col">Fecha Estimada</th>
                        <th scope="col">Fecha Entrega</th>
                        <th scope="col">Estado</th>
                        <th scope="col" class="text-center">Ver</th>
                    </tr>
                    </thead>

                    <tbody class="border-top-0">
                    <c:forEach var="viaje" items="${viajes}">
                        <tr>
                            <td class="fw-bold text-dark">
                                    ${viaje.origen} <span class="text-muted mx-1">&rarr;</span> ${viaje.destino}
                            </td>

                            <td>${viaje.camion.modelo}</td>

                            <td>${viaje.chofer.nombre} ${viaje.chofer.apellidoPaterno} ${viaje.chofer.apellidoMaterno}</td>

                            <td>${viaje.fechaSalida}</td>
                            <td>${viaje.fechaEstimada}</td>

                            <td class="${(viaje.fechaEntrega == null) ? 'text-muted fst-italic' : ''}">
                                    ${(viaje.fechaEntrega != null) ? viaje.fechaEntrega : "No entregado"}
                            </td>

                            <td class="fw-medium">${viaje.estado}</td>

                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/viajes?accion=editar&id=${viaje.id}"
                                   class="btn btn-sm btn-outline-primary fw-medium">
                                    Ver
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>

    </div>
</div>

</body>
</html>