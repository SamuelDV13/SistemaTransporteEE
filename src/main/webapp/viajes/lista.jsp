<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de Viajes</title>
</head>

<body>
    <div>

        <h2>Lista de Viajes</h2>

        <a href="${pageContext.request.contextPath}/viajes?accion=nuevo">Nuevo [+]</a>

        <table>

            <tr>
                <th>Origen -> Destino</th>
                <th>Camion</th>
                <th>Chofer</th>
                <th>Fecha Salida</th>
                <th>Fecha Estimada</th>
                <th>Fecha Entrega</th>
                <th>Estado</th>
                <th>Ver</th>
            </tr>

            <c:forEach var="viaje" items="${viajes}">
                <tr>
                    <td>${viaje.origen} -> ${viaje.destino}</td>
                    <td>${viaje.camion.modelo}</td>
                    <td>${viaje.chofer.nombre} ${viaje.chofer.apellidoPaterno} ${viaje.chofer.apellidoMaterno}</td>
                    <td>${viaje.fechaSalida}</td>
                    <td>${viaje.fechaEstimada}</td>
                    <td>${(viaje.fechaEntrega != null) ? viaje.fechaEntrega : "No entregado"}</td>
                    <td>${viaje.estado}</td>
                    <td><a href="${pageContext.request.contextPath}/viajes?accion=editar&id=${viaje.id}">Ver</a></td>
                </tr>
            </c:forEach>

        </table>

    </div>
</body>
</html>
